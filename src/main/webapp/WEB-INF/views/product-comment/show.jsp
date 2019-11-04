<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="comment">
    <c:forEach var="item" items="${productComments}">
        <div class="comment-item">
            <span class="time" data-time="${item.createTime}"></span>
            <div class="score" data-score="${item.score}">
                <ul>
                    <li id="1">☆</li>
                    <li id="2">☆</li>
                    <li id="3">☆</li>
                    <li id="4">☆</li>
                    <li id="5">☆</li>
                </ul>
            </div>
            <p class="comment">${item.comment}</p>
        </div>
    </c:forEach>
</div>

<style type="text/css">
    .comment-item .time {
        display: block;
        font-size: 14px;
        height: 20px;
        line-height: 20px;
    }
    .comment-item .score {
        display: block;
        font-size: 20px;
        color: red;
    }
    .comment-item .score li {
        display: inline-block;
    }
    .comment-item .comment {
        overflow: hidden;
        height: 100px;
        line-height: 20px;
        font-size: 14px;
        /*text-indent: 15px;*/
    }
    .comment-item {
        border-bottom: 1px solid #eee;
        padding: 10px 10px;
    }
</style>

<script type="text/javascript">
    $(function () {
        var array = $('#comment .comment-item');
        $.each(array, function (index, value) {
            var el = $(value).find('.time');
            // 时间
            var date = el.data('time');
            date = MXF.dateTimeFormatter(date);
            $(el).text(date);
            // 评分
            var score = $(value).find('.score').data('score');
            $(value).find('.score li').each(function (index, element) {
                if (index + 1 <= score) {
                    $(element).html("★");
                } else {
                    $(element).html("☆");
                }
            });
        });
    })
</script>