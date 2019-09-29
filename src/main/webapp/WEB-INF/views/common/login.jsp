<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="edge"/>
    <link rel="shortcut icon" href="favicon.ico"/>
    <title>云强企业管理系统 | 登录</title>
    <link href="/easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
    <link href="/easyui/themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">
    <link href="/easyui/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
    <link href="/easyui/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
    <link href="/easyui/themes/insdep/icon.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/themes/insdep/jquery.insdep-extend.min.js"></script>
</head>
<body class="theme-login-layout">
    <div id="theme-login-form">
        <form id="form" class="theme-login-form" action="/login" method="post">
            <dl>
                <dt>
                    <h3 class="title">云强企业管理系统</h3>
                </dt>
                <dd>
                    <label for="username">用户名：</label>
                    <input class="theme-login-text" type="text" id="username" name="username">
                </dd>
                <dd>
                    <label for="password">密 码：</label>
                    <input class="theme-login-text" type="password" id="password" name="password">
                </dd>
                <dd>
                    <span class="theme-login-message">${message}</span>
                </dd>
                <dd>
                    <button class="theme-login-btn" type="submit">登 录</button>
                </dd>
            </dl>
        </form>
    </div>
</body>
</html>
