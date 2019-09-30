<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="tab-wrap">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',title:'<spring:message code="mu.pt.type"/>',split:true,border:true"
             style="width:300px;">
            <ul id="productTypeTree" class="easyui-tree"
                data-options="
						url: '/product-type/json',
						state:'closed',
						method: 'get',
						animate: true,
						onClick: clickProductTypeTreeNode,
						onAfterEdit : updateProductTypeTreeNode,
						onContextMenu: function(e,node){
							e.preventDefault();
							$(this).tree('select',node.target);
							$('#productTypeTreeContextMenu').menu('show',{
								left: e.pageX,
								top: e.pageY
							});
						},
						onLoadSuccess:function(){
							$('#productTypeTree').tree('expandAll');
						}
					"></ul>
        </div>
        <div data-options="region:'center',border:true,title:'<spring:message code="common.edit"/>',href:'/product-type/edit'"
             style="padding: 15px;"></div>
    </div>
    <!-- 鼠标左键菜单 -->
    <div id="productTypeTreeContextMenu" class="easyui-menu" style="width: 120px;">
        <div onclick="appendProductTypeTreeNode()" data-options="iconCls:'icon-add'">
            <spring:message code="common.tree.append"/>
        </div>
        <div onclick="removeProductTypeTreeNode()" data-options="iconCls:'icon-remove'">
            <spring:message code="common.tree.remove"/>
        </div>
        <div class="menu-sep"></div>
        <div onclick="expandProductTypeTree()">
            <spring:message code="common.tree.expand"/>
        </div>
        <div onclick="collapseProductTypeTree()">
            <spring:message code="common.tree.collapse"/>
        </div>
        <div onclick="reloadProductTypeTree()">
            <spring:message code="common.tree.reload"/>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        MXF.getTabContentHeight();
    });

    function clickProductTypeTreeNode(node) {
        //$(this).tree('beginEdit',node.target);
        MXF.clearForm($('#productTypeForm'));
        $('#productTypeForm').form('load', node);
    }

    function updateProductTypeTreeNode(node) {
        console.debug(node);
    }

    function appendProductTypeTreeNode() {
        var t = $('#productTypeTree');
        console.log(t);
        var node = t.tree('getSelected');
        var parentNode = (node ? node.target : null);
        var parentNodeId = 1000;
        if (null != node) parentNodeId = node.id;
        t.tree('append', {
            parent: parentNode,
            data: [{
                pid: parentNodeId, text: '<spring:message code="common.tree.node"/>'
            }]
        });
    }

    function removeProductTypeTreeNode() {
        var node = $('#productTypeTree').tree('getSelected');
        if (node && node.id != null) {
            MXF.confirm('<spring:message code="message.delete"/>?', function () {
                MXF.ajaxing(true);
                $.post('/product-type/delete', {id: node.id}, function (data) {
                    MXF.ajaxing(false);
                    MXF.ajaxFormDone(data);
                    if (data.success) {
                        $('#productTypeTree').tree('remove', node.target);
                    }
                });
            });
        } else {
            $('#productTypeTree').tree('remove', node.target);
        }
    }

    function collapseProductTypeTree() {
        var node = $('#productTypeTree').tree('getSelected');
        $('#productTypeTree').tree('collapse', node.target);
    }

    function expandProductTypeTree() {
        var node = $('#productTypeTree').tree('getSelected');
        console.log(node);
        $('#productTypeTree').tree('expand', node.target);
    }

    function reloadProductTypeTree() {
        $('#productTypeTree').tree('reload');
    }

</script>
