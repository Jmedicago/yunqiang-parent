<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form id="dyStockDailyForm" method="post" style="width: 600px; margin: 30px auto;" action="/stock-daily/store">
    <input type="hidden" name="id" value="${dyDaily.id}"/>
    <div class="input-div">
        <label class="label-top">交付现金</label>
        <input class="easyui-textbox theme-textbox-radius" name="income" value="${dyDaily.income * 0.01}">
    </div>
    <div class="input-div">
        <label class="label-top">上货金额</label>
        <input class="easyui-textbox theme-textbox-radius" name="purch" value="${dyDaily.purch * 0.01}">
    </div>
    <div class="input-div">
        <label class="label-top">最新客商欠款</label>
        <input class="easyui-textbox theme-textbox-radius" name="arrears" value="${dyDaily.arrears * 0.01}">
    </div>
    <%--<div class="input-div">
        <label class="label-top">每日销售额</label>
        <input class="easyui-textbox theme-textbox-radius" name="sales" value="${dyDaily.sales}">
    </div>--%>

    <%--<div class="input-div">
        <label class="label-top">支出明细</label>
        <div style="width: 450px;height: 300px;margin-left: 113px;margin-top: -27px;">
            <table class="easyui-datagrid" data-options="
                    rownumbers: true,
                    fit: true,
                    method:'post',
				    pagination: true,
				    pageSize: 10,
				    striped: true,
				    singleSelect: false,
				    toolbar: '#dailyExpendTB',
				    url: '/daily-expend/json?dailyCode=Y1'">
                <thead>
                <tr>
                    <th data-options="field: 'expendItemId',width: 50, halign: 'center', align: 'center', formatter: categoryFormatter">
                        支出类别
                    </th>
                    <th data-options="field: 'detail',width: 200, halign: 'center', align: 'center'">
                        支出项目
                    </th>
                    <th data-options="field:'amount', width: 100, halign: 'center', align: 'center', formatter: MXF.priceFormatter">
                        金额
                    </th>
                </tr>
                </thead>
            </table>
            <div id="dailyExpendTB">
                <div>
                    <a href="javascript:addDailyExpend()" remote="false"
                       class="easyui-linkbutton" iconCls="icon-add" plain="true">
                        新增
                    </a>
                    <a href="#" data-cmd="editStockDaily" title="<spring:message code="common.edit"/>" mustsel
                       remote="false"
                       data-options="disabled:true" class="easyui-linkbutton"
                       iconCls="icon-edit" plain="true">
                        编辑
                    </a>
                    <a href="#" data-cmd="showStockDaily" class="easyui-linkbutton"
                       iconCls="icon-no" plain="true">
                        删除
                    </a>
                </div>
            </div>
        </div>
    </div>--%>
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
        MXF.ajaxForm('#dyStockDailyForm', function (form, data) {
            if (data.success) {
                // 打开编辑页面
                var editWindow = $('<div id="addDailyExpendWindow"></div>');
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