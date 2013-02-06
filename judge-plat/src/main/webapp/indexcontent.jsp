<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>XX产品现货估价平台 - XXXX网</title>
    <link href="${ctx}/style/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/style/jquery.lz.1.2.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/style/judge-plat.js" charset="utf-8"></script>
</head>
<body>
<form id="pageForm" action="" method="post">
    <input type="hidden" id="pageNumber" name="pageNumber" value="${currentIndex}">
    <input type="hidden" id="productName" name="productName" value="${productName}">
    <input type="hidden" id="area" name="area" value="${jmpArea}">
</form>

<div class="wrap">

<a href=""><img class="logo" src="${ctx}/images/logo.jpg" alt="XX产品现货估价平台"/></a>

<div class="main">

<div class="m_left">
    <div class="product_list">
        <h2>XX产品</h2>
        <dl>
            <dt>油品</dt>
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'柴油')">柴油</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'汽油')">汽油</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'液化气')">液化气</a></dd>
            &nbsp;&nbsp;
        </dl>
        <dl>
            <dt>化工</dt>
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'甲醇')">甲醇</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'苯乙烯')">苯乙烯</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'纯苯')">纯苯</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'DOP')">DOP</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'MTBE')">MTBE</dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'C5')">C5</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'C9')">C9</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'溶剂油')">溶剂油</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'醋酸')">醋酸</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'二甲苯')">二甲苯</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'苯酚')">苯酚</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丙酮')">丙酮</a></dd>
            &nbsp;&nbsp;
        </dl>
        <dl>
            <dt>橡胶</dt>
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'天然橡胶')">天然橡胶</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丁苯橡胶')">丁苯橡胶</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'顺丁橡胶')">顺丁橡胶</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'SBS')">SBS</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丁腈橡胶')">丁腈橡胶</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丁基橡胶')">丁基橡胶</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'乙丙橡胶')">乙丙橡胶</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丁二烯')">丁二烯</a></dd>
            &nbsp;&nbsp;
        </dl>
        <dl>
            <dt>塑料</dt>
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'PE')">PE</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'PP粒')">PP粒</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'PP粉')">PP粉</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'PVC')">PVC</a></dd>
            &nbsp;&nbsp;
        </dl>
        <dl>
            <dt>化纤</dt>
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丙烯腈')">丙烯腈</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'PTA')">PTA</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'聚酯切片')">聚酯切片</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'涤纶短纤')">涤纶短纤</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'涤纶长丝')">涤纶长丝</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'己内酰胺')">己内酰胺</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'腈纶短纤')">腈纶短纤</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'腈纶长丝')">腈纶长丝</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'环己酮')">环己酮</a></dd>
            &nbsp;&nbsp;
        </dl>
        <dl>
            <dt>聚氨酯</dt>
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'MDI')">MDI</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'TDI')">TDI</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'THF')">THF</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'DMF')">DMF</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'环氧丙烷')">环氧丙烷</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'聚醚软泡')">聚醚软泡</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'BDO')">BDO</a></dd>
            &nbsp;&nbsp;
        </dl>
        <dl>
            <dt>无机化工</dt>
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'硫磺')">硫磺</a></dd>
            &nbsp;&nbsp;
            <dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'烧碱')">烧碱</a></dd>
            &nbsp;&nbsp;
        </dl>
    </div>

    <div class="text01">
        <strong>XX估价的作用</strong>

        <p>1、XX估价为市场交易提供准确、客观并及时的第三方交易参考价格，作为第三方价格参与市场交易，减少不正常价格波动给交易各方造成的影响。另外可为远期交易提供第三方的价格参考。</p>

        <p>2、作为研究和评估市场的基础数据，为XX企业、期货公司及相关管理部门跟踪、分析或制订政策法规提供依据。</p>
    </div>

</div>

<div class="m_center">

    <div class="valuation">
        <h2>XX产品现货估价</h2>
        <table class="spot-list2">
            <tr id="marqueebox0_th">
                <th>商品名称</th>
                <th>地区</th>
                <c:if test="${noshowModel ne null && noshowModel eq 'false'}"><th>规格型号</th></c:if>
                <th>涨跌<span style="color:#f00;">↑</span><span style="color:#390;">↓</span></th>
                <th>价格</th>
                <th>单位</th>
                <th>日期</th>
                <th>曲线图</th>
            </tr>
            <c:forEach var="price" items="${marqueeList}">
                <tr>
                    <td>${price.productName}</td>
                    <td>${price.areaName}</td>
                    <c:if test="${noshowModel ne null && noshowModel eq 'false'}"><td>${price.modelName}</td></c:if>
                    <td>
                        <c:if test="${price.rateFlag lt 0}"><span style="color:#390;">${price.changeRate}</span></c:if>
                        <c:if test="${price.rateFlag gt 0}"><span style="color:#f00;">${price.changeRate}</span></c:if>
                        <c:if test="${price.rateFlag eq 0}">${price.changeRate}</c:if>
                    </td>
                    <td>${price.price}</td>
                    <td>${price.unit}</td>
                    <td><fmt:formatDate value="${price.priceDate}" type="date"/></td>
                    <td><a href="#" onclick="window
                            .showModalDialog('${ctx}/price/toJsChart.do?priceJudgeId=${price.priceJugdeId}&productName='
                            +encodeURI('${price.productName}')
                            + '&areaName='+encodeURI('${price.areaName}')
                            +'&modelName='+encodeURI('${price.modelName}')+'',
                            null,'dialogWidth=640px;dialogHeight=480px')">查看</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>


    <div class="hotword">
        热点关键词：
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'柴油')">柴油</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'甲醇')">甲醇</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'C5')">C5</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'C9')">C9</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丁苯橡胶')">丁苯橡胶</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丁二烯')">丁二烯</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'PE')">PE</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'PVC')">PVC</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丙烯腈')">丙烯腈</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'涤纶长丝')">涤纶长丝</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'MDI')">MDI</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'环氧丙烷')">环氧丙烷</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'硫磺')">硫磺</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'溶剂油')">溶剂油</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'SBS')">SBS</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'MTBE')">MTBE</a>&nbsp;
        <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'苯酚')">苯酚</a>&nbsp;
    </div>


    <div class="lzcj">
        <h2>XX财经</h2>

        <div class="content">
            <p class="lzcj_cap" id="head_news">
                <img src='${headNews.img}'/>
                <a href='${headNews.url}' target='_blank'>${headNews.content}</a>
            </p>
            <ul id="content_news"><c:forEach items="${newsList}" var="news">
                <li><a href='${news.url}' target='_blank'>${news.title}</a><span>[<fmt:formatDate value="${news.newsDate}" type="date" pattern="yyyy-MM-dd" />]</span></li>
            </c:forEach>
            </ul>
        </div>
    </div>


    <script>
        <!--
        function setTab(name, cursel, n) {
            for (i = 1; i <= n; i++) {
                var menu = document.getElementById(name + i);
                var con = document.getElementById("con_" + name + "_" + i);
                menu.className = i == cursel ? "hover" : "";
                con.style.display = i == cursel ? "block" : "none";
            }
        }

        var ctx = '${ctx}';
        $(function () {
            weekTop(ctx, 'up');
            weekTop(ctx, 'down');
        });

        var weekTop = function (ctx, type) {
            $.ajax({
                type: "get",
                url: ctx + "/week/" + type+".do",
                dataType: "json",
                success: function (data) {
                    if (type == 'up') {
                        $.each(data, function (i, item) {
                            var li = "  \
                    <li >       \
                        <h4 title='" + item.productName + "'>" + item.productName + "</h4>    \
                        <h5>" + item.areaName + "</h5>                                         \
                        <span >" + item.price + "<b style='color:red'>↑</b></span>         \
                        <i style='color:red;'>" + item.changeRate + "%</i>                   \
                    </li>       ";
                            $("#ContW0").append(li);
                        });
                    }
                    if (type == 'down') {
                        $.each(data, function (i, item) {
                            var li = "  \
                    <li >       \
                        <h4 title='" + item.productName + "'>" + item.productName + "</h4>    \
                        <h5>" + item.areaName + "</h5>                                         \
                        <span >" + item.price + "<b style='color:green'>↓</b></span>         \
                        <i style='color:green;'>" + item.changeRate + "%</i>                   \
                    </li>       ";
                            $("#ContW1").append(li);
                        });
                    }
                }
            })
        };
        //-->
    </script>
    <div id="lib_Tab2">
        <div class="lib_Menubox lib_tabborder">
            <ul>
                <li id="two1" onclick="setTab('two',1,6)" class="hover">原油</li>
                <li id="two2" onclick="setTab('two',2,6)">汽油</li>
                <li id="two3" onclick="setTab('two',3,6)">柴油</li>
                <li id="two4" onclick="setTab('two',4,6)">橡胶</li>
                <li id="two5" onclick="setTab('two',5,6)">丁苯</li>
                <li id="two6" onclick="setTab('two',6,6)">顺丁</li>
            </ul>
        </div>
        <div class="lib_Contentbox lib_tabborder">
            <div id="con_two_1"><a href="http://www.xxxx.net/chart/pricechart.shtml?index=1&showexp=1"
                                   target="_blank"><img
                    src="http://www.xxxx.com/chart/pricechart1.shtml?Button1=%E7%BB%98%E5%9B%BE&Button1=%E5%88%B7%E6%96%B0&index=1&showexp=1&kType=0&lb=1&lbid=1&cpmc=&cplb=&ggxh=&scqy=&jldw=&jldw2=&cPrice=hdind&mPrice=&dq=&qxh=&jglx=&jglx2=&bjsc=&sid=&TimeRange=6&startdate=2011/1/23&enddate=2013/1/22&ChartSize=bk&ChartType=1&MajorVGrid=1&HGrid=1&style=0&avgType1=None&movAvg1=10&avgType2=None&movAvg2=20&avgType3=None&movAvg3=50&Indicator1=None&Indicator2=None&Indicator3=None&Indicator4=None&Band=None&Button1=%E7%BB%98%E5%9B%BE&"
                    alt="原油价格指数曲线图"/></a></div>
            <div id="con_two_2" style="display:none"><a
                    href="http://www.xxxx.net/chart/pricechart.shtml?index=7&showexp=1" target="_blank"><img
                    src="http://www.xxxx.com/chart/pricechart1.shtml?Button1=%E7%BB%98%E5%9B%BE&Button1=%E5%88%B7%E6%96%B0&index=7&showexp=1&kType=0&lb=1&lbid=1&cpmc=&cplb=&ggxh=&scqy=&jldw=&jldw2=&cPrice=hdind&mPrice=&dq=&qxh=&jglx=&jglx2=&bjsc=&sid=&TimeRange=6&startdate=2011/1/23&enddate=2013/1/22&ChartSize=bk&ChartType=1&MajorVGrid=1&HGrid=1&style=0&avgType1=None&movAvg1=10&avgType2=None&movAvg2=20&avgType3=None&movAvg3=50&Indicator1=None&Indicator2=None&Indicator3=None&Indicator4=None&Band=None&Button1=%E7%BB%98%E5%9B%BE&"
                    alt="汽油价格指数曲线图"/></a></div>
            <div id="con_two_3" style="display:none"><a
                    href="http://www.xxxx.net/chart/pricechart.shtml?index=8&showexp=1" target="_blank"><img
                    src="http://www.xxxx.com/chart/pricechart1.shtml?Button1=%E7%BB%98%E5%9B%BE&Button1=%E5%88%B7%E6%96%B0&index=8&showexp=1&kType=0&lb=1&lbid=1&cpmc=&cplb=&ggxh=&scqy=&jldw=&jldw2=&cPrice=hdind&mPrice=&dq=&qxh=&jglx=&jglx2=&bjsc=&sid=&TimeRange=6&startdate=2011/1/23&enddate=2013/1/22&ChartSize=bk&ChartType=1&MajorVGrid=1&HGrid=1&style=0&avgType1=None&movAvg1=10&avgType2=None&movAvg2=20&avgType3=None&movAvg3=50&Indicator1=None&Indicator2=None&Indicator3=None&Indicator4=None&Band=None&Button1=%E7%BB%98%E5%9B%BE&"
                    alt="柴油价格指数曲线图"/></a></div>
            <div id="con_two_4" style="display:none"><a
                    href="http://www.xxxx.net/chart/pricechart.shtml?index=4&showexp=1" target="_blank"><img
                    src="http://www.xxxx.com/chart/pricechart1.shtml?Button1=%E7%BB%98%E5%9B%BE&Button1=%E5%88%B7%E6%96%B0&index=4&showexp=1&kType=0&lb=1&lbid=1&cpmc=&cplb=&ggxh=&scqy=&jldw=&jldw2=&cPrice=hdind&mPrice=&dq=&qxh=&jglx=&jglx2=&bjsc=&sid=&TimeRange=6&startdate=2011/1/23&enddate=2013/1/22&ChartSize=bk&ChartType=1&MajorVGrid=1&HGrid=1&style=0&avgType1=None&movAvg1=10&avgType2=None&movAvg2=20&avgType3=None&movAvg3=50&Indicator1=None&Indicator2=None&Indicator3=None&Indicator4=None&Band=None&Button1=%E7%BB%98%E5%9B%BE&"
                    alt="橡胶价格指数曲线图"/></a></div>
            <div id="con_two_5" style="display:none"><a
                    href="http://www.xxxx.net/chart/pricechart.shtml?index=9&showexp=1" target="_blank"><img
                    src="http://www.xxxx.com/chart/pricechart1.shtml?Button1=%E7%BB%98%E5%9B%BE&Button1=%E5%88%B7%E6%96%B0&index=9&showexp=1&kType=0&lb=1&lbid=1&cpmc=&cplb=&ggxh=&scqy=&jldw=&jldw2=&cPrice=hdind&mPrice=&dq=&qxh=&jglx=&jglx2=&bjsc=&sid=&TimeRange=6&startdate=2011/1/23&enddate=2013/1/22&ChartSize=bk&ChartType=1&MajorVGrid=1&HGrid=1&style=0&avgType1=None&movAvg1=10&avgType2=None&movAvg2=20&avgType3=None&movAvg3=50&Indicator1=None&Indicator2=None&Indicator3=None&Indicator4=None&Band=None&Button1=%E7%BB%98%E5%9B%BE&"
                    alt="丁苯橡胶价格指数曲线图"/></a></div>
            <div id="con_two_6" style="display:none"><a
                    href="http://www.xxxx.net/chart/pricechart.shtml?index=10&showexp=1" target="_blank"><img
                    src="http://www.xxxx.com/chart/pricechart1.shtml?Button1=%E7%BB%98%E5%9B%BE&Button1=%E5%88%B7%E6%96%B0&index=10&showexp=1&kType=0&lb=1&lbid=1&cpmc=&cplb=&ggxh=&scqy=&jldw=&jldw2=&cPrice=hdind&mPrice=&dq=&qxh=&jglx=&jglx2=&bjsc=&sid=&TimeRange=6&startdate=2011/1/23&enddate=2013/1/22&ChartSize=bk&ChartType=1&MajorVGrid=1&HGrid=1&style=0&avgType1=None&movAvg1=10&avgType2=None&movAvg2=20&avgType3=None&movAvg3=50&Indicator1=None&Indicator2=None&Indicator3=None&Indicator4=None&Band=None&Button1=%E7%BB%98%E5%9B%BE&"
                    alt="顺丁橡胶价格指数曲线图"/></a></div>
        </div>
    </div>

</div>

<div class="m_right">

    <div class="ads01">
        <a href="#" target="_blank"><img src="images/ads02.jpg" alt="XXXX网大宗商品数据云终端"/></a>
    </div>

    <div class="ads02">
        <a href="http://news.xxxx.net/2012/zt/" target="_blank"><img src="images/ads03.jpg" alt="XX专题"/></a>
    </div>
    <div class="box_comm" style="height:298px;">
        <h2><a href="http://price.xxxx.net/getDdfListIndex.lz" id="TitY0" onmouseover="MenuY(0)" class="on"
               target="_blank">周涨跌排行榜</a></h2>

        <div id="ContY0">
            <table width="240" cellpadding="0" cellspacing="0" class="TabNav">
                <tr>
                    <td width="90">产品名(规格)</td>
                    <td width="35">地区</td>
                    <td width="65">周涨跌幅</td>
                    <td width="50">
                        <span id="TitW1" onmouseover="MenuW(1)" onclick="copytoclipbyid('ContW1','周跌幅')"
                              title="点击可复制">跌</span>
                        <span id="TitW0" onmouseover="MenuW(0)" onclick="copytoclipbyid('ContW0','周涨幅')"
                              title="点击可复制">涨</span>
                    </td>
                </tr>
            </table>

            <div class="charts_data" id="topPriceDiv0">
                <ul id="ContW0"></ul>
                <ul id="ContW1" style="display:none"></ul>
            </div>
            <SCRIPT>
                <!--
                function startmarquee(lh, speed, delay, index) {
                    var t;
                    var p = false;
                    var o = document.getElementById("topPriceDiv" + index);
                    o.innerHTML += o.innerHTML;
                    o.onmouseover = function () {
                        p = true
                    }
                    o.onmouseout = function () {
                        p = false
                    }
                    o.scrollTop = 0;
                    function start() {
                        t = setInterval(scrolling, speed);
                        if (!p) o.scrollTop += 2;
                    }

                    function scrolling() {
                        if (o.scrollTop % lh != 0) {
                            o.scrollTop += 2;
                            if (o.scrollTop >= o.scrollHeight / 2) o.scrollTop = 0;
                        }
                        else {
                            clearInterval(t);
                            setTimeout(start, delay);
                        }
                    }

                    setTimeout(start, delay);
                }
                startmarquee(26, 20, 2000, 0);
                //-->
            </SCRIPT>

        </div>

    </div>
</div>
</div>
</div>

<div class="footer">
    <p>
        <a target="_blank" href="http://www.xxxx.net/about/">关于我们</a>
        <a target="_blank" href="http://www.xxxx.net/about/chanpin.htm">服务项目</a>
        <a target="_blank" href="http://news.xxxx.net/2012/cooperation/">广告服务</a>
        <a target="_blank" href="http://www.xxxx.net/about2/%E5%85%A5%E7%BD%91%E9%A1%BB%E7%9F%A5.html">入网须知</a>
        <a target="_blank" href="http://www.xxxx.net/about2/%E6%8A%80%E6%9C%AF%E6%94%AF%E6%8C%81.html">技术支持</a>
        <a target="_blank" href="http://www.xxxx.net/about2/%E6%B3%95%E5%BE%8B%E5%A3%B0%E6%98%8E.html">法律声明</a>
        <a target="_blank" href="http://www.xxxx.net/about/contact.htm">联系我们</a>
        <a target="_blank" href="http://www.xxxx.net/about2/汇款方式2.html">汇款方式</a>
        <a target="_blank" href="http://www.xxxx.net/about/link.htm">友情链接</a>
        <a target="_blank" href="http://www.xxxx.net/s/XX网络诊断工具.rar">网络诊断工具</a>
        <a target="_blank" href="http://www.xxxx.net/about/anli/">建网案例</a>
        <a target="_blank" href="http://news.xxxx.net/2012/hr/">人才招聘</a>
        <a target="_blank" href="http://news.xxxx.net/2012/sitemap/">网站地图</a>
    </p>

    <p>版权所有 XXXX网 ICP 经营许可证编号为:</p>
</div>
</div>

</body>
</html>