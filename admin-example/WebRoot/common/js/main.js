//Pop-it menu- By Dynamic Drive - Modified by Wbird
//For full source code and more DHTML scripts, visit http://www.dynamicdrive.com
//This credit MUST stay intact for use
var menuOffX=0	//菜单距连接文字最左端距离
var menuOffY=18	//菜单距连接文字顶端距离

var fo_shadows=new Array()
var linkset=new Array()

////No need to edit beyond here

var ie4=document.all&&navigator.userAgent.indexOf("Opera")==-1
var ns6=document.getElementById&&!document.all
var ns4=document.layers
function openScript(url, width, height){
    var Win = window.open(url,"openScript",'width=' + width + ',height=' + height + ',resizable=1,scrollbars=yes,menubar=no,status=no' );
}
function mybook() {
    h = 300;
    w = 300;
    t = ( screen.availHeight - h ) / 2;
    l = ( screen.availWidth - w ) / 2;
    window.open("http://forumAd.to5198.com/common/login.jsp?sCheckUrl=/out/login.jsp&sDesUrl=/out/mybook.jsp", "我的服务",
        "left=" + l + ",top=" + t + ",height=" + h + ",width=" + w
            + ",toolbar=no,status=no,scrollbars=no,resizable=yes" );
    return;
}
function showmenu(e,vmenu,mod){
    if (!document.all&&!document.getElementById&&!document.layers)
        return
    which=vmenu
    clearhidemenu()
    ie_clearshadow()
    menuobj=ie4? document.all.popmenu : ns6? document.getElementById("popmenu") : ns4? document.popmenu : ""
    menuobj.thestyle=(ie4||ns6)? menuobj.style : menuobj

    if (ie4||ns6)
        menuobj.innerHTML=which
    else{
        menuobj.document.write('<layer name=gui bgColor=#E6E6E6 width=165 onmouseover="clearhidemenu()" onmouseout="hidemenu()">'+which+'</layer>')
        menuobj.document.close()
    }
    menuobj.contentwidth=(ie4||ns6)? menuobj.offsetWidth : menuobj.document.gui.document.width
    menuobj.contentheight=(ie4||ns6)? menuobj.offsetHeight : menuobj.document.gui.document.height

    eventX=ie4? event.clientX : ns6? e.clientX : e.x
    eventY=ie4? event.clientY : ns6? e.clientY : e.y

    var rightedge=ie4? document.body.clientWidth-eventX : window.innerWidth-eventX
    var bottomedge=ie4? document.body.clientHeight-eventY : window.innerHeight-eventY
    if (rightedge<menuobj.contentwidth)
        menuobj.thestyle.left=ie4? document.body.scrollLeft+eventX-menuobj.contentwidth+menuOffX : ns6? window.pageXOffset+eventX-menuobj.contentwidth : eventX-menuobj.contentwidth
    else
        menuobj.thestyle.left=ie4? ie_x(event.srcElement)+menuOffX : ns6? window.pageXOffset+eventX : eventX

    if (bottomedge<menuobj.contentheight&&mod!=0)
        menuobj.thestyle.top=ie4? document.body.scrollTop+eventY-menuobj.contentheight-event.offsetY+menuOffY-23 : ns6? window.pageYOffset+eventY-menuobj.contentheight-10 : eventY-menuobj.contentheight
    else
        menuobj.thestyle.top=ie4? ie_y(event.srcElement)+menuOffY : ns6? window.pageYOffset+eventY+10 : eventY
    menuobj.thestyle.visibility="visible"
    ie_dropshadow(menuobj,"#999999",3)
    return false
}

function ie_y(e){
    var t=e.offsetTop;
    while(e=e.offsetParent){
        t+=e.offsetTop;
    }
    return t;
}
function ie_x(e){
    var l=e.offsetLeft;
    while(e=e.offsetParent){
        l+=e.offsetLeft;
    }
    return l;
}
function ie_dropshadow(el, color, size)
{
    var i;
    for (i=size; i>0; i--)
    {
        var rect = document.createElement('div');
        var rs = rect.style
        rs.position = 'absolute';
        rs.left = (el.style.posLeft + i) + 'px';
        rs.top = (el.style.posTop + i) + 'px';
        rs.width = el.offsetWidth + 'px';
        rs.height = el.offsetHeight + 'px';
        rs.zIndex = el.style.zIndex - i;
        rs.backgroundColor = color;
        var opacity = 1 - i / (i + 1);
        rs.filter = 'alpha(opacity=' + (100 * opacity) + ')';
        //el.insertAdjacentElement('afterEnd', rect);
        fo_shadows[fo_shadows.length] = rect;
    }
}
function ie_clearshadow()
{
    for(var i=0;i<fo_shadows.length;i++)
    {
        if (fo_shadows[i])
            fo_shadows[i].style.display="none"
    }
    fo_shadows=new Array();
}


function contains_ns6(a, b) {
    while (b.parentNode)
        if ((b = b.parentNode) == a)
            return true;
    return false;
}

function hidemenu(){
    if (window.menuobj)
        menuobj.thestyle.visibility=(ie4||ns6)? "hidden" : "hide"
    ie_clearshadow()
}

function dynamichide(e){
    if (ie4&&!menuobj.contains(e.toElement))
        hidemenu()
    else if (ns6&&e.currentTarget!= e.relatedTarget&& !contains_ns6(e.currentTarget, e.relatedTarget))
        hidemenu()
}

function delayhidemenu(){
    if (ie4||ns6||ns4)
        delayhide=setTimeout("hidemenu()",500)
}

function clearhidemenu(){
    if (window.delayhide)
        clearTimeout(delayhide)
}

function highlightmenu(e,state){
    if (document.all)
        source_el=event.srcElement
    else if (document.getElementById)
        source_el=e.target
    if (source_el.className=="menuitems"){
        source_el.id=(state=="on")? "mouseoverstyle" : ""
    }
    else{
        while(source_el.id!="popmenu"){
            source_el=document.getElementById? source_el.parentNode : source_el.parentElement
            if (source_el.className=="menuitems"){
                source_el.id=(state=="on")? "mouseoverstyle" : ""
            }
        }
    }
}

if (ie4||ns6)
    document.onclick=hidemenu
function doSClick() {
    var targetId, srcElement, targetElement, imageId, imageElement;
    srcElement = window.event.srcElement;
    targetId = srcElement.id + "content";
    targetElement = document.all(targetId);
    imageId = srcElement.id;
    imageId = imageId.charAt(0);
    imageElement = document.all(imageId);
    if (targetElement.style.display == "none") {
        imageElement.src = "Skins/Default/minus.gif"
        targetElement.style.display = "";
    } else {
        imageElement.src = "Skins/Default/plus.gif"
        targetElement.style.display = "none";
    }
}
function doClick() {
    var targetId, srcElement, targetElement;
    srcElement = window.event.srcElement;
    targetId = srcElement.id + "_content";
    targetElement = document.all(targetId);
    if (targetElement.style.display == "none") {
        srcElement.src = "Skins/Default/minus.gif"
        targetElement.style.display = "";
    } else {
        srcElement.src = "Skins/Default/plus.gif"
        targetElement.style.display = "none";
    }
}

//HTML过滤函数
function HTMLEncode(text)
{
    text = text.replace(/&/g, "&amp;") ;
    text = text.replace(/"/g, "&quot;") ;
    text = text.replace(/</g, "&lt;") ;
    text = text.replace(/>/g, "&gt;") ;
    text = text.replace(/'/g, "&#146;") ;

    return text ;
}
function bbimg(o){
    var zoom=parseInt(o.style.zoom, 10)||100;zoom+=event.wheelDelta/12;if (zoom>0) o.style.zoom=zoom+'%';
    return false;
}
function LzWeb_ViewCode(replyid)
{
    var bodyTag="<html><head><style type=text/css>.quote{margin:5px 20px;border:1px solid #CCCCCC;padding:5px; background:#F3F3F3 }\nbody{boder:0px}.HtmlCode{margin:5px 20px;border:1px solid #CCCCCC;padding:5px;background:#FDFDDF;font-size:14px;font-family:Tahoma;font-style : oblique;line-height : normal ;font-weight:bold;}\nbody{boder:0px}</style></head><BODY bgcolor=\"#FFFFFF\" >";
    bodyTag+=document.getElementById('scode'+replyid).CodeText.value
    bodyTag+="</body></html>"
    preWin=window.open('preview','','left=0,top=0,width=550,height=400,resizable=1,scrollbars=1, status=1, toolbar=1, menubar=0');
    preWin.document.open();
    preWin.document.write(bodyTag);
    preWin.document.close();
    preWin.document.title="查看贴子内容";
    preWin.document.charset="UTF-8";
}



var img=null;
function loadupfile(str){
    if(checkExt(str)){
        chkimg(str);
    }else{
        if(img)img.removeNode(true);
        footershowpic1.innerHTML = "";footershowpic2.innerHTML = "";
    }
}
function formatnumber(num,L){
    var Lnum = Math.pow(10,L)
    return Math.round(num*Lnum)/Lnum
}
function chkimg(inp)
{
    if(img)img.removeNode(true);
    img=document.createElement("img");
    img.attachEvent("onreadystatechange",isimg);
    img.attachEvent("onerror",notimg);
    img.src=inp;
}
function isimg()
{
    footershowpic2.insertAdjacentElement("BeforeEnd",img);
    var wd=img.offsetWidth;
    var hg=img.offsetHeight;
    footershowpic1.innerHTML = "<font color=blue>图片大小<font color=red>" + formatnumber(img.fileSize/1024,2) +"</font>K 图片实际宽度<font color=red>"+ wd +"</font>象素 图片实际高度<font color=red>"+ hg + "</font>象素</font>";
    if(img.width>580){ img.width=580;img.height=parseInt(580*hg/wd);}
}
function notimg()
{
    footershowpic1.innerHTML = "";
    alert("您插入的不是图片，请重新选择插入");
}

function trim(s)
{
    while (s.substring(0, 1) == ' ')
    {
        s = s.substring(1, s.length);
    }
    while (s.substring(s.length-1, s.length) == ' ')
    {
        s = s.substring(0, s.length-1);
    }
    return s;
}
function checkExt(str){
    var strs=str.toLowerCase();
    var lens=strs.length;
    var extname=strs.substring(lens-4,lens);
    var extname2=strs.substring(lens-5,lens);
    if(extname2==".jpeg") return true;
    if(extname==".jpg" || extname==".gif" || extname==".pic" || extname==".png" || extname==".bmp" || extname==".tif" || extname==".iff") return true;
    return false;
}
function checkExtasp(str){
    var strs=str.toLowerCase();
    var lens=strs.length;
    var extname=strs.substring(lens-4,lens);
    var extname2=strs.substring(lens-5,lens);
    if(extname2==".aspx") return true;
    if(extname==".asp" || extname==".asa" || extname==".dll") return true;
    return false;
}
var Sys = {};
var ua = navigator.userAgent.toLowerCase();
var s;
(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
    (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
        (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
            (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
                (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
if(Sys.ie==null){ Sys.ie = -1;}
var ie = parseInt(Sys.ie);
var BrowserInfo = new Object() ;
BrowserInfo.MajorVer = ie;
BrowserInfo.MinorVer = 0 ;
BrowserInfo.IsIE55OrMore = BrowserInfo.MajorVer >= 6 ;
//BrowserInfo.MajorVer = navigator.appVersion.match(/MSIE (.)/)[1] ;
//BrowserInfo.MinorVer = navigator.appVersion.match(/MSIE .\.(.)/)[1] ;
//BrowserInfo.IsIE55OrMore = BrowserInfo.MajorVer >= 6 || ( BrowserInfo.MajorVer >= 5 && BrowserInfo.MinorVer >= 5 ) ;
var MyLzNewWin=null;

function MyLzNewWinOpen(MyUrl,wname,w,h,f){
    var obj=document.EditPoPWindows;
    if(w==null) w = 600;
    if(h==null) h = 500;
    if(wname==null) wname = "junwindows";
    if(MyUrl==null) return ;
    if(BrowserInfo.IsIE55OrMore){
        if (MyLzNewWin && MyLzNewWin.open && !MyLzNewWin.closed){
            MyLzNewWin.moveTo(0, 0);
            MyLzNewWin.focus();
            //return ;
        }
    }
    MyLzNewWin=window.open("about:blank",wname,"width="+w+",height="+h+",top=0px,left=0px,Status=yes,resizable=yes,scrollbars=yes,toolbar=no,menubar=no");
    if(BrowserInfo.IsIE55OrMore && f != null){
        MyLzNewWin.moveTo(0,0);
        MyLzNewWin.resizeTo(screen.availWidth,screen.availHeight);
        MyLzNewWin.outerWidth=screen.availWidth;
        MyLzNewWin.outerHeight=screen.availHeight;
    }
    obj.action=MyUrl;
    obj.submit();
}

function getObject(id)
{
    if (document.getElementById)
    //IE 5.x or NS 6.x or above
        return document.getElementById(id);
    else if (document.all)
    //IE 4.x
        return document.all[id];
    else
    //Netscape 4.x
        return document[id];
}
//alert(ShowPageMsg(10,100,3,10,"","","",5,""))
function ShowPageMsg(aCurNum,aTotalNum,aCurPage,aMaxRow,aColor,aWidth,aAction,aFooterNum,Msg){
    //aCurNum 当前显示几条记录,aTotalNum,总记录数量,aCurPage当前页,aMaxRow每页记录数量,aColor 改变颜色,aWidth 表格宽度,aAction 翻页执行文件
    //aFooterNum 页脚翻页显示数
    var oHTML="";
    var n,MyAction ,i;
    var Start ,EndPage ;
    if (aFooterNum==""){ aFooterNum=10 ;}
    MyAction=aAction ;
    if(MyAction==""){
        MyAction="?ct="+aTotalNum;
    }else if(MyAction.indexOf("?")==-1){
        MyAction=MyAction + "?ct="+aTotalNum;
    }else{
        MyAction=MyAction + "&ct="+aTotalNum;
    }
    if(aCurNum==""){aCurNum=aMaxRow;}
    if(aCurPage<1){aCurPage=1;}
    if((aCurPage-1)*aMaxRow>aTotalNum){
        if( (aTotalNum % aMaxRow)==0 ){
            aCurPage= aTotalNum / aMaxRow ;
        }else{
            aCurPage= aTotalNum / aMaxRow + 1 ;
        }
    }
    if( (aTotalNum % aMaxRow)==0 ){
        n= aTotalNum / aMaxRow ;
    }else{
        n= aTotalNum / aMaxRow + 1 ;
    }
    n = parseInt(n)
    if(aColor =="" ){ aColor="#FF0000";}
    if(aWidth =="" ){ aWidth="95%";}

    oHTML += "<table border=0 cellpadding=0 cellspacing=3 width='";
    oHTML += String(aWidth);
    oHTML += "' align=center >";

    oHTML += "<form method=post action=";
    oHTML += String(aAction);
    oHTML += "><tr><td valign=middle>页次:<b>";
    oHTML += String(aCurPage);
    oHTML += "</b>/<b>";
    oHTML += String(n);
    oHTML += "</b>页 本页<b>";
    oHTML += String(aCurNum);
    oHTML += " </b> 总记录:<b>";
    oHTML += String(aTotalNum);
    oHTML += "</b>"+Msg+"</td><td valign=middle align=right>分页:<b>";
    if( aCurPage < (aFooterNum / 2) + 1 ){
        Start = 1 ;
    }else{
        Start = aCurPage - (aFooterNum / 2);
    }

    if(Start + aFooterNum - 1 < n){
        EndPage = Start + aFooterNum -1;
    }else{
        EndPage = n ;
    }
    if(aCurPage == 1){
        oHTML += "<img src=/images/pl/FirstSele.gif border=0>";
    }else{
        oHTML += "<a href="+MyAction+"&page=1><img src=/images/pl/First.gif border=0 alt=首页></a>";
    }
    if(aCurPage > aFooterNum + 1){
        oHTML += " <a href="+MyAction+"&page="+ (aCurPage - aFooterNum ) +"><img src=/images/pl/Previous.gif border=0 alt=上"+aFooterNum+"页></a>";
    }

    for(var i=Start;i<EndPage+1;i++){
        if(EndPage >= i){
            if(aCurPage == i){
                oHTML += " <font style='color : "+aColor+";text-decoration : underline;'><b>" + i + "</b></font>";
            }else{
                oHTML += " <a href="+MyAction+"&page=" + i + " >" + i + "</a>";
            }
        }else{
            break;
        }
    }
    if(n > aFooterNum + aCurPage){
        oHTML += " <a href="+MyAction+"&page="+(aCurPage + aFooterNum)+"><img src=/images/pl/Next.gif border=0 alt=下"+aFooterNum+"页></a>";
    }
    if(aCurPage == n){
        oHTML += " <img src=/images/pl/LastSele.gif border=0>";
    }else{
        oHTML += " <a href="+MyAction+"&page="+n+"><img src=/images/pl/Last.gif border=0 alt=尾页></a>";
    }

    oHTML += " </b>转到:<input type=text name=Page size=3 maxlength=10 value=";
    oHTML += aCurPage
    oHTML += "><input type=submit value=Go name=submit ><input type=hidden value='"+String(aTotalNum)+"' name=ct ></td></tr></form></table>";
    return oHTML;
}

function getNextElement(field) {
    var tmp= field;
    while(field.tagName.toUpperCase()!="FORM")
    {
        field=field.parentNode;
        if(field.tagName.toUpperCase()=="BODY")
        {break;
            return null;}
    }
    for(i=0;i<field.getElementsByTagName("input").length;i++)

    {
        if(field.getElementsByTagName("input")[i]==tmp)

            break;
    }
    return field.getElementsByTagName("input")[i+1];
}

function ChildPopMsg(Msg,MsgStr,Title){
    var T = Title;
    var M = MsgStr;
    if(T==null){ T = '提示：'; }
    if(M==null){ M = '返回信息："{0}".'; }
    if(window != window.top){
        parent.Ext.LongZhong.msg(T, M, Msg);
    }else{
        if(MsgStr!=null){
            if(MsgStr.indexOf("{0}")!=-1){
                var Mstr = M.replace("{0}",Msg);
                alert(Mstr);
            }else{
                alert(Msg);
            }
        }else{
            alert(Msg);
        }
    }
}