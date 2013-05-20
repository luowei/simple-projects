// 只允许文本框输入数字
function inputNumberText() {
	if (!(((window.event.keyCode >= 48) && (window.event.keyCode <= 57)) || (window.event.keyCode == 13) || (window.event.keyCode == 46) || (window.event.keyCode == 45))) {
		window.event.keyCode = 0;
	}
	return;
}

// 分页中上页下页判断
function trueOrFalse(flag,pageNumber,maxPage){
	if(pageNumber==maxPage && flag==2){
		alert("已经是最后一页");
		return false;
	}else if(pageNumber==1 && flag==1){
		alert("已经是第一页");
		return false;
	}else{
		return true;
	}
}

// 分页中跳到哪一页用
function goPage(pageNumber,len,maxPage,url){ 
	var page=document.getElementById("setPage").value;
	if(page.length!=0){
		if(page!=pageNumber){
			if(page<=maxPage){
				document.formPage.action=url+"?pageNumber="+page+"&len="+len;
				document.formPage.submit();
			}else{
				alert("超过最大页数，请重新输入！");
			}	
		}else{
			alert("您正在当前页，请重新输入！");
		}	
	}else{
		alert("请输入要到第几页！");
	}
}	

// 提交表单用 ID为表单ID
function doSubmit(url,id) {  
	document.getElementById(id).action = url;
	document.getElementById(id).submit();
}

// 传递进来一个字符串，剔除该字符串有效值之前的空格部分
function delStringNULL(str){ 
	lIdx=0;rIdx=str.length; 
	if (delStringNULL.arguments.length==2) 
		act=delStringNULL.arguments[1].toLowerCase(); 
	else 
		act="all"; 
	for(var i=0;i<str.length;i++){ 
		thelStr=str.substring(lIdx,lIdx+1); 
		therStr=str.substring(rIdx,rIdx-1); 
		if ((act=="all" || act=="left") && thelStr==" "){ 
			lIdx++; 
		} 
		if ((act=="all" || act=="right") && therStr==" "){ 
			rIdx--; 
		} 
	} 
	str=str.slice(lIdx,rIdx); 
	return str; 
} 

// 扩展窗体
FormEditWin = function(){
	var curFormWin;
	return {
		width : 100,
		height : 100,
		
		createWin : function(winId, winTitle, iframePage, closeFun) {
			// 供各类型窗口创建时调用
			var win = Ext.getCmp(winId);
			if (!win) {
				win = new Ext.Window({
					id : winId,
					title : "编辑窗口-" + winTitle,
					width : this.width,
					height : this.height,			
					maximizable : true,
					constrainHeader:true,
					modal : true,
					html : "<iframe width='100%' height='100%' frameborder='0' src='"
							+ iframePage + "'></iframe>"
				});
				this.reloadNavNode = closeFun;
			}
			curFormWin = win;
			return win;
		},
		reloadNavNode : function() {
		},
		close : function() {
			if(curFormWin){
				curFormWin.close();
			}
		}
	}
}();

/**
 * 弹出层 windowID 层ID windowName 层名字 url 传递链接
 */
function layerDisplay(windowID,windowName,url,width,height){
	FormEditWin.width=width;
	FormEditWin.height=height;
	var window = FormEditWin.createWin(windowID, windowName, url, function() {
		// parentNode.reload();
	});
	window.show();
}

// 关闭弹出层
function winClose(){
	window.parent.FormEditWin.close();
}

// 判断复选框是否选中
function checkboxChecked(name){
	var flag=0;
	var proid=document.getElementsByName(name);	
	for(var a=0;a<proid.length;a++){
		if(proid[a].checked==true){
			flag=1;
		}
	}
	return flag;
}

function getObj(id){
	var obj=document.getElementById(id);
	return obj;
}

function getObjValueDelNull(id){
	var objValue=document.getElementById(id).value;
	objValue=delStringNULL(objValue);
	return objValue;
}

// 判断是否正确的邮件形式
function isEmail(vEMail) {
	var regInvalid = /(@.*@)|(\.\.)|(@\.)|(\.@)|(^\.)/;
	var regValid = /^.+\@(\[?)[a-zA-Z0-9\-\.]+\.([a-zA-Z]{2,3}|[0-9]{1,3})(\]?)$/;
	return (!regInvalid.test(vEMail) && regValid.test(vEMail));
}

// 上传文件的验证
function uploadCheck() { 

	var houzui="RAR,ZIP,XLS,TXT,DOC,DOCX,XLSX";

	var fileName = document.getElementById("file").value;
	
	if(fileName==""){
		return true;
	} 
	 
	var exName = fileName.substr(fileName.lastIndexOf(".") + 1).toUpperCase(); 
	
	var returnFlag=false;
	var tempStr=houzui.split(","); 
	for(var i=0;i<tempStr.length;i++){
		if(tempStr[i]==exName){
			returnFlag=true;
			break;
		}
	}   
	if(returnFlag){ 
		return true;
	}else{ 
		alert("请输入正确的文件,后缀名必须是rar,zip,xls,txt,doc,docx,xlsx之一");
		return false;
	} 
	
}





