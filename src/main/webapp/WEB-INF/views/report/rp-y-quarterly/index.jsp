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
            <th style="background: #fff;" colspan="15">${report.year}年-${report.quarterly} 云强公司各区域连锁店盘点总表</th>
        </tr>
        <tr>
            <th style="background: #000; color: #fff;" colspan="2" rowspan="2">云强贸易公司 ${report.year}-${report.quarterly} 盘点报告</th>
            <th style="background: #000; color: #fff;">连锁店保险柜现金</th>
            <th style="background: #000; color: #fff;">商客总欠款</th>
            <th style="background: #000; color: #fff;">柜台零钱</th>
            <th style="background: #000; color: #fff;">库存商品总货值</th>
            <th style="background: #eaf1dd; color: #cd4a44;">${report.quarterly}-盘点总值</th>
            <th style="background: #eaf1dd; color: #cd4a44;">${report.quarterly}-总销售额</th>
            <th style="background: #eaf1dd; color: #cd4a44;">${report.quarterly}-上货值</th>
            <th style="background: #eaf1dd; color: #cd4a44;">${report.quarterly}-盘点总值</th>
            <th style="background: #eaf1dd; color: #cd4a44;">${report.quarterly}-公司毛利润</th>
        </tr>
        <tr>
            <td style="background: #000; color: #fff;"><fmt:formatNumber value="7200000" pattern="#,#00"/></td>
            <td style="background: #000; color: #fff;"><fmt:formatNumber value="660000" pattern="#,#00"/></td>
            <td style="background: #000; color: #fff;"><fmt:formatNumber value="492000" pattern="#,#00"/></td>
            <td style="background: #000; color: #fff;"><fmt:formatNumber value="25226400" pattern="#,#00"/></td>
            <td style="background: #eaf1dd; color: #cd4a44;"><fmt:formatNumber value="33578400" pattern="#,#00"/></td>
            <td style="background: #eaf1dd; color: #cd4a44;"><fmt:formatNumber value="108000000" pattern="#,#00"/></td>
            <td style="background: #eaf1dd; color: #cd4a44;"><fmt:formatNumber value="12000000" pattern="#,#00"/></td>
            <td style="background: #eaf1dd; color: #cd4a44;"><fmt:formatNumber value="49200000" pattern="#,#00"/></td>
            <td style="background: #eaf1dd; color: #cd4a44;"><fmt:formatNumber value="80378400" pattern="#,#00"/></td>
        </tr>
        <tr>
            <th style="background: #d8d8d8">区域名称</th>
            <th style="background: #d8d8d8">区域连锁店</th>
            <th style="background: #d8d8d8">保险柜现金余额</th>
            <th style="background: #d8d8d8">商客欠款</th>
            <th style="background: #d8d8d8">柜台零钱</th>
            <th style="background: #d8d8d8">库存商品总货值</th>
            <th style="background: #d8d8d8">${report.quarterly}-盘点总值</th>
            <th style="background: #d8d8d8">${report.quarterly}-销售额</th>
            <th style="background: #d8d8d8">${report.quarterly}-上货值</th>
            <th style="background: #d8d8d8">${report.quarterly}-盘点总值</th>
            <th style="background: #d8d8d8">${report.quarterly}-盈亏情况</th>
        </tr>
        <tr>
            <td>M-Xipamanine</td>
            <td>
                <ul>
                    <li>一号店</li>
                    <li style="background: #dbeef3;">二号店</li>
                    <li>三号店</li>
                    <li style="background: #dbeef3;">四号店</li>
                    <li style="background: #ff0000; color: #fff;">总计</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>N/A</li>
                    <li style="background: #dbeef3;">N/A</li>
                    <li>N/A</li>
                    <li style="background: #dbeef3;">N/A</li>
                    <li style="background: #ffff00">N/A</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
        </tr>

        <tr>
            <td>M-Xipamanine</td>
            <td>
                <ul>
                    <li>一号店</li>
                    <li style="background: #dbeef3;">二号店</li>
                    <li>三号店</li>
                    <li style="background: #dbeef3;">四号店</li>
                    <li style="background: #ff0000; color: #fff;">总计</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>N/A</li>
                    <li style="background: #dbeef3;">N/A</li>
                    <li>N/A</li>
                    <li style="background: #dbeef3;">N/A</li>
                    <li style="background: #ffff00">N/A</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td>M-Xipamanine</td>
            <td>
                <ul>
                    <li>一号店</li>
                    <li style="background: #dbeef3;">二号店</li>
                    <li>三号店</li>
                    <li style="background: #dbeef3;">四号店</li>
                    <li style="background: #ff0000; color: #fff;">总计</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>N/A</li>
                    <li style="background: #dbeef3;">N/A</li>
                    <li>N/A</li>
                    <li style="background: #dbeef3;">N/A</li>
                    <li style="background: #ffff00">N/A</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
            <td>
                <ul>
                    <li>15000</li>
                    <li style="background: #dbeef3;">40000</li>
                    <li></li>
                    <li style="background: #dbeef3;"></li>
                    <li style="background: #ff0000; color: #fff;">55000</li>
                </ul>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    $("table tr td ul").each(function (index, value) {
        var height = $(value).height();
        var tdStyle = {
            "height": height,
            "position": "relative"
        }
        $(value).parent().css(tdStyle);
        var ulStyle = {
            "position": "absolute",
            "top": 0,
            "left": 0,
            "width": "100%",
            /*"border-bottom": "1px solid gray"*/
        }
        $(value).css(ulStyle);
    });
</script>

