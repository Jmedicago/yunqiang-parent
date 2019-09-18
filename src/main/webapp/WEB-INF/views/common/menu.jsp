<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--start easyui-accordion-->
<div id="mainMenu" class="easyui-accordion" data-options="border:false,fit:true">
    <c:forEach var="item" items="${menus}">
        <div title="${item.text}">
            <ul class="easyui-datalist" data-options="border:false,fit:true">
                <c:forEach var="child" items="${item.children}">
                    <li><a href="javascript:toPage('${child.url}')">${child.text}</a></li>
                </c:forEach>
            </ul>
        </div>
    </c:forEach>
</div>
<!--end easyui-accordion-->