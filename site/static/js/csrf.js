/**
 * Created by onion on 2016/4/30.
 */
 $.ajaxSetup({
     beforeSend: function (xhr, settings) {
          var csrftoken = $.cookie('csrftoken');
          xhr.setRequestHeader("X-CSRFToken", csrftoken);
     }
 });