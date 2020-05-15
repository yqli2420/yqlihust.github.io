import lpcnet

import tensorflow as tf

from tensorflow.keras import backend as K


def freeze_session(session,
                   keep_var_names=None,
                   output_names=None,
                   clear_devices=True):
    """
    Freezes the state of a session into a pruned computation graph.

    Creates a new computation graph where variable nodes are replaced by
    constants taking their current value in the session. The new graph will be
    pruned so subgraphs that are not necessary to compute the requested
    outputs are removed.
    @param session The TensorFlow session to be frozen.
    @param keep_var_names A list of variable names that should not be frozen,
                          or None to freeze all the variables in the graph.
    @param output_names Names of the relevant graph outputs.
    @param clear_devices Remove the device directives from the graph for better portability.
    @return The frozen graph definition.
    """
    graph = session.graph
    with graph.as_default():
        freeze_var_names = list(
            set(v.op.name for v in tf.global_variables()).difference(
                keep_var_names or []))
        output_names = output_names or []
        output_names += [v.op.name for v in tf.global_variables()]
        input_graph_def = graph.as_graph_def()
        if clear_devices:
            for node in input_graph_def.node:
                node.device = ""
        frozen_graph = tf.graph_util.convert_variables_to_constants(
            session, input_graph_def, output_names, freeze_var_names)
        return frozen_graph


def to_pb():
    model, enc, dec = lpcnet.new_lpcnet_model(rnn_units1=384, use_gpu=False)
    model.compile(optimizer="adam",
                  loss="sparse_categorical_crossentropy",
                  metrics=["sparse_categorical_accuracy"])
    model.load_weights("lpcnet_384_10_G16_120.h5")

    # Create, compile and train model...
    output_names = []
    for out in model.outputs:
        output_names.append(out.op.name)
    for out in enc.outputs:
        output_names.append(out.op.name)
    for out in dec.outputs:
        output_names.append(out.op.name)
    print(output_names)
    frozen_graph = freeze_session(K.get_session(), output_names=output_names)

    tf.train.write_graph(frozen_graph, "pb_dir", "lpcnet.pb", as_text=False)


def show_pb():
    model = ("pb_dir/lpcnet.pb")
    graph = tf.get_default_graph()
    graph_def = graph.as_graph_def()
    graph_def.ParseFromString(tf.gfile.FastGFile(model, "rb").read())
    for n in graph_def.node:
        print(n.name, n.input)
    tf.import_graph_def(graph_def, name="graph")
    summary = tf.summary.FileWriter("pb_dir/log/", graph)


if __name__ == "__main__":
    to_pb()
    show_pb()
