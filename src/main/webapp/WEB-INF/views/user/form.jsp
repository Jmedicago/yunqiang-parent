<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form method="post" style="width: 600px; margin: 30px auto;" action="/user/store">
    <input type="hidden" name="id" value="${sysUser.id}"/>
    <div class="input-div">
        <label class="label-top"><spring:message code="user.name"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="username" value="${sysUser.username}"
               data-options="required:true">
    </div>
    <%--<div class="input-div">
        <label class="label-top"><spring:message code="user.email"/></label>
        <input class="easyui-textbox theme-textbox-radius" name="email" value="${sysUser.email}">
    </div>--%>
    <div class="input-div">
        <label class="label-top">负责人</label>
        <input class="easyui-textbox theme-textbox-radius" name="manager" value="${sysUser.manager}">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="user.phone"/></label>
        <input id="brandCombo" class="easyui-textbox theme-textbox-radius" name="phone" value="${sysUser.phone}">
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="user.roles"/></label>
        <select id="rolesCombobox" class="easyui-combobox" name="roleIds"
                data-options="required:true,valueField: 'id',textField: 'name',onLoadSuccess:initRolesValue,
			multiple:true,url:'/role/list'"
                style="width:420px;">
        </select>
    </div>
    <div class="input-div">
        <label class="label-top"><spring:message code="user.region"/></label>
        <select id="stocksCombotree" class="easyui-combotree" style="width:420px;" name="stockIds"
                data-options="url: '/stock/combo', cascadeCheck: false, multiple: false, onLoadSuccess: initStocksValue,
                              onClick: function(node) {
                                    $('#stocksCombotree').combotree('setText', node.stockPath);  
                              },"></select>
    </div>
    <div class="input-div">
        <label class="label-top">门店描述</label>
        <input class="easyui-textbox theme-textbox-radius" data-options="multiline:true" style="height:60px;"
               name="remark" value="${sysUser.remark}">
    </div>
    <div class="input-div" style="text-align: center; margin-top: 35px">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this)"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
    <hr style="border:0;margin-bottom:20px;"/>
</form>
<script type="text/javascript">

    function initRolesValue() {
        console.log('[roles]', ${selectRoles});
        if (Array.isArray(${selectRoles})) {
            $('#rolesCombobox').combobox('setValues', ${selectRoles});
        }
    }

    function initStocksValue() {
        console.log('[stocks]', ${selectStocks});
        console.log("${selectStockPath}")
        if (Array.isArray(${selectStocks})) {
            $('#stocksCombotree').combotree('setValues', ${selectStocks});
            // 设置显示的值
            $('#stocksCombotree').combotree('setText', "${selectStockPath}");
        }
    }

    /** 
      * 根据叶子节点选中的值，获取树整个路径的名称 
      * @param combotreeId 唯一ID 
      * @param leafValue 叶子节点的值 
      */
    /*function getCombotreePathNames(combotreeId, leafValue) {
        console.log(leafValue);
        var combotreeObj = $("#" + combotreeId);
        var treeObj = combotreeObj.combotree("tree");
        console.log(treeObj);
        var nodesChecked = treeObj.tree("getChecked");//获取选中的值  
        console.log("选中的值：" + nodesChecked);
        var pathName = "";
        if (nodesChecked.length > 0) {
            for (var i = 0; i < nodesChecked.length; i++) {
                pathName += getTreePathNames(treeObj, nodesChecked[i]);
            }
        }
        console.log(pathName)
        return pathName;
    };*/

    function getComboxTreePathNames(combotreeId, leafValue) {
        var combotreeObj = $("#" + combotreeId);
        var treeObj = combotreeObj.combotree("tree");
        var nodesChecked = treeObj.tree("getSelected");//获取选中的值  
        var array = nodesChecked.path.split(".");

        for (var i = 0; i < array.length; i ++) {
            if (array[i] != "") {


            }
        }
    }

    /** 
      * 根据叶子节点选中的值，获取树整个路径的名称 
      * @param treeObj 树对象，（combotree的树对象获取：var treeObj = comboObj.combotree("tree");） 
      * @param node 叶子节点 
      */
    function getTreePathNames(treeObj, node) {
        var pathName = node.text;
        var parentNode = treeObj.tree("getParent", node.target);
        if (parentNode != null && parentNode != "undefined") {
            pathName = getTreePathNames(treeObj, parentNode) + " > " + pathName;
        }
        console.log(pathName)
        return pathName;
    };

</script>