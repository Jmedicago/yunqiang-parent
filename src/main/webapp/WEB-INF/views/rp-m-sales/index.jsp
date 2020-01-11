<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div>
    <div class="bar">
        <div style="height: 37px; line-height: 37px; background: rgb(248, 248, 248); border-top: 1px solid rgb(225, 225, 225); border-bottom: 1px solid rgb(225, 225, 225) ">
            <span style="margin-left: 15px; font-weight: bold;height: 16px;font-size: 14px;line-height: 16px;">每月区域销售额汇总</span>
        </div>
        <div style="height: 50px; line-height: 50px; border-bottom: 1px solid rgb(238, 238, 238);">
            <div class="input-div" style="float:left;margin-top: 0;">
                <label style="width: 50px;" class="label-top">年份</label>
                <input id="mYear" class="easyui-combobox" name="year"
                       data-options="valueField:'id',textField:'name',url:'/year/list'"/>
            </div>
            <div class="input-div" style="float: left;margin-left: 15px;margin-top: 0;">
                <a href="javascript:;" data-cmd="mSearch" class="easyui-linkbutton button-default">
                    <spring:message code="common.search"/>
                </a>
            </div>
        </div>
    </div>
    <div id="mReport">

    </div>
</div>
<script>
    function mSearch(row) {
        var year = $('#mYear').val();
        if (year) {
            var html = '<iframe frameborder="0" height="1000" width="100%" scrolling="no" src="report?rn=rp-m-sales&year=' + year +'"> </iframe>';
            $('#mReport').html(html);
        } else {
            MXF.alert("请选择年份！");
        }
    }
</script>
