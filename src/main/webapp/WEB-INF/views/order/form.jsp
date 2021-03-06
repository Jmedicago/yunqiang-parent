<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:true,title:'商品列表'">
            <div class="tableGroup">
                <table id="editOrderGrid" class="easyui-datagrid" data-options="
                           rownumbers: true,
                           fit: true,
                           method: 'post',
                           pagination: true,
                           pageSize: 50,
                           striped: true,
                           singleSelect: false,
                           toolbar: '#editOrderGridTB',
                           url: '/sku/es'"> <!-- /sku/json -->
                    <thead>
                    <tr>
                        <th data-options="field: 'id', checkbox:true"></th>
                        <th data-options="field:'path',width:150,halign:'center',align:'center',sortable:true">
                            <spring:message
                                    code="product.type.name"/></th>
                        <%--<th data-options="field: 'skuName', width:100, halign: 'center', align: 'center'"><spring:message code="st.out.product"/></th>--%>
                        <th data-options="field: 'skuMainPic', width: 50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                            <spring:message code="sku.skuMainPic"/></th>
                        <th data-options="field:'name',width:150,halign:'center',align:'center',sortable:true">
                            <spring:message
                                    code="product.name"/></th>
                        <th data-options="field:'code',width:150,halign:'center',align:'center',sortable:true">
                            <spring:message
                                    code="product.code"/></th>
                        <th data-options="field: 'skuProperties', width:150, halign: 'center', align: 'left',formatter:MXF.cellTooltipFormatter">
                            <spring:message code="st.out.property"/></th>
                        <th data-options="field: 'pack', width:100, halign: 'center', align: 'center',sortable:true">
                            <spring:message
                                    code="sku.pack"/></th>
                        <th data-options="field: 'volume', width:80, halign: 'center', align: 'center',sortable:true">
                            <spring:message
                                    code="sku.volume"/>（m<sup>3</sup>）
                        </th>
                        <th data-options="field: 'costPrice', width:80, halign: 'center', align: 'center',sortable:true, formatter: MXF.priceFormatter">
                            <spring:message code="sku.costPrice"/></th>
                        <th data-options="field: 'marketPrice', width:80, halign: 'center', align: 'center',sortable:true, formatter: MXF.priceFormatter">
                            <spring:message code="sku.marketPrice"/></th>
                        <th data-options="field: 'profit', width:50, halign: 'center', align: 'center'"><spring:message
                                code="sku.profit"/>（%）
                        </th>
                        <th data-options="field: 'supplier', width:80, halign: 'center', align: 'center',sortable:true">
                            <spring:message code="sku.supplier"/></th>
                        <%--<th data-options="field: 'availableStock', width:50, halign: 'center', align: 'center',sortable:true">
                            <spring:message code="sku.availableStock"/></th>--%>
                        <th data-options="field: 'defaultStock', width:50, halign: 'center', align: 'center', formatter: function (val, row) {
                            var amount = 0;
                            $.each(row.stockShunt, function (index, item) {
                                if (item.stockId == 1000) {
                                    amount = item.amount;
                                }
                            });
                            return amount;
                        }">总仓
                        </th>
                        <th data-options="field: 'northStock', width:50, halign: 'center', align: 'center', formatter: function (val, row) {
                            var amount = 0;
                            $.each(row.stockShunt, function (index, item) {
                                if (item.stockId == 1062) {
                                    amount = item.amount;
                                }
                            });
                            return amount;
                        }">北部分仓
                        </th>
                        <th data-options="field: 'southStock', width:50, halign: 'center', align: 'center', formatter: function (val, row) {
                            var amount = 0;
                            $.each(row.stockShunt, function (index, item) {
                                if (item.stockId == 1050) {
                                    amount = item.amount;
                                }
                            });
                            return amount;
                        }">南部分仓
                        </th>
                        <th data-options="field: 'container', width:80, halign: 'center', align: 'center',sortable:true,formatter:MXF.cellTooltipFormatter">
                            <spring:message code="sku.container"/></th>
                        <th data-options="field: 'remark', width:100, halign: 'center', align: 'center',formatter:MXF.cellTooltipFormatter">
                            <spring:message code="st.out.remark"/></th>
                    </tr>
                    </thead>
                </table>
                <div id="editOrderGridTB">
                    <div>
                        <a href="#" data-cmd="addToEditOrder" remote="false"
                           class="easyui-linkbutton" iconCls="icon-add" plain="true">
                            加入订单
                        </a>
                        <%--<a href="#" data-cmd="viewComment" remote="false"
                           class="easyui-linkbutton" plain="true">
                            <i class="iconfont" style="font-size: 14px;">&#xe69b;</i>
                            <spring:message code="st.out.view.assess"/>
                        </a>--%>
                    </div>
                    <div class="searchForm">
                        <form>
                            关键字：
                            <input class="easyui-textbox theme-textbox-radius" name="keyword" style="width:200px;">&nbsp;
                            <spring:message code="product.info.type"/>：
                            <input class="easyui-combotree theme-textbox-radius" name="productType"
                                   data-options="url:'/product-type/json',method:'get'" style="width:200px;">
                            <%--<spring:message code="product.info.name"/>：
                            <input class="easyui-textbox theme-textbox-radius" name="name" style="width:200px;">&nbsp;
                            <spring:message code="product.info.type"/>：
                            <input class="easyui-combotree theme-textbox-radius" name="productType"
                                   data-options="url:'/product-type/json',method:'get'" style="width:200px;">--%>
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
        <div data-options="region:'south',border:true,title:'<spring:message code="order.detail.list"/>'"
             style="width: 100%; height: 430px;">
            <div class="tableGroup">
                <table id="editOrderDetailGrid" class="easyui-datagrid"
                       data-options="rownumbers: true,
				            fit: true,
				            method: 'get',
				            pagination: false,
				            pageSize: 10,
				            striped: true,
				            singleSelect: false,
				            toolbar: '#editOrderDetailTB',
				            onLoadSuccess: loadEditOrderDetailSuccess,
				            url: '/order-detail/json?orderId=${bisOrder.id}'">
                    <thead>
                    <tr>
                        <th data-options="field: 'skuMainPic', width:50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                            <spring:message code="order.detail.image"/>
                        </th>
                        <th data-options="field: 'name', width: 120, halign: 'center', align: 'center'"><spring:message
                                code="order.detail.ptname"/></th>
                        <th data-options="field: 'sku.skuCode', width:100, halign: 'center', align: 'center',formatter: function(val, row){ return row.sku.skuCode}">
                            货品编号
                        </th>
                        <th data-options="field: 'productType', width:150, halign: 'center', align: 'center'">
                            类别名称
                        </th>
                        <th data-options="field: 'sku.pack', width:50, halign: 'center', align: 'center', formatter: function(val, row){ return row.sku.pack}">
                            包装形态
                        </th>
                        <th data-options="field: 'skuProperties', width: 200, halign: 'center', align: 'left'">
                            <spring:message code="order.detail.property"/></th>
                        <th data-options="field: 'availableStock', width:100, halign: 'center', align: 'center'">
                            库存数量
                        </th>
                        <th data-options="field: 'amount', width:100, halign: 'center', align: 'center'">
                            需求数量
                        </th>
                        <th data-options="field: 'realAmount', width:100, halign: 'center', align: 'center', formatter: editOrderAmountFormatter">
                            实际数量
                        </th>
                        <th data-options="field: 'inputUser', width:100, halign: 'center', align: 'center'">
                            操作人
                        </th>
                    </tr>
                    </thead>
                </table>
                <div id="editOrderDetailTB">
                    <div style="height: 30px;">
                    </div>
                    <div style="position: absolute; top: 0; left: 0; width: 200px; height: 40px;">
                        <div style="height: 40px; line-height: 40px; font-size: 14px; text-align: left; padding: 0 20px;">
                            <span style="font-weight: 600;"><spring:message code="order.detail.volume.total"/>：</span>
                            <span id="editOrderVolumeTotal"></span>
                        </div>
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

    function skuMainPicFormatter(value) {
        if (value) {
            return '<img onclick="MXF.showImageDialog(this.src)" style="display: block" height="38" width="38" src="' + value + '"/>';
        }
        return '';
    }

    function addToEditOrder() {
        var rows = $('#editOrderGrid').datagrid('getSelections');
        var ids = '';
        for (var i = 0; i < rows.length; i++) {
            ids += ',' + rows[i].id;
        }
        if (ids.length > 1) ids = ids.substring(1);
        if ('' != ids) {
            MXF.ajaxing(true);
            $.post('/order-verify/add', {skuIds: ids, orderId: ${bisOrder.id}}, function (data) {
                MXF.ajaxing(false);
                MXF.ajaxFormDone(data);
                if (data.success) {
                    $('#editOrderGrid').datagrid('reload');
                    $('#orderGrid').datagrid('reload');
                    $('#editOrderGrid').datagrid('uncheckAll');
                } else {
                    MXF.alert(data.message + "，" + data.info, data.success);
                }
            });
        }
    }

    function loadEditOrderDetailSuccess(data) {
        $('#editOrderVolumeTotal').text(data.footer.volumeTotal.toFixed(2));
    }

    function editOrderAmountFormatter(value, row, index) {
        row.index = index;
        var obj = JSON.stringify(row);
        var add = "<a class='btn-d default' onclick='addOrderVerifyDetailAmount(" + obj + ")'><i style='border: 1px solid #ccc;' class='icon iconfont icon-cart-add'></i></a>";
        var remove = "<a class='btn-d default' onclick='removeOrderVerifyDetailAmount(" + obj + ")'><i style='border: 1px solid #ccc;' class='icon iconfont icon-cart-remove'></i></a>";
        var input = "<span style='display: inline-block; width: 30px;'>" + value + "</span>";
        var i = "<input onchange='changeEditOrderDetailAmount(this, " + obj + ")' type='text' value='" + value + "' style='width: 50px; text-align: center; height: 16px; margin-top: -5px; border: 1px solid #ccc;'>";
        return i;
    }


    function changeEditOrderDetailAmount(that, obj) {
        var temp = obj.realAmount;
        var amount = $(that).val();
        //$('.window-mask').show();
        var data = {"id": obj.id, "orderId": obj.orderId, "amount": amount};
        MXF.confirm('确认修改？', function () {
            $.post('/order-detail/changeAmount', data, function (res) {
                if (res.success) {
                    // 更新字段
                    $("#editOrderDetailGrid").datagrid("updateRow", {
                        index: obj.index, //行索引
                        row: {
                            realAmount: amount //行中的某个字段
                        }
                    });
                    // 更新统计
                    editOrderDetailStatistics(res.data);
                } else {
                    $(that).val(temp);
                    MXF.alert(res.message + " " + res.info, res.success);
                }
            });
            //$('.window-mask').hide();
        }, function () {
            $(that).val(temp);
            //$('.window-mask').hide();
        });
    }


    function addOrderVerifyDetailAmount(obj) {
        obj.amount++;
        $.post('/order-detail/addAmount', obj, function (res) {
            if (res.success) {
                // 更新字段
                $("#orderVerifyDetailGrid").datagrid("updateRow", {
                    index: obj.index, //行索引
                    row: {
                        amount: obj.amount //行中的某个字段
                    }
                });
                // 更新统计
                editOrderDetailStatistics(res.data);
            } else {
                MXF.alert(res.message, res.success);
            }
        });
    }

    function removeOrderVerifyDetailAmount(obj) {
        obj.amount--;
        $.post('/order-detail/removeAmount', obj, function (res) {
            if (res.success) {
                // 更新字段
                $("#orderVerifyDetailGrid").datagrid("updateRow", {
                    index: obj.index, //行索引
                    row: {
                        amount: obj.amount //行中的某个字段
                    }
                });
                // 更新统计
                orderVerifyDetailStatistics(res.data);
            } else {
                MXF.alert(res.message, res.success);
            }
        });
    }

    function editOrderDetailStatistics(data) {
        $('#editOrderVolumeTotal').text(data.totalVolume.toFixed(2));
        $('#orderGrid').datagrid('reload');
    }

</script>