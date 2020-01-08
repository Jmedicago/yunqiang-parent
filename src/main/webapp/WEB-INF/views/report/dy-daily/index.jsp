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
            <th style="background: #fff;" colspan="9">每日资金进出帐明细 - ${report.stockName}店员日报</th>
        </tr>
        <tr style="color: #fff;">
            <th style="background: #000;" colspan="2">进</th>
            <th style="background: #000;" colspan="4">出</th>
            <th style="background: #000;">本季度累计上货</th>
            <th style="background: #000;">客商总额</th>
            <th style="background: #000;">本季度累计销售额</th>
        </tr>
        <tr style="color: #FF0000">
            <td style="background: #000;" colspan="2"><fmt:formatNumber value="${report.incomeTotal}" pattern="#,#00"/></td>
            <td style="background: #000;" colspan="4"><fmt:formatNumber value="${report.expendTotal}" pattern="#,#00"/></td>
            <td style="background: #000;"><fmt:formatNumber value="${report.purchTotal}" pattern="#,#00"/></td>
            <td style="background: #000;"><fmt:formatNumber value="${report.arrearsTotal}" pattern="#,#00"/></td>
            <td style="background: #000;"><fmt:formatNumber value="${report.salesTotal}" pattern="#,#00"/></td>
        </tr>
        <tr>
            <th style="background: #ddd9c3">日期</th>
            <th style="background: #ddd9c3">交付现金</th>
            <th style="background: #ddd9c3">支出类别</th>
            <th style="background: #ddd9c3; width: 300px">支出项目</th>
            <th style="background: #ddd9c3">金额</th>
            <th style="background: #ddd9c3">当日总支出</th>
            <th style="background: #ddd9c3; color: #0082c1;">上货金额</th>
            <th style="background: #ddd9c3; color: #002060;">最新客商欠款</th>
            <th style="background: #ddd9c3; color: #c00000;">每日销售额</th>
        </tr>
        <c:forEach var="detail" items="${report.details}" varStatus="st">
            <tr class="${st.count % 2 == 0 ? 'gray' : ''}">
                <td>${detail.date}</td>
                <td><fmt:formatNumber value="${detail.income}" pattern="#,#00"/></td>
                <td>
                    <ul>
                        <c:forEach var="item" items="${detail.details}">
                            <li style="${item.finExpendItem.category == 'C' ? 'background:#F0EF36' : ''}">${item.finExpendItem.category}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="item" items="${detail.details}">
                            <li style="${item.finExpendItem.category == 'C' ? 'background:#F0EF36' : ''}">${item.detail}</li>
                        </c:forEach>
                    </ul>
                </td>
                <td>
                    <ul>
                        <c:forEach var="item" items="${detail.details}">
                            <li style="${item.finExpendItem.category == 'C' ? 'background:#F0EF36' : ''}"><fmt:formatNumber value="${item.amount}" pattern="#,#00"/></li>
                        </c:forEach>
                    </ul>
                </td>
                <td><fmt:formatNumber value="${detail.expendSubTotal}" pattern="#,#00"/></td>
                <td style="color: #0082c1;"><fmt:formatNumber value="${detail.purch}" pattern="#,#00"/></td>
                <td style="color: #002060;"><fmt:formatNumber value="${detail.arrears}" pattern="#,#00"/></td>
                <td style="color: #c00000;"><fmt:formatNumber value="${detail.sales}" pattern="#,#00"/></td>
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

    /**
     * 合并单元格(如果结束行传0代表合并所有行)
     * @param table1    表格的ID
     * @param startRow  起始行
     * @param endRow    结束行
     * @param col   合并的列号，对第几列进行合并(从0开始)。第一行从0开始
     */
    function mergeCell(table1, startRow, endRow, col) {
        var tb = document.getElementById(table1);
        if (!tb || !tb.rows || tb.rows.length <= 0) {
            return;
        }
        if (col >= tb.rows[0].cells.length || (startRow >= endRow && endRow != 0)) {
            return;
        }
        if (endRow == 0) {
            endRow = tb.rows.length - 1;
        }
        for (var i = startRow; i < endRow; i++) {
            if (tb.rows[startRow].cells[col].innerHTML == tb.rows[i + 1].cells[col].innerHTML) { //如果相等就合并单元格,合并之后跳过下一行
                tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[col]);
                tb.rows[startRow].cells[col].rowSpan = (tb.rows[startRow].cells[col].rowSpan) + 1;
            } else {
                mergeCell(table1, i + 1, endRow, col);
                break;
            }
        }
    }
</script>
