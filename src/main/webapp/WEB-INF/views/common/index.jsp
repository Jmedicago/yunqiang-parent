<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="edge"/>
    <link rel="shortcut icon" href="favicon.ico"/>
    <title><spring:message code="common.title"/></title>
    <link href="/easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
    <link href="/easyui/themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">
    <link href="/easyui/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
    <link href="/easyui/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
    <link href="/easyui/themes/insdep/icon.css" rel="stylesheet" type="text/css">
    <link href="/easyui/themes/insdep/iconfont/iconfont.css" rel="stylesheet" type="text/css">
    <link href="/easyui/plugin/kindeditor-4.1.10/themes/default/default.css" rel="stylesheet" type="text/css">
    <link href="/easyui/plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/easyui/my/core.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/themes/insdep/jquery.insdep-extend.min.js"></script>
    <script type="text/javascript" src="/easyui/plugin/currencyFormatter-master/dist/currencyFormatter.js"></script>

    <!-- 后台首页js -->
    <script type="text/javascript" src="/easyui/app.js"></script>
    <script type="text/javascript" src="/easyui/my/core.js"></script>

    <!--第三方插件加载-->
    <script src="/easyui/plugin/justgage-1.2.2/raphael-2.1.4.min.js"></script>
    <script src="/easyui/plugin/justgage-1.2.2/justgage.js"></script>
</head>

<body>
<div id="loading" style="position:absolute;z-index:1000;height:100%;text-align:center; background:#fff;width:100%;">
    <div style="position: absolute; left: 50%; top: 50%; margin-top: -71px; margin-left: -50px;">
        <img src="/easyui/themes/insdep/images/ring.svg"/>
        <h5 style="margin-top: 20px"><spring:message code="common.loading"/></h5>
    </div>
</div>

<div id="master-layout">
    <!-- 开始头部菜单 -->
    <div data-options="region:'north',border:false,bodyCls:'theme-header-layout'">
        <div class="theme-navigate">
            <div class="left">
                <div class="logo">
                    <span><spring:message code="common.title"/></span>
                </div>
            </div>
            <div class="right">
                <a href="#" class="easyui-menubutton theme-navigate-user-button"
                   data-options="menu:'.theme-navigate-user-panel'">${userInfo.username}</a>
                <a href="#" class="easyui-menubutton" data-options="menu:'#lang',hasDownArrow:false"><spring:message
                        code="common.lang"/></a>
                <div class="theme-navigate-user-panel">
                    <dl>
                        <dd>
                            <b class="badge-prompt">${userInfo.username}</b>
                            <span>${userInfo.phone}</span>
                            <p><i><spring:message code="common.roles"/></i>：
                                <c:forEach var="role" items="${roleInfo}">
                                    <i class="text-success">${role.name}</i>
                                </c:forEach>
                            </p>
                        </dd>
                        <dt>
                            <a class="theme-navigate-user-logout" href="/logout"><spring:message
                                    code="common.logout"/></a>
                        </dt>
                    </dl>
                </div>
                <div id="lang" class="theme-navigate-menu-panel">
                    <div>
                        <a href="/index?lang=zh">切换为中文</a>
                    </div>
                    <div>
                        <a href="/index?lang=en">Switch to english</a>
                    </div>
                    <div>
                        <a href="/index?lang=pt">Mude para português</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 结束头部菜单 -->
    <!--开始左侧菜单-->
    <div data-options="region:'west',border:false,bodyCls:'theme-left-layout'" style="width:250px;">
        <!--正常菜单-->
        <div class="theme-left-normal">
            <!--start class="easyui-layout"-->
            <div class="easyui-layout" data-options="border:false,fit:true">
                <!--start menus-->
                <%@ include file="/WEB-INF/views/common/menu.jsp" %>
                <!--end menus-->
            </div>
            <!--end class="easyui-layout"-->
        </div>
    </div>
    <!--结束左侧菜单-->
    <div data-options="region:'center',title:''">
        <div id="tabs" class="easyui-tabs">
            <div title="<spring:message code="common.home"/>" style="padding:20px;"
                 data-options="region:'center',border:false,href:'/main/dashbord'">
            </div>
        </div>
    </div>
</div>

<!--global Modal -->
<div id="globalMsgPane" class="globalPane bg-primary">
    <div class="globalContent"><spring:message code="common.message"/></div>
</div>
<div id="globalErrMsgPane" class="globalPane bg-danger">
    <div class="globalContent"><spring:message code="common.message"/></div>
</div>
<!--global Modal -->
<div id="globalConfirmPane" class="globalPane bg-gradient">
    <div class="globalContent"><spring:message code="common.message"/></div>
    <div class="globalButtons">
        <a class="easyui-linkbutton button-warning btn-confirm">
            <i class="fa fa-warning"></i>
            <span><spring:message code="common.confirm"/></span>
        </a>
        <a class="easyui-linkbutton  btn-cancel">
            <i class="fa fa-reply"></i>
            <span><spring:message code="common.cancel"/></span>
        </a>
    </div>
</div>
<div id="globalAjaxing" class="globalPane">
    <div class="globalContent"><spring:message code="common.loading"/></div>
</div>

<script src="/easyui/plugin/Highcharts-5.0.0/js/highcharts.js"></script>
<script type="text/javascript" src="/easyui/plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="/easyui/plugin/ueditor/ueditor.all.min.js"></script>
<link href="/easyui/plugin/cropper-2.3.4/dist/cropper.min.css" rel="stylesheet" type="text/css">
<script src="/easyui/plugin/cropper-2.3.4/dist/cropper.min.js"></script>
<!-- 七牛云上传 -->
<script type="text/javascript" src="http://cdn.staticfile.org/plupload/2.1.1/plupload.full.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/plupload/2.1.1/i18n/zh_CN.js"></script>
<script type="text/javascript" src="/easyui/plugin/qiniu/qiniu.js"></script>
<script type="text/javascript" src="/easyui/plugin/qiniu/qiniu.uploader.js"></script>
<link rel="stylesheet" href="/easyui/plugin/qiniu/qiniu.uploader.css" type="text/css"/>
<!-- Kindeditor -->
<script type="text/javascript" charset="utf-8" src="/easyui/plugin/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/easyui/plugin/kindeditor-4.1.10/lang/zh_CN.js"></script>
<!--第三方插件加载结束-->
</body>
</html>