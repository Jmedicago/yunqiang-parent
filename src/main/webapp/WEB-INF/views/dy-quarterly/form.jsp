<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<form method="post" style="width: 600px; margin: 30px auto;" action="/dy-quarterly/store">
    <div class="input-div">
        <label class="label-top">起始日期</label>
        <input class="easyui-datebox theme-textbox-radius" style="width: 420px" name="startDate" required ="required">
    </div>
    <div class="input-div">
        <label class="label-top">截止日期</label>
        <input class="easyui-datebox theme-textbox-radius" style="width: 420px" name="endDate" required ="required">
    </div>
    <div class="input-div">
        <label class="label-top">年份</label>
        <input class="easyui-combobox" name="yearId"
               data-options="valueField:'id',textField:'name',url:'/year/list'" style="width: 420px"/>
    </div>
    <div class="input-div">
        <label class="label-top">季度</label>
        <input id="quarterly" class="easyui-combobox" name="quarterlyId"
               data-options="valueField:'id',textField:'name',url:'/quarterly/list'" style="width: 420px"/>
    </div>
    <div class="input-div">
        <label class="label-top">柜台零钱</label>
        <input class="easyui-textbox theme-textbox-radius" name="change">
    </div>
    <div class="input-div">
        <label class="label-top">库存货值</label>
        <input class="easyui-textbox theme-textbox-radius" name="purch">
    </div>
    <div style="position: relative;" id="expendsList">
    </div>
    <div class="input-div" style="text-align: center; margin: 35px 0">
        <a class="easyui-linkbutton button-lg button-default" onclick="MXF.ajaxForm(this, function(form, res) {
          if (res.success) {
            onLoadDyDailyFrame();
          }
        })"><spring:message
                code="common.submit"/></a>
        <a class="easyui-linkbutton button-lg" onclick="MXF.clearForm(this)"><spring:message code="common.reset"/></a>
    </div>
</form>
