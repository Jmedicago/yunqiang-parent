<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="/easyui/my/report.css" rel="stylesheet"/>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<div style="padding: 10px">
    <c:set var="BQ" value="${fn:substring(report.quarterly, 1, 2)}"/>
    <!-- 表报开始 -->
    <table border="1">
        <tr>
            <th style="background: #f2f2f2" colspan="9">${report.year}年-${report.quarterly} ${report.stockName}盘点总表《区域店长》</th>
        </tr>
        <tr>
            <th style="background: #d8d8d8">直营店</th>
            <th style="background: #dbeef3" colspan="3">进</th>
            <th style="background: #eaf1dd;" colspan="2">出</th>
            <th style="background: #fde9d9" colspan="3">存</th>
        </tr>
        <c:forEach var="dyQuarterly" items="${report.dzInventory.dyInventoryList}">
            <tr style="background: #d8d8d8">
                <td rowspan="7">${dyQuarterly.stockName}</td>
            </tr>
            <tr>
                <td style="background: #dbeef3" rowspan="4">Q${BQ - 1 > 0 ? BQ - 1 : 4}（上季度）盘点货值</td>
                <td style="background: #dbeef3">保险柜现金</td>
                <td style="background: #dbeef3"><fmt:formatNumber value="${dyQuarterly.beforeSafe}"
                                                                  pattern="#,#00"/></td>
                <td style="background: #eaf1dd;">${report.quarterly}支出总额</td>
                <td style="background: #eaf1dd;"><fmt:formatNumber value="${dyQuarterly.expend}" pattern="#,#00"/></td>
                <td style="background: #fde9d9" rowspan="5">${report.quarterly}（本季度）实点货值</td>
                <td style="background: #fde9d9">保险柜现金</td>
                <td style="background: #fde9d9"><fmt:formatNumber value="${dyQuarterly.safe}" pattern="#,#00"/></td>
            </tr>
            <tr>
                <td style="background: #dbeef3">客商欠款</td>
                <td style="background: #dbeef3"><fmt:formatNumber value="${dyQuarterly.beforeArrears}"
                                                                  pattern="#,#00"/></td>
                <td style="background: #eaf1dd;">${report.quarterly}累计交付日现金总额</td>
                <td style="background: #eaf1dd;"><fmt:formatNumber value="${dyQuarterly.dailyCash}"
                                                                   pattern="#,#00"/></td>
                <td style="background: #fde9d9">客商欠款</td>
                <td style="background: #fde9d9"><fmt:formatNumber value="${dyQuarterly.arrears}" pattern="#,#00"/></td>
            </tr>
            <tr>
                <td style="background: #dbeef3">柜台零钱</td>
                <td style="background: #dbeef3"><fmt:formatNumber value="${dyQuarterly.beforeChange}"
                                                                  pattern="#,#00"/></td>
                <td style="background: #eaf1dd;"></td>
                <td style="background: #eaf1dd;"></td>
                <td style="background: #fde9d9">柜台零钱</td>
                <td style="background: #fde9d9"><fmt:formatNumber value="${dyQuarterly.change}" pattern="#,#00"/></td>
            </tr>
            <tr>
                <td style="background: #dbeef3">库存商品总货值</td>
                <td style="background: #dbeef3"><fmt:formatNumber value="${dyQuarterly.beforePurch}"
                                                                  pattern="#,#00"/></td>
                <td style="background: #eaf1dd;"></td>
                <td style="background: #eaf1dd;"></td>
                <td style="background: #fde9d9">库存商品总货值</td>
                <td style="background: #fde9d9"><fmt:formatNumber value="${dyQuarterly.purch}" pattern="#,#00"/></td>
            </tr>
            <tr>
                <td style="background: #dbeef3" colspan="2">${report.quarterly}累计进货值</td>
                <td style="background: #dbeef3"><fmt:formatNumber value="${dyQuarterly.income}" pattern="#,#00"/></td>
                <td style="background: #eaf1dd;"></td>
                <td style="background: #eaf1dd;"></td>
                <td style="background: #fde9d9"></td>
                <td style="background: #fde9d9"></td>
            </tr>
            <tr>
                <td style="background: #000000; color: #fff;" colspan="2">累计进帐总额</td>
                <td style="background: #000000; color: #fff;"><fmt:formatNumber value="${dyQuarterly.inTotal}"
                                                                                pattern="#,#00"/></td>
                <td style="background: #000000; color: #fff;">总销售额</td>
                <td style="background: #000000; color: #fff;"><fmt:formatNumber value="${dyQuarterly.outTotal}"
                                                                                pattern="#,#00"/></td>
                <td style="background: #000000; color: #fff;" colspan="2">实盘总额</td>
                <td style="background: #000000; color: #fff;"><fmt:formatNumber value="${dyQuarterly.saveTotal}"
                                                                                pattern="#,#00"/></td>
            </tr>
        </c:forEach>
        <tr style="background: #d8d8d8">
            <td rowspan="7">${report.stockName} ${report.quarterly}总盘点表</td>
        </tr>
        <tr>
            <td style="background: #dbeef3" rowspan="4">Q${BQ - 1 > 0 ? BQ - 1 : 4}（上季度）盘点货值</td>
            <td style="background: #dbeef3">保险柜现金</td>
            <td style="background: #dbeef3"><fmt:formatNumber value="${report.dzInventory.beforeSafe}"
                                                              pattern="#,#00"/></td>
            <td style="background: #eaf1dd;">${report.quarterly}各店支出总额</td>
            <td style="background: #eaf1dd;"><fmt:formatNumber value="${report.dzInventory.expendTotal}"
                                                               pattern="#,#00"/></td>
            <td style="background: #fde9d9" rowspan="5">${report.quarterly}（本季度）实点货值</td>
            <td style="background: #fde9d9">保险柜现金</td>
            <td style="background: #fde9d9"><fmt:formatNumber value="${report.dzInventory.safe}" pattern="#,#00"/></td>
        </tr>
        <tr>
            <td style="background: #dbeef3">各店客商欠款</td>
            <td style="background: #dbeef3"><fmt:formatNumber value="${report.dzInventory.beforeArrearsTotal}"
                                                              pattern="#,#00"/></td>
            <td style="background: #eaf1dd;">${report.quarterly}店长存入银行总额</td>
            <td style="background: #eaf1dd;"><fmt:formatNumber value="${report.dzInventory.dailyCash}"
                                                               pattern="#,#00"/></td>
            <td style="background: #fde9d9">各店客商欠款</td>
            <td style="background: #fde9d9"><fmt:formatNumber value="${report.dzInventory.arrearsTotal}"
                                                              pattern="#,#00"/></td>
        </tr>
        <tr>
            <td style="background: #dbeef3">各店柜台零钱</td>
            <td style="background: #dbeef3"><fmt:formatNumber value="${report.dzInventory.beforeChangeTotal}"
                                                              pattern="#,#00"/></td>
            <td style="background: #eaf1dd;"></td>
            <td style="background: #eaf1dd;"></td>
            <td style="background: #fde9d9">各店柜台零钱</td>
            <td style="background: #fde9d9"><fmt:formatNumber value="${report.dzInventory.changeTotal}"
                                                              pattern="#,#00"/></td>
        </tr>
        <tr>
            <td style="background: #dbeef3">各店盘点库存总货值</td>
            <td style="background: #dbeef3"><fmt:formatNumber value="${report.dzInventory.beforePurchTotal}"
                                                              pattern="#,#00"/></td>
            <td style="background: #eaf1dd;"></td>
            <td style="background: #eaf1dd;"></td>
            <td style="background: #fde9d9">各店盘点库存总货值</td>
            <td style="background: #fde9d9"><fmt:formatNumber value="${report.dzInventory.purchTotal}"
                                                              pattern="#,#00"/></td>
        </tr>
        <tr>
            <td style="background: #dbeef3" colspan="2">${report.quarterly}累计总进货值</td>
            <td style="background: #dbeef3"><fmt:formatNumber value="${report.dzInventory.incomeTotal}"
                                                              pattern="#,#00"/></td>
            <td style="background: #eaf1dd;"></td>
            <td style="background: #eaf1dd;"></td>
            <td style="background: #fde9d9"></td>
            <td style="background: #fde9d9"></td>
        </tr>
        <tr>
            <td style="background: #000000; color: #fff;" colspan="2">${report.stockName}累计进帐总额</td>
            <td style="background: #000000; color: #fff;"><fmt:formatNumber value="${report.dzInventory.inTotal}"
                                                                            pattern="#,#00"/></td>
            <td style="background: #000000; color: #fff;">区域支出总额</td>
            <td style="background: #000000; color: #fff;"><fmt:formatNumber value="${report.dzInventory.outTotal}"
                                                                            pattern="#,#00"/></td>
            <td style="background: #000000; color: #fff;" colspan="2">${report.stockName}实盘总额</td>
            <td style="background: #000000; color: #fff;"><fmt:formatNumber value="${report.dzInventory.saveTotal}"
                                                                            pattern="#,#00"/></td>
        </tr>
        <tr>
            <td style="background: #00b0f0" colspan="4">${report.year}年-${report.quarterly} ${report.stockName}盘点情况</td>
            <td style="background: #00b050; color: red;" colspan="2" colspan="2">${report.dzInventory.pl}</td>
            <td style="background: #00b0f0" colspan="3"><fmt:formatNumber value="${report.dzInventory.total}"
                                                                          pattern="#,#00"/></td>
        </tr>
    </table>
</div>
