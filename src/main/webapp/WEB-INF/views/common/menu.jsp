<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--start easyui-accordion-->
<div id="mainMenu" class="easyui-accordion" data-options="border:false,fit:true">
    <c:forEach var="item" items="${menus}">
        <div title="<spring:message code="${item.text}"/>">
            <ul class="easyui-datalist" data-options="border:false,fit:true">
                <c:forEach var="child" items="${item.children}">
                    <li><a href="javascript:" uri="${child.url}"><spring:message code="${child.text}"/></a></li>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>

    <%--<div title="测试菜单"/>--%>
        <%--<ul class="easyui-datalist" data-options="border:false,fit:true">--%>
            <%--<li><a href="javascript:" uri="/product2">测试商品列表</a></li>--%>
        <%--</ul>--%>
    <%--</div>--%>
</div>
<!--end easyui-accordion-->