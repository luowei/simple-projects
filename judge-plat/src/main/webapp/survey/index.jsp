<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href=" ${ctx}/survey/css/main.css">

    <script src="${ctx}/survey/js/jquery-1.9.0.min.js" type="text/javascript"></script>
    <%--<script src=" ${ctx}/common/js/jquery.validate.min.js" type="text/javascript"></script>--%>
    <style type="text/css">
        .errspan{
            color: #ff0000
        }
    </style>
    <script type="text/javascript">

        var submitSurvey = function () {
            var flag = true
            $(".errspan").remove()
            var question01=$('input:radio[name="question01"]:checked');
            if($(question01).val()==null){
                $("#1").prepend("<span class='errspan'>请回答下面的问题</span>")
                flag = false ;
            }
            var question02=$('input:radio[name="question02"]:checked');
            if($(question02).val()==null){
                $("#2").prepend("<span class='errspan'>请回答下面的问题</span>")
                flag = false ;
            }
            var question03=$('input:radio[name="question03"]:checked');
            if($(question03).val()==null){
                $("#3").prepend("<span class='errspan'>请回答下面的问题</span>")
                flag = false ;
            }
            var question04=$('input:radio[name="question04"]:checked');
            if($(question04).val()==null){
                $("#4").prepend("<span class='errspan'>请回答下面的问题</span>")
                flag = false ;
            }
            var question05=$('input:radio[name="question05"]:checked');
            if($(question05).val()==null){
                $("#5").prepend("<span class='errspan'>请回答下面的问题</span>")
                flag = false ;
            }
            var question06=$('input:radio[name="question06"]:checked');
            if($(question06).val()==null){
                $("#6").prepend("<span class='errspan'>请回答下面的问题</span>")
                flag = false ;
            }
            var question07=$('input:radio[name="question07"]:checked');
            if($(question07).val()==null){
                $("#7").prepend("<span class='errspan'>请回答下面的问题</span>")
                flag = false ;
            }
            var question08=$('input:radio[name="question08"]:checked');
            if($(question08).val()==null){
                $("#8").prepend("<span class='errspan'>请回答下面的问题</span>")
                flag = false ;
            }
            var question09=$('input:radio[name="question09"]:checked');
            if($(question09).val()==null){
                $("#9").prepend("<span class='errspan'>请回答下面的问题</span>")
                flag = false ;
            }
            if($("#question10").val()==null){
                $("#10").prepend("<span class='errspan'>请回答下面的问题</span>")
                flag = false ;
            }
            if(!flag){
                return;
            }else{
                 $("#surveyForm").submit()
            }

        }

    </script>
    <style type="text/css">
        #frmSurvey label.error {
            margin-right: 5px;
            color: #F00;
        }
    </style>
    <title>隆众石化网客户满意度调查问卷</title>
</head>

<body>
<c:if test="${dataEmpty eq true}">
    <script type="text/javascript">
        alert("您提交的数据不完整，* 标记的必须选择")
    </script>
</c:if>
<c:if test="${success eq true}">
    <script type="text/javascript">
        alert("感谢您的参与，提交成功！")
    </script>
</c:if>
<c:if test="${voted eq true}">
    <script type="text/javascript">
        alert("您已经投过票了")
    </script>
    <div style="text-align: center;margin-top: 30px;">
        <h2><a href="http://www.oilchem.net">返回</a></h2>
    </div>
</c:if>
<c:if test="${voted ne true}">
    <div class="container">
        <img alt="" src=" ${ctx}/survey/images/banner.jpg" height="300" width="1000">

        <div class="nr">
            <h1><img alt="" src=" ${ctx}/survey/images/tit_1.gif" height="20" width="74"></h1>

            <div class="intro">
                <p>尊敬的客户您好！<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;非常感谢您在过去的日子里对“隆众石化网”的大力支持和信任！
                    “为客户创造价值”一直是隆众人恪守的价值观，为了在未来的日子里，隆众能够给您提供更专业、更全面、更有价值的服务，我们开展此次客户调查活动。希望您能把在使用隆众产品过程中遇到的问题，或者您对隆众新的期望告诉我们，您的任何意见和建议都是“隆众石化网”的宝贵财富，并将激励我们不断改善提高。
                    <font color="red">为感谢您的热情参与，所有参与问卷调查的客户，隆众均有礼品相赠。</font>
                </p>
            </div>
            <h1 class="mart"><img alt="" src="${ctx}/survey/images/tit_3.gif" height="20" width="74"></h1>

            <form id="surveyForm" name="surveyForm" action="${ctx}/survey/submit.do" method="post">
                <div class="examframe">
                    <div class="exam_center">
                        <div id="1">
                            <h4><dfn>*</dfn>1. 您最早怎么找到的我们？</h4>
                            <ul>
                                <li><input name="question01" value="朋友或客户介绍" type="radio">朋友或客户介绍</li>
                                <li><input name="question01" value="百度等搜索引擎" type="radio">百度等搜索引擎</li>
                                <li><input name="question01" value="我们主动联系的您" type="radio">我们主动联系的您</li>
                            </ul>
                        </div>
                        <div id="2">
                            <h4><dfn>*</dfn>2. 出于什么原因让您成为我们的会员？</h4>
                            <ul>
                                <li><input name="question02" value="品牌好" type="radio">品牌好</li>
                                <li><input name="question02" value="服务优质" type="radio">服务优质</li>
                                <li><input name="question02" value="价格适中" type="radio">价格适中</li>
                                <li><input name="question02" value="朋友或客户推荐" type="radio">朋友或客户推荐</li>
                            </ul>
                        </div>
                        <div id="3">
                            <h4><dfn>*</dfn>3. 我们网站使用是否方便？</h4>
                            <ul>
                                <li><input name="question03" value="还可以" type="radio">还可以</li>
                                <li><input name="question03" value="一般般" type="radio">一般般</li>
                                <li><input name="question03" value="很不好找" type="radio">很不好找</li>
                            </ul>
                        </div>
                        <div id="4">
                            <h4><dfn>*</dfn>4. 您对我们的收费如何看待?</h4>
                            <ul>
                                <li><input name="question04" value="性价比高" type="radio">性价比高</li>
                                <li><input name="question04" value="相比其他较高" type="radio">相比其他较高</li>
                                <li><input name="question04" value="很高" type="radio">很高</li>
                            </ul>
                        </div>
                        <div id="5">
                            <h4><dfn>*</dfn>5. 您平时使用哪些服务最多？</h4>
                            <ul>
                                <li><input name="question05" value="价格中心" type="radio">价格中心</li>
                                <li><input name="question05" value="周月报" type="radio">周月报</li>
                                <li><input name="question05" value="市场价格等即时资讯" type="radio">市场价格等即时资讯</li>
                                <li><input name="question05" value="日评等评论服务" type="radio">日评等评论服务</li>
                            </ul>
                        </div>
                        <div id="6">
                            <h4><dfn>*</dfn>6. 您的问题反馈，隆众处理速度如何？</h4>
                            <ul>
                                <li><input name="question06" value="及时高效" type="radio">及时高效</li>
                                <li><input name="question06" value="基本解决问题" type="radio">基本解决问题</li>
                                <li><input name="question06" value="拖沓" type="radio">拖沓</li>
                            </ul>
                        </div>
                        <div id="7">
                            <h4><dfn>*</dfn>7. 我们的客服给您的感觉如何？</h4>
                            <ul>
                                <li><input name="question07" value="态度好也很专业" type="radio">态度好也很专业</li>
                                <li><input name="question07" value="态度好但不专业" type="radio">态度好但不专业</li>
                                <li><input name="question07" value="态度不好" type="radio">态度不好</li>
                            </ul>
                        </div>
                        <div id="8">
                            <h4><dfn>*</dfn>8. 您希望我们在哪个时间段与您沟通？</h4>
                            <ul>
                                <li><input name="question08" value="上午10点前" type="radio">上午10点前</li>
                                <li><input name="question08" value="上午10点后" type="radio">上午10点后</li>
                                <li><input name="question08" value="下午3点前" type="radio">下午3点前</li>
                                <li><input name="question08" value="下午3点后" type="radio">下午3点后</li>
                            </ul>
                        </div>
                        <div id="9">
                            <h4><dfn>*</dfn>9. 除电话外，您还倾向那种沟通方式？</h4>
                            <ul>
                                <li><input name="question09" value="QQ" type="radio">QQ</li>
                                <li><input name="question09" value="微信" type="radio">微信</li>
                                <li><input name="question09" value="邮件" type="radio">邮件</li>
                            </ul>
                        </div>

                        <div id="10">
                            <h4><dfn>*</dfn>10. 您希望我们今后增加哪些服务？</h4>
                            <dd><textarea id="question10" name="question10" maxlength="1000" cols="" rows=""
                                          class="checkarea"></textarea></dd>
                            <dd></dd>
                            <dd>(最多可输入1000个字)</dd>
                            <div class="clear"></div>
                        </div>
                        <div class="div_main">

                            <div class="prize">
                                <b>为感谢您对本次活动的参与和支持，隆众石化网为您准备了精美的奖品。请留下电话、姓名、地址，我们电话核实后为您邮寄礼品。</b><br>
                                <br>
                                姓名
                                <input name="userName" id="userName" class="username" type="text" value="${realName}">
                                手机号码
                                <input name="userMobie" id="userMobie" class="mobile" type="text" value="${userName}"> <br/><br/>
                                地址
                                <input name="userAddress" id="userAddress" class="address" type="text" value="${userAddress}">
                            </div>
                        </div>
                    </div>
                    <input type="button" onclick="submitSurvey()" style="width: 110px;height: 38px; background:url('${ctx}/survey/images/btn.gif') no-repeat">
                </div>
            </form>

        </div>
        <div><img alt="" src=" ${ctx}/survey/images/bg_2.gif" height="19" width="1000"></div>
    </div>
</c:if>
</body>
</html>