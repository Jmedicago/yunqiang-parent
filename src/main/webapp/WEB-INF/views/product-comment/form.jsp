<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<form method="post" style="width: 600px; margin: 30px auto;" action="/product-comment/store">
    <input type="hidden" name="detailId" value="${detailId}"/>
    <input type="hidden" name="orderId" value="${productComment.orderId}"/>
    <input type="hidden" name="productId" value="${productComment.productId}"/>
    <input type="hidden" id="score"  name="score" value="${productComment.score}"/>
    <div class="input-div">
        <label class="label-top"><spring:message code="commit.entity.score"/></label>
        <div id="starScore">
            <ul>
                <li id="1">☆</li>
                <li id="2">☆</li>
                <li id="3">☆</li>
                <li id="4">☆</li>
                <li id="5">☆</li>
            </ul>
        </div>
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="commit.entity.commit"/></label>
        <input class="easyui-textbox theme-textbox-radius" data-options="multiline:true" style="height:100px;"
               name="comment" value="${productComment.comment}">
    </div>
    <div class="input-div" style="text-align: center; margin-top: 35px">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this, function() {
          $('#productCommentGrid').datagrid('reload');
          $('#orderGrid').datagrid('reload');
        })"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
    <hr style="border:0;margin-bottom:20px;"/>
</form>
<style type="text/css">
    #starScore {
        width: 485px;
        float: right;
        height: 30px;
        line-height: 30px;
    }

    #starScore li {
        display: inline-block;
        font-size: 20px;
        cursor: pointer;
        color: red;
    }
</style>
<script>
    var star = {};
    star.select = false;
    star.score = 0;
    star.change = function (pres) { // 改空心为实心
        $("#starScore li").each(function (index, element) {
            star.score = pres.id;
            if (index + 1 <= pres.id) {
                $(element).html("★");
            } else {
                $(element).html("☆");
            }
            star.select = false;
        });
    }
    star.back = function () { //恢复实心为空心
        if (!star.select) {
            $("#starScore li").each(function (index, element) {
                $(element).html("☆");
            });
        }
    }

    $(function () {
        $('#starScore li').click(function (e) {
            var pres = e.target;
            star.change(pres);
            if (star.select) {
                star.select = false;
            } else {
                star.select = true;
            }
            $('#score').val(star.score);
        })
    })
</script>
