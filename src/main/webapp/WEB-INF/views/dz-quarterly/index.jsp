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
        height: 40px;
        line-height: 40px;
        border-bottom: 1px solid rgb(238, 238, 238);
    }

    .search-bar {
        padding-left: 15px;
        height: 50px;
        line-height: 50px;
        border-bottom: 1px solid rgb(238, 238, 238);
    }
</style>
<div>
    <div class="module-header">
        <span style="margin-left: 15px;">区域盘点总表《区域店长》</span>
    </div>
    <div class="edit-bar">
        <a href="javascript:addDzStockQuarterly()" class="easyui-linkbutton" iconCls="icon-add" plain="true">填报</a>
    </div>
    <div class="search-bar">
        年份：<input id="year" class="easyui-combobox" name="year"
                  data-options="valueField:'name',textField:'name',url:'/year/list'"/>
        季度：<input id="quarterly" class="easyui-combobox" name="quarterly"
                  data-options="valueField:'name',textField:'name',url:'/quarterly/list'"/>
        <a href="javascript:onLoadDzQuarterlyFrame();" class="easyui-linkbutton button-default">
            <spring:message code="common.search"/></a>
    </div>
    <div id="module-content">
        <iframe id="dzQuarterlyFrame" frameborder="0" height="1000" width="100%" scrolling="no"></iframe>
    </div>
</div>
<script>

    function onLoadDzQuarterlyFrame() {
        var year = $('#year').val();
        var quarterly = $('#quarterly').val();
        if (year && quarterly) {
            MXF.ajaxing(true);
            var that = $('#dzQuarterlyFrame');
            that.attr("src", "/report?rn=dz-quarterly&year=" + year + "&quarterly=" + quarterly + "&stockId=${stockId}");
            that.load(function () {
                MXF.ajaxing(false);
            });
        }
    }

    onLoadDzQuarterlyFrame();

    function addDzStockQuarterly() {
        // 打开编辑页面
        var editWindow = $('<div id="addDzStockQuarterlyWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: '区域盘点总表《区域店长》',
            modal: true,
            /*maximized: true,*/
            width: 600,
            height: 450,
            href: '/dz-quarterly/edit',
            onLoad: function () {
            },
            onClose: function () {
                editWindow.window('destroy');
            }
        });
    }
</script>

