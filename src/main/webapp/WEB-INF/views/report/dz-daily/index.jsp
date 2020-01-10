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
            <th style="background: #fff;" colspan="13">每日资金进出帐明细 - ${report.stockName}区域店长日报</th>
        </tr>
        <tr>
            <th style="background: #000; color: #fff;" colspan="4">进</th>
            <th style="background: #000; color: #fff;" colspan="5">出</th>
            <th style="background: #000; color: #fff;">存</th>
            <th style="background: #000; color: #fff;">本季度累计上货</th>
            <th style="background: #000; color: #fff;">客商总额</th>
            <th style="background: #000; color: #fff;">本季度累计销售额</th>
        </tr>
        <tr>
            <td style="background: #000; color: #FF0000;" colspan="4"><fmt:formatNumber value="${report.incomeTotal}" pattern="#,#00"/></td>
            <td style="background: #000; color: #FF0000;" colspan="5"><fmt:formatNumber value="${report.expendTotal}" pattern="#,#00"/></td>
            <td style="background: #000; color: #FF0000;"><fmt:formatNumber value="${report.depositTotal}" pattern="#,#00"/></td>
            <td style="background: #000; color: #FF0000;"><fmt:formatNumber value="${report.purchTotal}" pattern="#,#00"/></td>
            <td style="background: #000; color: #FF0000;"><fmt:formatNumber value="${report.arrearsTotal}" pattern="#,#00"/></td>
            <td style="background: #000; color: #FF0000;"><fmt:formatNumber value="${report.salesTotal}" pattern="#,#00"/></td>
        </tr>
        <tr>
            <th style="background: #ddd9c3">日期</th>
            <th style="background: #ddd9c3">店名</th>
            <th style="background: #ddd9c3">现金入账</th>
            <th style="background: #ddd9c3">日总额</th>
            <th style="background: #ddd9c3">支出店名</th>
            <th style="background: #ddd9c3">支出类别</th>
            <th style="background: #ddd9c3" style="width: 300px">支出项目</th>
            <th style="background: #ddd9c3">金额</th>
            <th style="background: #ddd9c3">当日总支出</th>
            <th style="background: #ddd9c3">保险柜现金</th>
            <th style="background: #ddd9c3; color: #0082c1;">上货金额</th>
            <th style="background: #ddd9c3; color: #002060;">最新商客欠款</th>
            <th style="background: #ddd9c3; color: #c00000;">每日销售额</th>
        </tr>
        <tr style="background: rgb(182, 211, 232)">
            <td colspan="9">Q${report.quarterly - 1 > 0 ? report.quarterly - 1 : 4}（上季度）盘点帐目入帐到Q${report.quarterly}（本季度）作为进帐 →《 保险柜现金（包含家里固定存放的现金 》</td>
            <td><fmt:formatNumber value="${report.beforeQuarterlyDeposit}" pattern="#,#00"/></td>
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
                            <c:forEach var="expend" items="${dyDetail.finDailyExpendList}"> <!-- or expend.finExpendItem.category == 'I' -->
                                <li style="background: ${expend.finExpendItem.category == 'C' ? '#F0EF36' : expend.finExpendItem.category == 'C1' ? 'red' : 0 }">${expend.stockName}</li>
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyDetail" items="${detail.dyDailies}">
                            <c:forEach var="expend" items="${dyDetail.finDailyExpendList}">
                                <li style="background: ${expend.finExpendItem.category == 'C' ? '#F0EF36' : expend.finExpendItem.category == 'C1' ? 'red' : 0}">${expend.finExpendItem.category}</li>
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyDetail" items="${detail.dyDailies}">
                            <c:forEach var="expend" items="${dyDetail.finDailyExpendList}">
                                <li style="background: ${expend.finExpendItem.category == 'C' ? '#F0EF36' : expend.finExpendItem.category == 'C1' ? 'red' : 0}">${expend.detail}</li>
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyDetail" items="${detail.dyDailies}">
                            <c:forEach var="expend" items="${dyDetail.finDailyExpendList}">
                                <li style="background: ${expend.finExpendItem.category == 'C' ? '#F0EF36' : expend.finExpendItem.category == 'C1' ? 'red' : 0}"><fmt:formatNumber value="${expend.amount}" pattern="#,#00"/></li>
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
                <td style="color: #0082c1;">
                    <fmt:formatNumber value="${detail.purch}" pattern="#,#00"/>
                </td>
                <td style="color: #002060;">
                    <fmt:formatNumber value="${detail.arrears}" pattern="#,#00"/>
                </td>
                <td style="color: #c00000;">
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
