<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="../../commons/head.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收集信息</title>
<style type="text/css">
<!--
body {
	background-color: ;
}
-->

</style>
<link rel="stylesheet" type="text/css" href="../../css/a.css">
<link rel="stylesheet" href="../../themes/ui-lightness/jquery.ui.all.css">
<link rel="stylesheet" type="text/css" href="../../css/tom.css">
<script src="../../js/jquery-1.6.2.js"></script>
<script src="../../ui/jquery.ui.core.js"></script>
<script src="../../ui/jquery.ui.widget.js"></script>
<script src="../../ui/jquery.ui.tabs.js"></script>
<script src="../../ui/jquery.ui.progressbar.js"></script>


<script type="text/javascript">
var rs;
var t;
var selectedphonebrands=new Array();
var processValue=0;  //进度条的值
var stepValue;       //步进值
function openW(url)
{
  window.open("/ttpod/manage/grapInfor/grapPhone.do?url="+url,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=no, copyhistory=yes, width=1280, height=1024"); 
}

/*加载手机页面*/
function loadPhonePage()
{
	processValue=0;
    $("#phonebrand2").hide();                 //隐藏选择框
    stepValue=100/selectedphonebrands.length; //步进value 
    $( "#progressbar" ).progressbar({value: processValue}); //设置进度条
	$("#phones").html(""); //清空显示
	var j=0;
	var wantToGrabBrand="";
    //获取选择的品牌列表
	for(j=0;j<selectedphonebrands.length;j++)
	{
		var t=selectedphonebrands[j]+"@";		
	    wantToGrabBrand=wantToGrabBrand+"<a href=\'"+t.split("@")[1].toString()+"\'>"+t.split("@")[0].toString()+"</a>"
	    
	}
	//显示需要抓取的机型
	$("#wantToGrabBrand").html(wantToGrabBrand); 
	//一个一个的抓取
	iterator(0);
	
}

/*迭代选择的手机品牌抓取并保存*/
function iterator(j)
{
   if(j>=selectedphonebrands.length)
   {
   		$("#GrabingBrand").html("");
   		selectedphonebrands.splice(0,selectedphonebrands.length); //清除选择数据
   		return ;
   }
   else
   {
   		var t=selectedphonebrands[j]+"@";
   		var brandName=t.split("@")[0];
   		var enCodeBrandName=encodeURI(brandName);
   		enCodeBrandName=encodeURI(enCodeBrandName);
   		var time=new Date();
		var turi="${ctx }/manage/grapInfor/grapPhone.do?url="+t.split("@")[1].toString()+"&brandName="+enCodeBrandName+"&time="+time;
		
	    $("#GrabingBrand").html(t.split("@")[0]);
	    $.ajax({url:turi,
	    		async:true,
	    		dataType:"html",
	    		success:function (data) 
								{
									processValue=processValue+stepValue;
									$( "#progressbar" ).progressbar({value: processValue});
									$("#phones").append(data); //显示抓取的东西
									var d=new Date();
									turi="${ctx }/manage/grapInfor/savePhones.do?time="+d;
								    $.ajax({url:turi,
	    									async:false,
	    									dataType:"json",
	    									success:function (data)
		    									{
		    										$("#rs").html("抓取的数量:"+data.grapedPhoneNum+
		    													  "</br>保存的数量:"+data.savedPhoneNum+
		    													  "</br>临时表已有:"+data.tempTableExsitNum+
		    													  "</br>真实表已有:"+data.realTableExsitNum);
		    									}
		    								}
	    								   );
									iterator(j+1);
								}    		
	    		});
   }
    
}
/*改变选择的手机项*/
function change(selectedphonebrand)
{
	if(selectedphonebrands==null||selectedphonebrands.length==0)
	{
		selectedphonebrands[0]=selectedphonebrand;
	}
	else
	{
			//删除已经存在的元素
			var i=0;
			var added=true;
			for(i=0;i<selectedphonebrands.length;i++)
			{
				if(selectedphonebrands[i]==selectedphonebrand)
				{
				    added=false;
					selectedphonebrands.splice(i,1);			
				}				
			}
			//集合中 无添加
			if(added==true)
			{
				selectedphonebrands[selectedphonebrands.length]=selectedphonebrand;
			}

	}	

}
/*加载手机品牌信息*/
function loadPhoneBrand()
{
  $.get("${ctx}/manage/grapInfor/loadPhonebrand.do", function(result){$("#phonebrand2").html(result);});
  $("#phonebrand2").slideToggle("slow");
   
}
</script>
</head>

<body style="BACKGROUND-POSITION-Y: -120px; BACKGROUND-IMAGE: url(${ctx }/manage/images/bg.gif); BACKGROUND-REPEAT: repeat-x">
<script>
	$(function() 
	{
		$( "#tabs" ).tabs();
	}
	);
</script>
<div id="tabs">
	<ul>
		<li><a href="#tab-phonebrand" >品牌信息</a></li>
		<li><a href="#tab-phone" >机型信息</a></li>
	</ul>
	
	<div id="tab-phonebrand">

		<iframe frameborder=0  style="height:600px;width:100%" src="${ctx}/manage/grapInfor/grapPhonebrand.do"></iframe>

	</div>
	
	<div id="tab-phone">
		<div style="height:600px;width:100%">
			<table boder="1" width=100%>
				<tr>
					<td >
							<p>品牌名称<input type="text"  id="bn" onblur="1" onfocus="loadPhoneBrand()"/></p>
							<label>待抓取的品牌：<div id="wantToGrabBrand"></div></label>
							<label>正在抓取的品牌：<div id="GrabingBrand"></div></label>
					</td>
					<td width=20%><div id="rs"></div></td>
				</tr>
			</table>
			<div id="phonebrand2" style="background:#98bf21;display:none;"></div>
			<div id="progressbar"></div>
			<div id="phones" ></div>
		</div>
	</div>
</div>
</body>

</html>
