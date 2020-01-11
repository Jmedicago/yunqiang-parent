<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div>
    <div class="bar">
        <div style="height: 37px; line-height: 37px; background: rgb(248, 248, 248); border-top: 1px solid rgb(225, 225, 225); border-bottom: 1px solid rgb(225, 225, 225) ">
            <span style="margin-left: 15px; font-weight: bold;height: 16px;font-size: 14px;line-height: 16px;">每月区域销售额汇总</span>
        </div>
        <div style="height: 50px; line-height: 50px; border-bottom: 1px solid rgb(238, 238, 238);">
            <div class="input-div" style="float: left;margin-top: 0;">
                <label style="width: 40px;" class="label-top">年份</label>
                <input id="dYear" class="easyui-combobox" name="year"
                       data-options="valueField:'name',textField:'name',url:'/year/list'"/>
            </div>
            <div class="input-div" style="float: left;margin-top: 0;">
                <label style="width: 40px;" class="label-top">月份</label>
                <input id="dMonth" class="easyui-combobox" name="month"
                       data-options="valueField:'id',textField:'name',url:'/month/list'"/>
            </div>
            <div class="input-div" style="float: left;margin-left: 20px;margin-top: 0;">
                <a href="javascript:search();" class="easyui-linkbutton button-default">
                    <spring:message code="common.search"/>
                </a>
            </div>
        </div>
    </div>
    <div id="dReport">

    </div>
</div>
<script>
    function search() {
        var year = $('#dYear').val();
        var month = $('#dMonth').val();
        console.log(year, month);
        if (year && month) {
            var html = '<iframe frameborder="0" height="1000" width="100%" scrolling="no" src="report?rn=rp-d-sales&year=' + year + '&month=' + month +'"> </iframe>';
            $('#dReport').html(html);
        } else {
            MXF.alert("请选择年份和月份！");
        }
    }
</script>
