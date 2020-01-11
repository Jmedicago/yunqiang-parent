<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<style>
    .expend-item {
        border: 1px solid rgb(225, 225, 225);
        width: 417px;
        margin-left: 114px;
        margin-top: 15px;
    }
    .expend-item .btn-remove {
        padding: 5px;
        border: 1px solid #ddd;
        color: #ff0000;
        float: right;
    }

    .expend-item .input-div {
        padding: 15px 0;
    }

    .expend-item .input-div .easyui-textbox {
        border: 1px solid #ddd;
        height: 30px;
        width: 280px;
        margin-top: 10px;
    }
</style>
<form method="post" style="width: 600px; margin: 30px auto;" action="/dy-daily/store">
    <div class="input-div">
        <label class="label-top">日期</label>
        <input class="easyui-datebox theme-textbox-radius" style="width: 420px" name="dateFormatter" required ="required">
    </div>
    <div class="input-div">
        <label class="label-top">交付现金</label>
        <input class="easyui-textbox theme-textbox-radius" name="income">
    </div>
    <div class="input-div">
        <label class="label-top">上货金额</label>
        <input class="easyui-textbox theme-textbox-radius" name="purch">
    </div>
    <div class="input-div">
        <label class="label-top">最新客商欠款</label>
        <input class="easyui-textbox theme-textbox-radius" name="arrears">
    </div>
    <div style="position: relative;" id="expendsList">
    </div>
    <div class="input-div" style="text-align: center; margin: 35px 0">
        <a class="easyui-linkbutton button-lg button-danger" onclick="appendExpend()">新增支出</a>
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this, function(form, res) {
          if (res.success) {
            onLoadDyDailyFrame();
          }
        })"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
</form>
<script>
    var size = 0;

    function appendExpend() {
        $('#expendsList').append(expendItem(size));
        size ++;
    }

    function removeExpend(that) {
        $(that).parent().remove();
    }

    function expendItem(size) {
        var data = [];
        $.ajax({
            type: 'GET',
            url: '/expend-item/list',
            async: false,
            success: function (res) {
                data = res;
            }
        });

        var htm = '';
        htm += '<div class="expend-item">';
        htm += '<button class="btn-remove" type="button" onclick="removeExpend(this)">移除</button>';
        htm += '<div class="input-div">';
        htm += '<label class="label-top">支出类别</label>';
        //htm += '<input class="easyui-textbox theme-textbox-radius" name="finDailyExpendList[' + size + '].category">';
        htm += '<select class="easyui-textbox" name="finDailyExpendList[' + size + '].expendItemId">';
        for (var i = 0; i < data.length; i++) {
            htm += '<option value="' + data[i].id + '">' + data[i].category + '&nbsp;' + data[i].article + '</option>';
        }
        //htm += '<option value="' + data.id + '">' + data.category + data.article + '</option>';
        htm += '</select> ';
        //htm += '</div>';
        //htm += '<div class="input-div">';
        htm += '<label class="label-top">支出项目</label>';
        htm += '<input class="easyui-textbox theme-textbox-radius" name="finDailyExpendList[' + size + '].detail">';
        //htm += '</div>';
        //htm += '<div class="input-div">';
        htm += '<label class="label-top">金额</label>';
        htm += '<input class="easyui-textbox theme-textbox-radius" name="finDailyExpendList[' + size + '].amount">';
        htm += '</div>';
        htm += '</div>';
        return htm;
    }
</script>
