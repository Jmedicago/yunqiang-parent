<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="/easyui/my/report.css" rel="stylesheet"/>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<div style="padding: 10px">
    <c:set var="months" value="1,2,3,4,5,6,7,8,9,10,11,12"></c:set>
    <!-- 表报开始 -->
    <table border="1">
        <tr>
            <th colspan="15">${report.year}年 云强公司连锁店支出明细表</th>
        </tr>
        <tr>
            <th>编号</th>
            <th>支出项目</th>
            <th>单项总额</th>
            <c:forEach var="month" items="${months}">
                <th>${month}月份</th>
            </c:forEach>
        </tr>
        <c:forEach var="expendItem" items="${report.expendItemsList}" varStatus="st">
            <tr class="${st.count % 2 == 0 ? 'gray' : ''}">
                <td>${expendItem.category}</td>
                <td>${expendItem.article}</td>
                <td><fmt:formatNumber value="${expendItem.expendItemTotal}" pattern="#,#00"/></td>
                <c:forEach var="month" items="${months}">
                    <td>
                        <c:forEach var="expend" items="${expendItem.expendList}">
                            <c:if test="${expend.monthId == month}">
                                <fmt:formatNumber value="${expend.amount}" pattern="#,#00"/>
                            </c:if>
                        </c:forEach>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
        <tr style="background: #f0ef36">
            <td colspan="2">云强公司连锁店年度支出</td>
            <td><fmt:formatNumber value="${report.expendTotal}" pattern="#,#00"/></td>
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
