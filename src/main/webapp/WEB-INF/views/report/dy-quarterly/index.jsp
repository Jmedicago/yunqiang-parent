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
            <th style="background: #f2f2f2" colspan="9">${report.year}年-${report.quarterly} ${report.regionStockName}-${report.stockName}盘点表《店员》</th>
        </tr>
        <tr>
            <th style="background: #d8d8d8">直营店</th>
            <th style="background: #dbeef3" colspan="3">进</th>
            <th style="background: #eaf1dd;" colspan="2">出</th>
            <th style="background: #fde9d9" colspan="3">存</th>
        </tr>
        <tr style="background: #d8d8d8">
            <td rowspan="8">${report.regionStockName}-${report.stockName}</td>
        </tr>
        <tr>
            <td style="background: #dbeef3" rowspan="4">Q1（上季度）盘点货值</td>
            <td style="background: #dbeef3">保险柜现金</td>
            <td style="background: #dbeef3"><fmt:formatNumber value="${report.beforeSafe}" pattern="#,#00"/></td>
            <td style="background: #eaf1dd;">Q2支出总额</td>
            <td style="background: #eaf1dd;"><fmt:formatNumber value="${report.expend}" pattern="#,#00"/></td>
            <td style="background: #fde9d9" rowspan="5">Q2（本季度）实点货值</td>
            <td style="background: #fde9d9">保险柜现金</td>
            <td style="background: #fde9d9"><fmt:formatNumber value="${report.safe}" pattern="#,#00"/></td>
        </tr>
        <tr>
            <td style="background: #dbeef3">客商欠款</td>
            <td style="background: #dbeef3"><fmt:formatNumber value="${report.beforeArrears}" pattern="#,#00"/></td>
            <td style="background: #eaf1dd;">Q2累计交付日现金总额</td>
            <td style="background: #eaf1dd;"><fmt:formatNumber value="${report.dailyCash}" pattern="#,#00"/></td>
            <td style="background: #fde9d9">客商欠款</td>
            <td style="background: #fde9d9"><fmt:formatNumber value="${report.arrears}" pattern="#,#00"/></td>
        </tr>
        <tr>
            <td style="background: #dbeef3">柜台零钱</td>
            <td style="background: #dbeef3"><fmt:formatNumber value="${report.beforeChange}" pattern="#,#00"/></td>
            <td style="background: #eaf1dd;"></td>
            <td style="background: #eaf1dd;"></td>
            <td style="background: #fde9d9">柜台零钱</td>
            <td style="background: #fde9d9"><fmt:formatNumber value="${report.change}" pattern="#,#00"/></td>
        </tr>
        <tr>
            <td style="background: #dbeef3">库存商品总货值</td>
            <td style="background: #dbeef3"><fmt:formatNumber value="${report.beforePurch}" pattern="#,#00"/></td>
            <td style="background: #eaf1dd;"></td>
            <td style="background: #eaf1dd;"></td>
            <td style="background: #fde9d9">库存商品总货值</td>
            <td style="background: #fde9d9"><fmt:formatNumber value="${report.purch}" pattern="#,#00"/></td>
        </tr>
        <tr>
            <td style="background: #dbeef3" colspan="2">Q2累计进货值</td>
            <td style="background: #dbeef3"><fmt:formatNumber value="${report.income}" pattern="#,#00"/></td>
            <td style="background: #eaf1dd;"></td>
            <td style="background: #eaf1dd;"></td>
            <td style="background: #fde9d9"></td>
            <td style="background: #fde9d9"></td>
        </tr>
        <tr>
            <td style="background: #000000; color: #fff;" colspan="2">累计进帐总额</td>
            <td style="background: #000000; color: #fff;"><fmt:formatNumber value="${report.inTotal}" pattern="#,#00"/></td>
            <td style="background: #000000; color: #fff;">总销售额</td>
            <td style="background: #000000; color: #fff;"><fmt:formatNumber value="${report.outTotal}" pattern="#,#00"/></td>
            <td style="background: #000000; color: #fff;" colspan="2">实盘总额</td>
            <td style="background: #000000; color: #fff;"><fmt:formatNumber value="${report.saveTotal}" pattern="#,#00"/></td>
        </tr>
        <tr>
            <td style="background: #ffff00" colspan="3">收支平衡</td>
            <td style="background: #00b050; color: red;" colspan="2">${report.pl}</td>
            <td colspan="3"><fmt:formatNumber value="${report.total}" pattern="#,#00"/></td>
        </tr>
    </table>
</div>
