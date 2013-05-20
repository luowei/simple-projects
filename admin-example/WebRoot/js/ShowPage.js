/*
	aCurNum 	当前显示几条记录
	aTotalNum	总记录数量
	aCurPage	当前页
	aMaxRow		每页记录数量
	acolor 		改变颜色
	aWidth 		表格宽度
	aAction 	翻页执行文件
	aFooterNum 	页脚翻页显示数
*/
function ShowPage(aCurNum,aTotalNum,aCurPage,aMaxRow,aColor,aWidth,aAction,aFooterNum){
	var n, MyAction, i;
	var Start, EndPage;
	
	if(aFooterNum ==""){
		aFooterNum=10;
	}
	
	MyAction = aAction;
	
	if(MyAction == ""){
		MyAction = "?";
	}else if(MyAction.indexOf("?") == -1){
		MyAction = MyAction + "?";
	}else{
		MyAction = MyAction + "&";
	}
	
	if(aCurNum == ""){
		aCurNum = aMaxRow;
	}
	
	if(aCurPage < 1){
		aCurPage = 1;
	}
	
	if((aCurPage - 1) * aMaxRow > aTotalNum){
		if(aTotalNum % aMaxRow == 0){
			aCurPage = aTotalNum / aMaxRow;
		}else{
			aCurPage = Math.floor(aTotalNum / aMaxRow) + 1;
		}
	}
	
	if(aTotalNum % aMaxRow == 0){
		n = aTotalNum / aMaxRow;
	}else{
		n = Math.floor(aTotalNum / aMaxRow) + 1;
	}
	
	if(aColor == ""){
		aColor = "#FF0000";
	}
	
	if(aWidth == ""){
		aWidth = "95%";
	}
	
	document.write("<table border=0 cellpadding=0 cellspacing=3 width='");
	document.write(aWidth);
	document.write("' align=center >");

	document.write("<form method=post action=");
	document.write(aAction);
	document.write("><tr><td valign=middle>页次:<b>");
	//document.write("<tr><td valign=middle>页次:<b>");//
	document.write(aCurPage);
	document.write("</b>/<b>");
	document.write(n);
	document.write("</b>页 本页<b>");
	document.write(aCurNum);
	document.write(" </b> 总:<b>");
	document.write(aTotalNum);
	document.write("</b></td><td valign=middle align=right>分页:<b>");
	
	if(aCurPage < ((aFooterNum / 2) + 1)){
		Start = 1;
	}else{
		Start = aCurPage - (aFooterNum / 2);
	}
	
	if(Start + aFooterNum - 1 < n){
		EndPage = Start + aFooterNum - 1;
	}else{
		EndPage = n;
	}
	
	if(aCurPage == 1){
		document.write("<img src=/images/pl/FirstSele.gif border=0>");
	}else{
		document.write("<a href=" + MyAction + "page=1><img src=/images/pl/First.gif border=0 alt=首页></a>");
	}
	
	if(aCurPage > aFooterNum + 1){
		document.write(" <a href=" + MyAction + "page=" + (aCurPage - aFooterNum) + "><img src=/images/pl/Previous.gif border=0 alt=上" + aFooterNum + "页></a>");
	}

	for(var i = Start; i <= EndPage; i++){
		if(EndPage >= i){
			if(aCurPage == i){
				document.write(" <font style='color : " + aColor + ";text-decoration : underline;'><b>" + i + "</b></font>");
			}else{
				document.write(" <a href=" + MyAction + "page=" + i + " >" + i + "</a>");
			}
		}else{
			break;
		}
	}

	if(n > aFooterNum + aCurPage){
		document.write(" <a href=" + MyAction + "page=" + (aCurPage + aFooterNum) + "><img src=/images/pl/Next.gif border=0 alt=下" + aFooterNum + "页></a>");
	}
	
	if(aCurPage == n){
		document.write(" <img src=/images/pl/LastSele.gif border=0>");
	}else{
		document.write(" <a href=" + MyAction + "page=" + n + "><img src=/images/pl/Last.gif border=0 alt=尾页></a>");
	}
	
	document.write(" </b>转到:<input type=text name=page size=3 maxlength=10 value=");
	document.write(aCurPage);
	document.write("><input type=submit value=Go name=submit ></td></tr></form></table>");
	//document.write("><input type=submit value=Go name=submit ></td></tr></table>");//
}