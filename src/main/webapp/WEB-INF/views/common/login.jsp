<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<div>
    <form action="/login" method="post">
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" id="username" name="username">
        </div>
        <div class="form-group">
            <label for="password">密  码：</label>
            <input type="text" id="password" name="password">
        </div>
        <div class="form-group">
            <div>
                <span style="color: red;">${message}</span>
            </div>
            <button type="submit">登  录</button>
        </div>
    </form>
</div>
</body>
</html>
