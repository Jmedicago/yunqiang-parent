<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="/easyui/my/report.css" rel="stylesheet"/>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<div style="padding: 10px">
    <c:set var="months" value="1,2,3,4,5,6,7,8,9,10,11,12"></c:set>
    <!-- 表报开始 -->
    <table border="1">
        <tr>
            <th colspan="35">${report.year}年 - 每月区域销售额汇总</th>
        </tr>
        <tr>
            <th>区域名称</th>
            <th>店名</th>
            <th>区域年销售额</th>
            <th>店年销售额</th>
            <c:forEach var="month" items="${months}">
                <th>${month}月</th>
            </c:forEach>
        </tr>
    </table>
</div>
