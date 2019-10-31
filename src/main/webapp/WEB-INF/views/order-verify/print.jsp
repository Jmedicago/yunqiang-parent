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
    <link href="/easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
    <link href="/easyui/themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">
    <link href="/easyui/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
    <link href="/easyui/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
    <link href="/easyui/themes/insdep/icon.css" rel="stylesheet" type="text/css">
    <link href="/easyui/themes/insdep/iconfont/iconfont.css" rel="stylesheet" type="text/css">
    <link href="/easyui/plugin/kindeditor-4.1.10/themes/default/default.css" rel="stylesheet" type="text/css">
    <link href="/easyui/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/easyui/my/core.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/themes/insdep/jquery.insdep-extend.min.js"></script>
    <script type="text/javascript" src="/easyui/plugin/currencyFormatter-master/dist/currencyFormatter.js"></script>

    <!-- 后台首页js -->
    <script type="text/javascript" src="/easyui/app.js"></script>
    <script type="text/javascript" src="/easyui/my/core.js"></script>

    <!--第三方插件加载-->
    <script src="/easyui/plugin/justgage-1.2.2/raphael-2.1.4.min.js"></script>
    <script src="/easyui/plugin/justgage-1.2.2/justgage.js"></script>
    
    <style type="text/css">
    .print-head {
        height: 80px;
    }
    .print-head li {
        display: inline-block;
        margin: 0 10px;
    }
    .print-head li > span {
        line-height: 40px;
        font-size: 14px;
    }
</style>
</head>

<body id="printContent">
<div class="tab-wrap">
    <div class="print-head">
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
        <table id="orderPrintGrid" class="easyui-datagrid" title="订单明细">
            <thead>
            <tr>
                <th data-options="field: 'id'">ID</th>
                <th data-options="field: 'skuMainPic', width:50, halign: 'center', align: 'center', formatter: skuMainPicFormatter">图片</th>
                <th data-options="field: 'name', width: 120, halign: 'center', align: 'center'">商品名</th>
                <th data-options="field: 'skuProperties', width: 200, halign: 'center', align: 'left'">属性</th>
                <th data-options="field: 'amount', width:100, halign: 'center', align: 'center'">数量</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${printOrder.detailList}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.skuMainPic}</td>
                        <td>${item.name}</td>
                        <td>${item.skuProperties}</td>
                        <td>x ${item.amount}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="/easyui/plugin/Highcharts-5.0.0/js/highcharts.js"></script>
<script type="text/javascript" src="/easyui/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="/easyui/plugin/ueditor/ueditor.all.min.js"></script>
<link href="/easyui/plugin/cropper-2.3.4/dist/cropper.min.css" rel="stylesheet" type="text/css">
<script src="/easyui/plugin/cropper-2.3.4/dist/cropper.min.js"></script>
<!-- 七牛云上传 -->
<script type="text/javascript" src="http://cdn.staticfile.org/plupload/2.1.1/plupload.full.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/plupload/2.1.1/i18n/zh_CN.js"></script>
<script type="text/javascript" src="/easyui/plugin/qiniu/qiniu.js"></script>
<script type="text/javascript" src="/easyui/plugin/qiniu/qiniu.uploader.js"></script>
<link rel="stylesheet" href="/easyui/plugin/qiniu/qiniu.uploader.css" type="text/css"/>
<!-- Kindeditor -->
<script type="text/javascript" charset="utf-8" src="/easyui/plugin/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/easyui/plugin/kindeditor-4.1.10/lang/zh_CN.js"></script>
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





