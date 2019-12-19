<%@ page import="java.util.Date" %>
<%@ page import="com.vgit.yunqiang.common.utils.TimeUtils" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="edge"/>
    <link rel="shortcut icon" href="favicon.ico"/>
    <title><spring:message code="common.title"/></title>
</head>
<body>
<div class="tab-wrap" id="printContent" style="width: 1000px">
    <div class="print-head">
        <style type="text/css">
            .print-head {
                /*height: 80px;*/
            }

            .print-head li {
                list-style-type: none;
                display: inline-block;
                margin: 0 10px;
            }

            .print-head li > span {
                line-height: 40px;
                font-size: 14px;
            }

            .print-head .button-default {
                background: #0c80d7;
                border-radius: 3px;
                padding: 5px 5px;
                color: #fff;
            }

            table {
                width: 100%;
            }

            table.gridtable {
                font-family: verdana, arial, sans-serif;
                font-size: 11px;
                color: #333333;
                border-width: 1px;
                border-color: #666666;
                border-collapse: collapse;
            }

            table.gridtable th {
                border-width: 1px;
                padding: 5px;
                border-style: solid;
                border-color: #666666;
                background-color: #dedede;
            }

            table.gridtable td {
                border-width: 1px;
                padding: 5px;
                border-style: solid;
                border-color: #666666;
                background-color: #ffffff;
                text-align: center;
            }
        </style>
        <div>
            <ul>
                <li>
                    <span><%=TimeUtils.dateFormat(new Date(), "yyyy/MM/dd")%></span>
                </li>
                <li>
                    <a class="easyui-linkbutton button-sm button-default" onclick="print()">
                        打印
                    </a>
                </li>
                <li>
                    特殊需求备注：
                    ${printOrder.remark}
                </li>
            </ul>
        </div>
        <div>
            <ul>
                <li>
                    <span>下单区域：${stockFormatter}</span><br>
                    <span>下单时间：${confirmTimeFormatter}</span>
                </li>
                <li>
                    <span>订单号：${printOrder.orderSn}</span><br>
                    <span id="totalMoney" data-money="${printOrder.totalMoney}">总金额：</span>
                </li>
                <li>
                    <span>总件数：${totalCount}</span><br>
                    <span>总体积：${printOrder.totalVolume}</span><br>
                </li>
                <li style="font-size: 14px">
                    ${printOrder.digest}
                </li>
            </ul>
        </div>
    </div>
    <div class="tableGroup">
        <table id="orderPrintGrid" class="gridtable" title="订单明细">
            <thead>
            <tr>
                <th data-options="field: 'id'">ID</th>
                <th data-options="field: 'skuMainPic', width:50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                    图片
                </th>
                <th data-options="field: 'code', width: 120, halign: 'center', align: 'center'">货品编号</th>
                <th data-options="field: 'name', width: 120, halign: 'center', align: 'center'">品名</th>
                <th data-options="field: 'pack', width: 120, halign: 'center', align: 'center'">包装形态</th>
                <th data-options="field: 'skuProperties', width: 200, halign: 'center', align: 'left'">属性</th>
                <th data-options="field: 'availableStock', width:50, halign: 'center', align: 'center'">库存数量</th>
                <th data-options="field: 'amount', width:50, halign: 'center', align: 'center'">需求数量</th>
                <th data-options="field: 'realAmount', width:50, halign: 'center', align: 'center'">实际上货</th>
                <th data-options="field: 'remark', width:200, halign: 'center', align: 'center'">备注</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${printOrder.detailList}">
                <tr>
                    <td style="width: 50px">${item.id}</td>
                    <td style="width:50px"><img width="50px" height="50px" src="${item.skuMainPic}"></td>
                    <td style="width: 100px">${item.sku.skuCode}</td>
                    <td style="width: 150px">${item.name}</td>
                    <td style="width: 50px">${item.sku.pack}</td>
                    <td style="width: 150px">${item.skuProperties}</td>
                    <td style="width: 50px">${item.availableStock}</td>
                    <td style="width: 50px">${item.amount}</td>
                    <td style="width: 50px">${item.realAmount}</td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<!-- Jquery Print -->
<script type="text/javascript" src="/easyui/plugin/jquery.print/jquery.print.js"></script>
<script type="text/javascript" src="/easyui/plugin/currencyFormatter-master/dist/currencyFormatter.js"></script>

<script type="text/javascript" src="/easyui/my/core.js"></script>
<!--第三方插件加载结束-->
<script type="text/javascript">
    $(function () {
        // 金额格式化
        //OSREC.CurrencyFormatter.formatAll({selector: '.money', currency: 'CNY'});
        var money = $('#totalMoney').data('money');
        $('#totalMoney').append(MXF.priceFormatter(money))
    });

    function skuMainPicFormatter(value) {
        if (value) {
            return '<img style="display: block" height="38" width="38" src="' + value + '"/>';
        }
        return '';
    }

    function print() {
        // 修改订单状态为待发货
        $.post('/order-verify/printed', {"orderId": ${printOrder.id}})
        $("#printContent").printArea();
    }
</script>
</body>
</html>





