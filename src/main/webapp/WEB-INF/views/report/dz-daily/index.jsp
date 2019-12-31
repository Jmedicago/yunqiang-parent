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
            <th colspan="13">每日资金进出帐明细 - ${report.stockName}区域店长日报</th>
        </tr>
        <tr>
            <th colspan="4">进</th>
            <th colspan="5">出</th>
            <th>存</th>
            <th>本季度累计上货</th>
            <th>客商总额</th>
            <th>本季度累计销售额</th>
        </tr>
        <tr>
            <td colspan="4"><fmt:formatNumber value="${report.incomeTotal}" pattern="#,#00"/></td>
            <td colspan="5"><fmt:formatNumber value="${report.expendTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.depositTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.purchTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.arrearsTotal}" pattern="#,#00"/></td>
            <td><fmt:formatNumber value="${report.salesTotal}" pattern="#,#00"/></td>
        </tr>
        <tr>
            <th>日期</th>
            <th>店名</th>
            <th>现金入账</th>
            <th>日总额</th>
            <th>支出店名</th>
            <th>支出类别</th>
            <th style="width: 300px">支出项目</th>
            <th>金额</th>
            <th>当日总支出</th>
            <th>保险柜现金</th>
            <th>上货金额</th>
            <th>最新商客欠款</th>
            <th>每日销售额</th>
        </tr>
        <tr style="background: rgb(182, 211, 232)">
            <td colspan="9">Q2（上季度）盘点帐目入帐到Q3（本季度）作为进帐 →《 保险柜现金（包含家里固定存放的现金 》</td>
            <td>150000</td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <c:forEach var="detail" items="${report.details}" varStatus="st">
            <tr class="${st.count % 2 == 0 ? 'gray' : ''}">
                <td>${detail.date}</td>
                <td>
                    <ul>
                        <c:forEach var="dyDetail" items="${detail.dyDailies}">
                            <li>${dyDetail.bisStock.name}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyDetail" items="${detail.dyDailies}">
                            <li><fmt:formatNumber value="${dyDetail.income}" pattern="#,#00"/></li>
                        </c:forEach>
                    </ul>
                </td>
                <td><fmt:formatNumber value="${detail.incomeSubTotal}" pattern="#,#00"/></td>
                <td>
                    <ul>
                        <c:forEach var="dyDetail" items="${detail.dyDailies}">
                            <c:forEach var="expend" items="${dyDetail.finDailyExpendList}">
                                <li>${expend.stockName}</li>
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyDetail" items="${detail.dyDailies}">
                            <c:forEach var="expend" items="${dyDetail.finDailyExpendList}">
                                <li>${expend.finExpendItem.category}</li>
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyDetail" items="${detail.dyDailies}">
                            <c:forEach var="expend" items="${dyDetail.finDailyExpendList}">
                                <li>${expend.detail}</li>
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyDetail" items="${detail.dyDailies}">
                            <c:forEach var="expend" items="${dyDetail.finDailyExpendList}">
                                <li><fmt:formatNumber value="${expend.amount}" pattern="#,#00"/></li>
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <fmt:formatNumber value="${detail.expendSubTotal}" pattern="#,#00"/>
                </td>
                <td>
                    <fmt:formatNumber value="${detail.deposit}" pattern="#,#00"/>
                </td>
                <td>
                    <fmt:formatNumber value="${detail.purch}" pattern="#,#00"/>
                </td>
                <td>
                    <fmt:formatNumber value="${detail.arrears}" pattern="#,#00"/>
                </td>
                <td>
                    <fmt:formatNumber value="${detail.sales}" pattern="#,#00"/>
                </td>
            </tr>
        </c:forEach>
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
