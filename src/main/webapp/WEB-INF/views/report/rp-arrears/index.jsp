<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="/easyui/my/report.css" rel="stylesheet"/>
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<div style="padding: 10px">
    <!-- 表报开始 -->
    <table border="1">
        <tr>
            <th colspan="14">月/季度/年 - 各区域连锁店客商欠款汇总</th>
        </tr>
        <tr>
            <th id="lineTd">
                <span style="float:left;margin-top:20px;padding-left: 2px;padding-bottom: 2px;">地区</span>
                <span style="float:right;margin-top:0px;padding-right: 2px;padding-top: 2px;">日期</span>
            </th>
            <th>1月份</th>
            <th>2月份</th>
            <th>3月份</th>
            <th>4月份</th>
            <th>5月份</th>
            <th>6月份</th>
            <th>7月份</th>
            <th>8月份</th>
            <th>9月份</th>
            <th>10月份</th>
            <th>11月份</th>
            <th>12月份</th>
            <th>Total</th>
        </tr>
        <tr>
            <td>BEIRA</td>
            <td>800,000</td>
            <td>800,000</td>
            <td>2,500,000</td>
            <td>900,000</td>
            <td>5,900,000</td>
            <td>6,300,000</td>
            <td>2,000,000</td>
            <td>500,000</td>
            <td>400,000</td>
            <td>500,000</td>
            <td>300,000</td>
            <td>4500,000</td>
            <td>254000,000</td>
        </tr>
        <tr>
            <td>BEIRA</td>
            <td>800,000</td>
            <td>800,000</td>
            <td>2,500,000</td>
            <td>900,000</td>
            <td>5,900,000</td>
            <td>6,300,000</td>
            <td>2,000,000</td>
            <td>500,000</td>
            <td>400,000</td>
            <td>500,000</td>
            <td>300,000</td>
            <td>4500,000</td>
            <td>254000,000</td>
        </tr>
        <tr style="background: #f0ef36">
            <td>当月各店总欠款</td>
            <td>800,000</td>
            <td>800,000</td>
            <td>2,500,000</td>
            <td>900,000</td>
            <td>5,900,000</td>
            <td>6,300,000</td>
            <td>2,000,000</td>
            <td>500,000</td>
            <td>400,000</td>
            <td>500,000</td>
            <td>300,000</td>
            <td>4500,000</td>
            <td>254000,000</td>
        </tr>
    </table>

    <script type="text/javascript">
        var data = {
            "year": 2019,
            "details": [{
                "stockName": "Beira",
                "arrearsList": [{
                    "month": 1,
                    "amount": 800000
                }, {
                    "month": 2,
                    "amount": 800000
                }]
            }]
        }
    </script>
</div>
