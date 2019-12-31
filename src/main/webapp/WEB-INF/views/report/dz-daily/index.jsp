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
            <td colspan="4"></td>
            <td colspan="5"></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
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
                            <li><fmt:formatNumber value="${dyDetail.income}" pattern="#,#00.00"/></li>
                        </c:forEach>
                    </ul>
                </td>
                <td><fmt:formatNumber value="${detail.incomeSubTotal}" pattern="#,#00.00"/></td>
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
                                <li><fmt:formatNumber value="${expend.amount}" pattern="#,#00.00"/></li>
                            </c:forEach>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <fmt:formatNumber value="${detail.expendSubTotal}" pattern="#,#00.00"/>
                </td>
            </tr>
        </c:forEach>
        <!-- 测试数据 -->
        <%--<tr>
            <td>2018/7/1</td>
            <td>
                <ul>
                    <li>A1</li>
                    <li>A2</li>
                    <li>B1</li>
                    <li>B2</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>120,000</li>
                    <li>250,000</li>
                    <li>100,000</li>
                    <li>150,000</li>
                </ul>
            </td>
            <td>620,000</td>
            <td>
                <ul>
                    <li>A1</li>
                    <li>A2</li>
                    <li>B1</li>
                    <li>B2</li>
                    <li>Beira店长</li>
                    <li>Beira店长</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>A</li>
                    <li>G</li>
                    <li>G</li>
                    <li>C</li>
                    <li>I</li>
                    <li>C</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>买早餐</li>
                    <li>付床垫货款</li>
                    <li>做发票</li>
                    <li>B1客户拿货存款BCI银行</li>
                    <li>中国人换钱</li>
                    <li>现金存入SD银行</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>80</li>
                    <li>37,710</li>
                    <li>3,700</li>
                    <li>28,000</li>
                    <li>10,000</li>
                    <li>800,000</li>
                </ul>
            </td>
            <td>879,490</td>
            <td>770,000</td>
            <td>20,200</td>
            <td>12,500</td>
            <td>689,490</td>
        </tr>
        <tr class="gray">
            <td>2018/7/1</td>
            <td>
                <ul>
                    <li>A1</li>
                    <li>A2</li>
                    <li>B1</li>
                    <li>B2</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>120,000</li>
                    <li>250,000</li>
                    <li>100,000</li>
                    <li>150,000</li>
                </ul>
            </td>
            <td>620,000</td>
            <td>
                <ul>
                    <li>A1</li>
                    <li>A2</li>
                    <li>B1</li>
                    <li>B2</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>A</li>
                    <li>G</li>
                    <li>G</li>
                    <li>C</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>买早餐</li>
                    <li>付床垫货款</li>
                    <li>做发票</li>
                    <li>B1客户拿货存款BCI银行</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>80</li>
                    <li>37,710</li>
                    <li>3,700</li>
                    <li>28,000</li>
                </ul>
            </td>
            <td>879,490</td>
            <td>770,000</td>
            <td>20,200</td>
            <td>12,500</td>
            <td>689,490</td>
        </tr>
        <tr>
            <td>2018/7/1</td>
            <td>
                <ul>
                    <li>A1</li>
                    <li>A2</li>
                    <li>B1</li>
                    <li>B2</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>120,000</li>
                    <li>250,000</li>
                    <li>100,000</li>
                    <li>150,000</li>
                </ul>
            </td>
            <td>620,000</td>
            <td>
                <ul>
                    <li>A1</li>
                    <li>A2</li>
                    <li>B1</li>
                    <li>B2</li>
                    <li>Beira店长</li>
                    <li>Beira店长</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>A</li>
                    <li>G</li>
                    <li>G</li>
                    <li>C</li>
                    <li>I</li>
                    <li>C</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>买早餐</li>
                    <li>付床垫货款</li>
                    <li>做发票</li>
                    <li>B1客户拿货存款BCI银行</li>
                    <li>中国人换钱</li>
                    <li>现金存入SD银行</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>80</li>
                    <li>37,710</li>
                    <li>3,700</li>
                    <li>28,000</li>
                    <li>10,000</li>
                    <li>800,000</li>
                </ul>
            </td>
            <td>879,490</td>
            <td>770,000</td>
            <td>20,200</td>
            <td>12,500</td>
            <td>689,490</td>
        </tr>--%>
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
