<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="tab-wrap">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',border:true,title:'<spring:message code="mu.st.out"/>'">
            <div class="tableGroup">
                <table id="stockOutGrid" class="easyui-datagrid" data-options="
                           rownumbers: true,
                           fit: true,
                           method: 'post',
                           pagination: true,
                           pageSize: 50,
                           striped: true,
                           singleSelect: false,
                           toolbar: '#stockOutTB',
                           url: '/sku/es'"> <!-- /sku/json -->
                    <thead>
                    <tr>
                        <th data-options="field: 'id', checkbox:true"></th>
                        <th data-options="field:'path',width:150,halign:'center',align:'center',sortable:true"><spring:message
                                code="product.type.name"/></th>
                        <%--<th data-options="field: 'skuName', width:100, halign: 'center', align: 'center'"><spring:message code="st.out.product"/></th>--%>
                        <th data-options="field: 'skuMainPic', width: 50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                            <spring:message code="sku.skuMainPic"/></th>
                        <th data-options="field:'name',width:150,halign:'center',align:'center',sortable:true"><spring:message
                                code="product.name"/></th>
                        <th data-options="field:'code',width:150,halign:'center',align:'center',sortable:true"><spring:message
                                code="product.code"/></th>
                        <th data-options="field: 'skuProperties', width:150, halign: 'center', align: 'left',formatter:MXF.cellTooltipFormatter"><spring:message code="st.out.property"/></th>
                        <th data-options="field: 'pack', width:100, halign: 'center', align: 'center',sortable:true"><spring:message
                                code="sku.pack"/></th>
                        <th data-options="field: 'volume', width:80, halign: 'center', align: 'center',sortable:true"><spring:message
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
                        <th data-options="field: 'availableStock', width:50, halign: 'center', align: 'center',sortable:true">
                            <spring:message code="sku.availableStock"/></th>
                        <th data-options="field: 'container', width:80, halign: 'center', align: 'center',sortable:true,formatter:MXF.cellTooltipFormatter">
                            <spring:message code="sku.container"/></th>
                        <th data-options="field: 'remark', width:100, halign: 'center', align: 'center',formatter:MXF.cellTooltipFormatter"><spring:message code="st.out.remark"/></th>
                    </tr>
                    </thead>
                </table>
                <div id="stockOutTB">
                    <div>
                        <a href="#" data-cmd="addCart" remote="false"
                           class="easyui-linkbutton" iconCls="icon-add" plain="true">
                            <spring:message code="st.out.add.cart"/>
                        </a>
                        <a href="#" data-cmd="viewComment" remote="false"
                           class="easyui-linkbutton" plain="true">
                            <i class="iconfont" style="font-size: 14px;">&#xe69b;</i>
                            <spring:message code="st.out.view.assess"/>
                        </a>
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
        <!-- east -->
        <div data-options="region:'south',border:true,title:'<spring:message code="st.out.cart"/>'" style="width: 100%;"> <!-- width: 910px; -->
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
                        <th data-options="field: 'sku', width:100, halign: 'center', align: 'center', formatter: function(val) { return val.skuCode }">
                            <spring:message code="product.code"/></th>
                        </th>
                        <th data-options="field: 'name', width:100, halign: 'center', align: 'center'">
                            <spring:message code="product.name"/></th>
                        </th>
                        <th data-options="field: 'sku.availableStock', width:50, halign: 'center', align: 'center', formatter: function(val, row) { console.log(row); return row.sku.availableStock }">
                            <spring:message code="sku.availableStock"/></th>
                        </th>
                        <th data-options="field: 'sku.pack', width:100, halign: 'center', align: 'center', formatter: function(val, row) { return row.sku.pack }">
                            <spring:message code="sku.pack"/>
                        </th>
                        <th data-options="field: 'sku.costPrice', width:100, halign: 'center', align: 'center', formatter: function(val, row) { return MXF.priceFormatter(row.sku.costPrice)}">
                            <spring:message code="sku.costPrice"/></th>
                        </th>
                        <th data-options="field: 'skuMainPic', width:50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                            <spring:message code="sku.skuMainPic"/></th>
                        </th>
                        <th data-options="field: 'skuProperties', width:150, halign: 'center', align: 'left'">
                            <spring:message code="st.out.property"/>
                        </th>
                        <th data-options="field: 'amount', width:100, halign: 'center', align: 'center', formatter: cartAmountFormatter">
                            <spring:message code="st.out.amount"/>
                        </th>
                        <%--<th data-options="field: 'skuMainPic', width:50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                            <spring:message code="st.out.image"/>
                        </th>
                        <th data-options="field: 'name', width:100, halign: 'center', align: 'center'"><spring:message code="st.out.product"/></th>
                        <th data-options="field: 'skuProperties', width:150, halign: 'center', align: 'left'"><spring:message code="st.out.property"/></th>
                        <th data-options="field: 'amount', width:100, halign: 'center', align: 'center', formatter: cartAmountFormatter"><spring:message code="st.out.amount"/></th>--%>
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
                            <spring:message code="st.out.confirm.order"/>
                        </a>
                    </div>
                </div>
            </div>
            <!-- 购物车统计 -->
            <div id="cartStatistics" class="cart statistics">
                <div class="statistics-box">
                    <div class="remark-box">
                        <input class="easyui-textbox theme-textbox-radius" data-options="multiline:true, prompt: '备注：'" style="height:60px; width: 100%;"
                               name="remark" value="${bisCart.remark}">
                    </div>
                    <div class="statistics-group">
                        <label for="selectedGoodsTotalVolume"><spring:message code="st.out.volume.total"/>：</label>
                        <span id="selectedGoodsTotalVolume">N/A</span>
                    </div>
                    <div class="statistics-group">
                        <label for="selectedGoodsTotalPrice"><spring:message code="st.out.total"/>：</label>
                        <span id="selectedGoodsTotalPrice">N/A</span>
                    </div>
                    <div class="statistics-group">
                        <label for="selectedGoodsTotalCount">总件数：</label>
                        <span id="selectedGoodsTotalCount">N/A</span>件
                    </div>
                    <div class="statistics-group">
                        <label for="selectedFactoryShoesTotalCount">工厂鞋：</label>
                        <span id="selectedFactoryShoesTotalCount">N/A</span>件
                    </div>
                    <div class="statistics-group">
                        <label for="selectedTradeShoesTotalCount">贸易鞋：</label>
                        <span id="selectedTradeShoesTotalCount">N/A</span>件
                    </div>
                    <div class="statistics-group">
                        <label for="selectedGMTotalCount">百货类：</label>
                        <span id="selectedGMTotalCount">N/A</span>件
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
        /*width: 520px;*/
        /*width: 910px;*/
        /*width: 100%;*/
        /*height: 50px;*/
        height: 110px;
        background: #fff;
        border-top: 1px solid #eee;
    }

    .cart.statistics .statistics-box .statistics-group {
        display: inline-block;
        height: 25px;
        line-height: 25px;
    }

    .cart.statistics .statistics-box .statistics-group label {
        vertical-align: unset;
        font-size: 14px;
        /*font-weight: 600;*/
        margin-left: 15px;
        color: #0c80d7;
    }

    .cart.statistics .statistics-box .statistics-group span {
        font-size: 14px;
    }

</style>

<script>
    $(function () {
        MXF.getTabContentHeight();

        // 初始化购物车高度
        var cartHeight = $(document.body).height();
        var cartWidth = $(document.body).width();
        var menuWidth = 250;
        var remarkHeight = 60;
        cartHeight = cartHeight - (89 + 89 + remarkHeight);
        cartWidth = cartWidth -menuWidth;
        $(".cart-table").css("height", cartHeight);
        $(".statistics-box").css("width", cartWidth);
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
            return '<img onclick="MXF.showImageDialog(this.src)" style="display: block" height="38" width="38" src="' + value + '"/>';
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
        var ids = '';
        for (var i = 0; i < rows.length; i++) {
            ids += ',' + rows[i].id;
        }
        if (ids.length > 1) ids = ids.substring(1);
        if ('' != ids) {
            MXF.confirm('<spring:message code="message.remove.cart"/>？', function () {
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
            MXF.error('<spring:message code="message.remove.cart"/>！');
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
                } else {
                    MXF.alert(data.info + "，" + data.message, data.success);
                }
            });
        }
    }

    function statistics(data) {
        // 总体积
        $('#selectedGoodsTotalVolume').text((data.selectedGoodsTotalVolume).toFixed(2));
        // 总计
        $('#selectedGoodsTotalPrice').text(MXF.priceFormatter(data.selectedGoodsTotalPrice)); // OSREC.CurrencyFormatter.format((data.selectedGoodsTotalPrice * 0.01).toFixed(2), {currency: 'MZN'})

        $('#selectedGoodsTotalCount').text(data.selectedGoodsTotalCount);
        $('#selectedFactoryShoesTotalCount').text(data.selectedFactoryShoesTotalCount);
        $('#selectedTradeShoesTotalCount').text(data.selectedTradeShoesTotalCount);
        $('#selectedGMTotalCount').text(data.selectedGMTotalCount);

    }

    function submitOrder() {
        var digest = '工厂鞋：' + $('#selectedFactoryShoesTotalCount').text() + '件<br>贸易鞋：' + $('#selectedTradeShoesTotalCount').text() + '件<br>百货类：' + $('#selectedGMTotalCount').text() + '件';
        var data = {
            digest:  digest,
            remark: $('#cartStatistics input[name=remark]').val()
        };
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
    
    function viewComment(val, row) {
        var row = $('#stockOutGrid').datagrid('getSelected');
        if (row == null) {
            MXF.error("<spring:message code="message.select"/>");
            return;
        }
        MXF.openDialog('#viewCommentWindow', '<spring:message code="st.out.view.assess"/>', '/product-comment/show?productId=' + row.productId, function () {
        }, 480, 600);
    }

</script>
