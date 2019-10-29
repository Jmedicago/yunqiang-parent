<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:true,title:'商品出库'">
            <div class="tableGroup">
                <table id="stockOutGrid" class="easyui-datagrid" data-options="
                           rownumbers: true,
                           fit: true,
                           method: 'post',
                           pagination: true,
                           pageSize: 10,
                           striped: true,
                           singleSelect: false,
                           toolbar: '#stockOutTB',
                           url: '/sku/json'">
                    <thead>
                    <tr>
                        <th data-options="field: 'id', checkbox:true"></th>
                        <th data-options="field: 'skuName', width:100, halign: 'center', align: 'center'">商品名</th>
                        <th data-options="field: 'skuMainPic', width: 50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                            <spring:message code="sku.skuMainPic"/></th>
                        <th data-options="field: 'skuProperties', width:150, halign: 'center', align: 'left'">属性</th>
                        <th data-options="field: 'pack', width:100, halign: 'center', align: 'center'"><spring:message
                                code="sku.pack"/></th>
                        <th data-options="field: 'volume', width:80, halign: 'center', align: 'center'"><spring:message
                                code="sku.volume"/>（m<sup>3</sup>）
                        </th>
                        <th data-options="field: 'costPrice', width:80, halign: 'center', align: 'center', formatter: MXF.priceFormatter">
                            <spring:message code="sku.costPrice"/></th>
                        <th data-options="field: 'marketPrice', width:80, halign: 'center', align: 'center', formatter: MXF.priceFormatter">
                            <spring:message code="sku.marketPrice"/></th>
                        <th data-options="field: 'profit', width:50, halign: 'center', align: 'center'"><spring:message
                                code="sku.profit"/>（%）
                        </th>
                        <th data-options="field: 'supplier', width:80, halign: 'center', align: 'center'">
                            <spring:message code="sku.supplier"/></th>
                        <th data-options="field: 'availableStock', width:50, halign: 'center', align: 'center'">
                            <spring:message code="sku.availableStock"/></th>
                        <th data-options="field: 'container', width:80, halign: 'center', align: 'center'">
                            <spring:message code="sku.container"/></th>
                        <th data-options="field: 'remark', width:100, halign: 'center', align: 'center'">备注</th>
                    </tr>
                    </thead>
                </table>
                <div id="stockOutTB">
                    <div>
                        <a href="#" data-cmd="addCart" remote="false"
                           class="easyui-linkbutton" iconCls="icon-add" plain="true">
                            加入购物车
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
        <div data-options="region:'east',border:true,title:'购物车'" style="width: 520px;">
            <div class="tableGroup cart-table">
                <table id="cartGrid" class="easyui-datagrid" data-options="
                            singleSelect: false,
                            fit: true,
                            method: 'get',
							pagination: false,
							striped: false,
							toolbar: '#cartTB',
							showFooter: false,
							checkOnSelect: false,
							onLoadSuccess: function(data) {
							    console.log(data);
							    // 初始化页脚
							    statistics(data.footer);
							    if (data) {
							        $.each(data.rows, function (index, item) {
							            if (item.selected == 1) {
							                $('#cartGrid').datagrid('checkRow', index);
							            }
							        });
							    }
							},
							onCheck: function(index, row) {
                                $.post('/cart/changeSelected', {cartId: row.id, selected: 1}, function(res) {
                                    // 更新统计
                                    statistics(res.data);
                                });
							},
							onUncheck: function(index, row) {
                                $.post('/cart/changeSelected', {cartId: row.id, selected: 0}, function(res) {
                                    // 更新统计
                                    statistics(res.data);
                                });
							},
							url: '/cart/json'">
                    <thead>
                    <tr>
                        <th data-options="field: 'id',checkbox:true"></th>
                        <th data-options="field: 'skuMainPic', width:50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                            图片
                        </th>
                        <th data-options="field: 'name', width:100, halign: 'center', align: 'center'">商品名</th>
                        <th data-options="field: 'skuProperties', width:150, halign: 'center', align: 'left'">属性</th>
                        <th data-options="field: 'amount', width:100, halign: 'center', align: 'center', formatter: cartAmountFormatter">数量</th>
                    </tr>
                    </thead>
                </table>
                <div id="cartTB">
                    <div>
                        <a href="#" data-cmd="removeCart" mustsel data-options="disabled:true" class="easyui-linkbutton"
                           iconCls="icon-remove" plain="true">
                            <spring:message code="common.delete"/>
                        </a>
                        <a href="#" data-cmd="submitOrder" mustsel data-options="disabled:true"
                           class="easyui-linkbutton"
                           iconCls="icon-tip" plain="true">
                            提交订单
                        </a>
                    </div>
                </div>
            </div>
            <!-- 购物车统计 -->
            <div class="cart statistics">
                <div class="statistics-box">
                    <div class="statistics-group">
                        <label for="selectedGoodsTotalVolume">总体积：</label>
                        <span id="selectedGoodsTotalVolume">N/A</span>
                    </div>
                    <div class="statistics-group">
                        <label for="selectedGoodsTotalPrice">总计：</label>
                        <span id="selectedGoodsTotalPrice">N/A</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<style type="text/css">
    .cart.statistics {
        position: absolute;
        bottom: 0;
    }

    .cart.statistics .statistics-box {
        width: 520px;
        height: 50px;
        background: #fff;
        border-top: 1px solid #eee;
    }

    .cart.statistics .statistics-box .statistics-group {
        display: inline-block;
        height: 50px;
        line-height: 50px;
    }

    .cart.statistics .statistics-box .statistics-group label {
        vertical-align: unset;
        font-size: 16px;
        font-weight: 600;
        margin-left: 15px;
    }

    .cart.statistics .statistics-box .statistics-group span {
        font-size: 16px;
    }

</style>

<script>
    $(function () {
        MXF.getTabContentHeight();

        // 初始化购物车高度
        var cartHeight = $(document.body).height();
        cartHeight = cartHeight - (89 + 89);
        $(".cart-table").css("height", cartHeight);
    });

    function productStateFormatter(value) {
        if (0 == value) {
            return '<red><spring:message code="product.state.scarce"/></red>';
        } else if (1 == value) {
            return '<green><spring:message code="product.state.normal"/></green>';
        }
        return '';
    }

    function skuMainPicFormatter(value) {
        if (value) {
            return '<img style="display: block" height="38" width="38" src="' + value + '"/>';
        }
        return '';
    }

    function cartAmountFormatter(value, row, index) {
        row.index = index;
        var obj = JSON.stringify(row);
        var add = "<a class='btn-d default' onclick='addCartAmount(" + obj + ")'><i style='border: 1px solid #ccc;' class='icon iconfont icon-cart-add'></i></a>";
        var remove = "<a class='btn-d default' onclick='removeCartAmount(" + obj + ")'><i style='border: 1px solid #ccc;' class='icon iconfont icon-cart-remove'></i></a>";
        var input = "<span style='display: inline-block; width: 30px;'>" + value + "</span>";
        return remove + input + add;
    }

    function addCartAmount(obj) {
        obj.amount++;
        $.post('/cart/addAmount', {cartId: obj.id, number: obj.amount}, function (res) {
            if (res.success) {
                // 更新字段
                $("#cartGrid").datagrid("updateRow", {
                    index: obj.index, //行索引
                    row: {
                        amount: obj.amount //行中的某个字段
                    }
                });
                // 更新统计
                statistics(res.data);
            } else {
                MXF.alert(res.info, res.success);
            }
        });
    }

    function removeCartAmount(obj) {
        obj.amount--;
        $.post('/cart/removeAmount', {cartId: obj.id, number: obj.amount}, function (res) {
            if (res.success) {
                // 更新字段
                $("#cartGrid").datagrid("updateRow", {
                    index: obj.index, //行索引
                    row: {
                        amount: obj.amount //行中的某个字段
                    }
                });
                // 更新统计
                statistics(res.data);
            } else {
                MXF.alert(res.info, res.success);
            }
        });
    }

    function removeCart() {
        var rows = $('#cartGrid').datagrid('getSelections');
        console.log('移除购物车', rows);
        var ids = '';
        for (var i = 0; i < rows.length; i++) {
            ids += ',' + rows[i].id;
        }
        if (ids.length > 1) ids = ids.substring(1);
        if ('' != ids) {
            MXF.confirm('是否从购物车中移除所勾选的商品？', function () {
                MXF.ajaxing(true);
                $.post('/cart/remove', {cartIds: ids}, function (data) {
                    MXF.ajaxing(false);
                    MXF.ajaxFormDone(data);
                    if (data.success) {
                        $('#cartGrid').datagrid('reload');
                    }
                });
            });
        } else {
            MXF.error('请至少勾选一款商品！');
        }
    }

    function addCart() {
        var rows = $('#stockOutGrid').datagrid('getSelections');
        var ids = '';
        for (var i = 0; i < rows.length; i++) {
            ids += ',' + rows[i].id;
        }
        if (ids.length > 1) ids = ids.substring(1);
        if ('' != ids) {
            MXF.ajaxing(true);
            $.post('/cart/add', {skuIds: ids}, function (data) {
                MXF.ajaxing(false);
                MXF.ajaxFormDone(data);
                if (data.success) {
                    $('#cartGrid').datagrid('reload');
                }
            });
        }
    }

    function statistics(data) {
        // 总体积
        $('#selectedGoodsTotalVolume').text((data.selectedGoodsTotalVolume).toFixed(2));
        // 总计
        $('#selectedGoodsTotalPrice').text(MXF.priceFormatter(data.selectedGoodsTotalPrice)); // OSREC.CurrencyFormatter.format((data.selectedGoodsTotalPrice * 0.01).toFixed(2), {currency: 'MZN'})
    }

    function submitOrder() {
        var data = {};
        MXF.ajaxing(true);
        $.post('/order/submit', data, function (data) {
            if (data.success) {
                MXF.ajaxing(false);
                MXF.ajaxFormDone(data);
                if (data.success) {
                    $('#cartGrid').datagrid('reload');
                }
            } else {
                MXF.alert(data.message, data.success);
            }
        });
    }

</script>
