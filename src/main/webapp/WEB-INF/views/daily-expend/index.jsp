<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tableGroup">
    <table id="dailyExpendGrid" class="easyui-datagrid" data-options="
                    rownumbers: true,
                    fit: true,
                    method:'post',
				    pagination: true,
				    pageSize: 10,
				    striped: true,
				    singleSelect: false,
				    toolbar: '#dailyExpendTB',
				    url: '/daily-expend/json?dailyCode=${dailyCode}'">
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
            <a href="javascript:addDailyExpendItem()" remote="false"
               class="easyui-linkbutton" iconCls="icon-add" plain="true">
                新增
            </a>
            <a href="javascript:editDailyExpendItem()" title="<spring:message code="common.edit"/>" mustsel
               remote="false"
               data-options="disabled:true" class="easyui-linkbutton"
               iconCls="icon-edit" plain="true">
                编辑
            </a>
            <%--<a href="javascript:delDailyExpendItem()" class="easyui-linkbutton"
               iconCls="icon-no" plain="true">
                删除
            </a>--%>
        </div>
    </div>
</div>
<script>
    function addDailyExpendItem() {
        // 打开编辑页面
        var editWindow = $('<div id="addDailyExpendItemWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: '新增支出项',
            modal: true,
            /*maximized: true,*/
            width: 600,
            height: 450,
            href: '/daily-expend/edit?dailyCode=${dailyCode}',
            onLoad: function () {
                //var $form = editWindow.find('form');
                //$form.data('window',editWindow);
            },
            onClose: function () {
                editWindow.window('destroy');
            }
        });
    }
    
    function editDailyExpendItem() {
        var row = $('#dailyExpendGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("请选择一个您要编辑的支出项！");
            return;
        }
        // 打开编辑页面
        var editWindow = $('<div id="addDailyExpendItemWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: '编辑支出项',
            modal: true,
            /*maximized: true,*/
            width: 600,
            height: 450,
            href: '/daily-expend/edit?dailyCode=${dailyCode}&id=' + row.id,
            onLoad: function () {
                //var $form = editWindow.find('form');
                //$form.data('window',editWindow);
            },
            onClose: function () {
                editWindow.window('destroy');
            }
        });
    }
    
    function delDailyExpendItem() {
        var row = $('#dailyExpendGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("请选择一个您要删除的支出项！");
            return;
        }
        MXF.confirm('确认删除?', function () {
            MXF.ajaxing(true);
            $.post('/daily-expend/delete', {id: row.id}, function (data) {
                MXF.ajaxing(false);
                MXF.ajaxFormDone(data);
                if (data.success) {
                    $('#dailyExpendGrid').datagrid('reload');
                }
            });
        });
    }
</script>
