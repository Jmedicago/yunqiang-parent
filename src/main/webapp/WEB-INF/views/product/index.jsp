<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="productGrid" class="easyui-datagrid" title="<spring:message code="mu.pt.info"/>"
               data-options="rownumbers:true,fit:true,method:'get',
				pagination:true,pageSize:10,striped:true,singleSelect:false,
				toolbar:'#productTB',url:'/product/json'">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'code',width:120"><spring:message code="product.code"/></th>
                <th data-options="field:'name',width:200"><spring:message code="product.name"/></th>
                <th data-options="field:'state',width:80, formatter:productStateFormatter"><spring:message
                        code="product.state"/></th>
            </tr>
            </thead>
        </table>
        <div id="productTB">
            <div>
                <a href="#" data-cmd="add" height="620" title="<spring:message code="common.add"/>" remote="false"
                   class="easyui-linkbutton" iconCls="icon-add" plain="true">
                    <spring:message code="common.add"/>
                </a>
                <a href="#" data-cmd="edit" height="620" title="<spring:message code="common.edit"/>" mustsel
                   remote="false"
                   data-options="disabled:true"
                   class="easyui-linkbutton"
                   iconCls="icon-edit" plain="true">
                    <spring:message code="common.edit"/>
                </a>
                <a href="#" data-cmd="del" mustsel data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-remove" plain="true">
                    <spring:message code="common.delete"/>
                </a>
                <span class="buttonSplit">&nbsp;</span>
                <a href="#" data-cmd="batch" class="easyui-linkbutton"
                   plain="true">
                    <i class="iconfont">&#xe73f;</i>
                    批量导入
                </a>
                <a href="#" data-cmd="showSku" mustsel data-options="disabled:true" class="easyui-linkbutton"
                   plain="true">
                    <i class="iconfont">&#xe6cb;</i>
                    <spring:message code="product.info.tools.sku"/>
                </a>
            </div>
            <div class="searchForm">
                <form>
                    <spring:message code="product.info.name"/>：
                    <input class="easyui-textbox theme-textbox-radius" name="name" style="width:200px;">&nbsp;
                    <spring:message code="product.info.type"/>：
                    <input class="easyui-combotree theme-textbox-radius" name="productType"
                           data-options="url:'/product-type/json',method:'get'" style="width:200px;">
                    <a href="javascript:;" data-cmd="search" class="easyui-linkbutton button-default">
                        <spring:message code="common.search"/>
                    </a>
                    <a href="javascript:;" data-cmd="resetSearch" class="easyui-linkbutton">
                        <spring:message code="common.reset"/>
                    </a>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        MXF.getTabContentHeight();
    });

    function productStateFormatter(value) {
        if (0 == value) {
            return '<red><spring:message code="product.state.scarce"/></red>';
        } else if (1 == value) {
            return '<green><spring:message code="product.state.normal"/></green>';
        }
        return '';
    }

    function showSku(clickTarget) {
        if ($(clickTarget).hasClass('l-btn-disabled')) return;
        var row = $('#productGrid').datagrid('getSelected');
        var editWindow = $('<div id="skuWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: row.name,
            modal: true,
            maximized: true,
            href: '/product/skus?id=' + row.id,
            onLoad: function () {
                //var $form = editWindow.find('form');
                //$form.data('window',editWindow);
            },
            onClose: function () {
                editWindow.window('destroy');
            }
        });
    }

    function batch() {
        // 编辑器参数
        var kingEditorParams = {
            //指定上传文件参数名称
            filePostName: "uploadFile",
            //指定上传文件请求的url。
            uploadJson: '/resources/upload',
            //上传类型，分别为image、flash、media、file
            dir: "file"
        };
        KindEditor.editor(kingEditorParams).loadPlugin('image', function () {
            this.plugin.imageDialog({
                showRemote: false,
                clickFn: function (url, title, width, height, border, align) {
                    this.hideDialog();
                    MXF.ajaxing(true);
                    $.post('/product/batch', {excelUrl: url}, function (data) {
                        MXF.ajaxing(false);
                        MXF.alert("导入成功！");
                        $('#productGrid').datagrid('reload');
                    });
                }
            });
        });
    }

</script>
