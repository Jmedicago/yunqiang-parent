<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="/easyui/my/report.css" rel="stylesheet"/>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<div style="padding: 10px">
    <!-- 表报开始 -->
    <table border="1">
        <tr>
            <th colspan="14">${report.year}年 - 各区域连锁店客商欠款汇总</th>
        </tr>
        <tr>
            <th id="lineTd">
                <span style="float:left;margin-top:20px;padding-left: 2px;padding-bottom: 2px;">地区</span>
                <span style="float:right;margin-top:0px;padding-right: 2px;padding-top: 2px;">日期</span>
            </th>
            <th>1月份</th>
            <th>2月份</th>
            <th>3月份</th>
            <th>4月份</th>
            <th>5月份</th>
            <th>6月份</th>
            <th>7月份</th>
            <th>8月份</th>
            <th>9月份</th>
            <th>10月份</th>
            <th>11月份</th>
            <th>12月份</th>
            <th>Total</th>
        </tr>
        <c:forEach var="stock" items="${report.stockList}" varStatus="st">
            <tr class="${st.count % 2 == 0 ? 'gray' : ''}">
                <td>${stock.name}</td>
                <c:set var="months" value="1,2,3,4,5,6,7,8,9,10,11,12"></c:set>
                <c:forEach var="month" items="${months}">
                    <td>
                        <c:forEach var="arrear" items="${stock.arrearsList}">
                            <c:if test="${arrear.monthId == month}">
                                <fmt:formatNumber value="${arrear.amount}" pattern="#,#00"/>
                            </c:if>
                        </c:forEach>
                    </td>
                </c:forEach>
                <td><fmt:formatNumber value="${stock.total}" pattern="#,#00"/></td>
            </tr>
        </c:forEach>
        <tr style="background: #f0ef36">
            <td>当月各店总欠款</td>
            <td><fmt:formatNumber value="${report.janTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.febTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.marTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.aprTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.mayTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.junTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.julTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.augTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.septTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.octTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.novTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.decTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.allMonthTotal}" pattern="#,#00"/></td>
        </tr>
    </table>
</div>
