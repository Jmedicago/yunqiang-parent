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
        <span style="margin-left: 15px;">季度区域盘点表《店员》</span>
    </div>
    <div class="edit-bar">
        <a href="javascript:addDyStockQuarterly()" class="easyui-linkbutton" iconCls="icon-add" plain="true">填报</a>
    </div>
    <div class="search-bar">
        年份：<input id="year" class="easyui-combobox" name="year"
                  data-options="valueField:'name',textField:'name',url:'/year/list'"/>
        季度：<input id="quarterly" class="easyui-combobox" name="quarterly"
                  data-options="valueField:'name',textField:'name',url:'/quarterly/list'"/>
        <a href="javascript:onLoadDyQuarterlyFrame();" class="easyui-linkbutton button-default">
            <spring:message code="common.search"/></a>
    </div>
    <div id="module-content">
        <iframe id="dyQuarterlyFrame" frameborder="0" height="1000" width="100%" scrolling="no"></iframe>
    </div>
</div>
<script>

    function onLoadDyQuarterlyFrame() {
        var year = $('#year').val();
        var quarterly = $('#quarterly').val();
        if (year && quarterly) {
            MXF.ajaxing(true);
            var that = $('#dyQuarterlyFrame');
            that.attr("src", "/report?rn=dy-quarterly&year=" + year + "&quarterly=" + quarterly + "&stockId=${stockId}");
            that.load(function () {
                MXF.ajaxing(false);
            });
        }
    }

    onLoadDyQuarterlyFrame();

    function addDyStockQuarterly() {
        // 打开编辑页面
        var editWindow = $('<div id="addDyStockQuarterlyWindow"></div>');
        editWindow.appendTo('body');
        $(editWindow).window({
            title: '季度区域盘点表《店员》',
            modal: true,
            /*maximized: true,*/
            width: 600,
            height: 450,
            href: '/dy-quarterly/edit',
            onLoad: function () {
            },
            onClose: function () {
                editWindow.window('destroy');
            }
        });
    }
</script>

