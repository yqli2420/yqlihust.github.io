layui.define(function(exports) {
  //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
  var render = function(options) {
    var container = options.elem,
      sort = options.sort || 'default',
      url = options.url || null,
      fine = options.fine || '',
      param = options.param || '',
      done = options.done || function() {},
      type

    layui.use(['laydate', 'layer', 'jquery', 'artTemplate'], function() {
      var laydate = layui.laydate,
        layer = layui.layer,
        $ = layui.jquery,
        template = layui.artTemplate

      //判断渲染容器
      if (!$(container)[0])
        return layer.msg('没有找到渲染容器！', { icon: 0, time: 1000 })

      //获取数据接口
      var getData = function(url) {
        $.ajax({
          url: url,
          data: param,
          success: function(res) {
            var code = res.code,
              data = res.data
            if (code === 0) {
              renderDate(setMark(data), data)
            }
          },
          error: function() {
            return layer.msg('请求数据异常', { icon: 2, time: 1000 })
          }
        })
      }
      url && getData(url)

      //处理Mark

      var setMark = function(data) {
        data = data || []
        var mark = {}
        data.forEach(function(item) {
          mark[item.time] = item.value
        })
        return mark
      }

      //详细模式
      var notebook = function(target, data) {
        $(target).show()
        $(target)
          .children()
          .html(template('fineTpl', { lists: data }))
      }

      //执行一个laydate实例
      var renderDate = function(mark, data, value) {
        data = data || []
        value = value || new Date()

        laydate.render({
          value: value,
          elem: container, //指定元素
          position: 'static',
          theme: 'grid',
          mark: mark,
          ready: function() {
            if (fine) {
              var container = document.querySelector(fine)
              container && notebook(container, data)
            }
          },
          done: function(time, date) {
            var choose = data.filter(function(item) {
              return item.time === time
            })
            var chooseData =
              choose.length === 0 ? { time: time, value: '' } : choose[0]

            moudle(chooseData, data)
          }
        })
      }
      if (!url) {
        var data = []
        renderDate(setMark(data), data)
      }

      //清空容器
      var clear = function() {
        $(container).empty()
      }

      //弹出层
      var moudle = function(chooseData, data) {
        var index = layer.open({
          type: 1,
          skin: 'layui-layer-rim', //加上边框
          title: '添加记录',
          area: ['400px', 'auto'], //宽高
          btn: ['确定', '撤销', '取消'],
          content:
            ' <div class="laydateNote_text_box">' +
            '<form class="layui-form" action="">' +
            '<div class="layui-form-mid layui-word-aux">' +
            chooseData.time +
            '</div>' +
            '<div class="layui-form-item layui-form-text">' +
            '<textarea id="text_book" placeholder="请输入内容" class="layui-textarea">' +
            chooseData.value +
            '</textarea>' +
            '</div>' +
            '</form>' +
            '</div>',

          success: function() {
            //   console.log(data);
            type = chooseData.value ? 'patch' : 'post'
          },
          yes: function() {
            //保存
            chooseData.value = $('#text_book').val()
            //  console.log(chooseData, data);
            var result = data.every(function(item) {
              return item.time !== chooseData.time
            })
            if (result) {
              data.push(chooseData)
            }
            saveData(data, chooseData)
            layer.close(index)
          },
          btn2: function() {
            //撤销
            if (!chooseData.value) {
              return layer.msg('没有内容哦!', { icon: 0, time: 1000 })
            }
            data = data.filter(function(item) {
              return item.time !== chooseData.time
            })
            type = 'delete'
            saveData(data, chooseData)
          }
        })
      }

      //保存数据
      var saveData = function(data, chooseData) {
        clear()
        renderDate(setMark(data), data, chooseData.time)

        switch (sort) {
          case 'up':
            data.sort(function(v, i) {
              return new Date(v.time) - new Date(i.time)
            })
            break
          case 'down':
            data.sort(function(v, i) {
              return new Date(i.time) - new Date(v.time)
            })
            break
        }

        done(data, chooseData, type)
      }
    })
  }

  //输出test接口
  exports('laydateNote', render)
})
