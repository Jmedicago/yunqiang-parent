<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="/easyui/my/report.css" rel="stylesheet"/>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<div style="padding: 10px">
    <c:set var="expendItems" value="A,B,D,E,F,G,H,I,J"></c:set>
    <!-- 表报开始 -->
    <table border="1">
        <tr>
            <th colspan="13">${report.year}年 ${report.stockName}各季度支出统计表</th>
        </tr>
        <tr>
            <th rowspan="2">季度</th>
            <th rowspan="2" id="lineTd">
                <span style="float:left;margin-top:20px;padding-left: 2px;padding-bottom: 2px;">店名</span>
                <span style="float:right;margin-top:0px;padding-right: 2px;padding-top: 2px;">支出</span>
            </th>
            <th colspan="2">季度总支出</th>
            <th>生活费</th>
            <th>黑人工资</th>
            <th>运费</th>
            <th>政府部门处理费用</th>
            <th>税务、会计薪资</th>
            <th>店名运营费用</th>
            <th>废料回收费用</th>
            <th>签证、租金</th>
            <th>代付其它区域费用</th>
        </tr>
        <tr>
            <th>区域</th>
            <th>店</th>
            <th>A</th>
            <th>B</th>
            <th>D</th>
            <th>E</th>
            <th>F</th>
            <th>G</th>
            <th>H</th>
            <th>I</th>
            <th>J</th>
        </tr>
        <c:forEach var="quar" items="${report.quarterlyList}">
            <tr>
                <td>${report.year}-${quar.name}</td>
                <td>
                    <ul>
                        <c:forEach var="stock" items="${quar.stockList}">
                            <li>${stock.name}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <fmt:formatNumber value="${quar.regionStockExpendsTotal}" pattern="#,#00"/>
                </td>
                <td>
                    <ul>
                        <c:forEach var="stock" items="${quar.stockList}">
                            <li><fmt:formatNumber value="${stock.stockExpendsTotal}" pattern="#,#00"/></li>
                        </c:forEach>
                    </ul>
                </td>
                <c:forEach var="expendItem" items="${expendItems}">
                    <td>
                        <ul>
                            <c:forEach var="stock" items="${quar.stockList}">
                                <li>
                                    <c:forEach var="expend" items="${stock.expendsList}">
                                        <c:if test="${expend.expendItem.category == expendItem}">
                                            <fmt:formatNumber value="${expend.amount}" pattern="#,#00"/>
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
            <td colspan="2">Total</td>
            <td colspan="2"><fmt:formatNumber value="${report.expendTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.expendA}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.expendB}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.expendD}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.expendE}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.expendF}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.expendG}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.expendH}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.expendI}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.expendJ}" pattern="#,#00"/></td>
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