<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="/easyui/my/report.css" rel="stylesheet"/>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<div style="padding: 10px; width: 4500px;">
    <c:set var="days"
           value="1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31"></c:set>
    <!-- 表报开始 -->
    <table border="1">
        <tr>
            <th colspan="35">${report.year}年${report.month}月 - 每日区域销售额汇总</th>
        </tr>
        <tr>
            <th>区域名称</th>
            <th style="width: 200px">店名</th>
            <th>区域月销售额</th>
            <th>店月销售额</th>
            <c:forEach var="day" items="${days}">
                <th>${day}日</th>
            </c:forEach>
        </tr>
        <c:forEach var="stockRegion" items="${report.stockRegionList}" varStatus="st">
            <tr class="${st.count % 2 == 0 ? 'gray' : ''}">
                <td>${stockRegion.name}</td>
                <td>
                    <ul>
                        <c:forEach var="stock" items="${stockRegion.stockList}">
                            <li>${stock.name}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td><fmt:formatNumber value="${stockRegion.stockRegionTotal}" pattern="#,#00"/></td>
                <td>
                    <ul>
                        <c:forEach var="stock" items="${stockRegion.stockList}">
                            <li>${stock.stockTotal}</li>
                        </c:forEach>
                    </ul>
                </td>
                <c:forEach var="day" items="${days}">
                    <td>
                        <ul>
                            <c:forEach var="stock" items="${stockRegion.stockList}">
                                <li>
                                    <c:forEach var="detail" items="${stock.details}">
                                        <c:if test="${detail.dayId == day}">
                                            <fmt:formatNumber value="${detail.amount}" pattern="#,#00"/>
                                        </c:if>
                                    </c:forEach>
                                </li>
                            </c:forEach>
                        </ul>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
        <tr style="background: #f0ef36">
            <td colspan="2">云强公司月销售额</td>
            <td colspan="2"><fmt:formatNumber value="${report.allSalesTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.oneDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.twoDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.threeDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.fourDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.fiveDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.sixDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.sevenDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.eightDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.nineDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.tenDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.elevenDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.twelveDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.thirteenDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.fourteenDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.fifteenDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.sixteenDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.seventeenDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.eighteenDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.nineteenDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.twentyDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.twentyOneDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.twentyTwoDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.twentyThreeDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.twentyFourDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.twentyFiveDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.twentySixDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.twentySevenDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.twentyEightDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.twentyNineDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.thirtyDay}" pattern="#,#00"/></td></td>
            <td><fmt:formatNumber value="${report.thirtyOneDay}" pattern="#,#00"/></td></td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    $("table tr td ul").each(function (index, value) {
        var height = $(value).height();
        var tdStyle = {
            "height": height,
            "position": "relative"
        }
        $(value).parent().css(tdStyle);
        var ulStyle = {
            "position": "absolute",
            "top": 0,
            "left": 0,
            "width": "100%",
            /*"border-bottom": "1px solid gray"*/
        }
        $(value).css(ulStyle);
    });
</script>
