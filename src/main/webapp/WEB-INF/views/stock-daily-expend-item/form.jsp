<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form method="post" action="/stock-daily-expend-item/store">
    <input type="hidden" name="id" value="${finStockDailyExpendItem.id}"/>
    <input type="hidden" name="dailyId" value="${finStockDailyExpendItem.dailyId}"/>
    <div class="input-div">
        <label class="label-top">类别</label>
        <select id="expendItemCombobox" class="easyui-combobox" name="expendItemId" data-options="
                    required: true,
                    valueField: 'id',
                    textField: 'category',
                    onLoadSuccess: initExpendItemValue,
			        multiple: false,
			        formatter: function(row) {
                        var text = row.category + '&nbsp;' + row.article;
                        return text;
                    },
			        url: '/expend-item/list'" style="width:420px;">
        </select>
    </div>
    <div class="input-div">
        <label class="label-top">项目</label>
        <input class="easyui-textbox theme-textbox-radius" name="detail" value="${finStockDailyExpendItem.detail}"
               data-options="required:true">
    </div>
    <div class="input-div">
        <label class="label-top">金额</label>
        <input class="easyui-textbox theme-textbox-radius" name="amount" value="${finStockDailyExpendItem.amount * 0.01}">
    </div>
    <div class="input-div" style="text-align: center; margin-top: 35px">
        <a class="easyui-linkbutton button-lg button-default"
           onclick="MXF.ajaxForm(this, StockDailyExpendItemCallBack)"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
    <hr style="border:0;margin-bottom:20px;"/>
</form>
<script type="text/javascript">

    function initExpendItemValue() {
        $('#expendItemCombobox').combobox('setValues', [${finStockDailyExpendItem.expendItemId}]);
    }

    function StockDailyExpendItemCallBack($form, data) {
        if (data.success) {
            $('#stockDailyExpendItemGrid').datagrid('reload');
            $('#editStockDailyExpendItemWindow').window('close');
        }
    }

</script>