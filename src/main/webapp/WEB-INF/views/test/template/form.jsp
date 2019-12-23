<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="${action}" method="post">
    <jsp:include page="${components}"/>
    <%@ include file="../component/datagrid.jsp" %>
</form>
