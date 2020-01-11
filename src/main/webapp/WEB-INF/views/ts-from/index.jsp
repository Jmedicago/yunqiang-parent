<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<style type="text/css">
</style>
<div>
    <form action="/ts-from/store" method="post">
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
        <div class="input-div">
            <label class="label-top">每日销售额</label>
            <input class="easyui-textbox theme-textbox-radius" name="sales">
        </div>
        <button id="appendExpend" type="button">添加支出项</button>
        <div id="expendsList">

        </div>
        <button type="submit">提交</button>
    </form>
</div>

<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script>
    var size = 0;
    $('#appendExpend').on('click', function () {
        $('#expendsList').append(expendItem(size));
        size++;
    });
    
    function removeExpend(that) {
        $(that).parent().remove();
    }

    function expendItem(size) {
        var htm = '';
        htm += '<div>';
        htm += '<div class="expend-item">';
        htm += '<label class="label-top">支出类别</label>';
        htm += '<input class="easyui-textbox theme-textbox-radius" name="baseFormList[' + size + '].category">';
        htm += '</div>';
        htm += '<div class="expend-item">';
        htm += '<label class="label-top">支出项目</label>';
        htm += '<input class="easyui-textbox theme-textbox-radius" name="baseFormList[' + size + '].detail">';
        htm += '</div>';
        htm += '<div class="expend-item">';
        htm += '<label class="label-top">金额</label>';
        htm += '<input class="easyui-textbox theme-textbox-radius" name="baseFormList[' + size + '].amount">';
        htm += '</div>';
        htm += '<button type="button" onclick="removeExpend(this)">移除</button>';
        htm += '</div>';
        return htm;
    }

</script>
