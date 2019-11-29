<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap">
    <div class="tableGroup">
        <table id="productGrid" class="easyui-datagrid" title="<spring:message code="mu.pt.info"/>"
               data-options="rownumbers:true,fit:true,method:'get',
				pagination:true,pageSize:50,striped:true,singleSelect:false,
				toolbar:'#productTB',url:'/product/json'">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'code',width:120,halign:'center',align:'center',sortable:true"><spring:message code="product.code"/></th>
                <th data-options="field:'name',halign:'center',align:'center',width:150,sortable:true"><spring:message code="product.name"/></th>
                <%--<th data-options="field:'state',width:80, formatter:productStateFormatter"><spring:message
                        code="product.state"/></th>--%>
               <%-- <th data-options="field:'productMedia',width:80,halign:'center',align:'center',formatter:productMediaFormatter"><spring:message
                        code="product.resources"/></th>--%>
                <%--<th data-options="field:'stockName',width:80,halign:'center',align:'center',formatter:productStockFormatter"><spring:message
                        code="product.stock"/></th>--%>
                <th data-options="field:'typeName',width:150,halign:'center',align:'center',sortable:true"><spring:message
                        code="product.type.name"/></th>
                <th data-options="field:'createTime',width:150,halign:'center',align:'center',formatter:MXF.dateTimeFormatter"><spring:message
                        code="common.createTime"/></th>
                <th data-options="field:'option',width:150,halign:'center',align:'center',formatter:optionFormatter"><spring:message
                        code="common.option"/></th>
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
                <a href="#" data-cmd="del" mustsel msg="<spring:message code="message.delete"/>" data-options="disabled:true" class="easyui-linkbutton"
                   iconCls="icon-remove" plain="true">
                    <spring:message code="common.delete"/>
                </a>
                <span class="buttonSplit">&nbsp;</span>
                <a href="#" data-cmd="batch" class="easyui-linkbutton"
                   plain="true">
                    <i class="iconfont">&#xe73f;</i>
                    <spring:message code="product.btn.import"/>
                </a>
                <%--<a href="#" data-cmd="showSku" mustsel data-options="disabled:true" class="easyui-linkbutton"
                   plain="true">
                    <i class="iconfont">&#xe6cb;</i>
                    <spring:message code="product.info.tools.sku"/>
                </a>--%>
                <a href="#" data-cmd="toPage" class="easyui-linkbutton"
                   plain="true">
                    <i class="iconfont">&#xe699;</i>
                    <spring:message code="mu.pt.view"/>
                </a>
            </div>
            <div class="searchForm">
                <form>
                    <spring:message code="product.code"/>：
                    <input class="easyui-textbox theme-textbox-radius" name="code" style="width:200px;">&nbsp;
                    <spring:message code="product.info.name"/>：
                    <input class="easyui-textbox theme-textbox-radius" name="name" style="width:200px;">&nbsp;
                    <spring:message code="product.info.type"/>：
                    <input class="easyui-combotree theme-textbox-radius" name="productType"
                           data-options="url:'/product-type/json',method:'get',onChange:onProductTypeChange" style="width:200px;">
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
    
    function productMediaFormatter(value) {
        return "<img onclick='MXF.showImageDialog(this.src)' height='35' width='35' src='" + value +"'/>";
    }

    function productStateFormatter(value) {
        if (0 == value) {
            return '<red><spring:message code="product.state.scarce"/></red>';
        } else if (1 == value) {
            return '<green><spring:message code="product.state.normal"/></green>';
        }
        return '';
    }
    
    function productStockFormatter(val, row) {
        console.log(row);
        return val;
    }
    
    function optionFormatter(val, row) {
        var a = "<a href='#' onClick='showSku("+ row.id +")'><i class='iconfont'>&#xe6cb;</i><span style='margin-left: 5px;'><spring:message code='product.info.tools.sku'/></span></a>";
        return a;
    }

    /*function showSku(clickTarget) {
        if ($(clickTarget).hasClass('l-btn-disabled')) return;
        var row = $('#productGrid').datagrid('getSelected');
        var name = row.name || 'SKU管理';
        var editWindow = $('<div id="skuWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: name,
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
    }*/

    function showSku(id) {
        var editWindow = $('<div id="skuWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: 'SKU管理',
            modal: true,
            maximized: true,
            href: '/product/skus?id=' + id,
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
                        if (data.success) {
                            MXF.alert("导入成功！");
                            $('#productGrid').datagrid('reload');
                        } else {
                            MXF.alert(data.message + "，" + data.info);
                        }
                    });
                }
            });
        });
    }
    
    function toPage() {
        var tabs = $("#tabs");
        var uri = '/product-view';
        var title = '商品列表';
        var tab = tabs.tabs("getTab", title);
        console.log('open' + title, tab);
        if (tab) {
            tabs.tabs("select", title);
        } else {
            tabs.tabs('add', {
                title: title,
                href: uri,
                closable: true,
                bodyCls: "content"
            });
        }
    }
    
    function onProductTypeChange() {
        commonCmd.search($("#productGrid"));
    }

</script>
