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
<div class="tab-wrap" id="printContent" style="width: 600px">
    <div class="print-head">
        <style type="text/css">
            .print-head {
                height: 80px;
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
        <ul>
            <li>
                <span>订单编号：${printOrder.orderSn}</span><br>
                <span>下单店长：${stockFormatter}</span>
            </li>
            <li>
                <span>金额总计：</span>
                <span class="money">${printOrder.totalMoney}</span><br>
                <span>体积总计：${printOrder.totalVolume}</span>
            </li>
            <li>
                <span style="margin-bottom: 10px">下单时间：${confirmTimeFormatter}</span><br>
                <span>&nbsp;</span>
                <a class="easyui-linkbutton button-sm button-default" onclick="print()">
                    打印
                </a>
            </li>
        </ul>
    </div>
    <div class="tableGroup">
        <table id="orderPrintGrid" class="gridtable" title="订单明细">
            <thead>
            <tr>
                <th data-options="field: 'id'">ID</th>
                <th data-options="field: 'skuMainPic', width:50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">
                    图片
                </th>
                <th data-options="field: 'name', width: 120, halign: 'center', align: 'center'">商品名</th>
                <th data-options="field: 'skuProperties', width: 200, halign: 'center', align: 'left'">属性</th>
                <th data-options="field: 'amount', width:100, halign: 'center', align: 'center'">数量</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${printOrder.detailList}">
                <tr>
                    <td>${item.id}</td>
                    <td><img width="50px" height="50px" src="${item.skuMainPic}"></td>
                    <td>${item.name}</td>
                    <td>${item.skuProperties}</td>
                    <td>x ${item.amount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<!-- Jquery Print -->
<script type="text/javascript" src="/easyui/plugin/jquery.print/jquery.print.js"></script>
<!--第三方插件加载结束-->
<script type="text/javascript">
    $(function () {
        // 金额格式化
        OSREC.CurrencyFormatter.formatAll({selector: '.money', currency: 'CNY'});
    });

    function skuMainPicFormatter(value) {
        if (value) {
            return '<img style="display: block" height="38" width="38" src="' + value + '"/>';
        }
        return '';
    }

    function print() {
        $("#printContent").printArea();
    }
</script>
</body>
</html>





