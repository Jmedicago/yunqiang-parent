<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',title:'<spring:message code="mu.pt.type"/>',split:true,border:true" style="width:300px;">
            <ul class="easyui-tree"
                data-options="
				    url: '/product-type/json',
					method: 'get',
					animate: true,
					onClick: clickTreeNode">
            </ul>
        </div>
        <div data-options="region:'center',border:true,title:'<spring:message code="mu.pt.prop"/>'">
            <div class="tableGroup">
                <table id="propertyGrid" class="easyui-datagrid"
                       data-options="
                           singleSelect:true,
                           fit:true,
                           method:'get',
					       pagination:false,
					       striped:true,
					       onSelect:selectProperty,
						   onLoadSuccess:loadPropertySuccess,
						   toolbar:'#propertyTB',
						   url:'/property/json'">
                    <thead>
                    <tr>
                        <th data-options="field:'name',width:120"><spring:message code="product.prop.name"/></th>
                        <th data-options="field:'type',width:120,formatter:formatterPropertyType"><spring:message code="product.prop.type"/></th>
                        <th data-options="field:'inputMode',width:120,formatter:formatterInputMode"><spring:message code="product.prop.mode"/></th>
                    </tr>
                    </thead>
                </table>
                <div id="propertyTB">
                    <div>
                        <a href="#" data-cmd="add" title="<spring:message code="common.add"/>" id="addPropertyBtn"
                           class="easyui-linkbutton"
                           data-options="disabled:true" iconCls="icon-add" plain="true">
                            <spring:message code="common.add"/>
                        </a>
                        <a href="#" data-cmd="edit" title="<spring:message code="common.edit"/>" mustsel remote="false"
                           data-options="disabled:true"
                           class="easyui-linkbutton" iconCls="icon-edit" plain="true">
                            <spring:message code="common.edit"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div data-options="region:'east',border:true,title:'<spring:message code="mu.pt.ppv"/>'" style="width:400px;">
            <div class="tableGroup">
                <table id="propertyOptionGrid" class="easyui-datagrid"
                       data-options="
                            singleSelect:true,
                            fit:true,method:'get',
							pagination:false,
							striped:true,
							toolbar:'#propertyOptionTB',
							url:'/property-option/json'">
                    <thead>
                    <tr>
                        <th data-options="field:'optionValue', width:120"><spring:message code="product.ppv.name"/></th>
                        <th data-options="field:'optionIcon', width:120"><spring:message code="product.ppv.icon"/></th>
                    </tr>
                    </thead>
                </table>
                <div id="propertyOptionTB">
                    <div>
                        <a href="#" id="addPropertyOptionBtn" height="250" data-cmd="add"
                           title="<spring:message code="common.add"/>"
                           class="easyui-linkbutton"
                           data-options="disabled:true" iconCls="icon-add" plain="true">
                            <spring:message code="common.add"/>
                        </a>
                        <a href="#" data-cmd="edit" height="250" title="<spring:message code="common.edit"/>" mustsel
                           remote="false" data-options="disabled:true"
                           class="easyui-linkbutton" iconCls="icon-edit" plain="true">
                            <spring:message code="common.edit"/>
                        </a>
                        <a href="#" data-cmd="del" title="<spring:message code="common.delete"/>" mustsel
                           msg="<spring:message code="message.delete"/>"
                           data-options="disabled:true" class="easyui-linkbutton"
                           iconCls="icon-remove" plain="true">
                            <spring:message code="common.delete"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        MXF.getTabContentHeight();
    });

    function formatterPropertyType(value, row, index) {
        if (0 == value) {
            return '<spring:message code="common.accord"/>';
        } else if (1 == value) {
            return '<spring:message code="common.sales"/>';
        }
        return '';
    }

    function formatterInputMode(value, row, index) {
        if (0 == value) {
            return '<spring:message code="common.mode.text"/>';
        } else if (1 == value) {
            return '<spring:message code="common.mode.select"/>';
        } else if (2 == value) {
            return '<spring:message code="common.mode.checkbox"/>';
        }
        return '';
    }

    function clickTreeNode(node) {
        if (null == node.children || 0 == node.children.length) { //叶子节点
            $('#propertyGrid').datagrid('load', {productType: node.id});
            $('#propertyTB').find('a[data-cmd]').linkbutton('disable');
            $('#addPropertyBtn').linkbutton('enable');
        } else {
            $('#propertyGrid').datagrid('load', {});
            $('#propertyTB').find('a[data-cmd]').linkbutton('disable');
        }
    }

    function loadPropertySuccess(data) {
        $('#propertyOptionTB').find('a[data-cmd]').linkbutton('disable');
        $('#propertyOptionGrid').datagrid('load', {});
    }

    function selectProperty(index, row) {
        if (0 == row.inputMode) { //文本框输入模式
            $('#propertyOptionTB').find('a[data-cmd]').linkbutton('disable');
            $('#propertyOptionGrid').datagrid('load', {});
        } else {
            $('#addPropertyOptionBtn').linkbutton('enable');
            $('#propertyOptionGrid').datagrid('load', {property: row.id});
        }
    }

</script>