<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form id="dzStockDailyForm" method="post" style="width: 600px; margin: 30px auto;" action="/region-stock-daily/store">
    <input type="hidden" name="id" value="${dzDaily.id}"/>
    <div class="input-div">
        <label class="label-top">交付现金</label>
        <input class="easyui-textbox theme-textbox-radius" name="income" value="${dzDaily.incomeSubTotal * 0.01}">
    </div>
    <div class="input-div">
        <label class="label-top">上货金额</label>
        <input class="easyui-textbox theme-textbox-radius" name="purch" value="${dzDaily.purch * 0.01}">
    </div>
    <div class="input-div">
        <label class="label-top">最新客商欠款</label>
        <input class="easyui-textbox theme-textbox-radius" name="arrears" value="${dzDaily.arrears * 0.01}">
    </div>
    <div class="input-div" style="text-align: center; margin-top: 35px">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this)"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg button-red" onclick="addDailyExpend()">添加支出</a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
    <hr style="border:0;margin-bottom:20px;"/>
</form>
<script>
    function categoryFormatter(val, row) {
        return row.finExpendItem.category;
    }

    function addDailyExpend() {
        MXF.ajaxForm('#dzStockDailyForm', function (form, data) {
            if (data.success) {
                // 打开编辑页面
                var editWindow = $('<div id="addDzDailyExpendWindow"></div>');
                editWindow.appendTo('body');
                $(editWindow).window({
                    title: '新增支出',
                    modal: true,
                    /*maximized: true,*/
                    width: 600,
                    height: 450,
                    href: '/daily-expend?dailyCode=' + data.data.code,
                    onLoad: function () {
                        //var $form = editWindow.find('form');
                        //$form.data('window',editWindow);
                    },
                    onClose: function () {
                        editWindow.window('destroy');
                    }
                });
            }
        });
    }

</script>
