<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<style type="text/css">
    .module-header {
        height: 37px;
        line-height: 37px;
        background: rgb(248, 248, 248);
        border-top: 1px solid rgb(225, 225, 225);
        border-bottom: 1px solid rgb(225, 225, 225)
    }

    .edit-bar {
        padding-left: 10px;
        height: 50px;
        line-height: 50px;
        border-bottom: 1px solid rgb(238, 238, 238);
    }
</style>
<div>
    <div class="module-header">
        <span style="margin-left: 15px;">每日资金进出帐明细</span>
    </div>
    <div class="edit-bar">
        <a href="javascript:addDyStockDaily()" class="easyui-linkbutton" iconCls="icon-add" plain="true">填报</a>
    </div>
    <div id="module-content">
        <iframe id="dyDailyFrame" frameborder="0" height="1000" width="100%" scrolling="no"></iframe>
    </div>
</div>

<script>

    function onLoadDyDailyFrame() {
        MXF.ajaxing(true);
        var that = $('#dyDailyFrame');
        that.attr("src", "/report?rn=dy-daily&stockId=${stockId}");
        that.load(function () {
            MXF.ajaxing(false);
        });
    }
    onLoadDyDailyFrame();

    function addDyStockDaily() {
        // 打开编辑页面
        var editWindow = $('<div id="addDyStockDailyWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: '当日资金出账明细',
            modal: true,
            /*maximized: true,*/
            width: 600,
            height: 450,
            href: '/dy-daily/edit',
            onLoad: function () {
            },
            onClose: function () {
                editWindow.window('destroy');
            }
        });
    }
</script>
