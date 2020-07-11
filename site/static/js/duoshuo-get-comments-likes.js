<!-- 多说 获取评论数-点赞数 -->
$(document).ready(function () {
    var likes_a = document.getElementsByClassName('likes_a');
    var comments_a = document.getElementsByClassName('comments_a');
    var threads = [likes_a.length];
    var short_name = "tuonion";
    for (var i = 0; i < likes_a.length; i++) {
        threads[i] = (likes_a[i].id).toString().split('_')[2];
    }
    var url = "http://api.duoshuo.com/threads/counts.jsonp?short_name=" + short_name + "&threads=" + threads + "&callback=?";
    $.getJSON(url, function (data) {
        $.each(data.response, function (i, item) {

            $.each(likes_a, function (i, tag) {
                if (item.thread_key == (tag.id).toString().split('_')[2]) {
                    tag.innerHTML = item.likes;
                }
            });
            $.each(comments_a, function (i, tag) {
                if (item.thread_key == (tag.id).toString().split('_')[2]) {
                    tag.innerHTML = item.comments;
                }
            });
        });
    });
});
