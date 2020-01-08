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
            <th style="background: #fff;" colspan="15">${report.year}年-${report.quarterly} 云强公司各区域连锁店盘点总表</th>
        </tr>
        <tr>
            <th style="background: #000; color: #fff;" colspan="2" rowspan="2">云强贸易公司 ${report.year}-${report.quarterly}
                盘点报告
            </th>
            <th style="background: #000; color: #fff;">连锁店保险柜现金</th>
            <th style="background: #000; color: #fff;">商客总欠款</th>
            <th style="background: #000; color: #fff;">柜台零钱</th>
            <th style="background: #000; color: #fff;">库存商品总货值</th>
            <th style="background: #eaf1dd; color: #cd4a44;">${report.quarterly}-盘点总值</th>
            <th style="background: #eaf1dd; color: #cd4a44;">${report.quarterly}-总销售额</th>
            <th style="background: #eaf1dd; color: #cd4a44;">${report.quarterly}-上货值</th>
            <th style="background: #eaf1dd; color: #cd4a44;">${report.quarterly}-盘点总值</th>
            <th style="background: #eaf1dd; color: #cd4a44;">${report.quarterly}-公司毛利润</th>
        </tr>
        <tr>
            <td style="background: #000; color: #fff;"><fmt:formatNumber value="${report.allSafeTotal}"
                                                                         pattern="#,#00"/></td>
            <td style="background: #000; color: #fff;"><fmt:formatNumber value="${report.allArrearsTotal}"
                                                                         pattern="#,#00"/></td>
            <td style="background: #000; color: #fff;"><fmt:formatNumber value="${report.allChangeTotal}"
                                                                         pattern="#,#00"/></td>
            <td style="background: #000; color: #fff;"><fmt:formatNumber value="${report.allPurchTotal}"
                                                                         pattern="#,#00"/></td>
            <td style="background: #eaf1dd; color: #cd4a44;"><fmt:formatNumber value="${report.allInventoryTotal}"
                                                                               pattern="#,#00"/></td>
            <td style="background: #eaf1dd; color: #cd4a44;"><fmt:formatNumber value="${report.sumAllOutTotal}"
                                                                               pattern="#,#00"/></td>
            <td style="background: #eaf1dd; color: #cd4a44;"><fmt:formatNumber value="${report.sumAllOrderTotal}"
                                                                               pattern="#,#00"/></td>
            <td style="background: #eaf1dd; color: #cd4a44;"><fmt:formatNumber
                    value="${report.sumAllBeforeInventoryTotal}" pattern="#,#00"/></td>
            <td style="background: #eaf1dd; color: #cd4a44;"><fmt:formatNumber value="${report.sumAllPl}"
                                                                               pattern="#,#00"/></td>
        </tr>
        <tr>
            <th style="background: #d8d8d8">区域名称</th>
            <th style="background: #d8d8d8">区域连锁店</th>
            <th style="background: #d8d8d8">保险柜现金余额</th>
            <th style="background: #d8d8d8">商客欠款</th>
            <th style="background: #d8d8d8">柜台零钱</th>
            <th style="background: #d8d8d8">库存商品总货值</th>
            <th style="background: #d8d8d8">${report.quarterly}-盘点总值</th>
            <th style="background: #d8d8d8">${report.quarterly}-销售额</th>
            <th style="background: #d8d8d8">${report.quarterly}-上货值</th>
            <th style="background: #d8d8d8">${report.quarterly}-盘点总值</th>
            <th style="background: #d8d8d8">${report.quarterly}-盈亏情况</th>
        </tr>
        <c:forEach var="dzQInventory" items="${report.dzQInventoryList}">
            <tr>
                <td>${dzQInventory.stockName}</td>
                <td>
                    <ul>
                        <c:forEach var="dyInventory" items="${dzQInventory.dyInventoryList}" varStatus="st">
                            <li style="${st.count % 2 == 0 ? 'background: #dbeef3;' : ''}">${dyInventory.stockName}</li>
                        </c:forEach>
                        <li style="background: #ff0000; color: #fff;">总计</li>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyInventory" items="${dzQInventory.dyInventoryList}" varStatus="st">
                            <li style="${st.count % 2 == 0 ? 'background: #dbeef3;' : ''}"><fmt:formatNumber value="${dyInventory.safe}" pattern="#,#00"/></li>
                        </c:forEach>
                        <li style="background: #ffff00"><fmt:formatNumber value="${dzQInventory.safeTotal}" pattern="#,#00"/></li>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyInventory" items="${dzQInventory.dyInventoryList}" varStatus="st">
                            <li style="${st.count % 2 == 0 ? 'background: #dbeef3;' : ''}"><fmt:formatNumber value="${dyInventory.arrears}" pattern="#,#00"/></li>
                        </c:forEach>
                        <li style="background: #ff0000; color: #fff;"><fmt:formatNumber value="${dzQInventory.arrearsTotal}" pattern="#,#00"/></li>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyInventory" items="${dzQInventory.dyInventoryList}" varStatus="st">
                            <li style="${st.count % 2 == 0 ? 'background: #dbeef3;' : ''}"><fmt:formatNumber value="${dyInventory.change}" pattern="#,#00"/></li>
                        </c:forEach>
                        <li style="background: #ff0000; color: #fff;"><fmt:formatNumber value="${dzQInventory.changeTotal}" pattern="#,#00"/></li>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyInventory" items="${dzQInventory.dyInventoryList}" varStatus="st">
                            <li style="${st.count % 2 == 0 ? 'background: #dbeef3;' : ''}"><fmt:formatNumber value="${dyInventory.purch}" pattern="#,#00"/></li>
                        </c:forEach>
                        <li style="background: #ff0000; color: #fff;"><fmt:formatNumber value="${dzQInventory.purchTotal}" pattern="#,#00"/></li>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyInventory" items="${dzQInventory.dyInventoryList}" varStatus="st">
                            <li style="${st.count % 2 == 0 ? 'background: #dbeef3;' : ''}"><fmt:formatNumber value="${dyInventory.inventoryTotal}" pattern="#,#00"/></li>
                        </c:forEach>
                        <li style="background: #ff0000; color: #fff;"><fmt:formatNumber value="${dzQInventory.inventoryTotal}" pattern="#,#00"/></li>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyInventory" items="${dzQInventory.dyInventoryList}" varStatus="st">
                            <li style="${st.count % 2 == 0 ? 'background: #dbeef3;' : ''}"><fmt:formatNumber value="${dyInventory.outTotal}" pattern="#,#00"/></li>
                        </c:forEach>
                        <li style="background: #ff0000; color: #fff;"><fmt:formatNumber value="${dzQInventory.allOutTotal}" pattern="#,#00"/></li>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyInventory" items="${dzQInventory.dyInventoryList}" varStatus="st">
                            <li style="${st.count % 2 == 0 ? 'background: #dbeef3;' : ''}"><fmt:formatNumber value="${dyInventory.orderTotal}" pattern="#,#00"/></li>
                        </c:forEach>
                        <li style="background: #ff0000; color: #fff;"><fmt:formatNumber value="${dzQInventory.allOrderTotal}" pattern="#,#00"/></li>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyInventory" items="${dzQInventory.dyInventoryList}" varStatus="st">
                            <li style="${st.count % 2 == 0 ? 'background: #dbeef3;' : ''}"><fmt:formatNumber value="${dyInventory.beforeInventoryTotal}" pattern="#,#00"/></li>
                        </c:forEach>
                        <li style="background: #ff0000; color: #fff;"><fmt:formatNumber value="${dzQInventory.allBeforeInventoryTotal}" pattern="#,#00"/></li>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="dyInventory" items="${dzQInventory.dyInventoryList}" varStatus="st">
                            <li style="${st.count % 2 == 0 ? 'background: #dbeef3;' : ''}"><fmt:formatNumber value="${dyInventory.pl}" pattern="#,#00"/></li>
                        </c:forEach>
                        <li style="background: #ff0000; color: #fff;"><fmt:formatNumber value="${dzQInventory.allPl}" pattern="#,#00"/></li>
                    </ul>
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

