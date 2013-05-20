function __(objID)
{
    if (document.getElementById)
    //IE 5.x or NS 6.x or above
        return document.getElementById(objID);
    else if (document.all)
    //IE 4.x
        return document.all[objID];
    else
    //Netscape 4.x
        return document[objID];
}
function getPostString(theform){
    var elements = __(theform).elements;
    var url = ""; var coo;
    for (var i = 0; i < elements.length; ++i)
    {
        var e = elements[i];
        if (e.type == "checkbox"){
            //url = url + e.id + "=" + (e.checked ? "1" : "0") + "&";
            if(e.checked) url = url + e.id + "=" + escape(e.value) + "&";
        }else if(e.type == "radio"){
            if(e.checked) url = url + e.id + "=" + escape(e.value) + "&";
        }else{
            url = url + e.id + "=" + escape(e.value) + "&";
        }
    }
    return url;
}
function ShowLoading(obj,Y)
{
    var layer = __("popLayer");
    if(obj!=null){
        var divtop = obj.offsetTop;
        var divleft = obj.offsetLeft;
        var BodyWidth=document.body.clientWidth;
        while (obj = obj.offsetParent){
            divtop += obj.offsetTop;
            divleft += obj.offsetLeft;
        }
        layer.style.top = divtop + 13;
        //layer.style.left = divleft + Y;
        if((divleft+500)>BodyWidth)
        {
            layer.style.left = BodyWidth-525;
        }else{
            layer.style.left=divleft;
        }
        //layer.style.left = divleft -100;
    }
    layer.innerHTML="<img src='/images/ajax-loader.gif' height='32' width='32'>请稍后.................."
    layer.style.visibility = 'visible';
}
function ShowLoading2(obj,Y)
{
    var layer = __("popLayer2");
    if(obj!=null){
        var divtop = obj.offsetTop;
        var divleft = obj.offsetLeft;
        var BodyWidth=document.body.clientWidth;
        while (obj = obj.offsetParent){
            divtop += obj.offsetTop;
            divleft += obj.offsetLeft;
        }
        layer.style.top = divtop + 13;
        //layer.style.left = divleft + Y;
        if((divleft+500)>BodyWidth)
        {
            layer.style.left = BodyWidth-525;
        }else{
            layer.style.left=divleft;
        }
        //layer.style.left = divleft -100;
    }
    layer.innerHTML="<img src='/images/ajax-loader.gif' height='32' width='32'>请稍后.................."
    layer.style.visibility = 'visible';
}
function ShowLoadingDiv(obj)
{
    var layer = __(obj);
    layer.innerHTML="<img src='/images/ajax-loader2.gif' height='16' width='16'>请稍后......"
    layer.style.visibility = 'visible';
}
function hidePopLayer(){
    var layer = __("popLayer");
    var layer2 = __("popLayer2");
    if(typeof(layer2)=="object")
    {
        if(layer2.style.visibility=='hidden')
        {
            layer.innerHTML = '';
            layer.style.visibility = 'hidden';
        }else if(layer2.style.visibility=='visible'){
            layer2.innerHTML = '';
            layer2.style.visibility = 'hidden';
        }
        //alert(layer2.style.visibility);
        //alert("popLayer2 is Object!");

    }
    else
    {
        layer.innerHTML = '';
        layer.style.visibility = 'hidden';
    }
    //if(layer.style.visibility != 'hidden')
    //{
    //layer2.innerHTML = '';
    //layer2.style.visibility = 'hidden';
    //	return ;
    //}


}
function hidePopLayer2(obj){
    var layer = __(obj);
    layer.style.visibility = 'hidden';
}
function keepPopLayer(){
    var layer = __("popLayer");
    layer.style.visibility = 'visible';
}
function hideDiv()
{
    var layer = __("popLayer");
    layer.innerHTML = '';
    layer.style.visibility = 'hidden';
}
function keepPopLayer2(){
    var layer = __("popLayer2");
    layer.style.visibility = 'visible';
}
function hideDiv2()
{
    var layer = __("popLayer2");
    layer.innerHTML = '';
    layer.style.visibility = 'hidden';
}
function checkMerge(){
    alert("暂不开放")
    return false;
    var obj = document.form2;
    var toMerge = obj.toMerge;
    var fromMerge = obj.fromMerge;

    var founderTo = "";
    var founderFrom = "";
    var founder = 0;
    var f1 = "";
    var f2 = "";
    if(fromMerge==null){
        alert("没有可操作数据.")
        return false;
    }
    if(fromMerge.length==null) {
        alert("没有可操作数据.")
        return false;
    }

    for (var i=0;i<fromMerge.length;i++) {
        if(fromMerge[i].checked){
            founder ++;
            if(founderFrom==""){
                founderFrom = fromMerge[i].value;
                f1 = getObject("qy_"+fromMerge[i].value).innerText;
            }else{
                founderFrom += ","+fromMerge[i].value;
                f1 += ","+getObject("qy_"+fromMerge[i].value).innerText;
            }
        }
    }
    for (var i=0;i<toMerge.length;i++) {
        if(toMerge[i].checked){
            founderTo = toMerge[i].value;
            f2 = getObject("qy_"+toMerge[i].value).innerText;
            break;
        }
    }
    if(founder==0) {
        alert("没有可操作数据.")
        return false;
    }

    if(founder==1 && founderTo == founderFrom) {
        alert("没有可操作数据.")
        return false;
    }
    if(founderTo =="" || founderFrom=="") {
        alert("没有可操作数据.")
        return false;
    }
    if(confirm("确认将["+f1+"] 合并到["+f2+"]吗？")) {
        if(confirm("再次确认将["+f1+"] 合并到["+f2+"]吗？")) {
            obj.tracklog.value="将["+f1+"]的手机列表和短信组合并到当前帐号";
            return true;
        }else{
            return false;
        }

    }else{

        return false;
    }
    return false;
}
function showProductDialog(udd,myid,myname,savebutton){
    if(document.form1.flx.checked==false){
        alert("请选选择分类树");

    }else{
        MySelID = document.getElementById('class_p_'+myid).value;
        showModalDialog('lzproduct1.asp?savebutton='+savebutton+'&udd='+udd+'&myid='+myid+'&MySelID='+MySelID+'&myname='+escape(myname)+'&load='+Math.random(), window, 'dialogWidth:500px;dialogHeight:500px;center:yes;help:no;scroll:yes;resizable:yes;status:no');
    }
}
function showProductRelation(udd,obj,myid,myname,savebutton){
    var sendList = "sendList_ProductID";
    if(udd==7 || udd==8){sendList = "sendList_LanMuID";};
    var MySelID = "";
    for (var i = 0; i < obj.length; ++i)
    {
        var e = obj[i];
        if (e.type == "checkbox" && e.name==sendList){
            if(e.checked){
                if(MySelID==""){
                    MySelID = e.value;
                }else{
                    MySelID += "," + e.value;
                }
            }
        }
    }
    if(MySelID.length>1500){
        MySelID="";
    }else{
        MySelID="&MySelID="+ MySelID;
    }
    if(udd==7 || udd==8){
        showModalDialog('lzLanMu2.asp?savebutton='+savebutton+'&udd='+udd+'&myid='+myid+MySelID+'&myname='+escape(myname)+'&load='+Math.random(), window, 'dialogWidth:500px;dialogHeight:500px;dialogTop:0px;dialogLeft:0px;help:no;scroll:yes;resizable:yes;status:no');
    }else if(udd==6){
        showModalDialog('lzproduct3.asp?savebutton='+savebutton+'&udd='+udd+'&myid='+myid+MySelID+'&myname='+escape(myname)+'&load='+Math.random(), window, 'dialogWidth:500px;dialogHeight:500px;dialogTop:0px;dialogLeft:0px;help:no;scroll:yes;resizable:yes;status:no');
    }else{
        showModalDialog('lzproduct2.asp?savebutton='+savebutton+'&udd='+udd+'&myid='+myid+MySelID+'&myname='+escape(myname)+'&load='+Math.random(), window, 'dialogWidth:500px;dialogHeight:500px;dialogTop:0px;dialogLeft:0px;help:no;scroll:yes;resizable:yes;status:no');
    }
}
function ProductCorpProductSave(){
    ProductCorpSave('add','0');
}
// function CreatProductList(treeid,v,ud,ex,sp,mb,content,idName,chd,fb)
function CreatProductList(treeid,v,content,idName)
{
    var oDiv;
    var groupid = treeid.split("_");
    if(document.getElementById("s"+treeid)==null){
        var tmpstr=content + "<input type='checkbox' checked id='"+idName+"' name='"+idName+"' value='"+v+"' >";
        oDiv = window.document.createElement( "DIV" );
        oDiv.id = "s"+treeid;
        oDiv.noWrap = true;
        //	oDiv.mb = mb ;
        //	oDiv.cd = chd ;
        oDiv.style.height = '14pt';
        //	if (chd=="-1") { oDiv.className = "MobileOut"; }else{ oDiv.className = "GroupOut"; }
        //	if(parseInt(ex) < 0){oDiv.style.color="gray";}
        oDiv.innerHTML = tmpstr;
        document.all.sLzProductList.insertAdjacentElement( "AfterBegin" , oDiv );
    }
}
function DeleteProductList(Arr,obj){
    if(Arr.length==0) return;
    for(var i=0;i<Arr.length;i++){

        for(var j=0;j<obj.length;j++){
            var s=obj[j].id;
            if(s.substring(1,s.length)==Arr[i])
            {
                //eval("document.all."+obj[j].id+".removeNode(true);");
                document.getElementById(obj[j].id).removeNode(true);
                break;
            }
        }
    }
}
function saveparent(myid,myfid){
    var oldfid;
    oldfid=$("#class_p_"+myid).val();
    if(oldfid==myfid)
    {
        alert("相同的父id!");
        return false;
    }
    if(myid==myfid)
    {
        alert("不能把自己设置为自己的子分类!");
        return false;
    }
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: "EditAct=ClassChange&EditID="+myid+"&fid="+myfid,
//			dataType: "script",
        beforeSend: function(XMLHttpRequest){
            //alert(FormData);
            //ShowLoading();
        },
        success: function(data, textStatus){
            //eval(data);
            if(data=="1"){
                document.getElementById("class_i_"+myid).innerHTML = document.getElementById("class_up_"+myid).innerHTML;
                closesaveparent(myid,myfid);
                document.getElementById("class_p_"+myid).value = myfid;

                alert("操作成功！")
            }else{
                alert(data);
            }
        },
        complete: function(XMLHttpRequest, textStatus){
            //HideLoading();
            //alert("HideLoading");
        },
        error: function(_XMLHttpRequest,_ErrMsg,_ErrObject){
            alert("更新失败,请稍后重试!");
            hidePopLayer();
        }
    });

}
function UpdateClassFather(treeid,v,content,myid,myfid)
{
    var oDiv;
    var cc;
    var tmpstr = "";
    if(v==myfid){
        document.getElementById("class_i_"+myid).style.display="";
        document.getElementById("class_up_"+myid).style.display="none";
        document.getElementById("class_opt_"+myid).style.display="none";
        document.getElementById("class_up_"+myid).innerHTML="";
        document.getElementById("class_opt_"+myid).innerHTML="";
    }else{
        document.getElementById("class_i_"+myid).style.display="none";
        document.getElementById("class_up_"+myid).style.display="";
        document.getElementById("class_opt_"+myid).style.display="";
        cc=content.split("->");
        var productid = treeid.split("_");
        for(var j=0;j<cc.length;j++){
            tmpstr += "<a onclick='EditProduct(this,"+productid[j+1]+")' style='cursor:pointer'>"+cc[j]+"</a>";
            if(tmpstr!="") tmpstr += "-> ";
        }
        document.getElementById("class_up_"+myid).innerHTML=tmpstr;
        document.getElementById("class_opt_"+myid).innerHTML="<INPUT onclick='javascript:saveparent("+myid+","+v+");' type='button' value='确定'>&nbsp;&nbsp;<INPUT onclick='javascript:closesaveparent("+myid+","+v+");' type='button' value='取消'>";
    }

}
function closesaveparent(myid,myfid){
    document.getElementById("class_i_"+myid).style.display="";
    document.getElementById("class_up_"+myid).style.display="none";
    document.getElementById("class_opt_"+myid).style.display="none";
    document.getElementById("class_up_"+myid).innerHTML="";
    document.getElementById("class_opt_"+myid).innerHTML="";
}
function ShowCorporation(obj,id){
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: "EditAct=ShowCorporation&EditID="+id,
        beforeSend: function(XMLHttpRequest){
            ShowLoading(obj,-250);
        },
        success: function(data, textStatus){
            __("popLayer").innerHTML=data;
        },
        complete: function(XMLHttpRequest, textStatus){},
        error: function(){
            alert("未知错误,请稍后重试!");
            hidePopLayer();
        }
    });
}
function ETProduct(obj,id){
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: "EditAct=ETProduct&EditID="+id,
        beforeSend: function(XMLHttpRequest){
            ShowLoading(obj,-250);
        },
        success: function(data, textStatus){
            __("popLayer").innerHTML=data;
            bindseletProducts('selProducts','sLzProductList',1);
        },
        complete: function(XMLHttpRequest, textStatus){},
        error: function(){
            alert("未知错误,请稍后重试!");
            hidePopLayer();
        }
    });
}
function UpDown(obj,id,relation){
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: "EditAct=UpDownProduct&relation="+relation+"&EditID="+id,
        beforeSend: function(XMLHttpRequest){
            ShowLoading(obj,-250);
        },
        success: function(data, textStatus){
            __("popLayer").innerHTML=data;
            if(relation=='lm'|| relation=='tc'){
                bindseletLanMu('selProducts','sLzLanMuList',1);
            }else if(relation=='qy'){
                bindselQYLX('selProducts','sLzProductList',1);
            }else{
                bindselProducts('selProducts','sLzProductList',1);
            }
        },
        complete: function(XMLHttpRequest, textStatus){},
        error: function(){
            alert("未知错误,请稍后重试!");
            hidePopLayer();
        }
    });
}
function UpDownProductSave(){
    var FormData=$('#relationproductForm1').formSerialize();
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: FormData,
//			dataType: "script",
        beforeSend: function(XMLHttpRequest){
            //alert(FormData);
            //ShowLoading();
        },
        success: function(data, textStatus){
            //eval(data);
            if(data=="1"){
                alert("操作成功！");
            }else{
                alert(data);
            }
        },
        complete: function(XMLHttpRequest, textStatus){
            //HideLoading();
            //alert("HideLoading");
        },
        error: function(_XMLHttpRequest,_ErrMsg,_ErrObject){
            alert("更新失败,请稍后重试!");
            hidePopLayer();
        }
    });

}

function bindXproductSave(){
    var FormData=$('#relationproductForm1').formSerialize();
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: FormData,
//			dataType: "script",
        beforeSend: function(XMLHttpRequest){
            //alert(FormData);
            //ShowLoading();
        },
        success: function(data, textStatus){
            //eval(data);
            if(data.indexOf("保存成功!|")!=-1){
                var xproduct = data.split("|");
                if(document.getElementById("xproductid_"+xproduct[1])!=null){
                    document.getElementById("xproductid_"+xproduct[1]).innerText= xproduct[2];
                }
            }else{
                alert(data);
            }
        },
        complete: function(XMLHttpRequest, textStatus){
            hidePopLayer();
            //alert("HideLoading");
        },
        error: function(_XMLHttpRequest,_ErrMsg,_ErrObject){
            alert("更新失败,请稍后重试!");
            hidePopLayer();
        }
    });

}

function EditAttrProduct(obj,id){
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: "EditAct=EditAttrProduct&EditID="+id,
        beforeSend: function(XMLHttpRequest){
            ShowLoading(obj,-250);
        },
        success: function(data, textStatus){
            __("popLayer").innerHTML=data;
            //		bindselProducts('selProducts','sLzProductList',1);
        },
        complete: function(XMLHttpRequest, textStatus){},
        error: function(){
            alert("未知错误,请稍后重试!");
            hidePopLayer();
        }
    });
}
function ProductCorp(obj,id){
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: "EditAct=ProductCorp&EditID="+id,
        beforeSend: function(XMLHttpRequest){
            ShowLoading(obj,-250);
        },
        success: function(data, textStatus){
            __("popLayer").innerHTML=data;
            //		bindselProducts('selProducts','sLzProductList',1);
            if(id!="0"){
                bindSearchCorp('Corporation_Name','Corporation_EName','Corporation_AbName',1);
            }else{
                bindselProducts('selProducts','sLzProductList',1);
            }
        },
        complete: function(XMLHttpRequest, textStatus){},
        error: function(){
            alert("未知错误,请稍后重试!");
            hidePopLayer();
        }
    });
}
function ProductCorpSave(act,aid){
    var productname;
    var Corporation_Name=trim($("#Corporation_Name").val());
    if(Corporation_Name==""){
        alert("请输入企业名称!");
        $("#Corporation_Name").focus();
        return false;
    }
    var FormData=$('#editproductcorpForm1').formSerialize();
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: FormData,
//			dataType: "script",
        beforeSend: function(XMLHttpRequest){
            //alert(FormData);
            //ShowLoading();
        },
        success: function(data, textStatus){
            //eval(data);
            if(data.indexOf("|||")!=-1){
                if(act=="add"){
                    $("#Corporation_Name").val("");
                    $("#Corporation_EName").val("");
                    $("#Corporation_AbName").val("");
                    var cp = data.split("|||");
                    var tt = '<a href=\'javascript:parent.OpenExtWin("admin_lzCorporationedit.asp?act=edit&Corporation_ID='+cp[1]+'","编辑生产企业:'+cp[2]+'",900,550);\'>'+cp[2]+'</a>';
                    var dd = '<input type="button" onclick="ProductCorpDel('+cp[0]+','+cp[1]+');" style="color:red;" value="删除">';
                    addProductAttrRow('Table_ProductCorp',tt,cp[4],cp[3],dd);
                    alert("保存成功！");
                }else{
                    $("#Corporation_Name"+aid).html(Corporation_Name);
                    $("#Corporation_Name"+aid).html(Corporation_EName);
                    $("#Corporation_Name"+aid).html(Corporation_AbName);
                    hidePopLayer();
                    alert("修改成功！");
                }
            }else{
                if(aid=="0"){
                    $("#Corporation_Name").val("");
                    $("#Corporation_EName").val("");
                    $("#Corporation_AbName").val("");
                    var reloadpage = $("#reloadpage1").attr("checked");
                    var clearList = $("#clearList1").attr("checked");
                    if(clearList!="undefined" &&  clearList){
                        __('sLzProductList').innerHTML='';document.editproductcorpForm1.selProducts.value='';
                    }

                    if(reloadpage) {
                        alert(data);
                        self.location.reload();
                    }else{
                        alert(data);
                    }
                }else{
                    alert(data);
                }


            }
        },
        complete: function(XMLHttpRequest, textStatus){
            //HideLoading();
            //alert("HideLoading");
        },
        error: function(_XMLHttpRequest,_ErrMsg,_ErrObject){
            alert("更新失败,请稍后重试!");
            hidePopLayer();
        }
    });

}
function AttrProduct(obj,id){
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: "EditAct=AttributeProduct&EditID="+id,
        beforeSend: function(XMLHttpRequest){
            ShowLoading(obj,-250);
        },
        success: function(data, textStatus){
            __("popLayer").innerHTML=data;
            //		bindselProducts('selProducts','sLzProductList',1);
        },
        complete: function(XMLHttpRequest, textStatus){},
        error: function(){
            alert("未知错误,请稍后重试!");
            hidePopLayer();
        }
    });
}

function AttrCorporation(obj,id,db){
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: "EditAct=AttrCorporation&Editdb="+db+"&EditID="+id,
        beforeSend: function(XMLHttpRequest){
            ShowLoading(obj,-250);
        },
        success: function(data, textStatus){
            __("popLayer").innerHTML=data;
            //		bindselProducts('selProducts','sLzProductList',1);
        },
        complete: function(XMLHttpRequest, textStatus){},
        error: function(){
            alert("未知错误,请稍后重试!");
            hidePopLayer();
        }
    });
}
function AttrCorporationSave(act,aid,db,chkid){
    if(db=="lxr"){
        var lxr_Name=trim($("#lxr_Name").val());
        if(lxr_Name==""){
            alert("请输入联系人!");
            $("#lxr_Name").focus();
            return false;
        }
    }else if(db=="qydt"){
        var qydt_RQ=trim($("#qydt_RQ").val());
        if(qydt_RQ==""){
            alert("请输入日期!");
            $("#qydt_RQ").focus();
            return false;
        }
        var qydt_Title=trim($("#qydt_Title").val());
        if(qydt_Title==""){
            alert("请输入标题!");
            $("#qydt_Title").focus();
            return false;
        }
        var qydt_Content=trim($("#qydt_Content").val());
        if(qydt_Content==""){
            alert("请输入内容!");
            $("#qydt_Content").focus();
            return false;
        }
    }else{
        var sel_Sys_ProductID = trim($("#sel_Sys_ProductID").val());
        if(sel_Sys_ProductID==""){
            alert("请选择产品!");
            $("#sel_Sys_ProductID").focus();
            return false;
        }
    }
    var FormData=$('#editlxrcorpForm1').formSerialize();
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: FormData,
//			dataType: "script",
        beforeSend: function(XMLHttpRequest){
            //alert(FormData);
            //ShowLoading();
        },
        success: function(data, textStatus){
            //eval(data);
            if(data.indexOf("|||")!=-1){
                if(act=="add"){


                    var cp = data.split("|||");
                    if(db=="lxr"){
                        var tt = '<a href=\'javascript:parent.OpenExtWin("admin_lzCorporationAttribEdit.asp?editdb='+db+'&id='+cp[0]+'","编辑['+cp[1]+']的'+cp[2]+':'+$("#lxr_Name").val()+'",900,550);\'>'+$("#lxr_Name").val()+'</a>';
                        var dd = '<input type="button" onclick="AttrCorporationDel('+cp[0]+','+cp[1]+',\''+db+'\');" style="color:red;" value="删除">';
                        addProductAttrRow('Table_LxlCorp',tt,$("#lx_gender").val(),$("#lxr_dpt").val(),$("#lxr_jobTitle").val(),$("#lxr_CountryNo").val() + ' ' + $("#lxr_AreaNo").val() + ' ' + $("#lxr_phone").val() ,$("#lxr_fax").val(),$("#lxr_mobile").val(),$("#lxr_email").val(),$("#lxr_QQ").val(),$("#lxr_msn").val(),$("#lxr_intro").val(),dd);
                        $("#lxr_Name").val("");
                        $("#lxr_dpt").val("");
                        $("#lxr_jobTitle").val("");
                        $("#lxr_CountryNo").val("86");
                        //$("#lxr_AreaNo").val("");
                        $("#lxr_phone").val("");
                        $("#lxr_fax").val("");
                        $("#lxr_mobile").val("");
                        $("#lxr_email").val("");
                        $("#lxr_QQ").val("");
                        $("#lxr_msn").val("");
                        $("#lxr_intro").val("");
                    }else{
                        if(db=="qydt"){
                            var tt = '<a href=\'javascript:parent.OpenExtWin("admin_lzCorporationAttribEdit.asp?editdb='+db+'&id='+cp[0]+'","编辑['+cp[2]+']的'+cp[3]+'",900,550);\'>'+$("#"+chkid).val()+'</a>';
                        }else{
                            var tt = '<a href=\'javascript:parent.OpenExtWin("admin_lzCorporationAttribEdit.asp?editdb='+db+'&id='+cp[0]+'","编辑['+cp[2]+']的'+cp[4]+'的'+cp[3]+'",900,550);\'>'+$("#"+chkid).val()+'</a>';
                        }
                        var dd = '<input type="button" onclick="AttrCorporationDel('+cp[0]+','+cp[1]+',\''+db+'\');" style="color:red;" value="删除">';
                        if(db=="qydt"){
                            addProductAttrRow('Table_LxlCorp',tt,$("#qydt_RQ").val(),$("#qydt_Content").val(),$("#qydt_PostName").val() ,dd);
                        }else if(db=="yscnl"){
                            addProductAttrRow('Table_LxlCorp',tt,cp[4],$("#yscnl_cn").val(),$("#yscnl_cl").val(),$("#yscnl_kgl").val() ,dd);
                        }else if(db=="nscnl"){
                            addProductAttrRow('Table_LxlCorp',tt,cp[4],$("#nscnl_scgy").val(),$("#nscnl_nscnl").val() ,dd);
                        }else if(db=="zzdt"){
                            addProductAttrRow('Table_LxlCorp',tt,cp[4],$("#zzdt_zt").val(),$("#zzdt_zcqk").val() ,dd);
                        }else if(db=="xsdt"){
                            addProductAttrRow('Table_LxlCorp',tt,cp[4],$("#xsdt_qy").val() ,dd);
                        }else if(db=="kc"){
                            addProductAttrRow('Table_LxlCorp',tt,cp[4],$("#kc_kc").val() ,dd);
                        }
                        var obj = document.editlxrcorpForm1;
                        for (var i = 0; i < obj.length; ++i)
                        {
                            var e = obj[i];
                            if (e.type == "text" || e.type == "textarea"){
                                e.value="";
                            }
                        }
                    }
                    alert("保存成功！");
                }
            }else{
                alert(data);


            }
        },
        complete: function(XMLHttpRequest, textStatus){
            //HideLoading();
            //alert("HideLoading");
        },
        error: function(_XMLHttpRequest,_ErrMsg,_ErrObject){
            alert("更新失败,请稍后重试!");
            hidePopLayer();
        }
    });

}
function AttrCorporationDel(lxrid,Corporation_ID,db){
    var Delconfirm=$("#Delconfirm").attr("checked");
    if(Delconfirm){
        if(confirm('确定删除吗？')){

        }else{
            return false;
        }
    }
    var Elm  =  event.srcElement;
    var thisrow = Elm.parentElement.parentElement ;
    var intThisIndex = thisrow.rowIndex;
    var FormData="EditAct=AttrCorporationDel&Editdb="+db+"&EditID="+lxrid+"&Corporation_ID="+Corporation_ID;
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: FormData,
//			dataType: "script",
        beforeSend: function(XMLHttpRequest){
            //alert(FormData);
            //ShowLoading();
        },
        success: function(data, textStatus){
            //eval(data);
            if(data=="1"){
                thisrow.parentElement.deleteRow(intThisIndex);
            }else{
                alert(data);
            }
        },
        complete: function(XMLHttpRequest, textStatus){
            //HideLoading();
            //alert("HideLoading");
        },
        error: function(_XMLHttpRequest,_ErrMsg,_ErrObject){
            alert("删除失败,请稍后重试!");
            //	hidePopLayer();
        }
    });

}
function ProductCorpDel(ProductID,Corporation_ID){
    var Delconfirm=$("#Delconfirm").attr("checked");
    if(Delconfirm){
        if(confirm('确定删除吗？')){

        }else{
            return false;
        }
    }
    var Elm  =  event.srcElement;
    var thisrow = Elm.parentElement.parentElement ;
    var intThisIndex = thisrow.rowIndex;
    var FormData="EditAct=ProductCorpDel&EditID="+ProductID+"&Corporation_ID="+Corporation_ID;
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: FormData,
//			dataType: "script",
        beforeSend: function(XMLHttpRequest){
            //alert(FormData);
            //ShowLoading();
        },
        success: function(data, textStatus){
            //eval(data);
            if(data=="1"){
                thisrow.parentElement.deleteRow(intThisIndex);
            }else{
                alert(data);
            }
        },
        complete: function(XMLHttpRequest, textStatus){
            //HideLoading();
            //alert("HideLoading");
        },
        error: function(_XMLHttpRequest,_ErrMsg,_ErrObject){
            alert("删除失败,请稍后重试!");
            //	hidePopLayer();
        }
    });

}
function ShowAttributeProductSave(){

    var Product_PaiHao=trim($("#Product_PaiHao").val());
    var Product_GGXH=trim($("#Product_GGXH").val());
    var Product_ZLBZ=trim($("#Product_ZLBZ").val());
    var Product_YongTu=trim($("#Product_YongTu").val());
    var Product_SCFF=trim($("#Product_SCFF").val());
    var Product_WLXZ=trim($("#Product_WLXZ").val());
    $("#Product_PaiHao").val("");
    $("#Product_GGXH").val("");
    $("#Product_ZLBZ").val("");
    $("#Product_YongTu").val("");
    $("#Product_SCFF").val("");
    $("#Product_WLXZ").val("");
    addProductAttrRow('Table_ProductAttrib',Product_PaiHao,Product_GGXH,Product_WLXZ.replace(/\n/g,'<br>'),Product_ZLBZ,Product_YongTu.replace(/\n/g,'<br>'),Product_SCFF.replace(/\n/g,'<br>'))
    alert("保存成功！");
}
function ShowEditAttrProductSave(aid){
    var Product_PaiHao=trim($("#Product_PaiHao").val());
    var Product_GGXH=trim($("#Product_GGXH").val());
    var Product_ZLBZ=trim($("#Product_ZLBZ").val());
    var Product_YongTu=trim($("#Product_YongTu").val());
    var Product_SCFF=trim($("#Product_SCFF").val());
    var Product_WLXZ=trim($("#Product_WLXZ").val());
    $("#Product_PaiHao_"+aid).html(Product_PaiHao);
    $("#Product_GGXH_"+aid).html(Product_GGXH);
    $("#Product_ZLBZ_"+aid).html(Product_ZLBZ);
    $("#Product_YongTu_"+aid).html(Product_YongTu.replace(/\n/g,'<br>'));
    $("#Product_SCFF_"+aid).html(Product_SCFF.replace(/\n/g,'<br>'));
    $("#Product_WLXZ_"+aid).html(Product_WLXZ.replace(/\n/g,'<br>'));
    hidePopLayer();
    alert("修改成功！");
}
function AttributeProductSave(act,aid){
    var productname;
    var obj = document.attributeproductForm1;
    var Product_PaiHao=trim($("#Product_PaiHao").val());
    var Product_GGXH=trim($("#Product_GGXH").val());
    var Product_ZLBZ=trim($("#Product_ZLBZ").val());
    var Product_YongTu=trim($("#Product_YongTu").val());
    var Product_SCFF=trim($("#Product_SCFF").val());
    var Product_WLXZ=trim($("#Product_WLXZ").val());
    if(Product_PaiHao==""&&Product_GGXH==""&&Product_ZLBZ==""&&Product_YongTu==""&&Product_SCFF==""&&Product_WLXZ==""){
        alert("至少输入一项!");
        $("#Product_PaiHao").focus();
        return false;
    }
    var postType = trim($("#PostType").val());
    if(postType == "iframe") {
        obj.target="PostResult";
        obj.submit();
        return true;
    }
    var FormData=$('#attributeproductForm1').formSerialize();
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: FormData,
//			dataType: "script",
        beforeSend: function(XMLHttpRequest){
            //alert(FormData);
            //ShowLoading();
        },
        success: function(data, textStatus){
            //eval(data);
            if(data=="1"){
                if(act=="add"){
                    $("#Product_PaiHao").val("");
                    $("#Product_GGXH").val("");
                    $("#Product_ZLBZ").val("");
                    $("#Product_YongTu").val("");
                    $("#Product_SCFF").val("");
                    $("#Product_WLXZ").val("");
                    addProductAttrRow('Table_ProductAttrib',Product_PaiHao,Product_GGXH,Product_WLXZ,Product_ZLBZ,Product_YongTu,Product_SCFF)
                    alert("保存成功！");
                }else{
                    $("#Product_PaiHao_"+aid).html(Product_PaiHao);
                    $("#Product_GGXH_"+aid).html(Product_GGXH);
                    $("#Product_ZLBZ_"+aid).html(Product_ZLBZ);
                    $("#Product_YongTu_"+aid).html(Product_YongTu);
                    $("#Product_SCFF_"+aid).html(Product_SCFF);
                    $("#Product_WLXZ_"+aid).html(Product_WLXZ);
                    hidePopLayer();
                    alert("修改成功！");
                }
            }else{
                alert(data);
            }
        },
        complete: function(XMLHttpRequest, textStatus){
            //HideLoading();
            //alert("HideLoading");
        },
        error: function(_XMLHttpRequest,_ErrMsg,_ErrObject){
            alert("更新失败,请稍后重试!");
            hidePopLayer();
        }
    });

}
function addProductAttrRow(tid,t0,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11){
    var objTable = document.getElementById(tid);
    var newTr = objTable.insertRow();
    var newTd0 = newTr.insertCell();
//objTable.setAttribute("border",1);
    if(t1!=null ) {var newTd1 = newTr.insertCell();}
    if(t2!=null ) {var newTd2 = newTr.insertCell();}
    if(t3!=null ) {var newTd3 = newTr.insertCell();}
    if(t4!=null ) {var newTd4 = newTr.insertCell();}
    if(t5!=null ) {var newTd5 = newTr.insertCell();}
    if(t6!=null ) {var newTd6 = newTr.insertCell();}
    if(t7!=null ) {var newTd7 = newTr.insertCell();}
    if(t8!=null ) {var newTd8 = newTr.insertCell();}
    if(t9!=null ) {var newTd9 = newTr.insertCell();}
    if(t10!=null ) {var newTd10 = newTr.insertCell();}
    if(t11!=null ) {var newTd11 = newTr.insertCell();}
    newTd0.innerHTML = '<td bgcolor="#FFFFFF" align="center">'+t0+'</td>';
    if(t1!=null ) {newTd1.innerHTML = '<td bgcolor="#FFFFFF" align="center">'+t1+'</td>';}
    if(t2!=null ) {newTd2.innerHTML = '<td bgcolor="#FFFFFF" align="left">'+t2+'</td>';}
    if(t3!=null ) {newTd3.innerHTML = '<td bgcolor="#FFFFFF" align="left">'+t3+'</td>';}
    if(t4!=null ) {newTd4.innerHTML = '<td bgcolor="#FFFFFF" align="left">'+t4+'</td>';}
    if(t5!=null ) {newTd5.innerHTML = '<td bgcolor="#FFFFFF" align="left">'+t5+'</td>';}
    if(t6!=null ) {newTd6.innerHTML = '<td bgcolor="#FFFFFF" align="left">'+t6+'</td>';}
    if(t7!=null ) {newTd7.innerHTML = '<td bgcolor="#FFFFFF" align="left">'+t7+'</td>';}
    if(t8!=null ) {newTd8.innerHTML = '<td bgcolor="#FFFFFF" align="left">'+t8+'</td>';}
    if(t9!=null ) {newTd9.innerHTML = '<td bgcolor="#FFFFFF" align="left">'+t9+'</td>';}
    if(t10!=null ) {newTd10.innerHTML = '<td bgcolor="#FFFFFF" align="left">'+t10+'</td>';}
    if(t11!=null ) {newTd11.innerHTML = '<td bgcolor="#FFFFFF" align="left">'+t11+'</td>';}
}

function EditProduct(obj,id){
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: "EditAct=EditProduct&EditID="+id,
        beforeSend: function(XMLHttpRequest){
            ShowLoading(obj,-250);
        },
        success: function(data, textStatus){
            __("popLayer").innerHTML=data;
            bindSearchProduct('editparentidstr1','editparentid1',1);
            bindSearchProduct('editparentidstr2','editparentid2',1);
        },
        complete: function(XMLHttpRequest, textStatus){},
        error: function(){
            alert("未知错误,请稍后重试!");
            hidePopLayer();
        }
    });
}
function EditProductSave(){
    var productname;
    productname=trim($("#editproductname").val());
    var reloadpage=$("#reloadpage1").attr("checked");
    if(productname=="")
    {
        alert("请输入产品名称!");
        $("#editproductname").focus();
        return false;
    }
    var FormData=$('#editproductForm1').formSerialize();
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: FormData,
//			dataType: "script",
        beforeSend: function(XMLHttpRequest){
            //alert(FormData);
            //ShowLoading();
        },
        success: function(data, textStatus){
            //eval(data);
            if(data=="1"){
                alert("操作成功！");
            }else{
                alert(data);
            }

            if(reloadpage) self.location.reload();
        },
        complete: function(XMLHttpRequest, textStatus){
            //HideLoading();
            //alert("HideLoading");
        },
        error: function(_XMLHttpRequest,_ErrMsg,_ErrObject){
            alert("更新失败,请稍后重试!");
            hidePopLayer();
        }
    });

}
function AddProductSave(){
    var productname;
    productname=$("#addproductname").val();
    var reloadpage=$("#reloadpage2").attr("checked");
    if(productname=="")
    {
        alert("请输入产品名称!");
        $("#addproductname").focus();
        return false;
    }
    var FormData=$('#editproductForm2').formSerialize();
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: FormData,
//			dataType: "script",
        beforeSend: function(XMLHttpRequest){
            //alert(FormData);
            //ShowLoading();
        },
        success: function(data, textStatus){
            //eval(data);
            if(data=="1"){
                alert("操作成功！");
            }else{
                alert(data);
            }
            if(reloadpage) self.location.reload();
        },
        complete: function(XMLHttpRequest, textStatus){
            //HideLoading();
            //alert("HideLoading");
        },
        error: function(_XMLHttpRequest,_ErrMsg,_ErrObject){
            alert("更新失败,请稍后重试!");
            hidePopLayer();
        }
    });

}

function bindselProducts(cid,pid,fm){
    $("#"+cid).autocomplete("admin_showlzproduct.asp?fm="+fm, {
        minChars: 1,
        width: 260,
        max: 300,
        selectFirst: true,
        scroll: true,
        scrollHeight: 280,
        multiple: false,
        autoFill: false,
        matchContains: true,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.ph.replace(/<\/?font[^>]*>/gi,"")
                }
            });
        },
        formatItem: function(item) {
            return item.ph;
            //return item.name + " (" + item.dj + ")" ;
        }
    }).result(function(event, item) {
            if(item.id){
                CreatProductList("i_"+((item.pr!="0")?(item.pr+"_"):"")+item.id,item.id,item.ph,"sendList_ProductID");
                //	alert($("#"+pid).val())
                //	$("#"+pid).val(item.id);
            }
        });
}

function bindselQYLX(cid,pid,fm){
    $("#"+cid).autocomplete("admin_showlzqylx.asp?fm="+fm, {
        minChars: 1,
        width: 260,
        max: 300,
        selectFirst: true,
        scroll: true,
        scrollHeight: 280,
        multiple: false,
        autoFill: false,
        matchContains: true,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.ph.replace(/<\/?font[^>]*>/gi,"")
                }
            });
        },
        formatItem: function(item) {
            return item.ph;
            //return item.name + " (" + item.dj + ")" ;
        }
    }).result(function(event, item) {
            if(item.id){
                CreatProductList("i_"+((item.pr!="0")?(item.pr+"_"):"")+item.id,item.id,item.ph,"sendList_ProductID");
                //	alert($("#"+pid).val())
                //	$("#"+pid).val(item.id);
            }
        });
}

function bindseletProducts(cid,pid,fm){
    $("#"+cid).autocomplete("admin_showetproduct.asp?fm="+fm, {
        minChars: 1,
        width: 260,
        max: 300,
        selectFirst: true,
        scroll: true,
        scrollHeight: 280,
        multiple: false,
        autoFill: false,
        matchContains: true,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.ph.replace(/<\/?font[^>]*>/gi,"")
                }
            });
        },
        formatItem: function(item) {
            return item.ph;
            //return item.name + " (" + item.dj + ")" ;
        }
    }).result(function(event, item) {
            if(item.id){
                CreatProductList("i_"+((item.pr!="0")?(item.pr+"_"):"")+item.id,item.id,item.ph,"sendList_ProductID");
                //	alert($("#"+pid).val())
                //	$("#"+pid).val(item.id);
            }
        });
}

function bindSearchProduct(cid,pid,fm){
    $("#"+cid).autocomplete("admin_showlzproduct.asp?fm="+fm, {
        minChars: 1,
        width: 260,
        max: 300,
        selectFirst: true,
        scroll: true,
        scrollHeight: 280,
        multiple: false,
        autoFill: false,
        matchContains: true,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.ph.replace(/<\/?font[^>]*>/gi,"")
                }
            });
        },
        formatItem: function(item) {
            return item.ph;
            //return item.name + " (" + item.dj + ")" ;
        }
    }).result(function(event, item) {
            if(item.id){
                $("#"+pid).val(item.id);
            }
        });
}

function bindSearchCorp(cid,pid1,pid2,fm){
    $("#"+cid).autocomplete("admin_showlzcorporation.asp?fm="+fm, {
        minChars: 1,
        width: 260,
        max: 300,
        selectFirst: true,
        scroll: true,
        scrollHeight: 280,
        multiple: false,
        autoFill: false,
        matchContains: true,
        dataType: "json",
        parse: function(data) {
            if(data==""){
                $("#"+pid1).val("");
                $("#"+pid2).val("");
            }
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name.replace(/<\/?font[^>]*>/gi,"")
                }
            });
        },
        formatItem: function(item) {
            return item.name;
            //return item.name + " (" + item.dj + ")" ;
        }
    }).result(function(event, item) {
            if(item.id){
                $("#"+pid1).val(item.ea);
                $("#"+pid2).val(item.ab);
            }
        });
}

function bindseletLanMu(cid,pid,fm){
    $("#"+cid).autocomplete("/admin/admin_showlzLanMu.asp?&fm="+fm, {
        minChars: 1,
        width: 260,
        max: 300,
        selectFirst: true,
        scroll: true,
        scrollHeight: 280,
        multiple: false,
        autoFill: false,
        matchContains: true,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.ph.replace(/<\/?font[^>]*>/gi,"")
                }
            });
        },
        formatItem: function(item) {
            return item.ph;
            //return item.name + " (" + item.dj + ")" ;
        }
    }).result(function(event, item) {
            if(item.id){
                CreatLanMuList("i_"+((item.pr!="0")?(item.pr+"_"):"")+item.id,item.id,item.ph,"sendList_LanMuID");
                //	alert($("#"+pid).val())
                //	$("#"+pid).val(item.id);
            }
        });
}

function CreatLanMuList(treeid,v,content,idName)
{
    var oDiv;
    var groupid = treeid.split("_");
    if(document.getElementById("s"+treeid)==null){
        var tmpstr=content + "<input type='checkbox' checked id='"+idName+"' name='"+idName+"' value='"+v+"' >";
        oDiv = window.document.createElement( "DIV" );
        oDiv.id = "s"+treeid;
        oDiv.noWrap = true;
        //	oDiv.mb = mb ;
        //	oDiv.cd = chd ;
        oDiv.style.height = '14pt';
        //	if (chd=="-1") { oDiv.className = "MobileOut"; }else{ oDiv.className = "GroupOut"; }
        //	if(parseInt(ex) < 0){oDiv.style.color="gray";}
        oDiv.innerHTML = tmpstr;
        document.all.sLzLanMuList.insertAdjacentElement( "AfterBegin" , oDiv );
    }
}
function DeleteLanMuList(Arr,obj){
    if(Arr.length==0) return;
    for(var i=0;i<Arr.length;i++){

        for(var j=0;j<obj.length;j++){
            var s=obj[j].id;
            if(s.substring(1,s.length)==Arr[i])
            {
                //eval("document.all."+obj[j].id+".removeNode(true);");
                document.getElementById(obj[j].id).removeNode(true);
                break;
            }
        }
    }
}

function CreatLanMuList2(treeid,v,content,idName,pb)
{
    var oDiv;
    var groupid = treeid.split("_");
    if(document.getElementById("s"+treeid)==null){
        var tmpstr=content + "<input type='checkbox' checked id='"+idName+"' name='"+idName+"' value='"+v+"' >" +
            " 定价:<input type='text' size='4' id='sendList_price"+v+"' name='sendList_price"+v+"' value='"+pb+"' >";
        oDiv = window.document.createElement( "DIV" );
        oDiv.id = "s"+treeid;
        oDiv.noWrap = true;
        //	oDiv.mb = mb ;
        //	oDiv.cd = chd ;
        oDiv.style.height = '14pt';
        //	if (chd=="-1") { oDiv.className = "MobileOut"; }else{ oDiv.className = "GroupOut"; }
        //	if(parseInt(ex) < 0){oDiv.style.color="gray";}
        oDiv.innerHTML = tmpstr;
        document.all.sLzLanMuList.insertAdjacentElement( "AfterBegin" , oDiv );
    }
}

function ShowTaocan(obj,id){
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: "EditAct=ShowTaocan&EditID="+id,
        beforeSend: function(XMLHttpRequest){
            ShowLoading(obj,-250);
        },
        success: function(data, textStatus){
            __("popLayer").innerHTML=data;
        },
        complete: function(XMLHttpRequest, textStatus){},
        error: function(){
            alert("未知错误,请稍后重试!");
            hidePopLayer();
        }
    });
}

function ShowTaocanSave(){
    var FormData=$('#editproductForm1').formSerialize();
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: FormData,
//			dataType: "script",
        beforeSend: function(XMLHttpRequest){
            //alert(FormData);
            //ShowLoading();
        },
        success: function(data, textStatus){
            //eval(data);
            if(data=="1"){
                alert("操作成功！");
            }else{
                alert(data);
            }
        },
        complete: function(XMLHttpRequest, textStatus){
            //HideLoading();
            //alert("HideLoading");
        },
        error: function(_XMLHttpRequest,_ErrMsg,_ErrObject){
            alert("更新失败,请稍后重试!");
            hidePopLayer();
        }
    });

}

function TaoCanList(obj,id){
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: "EditAct=TaoCanList&EditID="+id,
        beforeSend: function(XMLHttpRequest){
            ShowLoading2(obj,-250);
        },
        success: function(data, textStatus){
            __("popLayer2").innerHTML=data;
        },
        complete: function(XMLHttpRequest, textStatus){},
        error: function(){
            alert("未知错误,请稍后重试!");
            hidePopLayer2();
        }
    });
}

function CreateRightGroup(obj,id){
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: "EditAct=CreateRightGroup&EditID="+id,
        beforeSend: function(XMLHttpRequest){
            ShowLoading(obj,-250);
        },
        success: function(data, textStatus){
            __("popLayer").innerHTML=data;
        },
        complete: function(XMLHttpRequest, textStatus){},
        error: function(){
            alert("未知错误,请稍后重试!");
            hidePopLayer();
        }
    });
}

function CreateRightGroupSave(act,aid){
    var PowerName=trim($("#PowerName").val());
    if(PowerName==""){
        alert("请输入名称!");
        $("#PowerName").focus();
        return false;
    }
    var FormData=$('#editproductcorpForm1').formSerialize();
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: FormData,
//			dataType: "script",
        beforeSend: function(XMLHttpRequest){
            //alert(FormData);
            //ShowLoading();
        },
        success: function(data, textStatus){
            //eval(data);
            var reloadpage = $("#reloadpage1").attr("checked");
            if(reloadpage) {
                alert(data);
                self.location.reload();
            }else{
                alert(data);
            }


        },
        complete: function(XMLHttpRequest, textStatus){
            //HideLoading();
            //alert("HideLoading");
        },
        error: function(_XMLHttpRequest,_ErrMsg,_ErrObject){
            alert("更新失败,请稍后重试!");
            hidePopLayer();
        }
    });

}

function CreateStaffGroup(obj,id){
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: "EditAct=CreateStaffGroup&EditID="+id,
        beforeSend: function(XMLHttpRequest){
            ShowLoading(obj,-250);
        },
        success: function(data, textStatus){
            __("popLayer").innerHTML=data;
        },
        complete: function(XMLHttpRequest, textStatus){},
        error: function(){
            alert("未知错误,请稍后重试!");
            hidePopLayer();
        }
    });
}

function CreateStaffGroupSave(act,aid){
    var StaffGroupName=trim($("#StaffGroupName").val());
    if(StaffGroupName==""){
        alert("请输入名称!");
        $("#StaffGroupName").focus();
        return false;
    }
    var FormData=$('#editproductcorpForm1').formSerialize();
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: FormData,
//			dataType: "script",
        beforeSend: function(XMLHttpRequest){
            //alert(FormData);
            //ShowLoading();
        },
        success: function(data, textStatus){
            //eval(data);
            var reloadpage = $("#reloadpage1").attr("checked");
            if(reloadpage) {
                alert(data);
                self.location.reload();
            }else{
                alert(data);
            }


        },
        complete: function(XMLHttpRequest, textStatus){
            //HideLoading();
            //alert("HideLoading");
        },
        error: function(_XMLHttpRequest,_ErrMsg,_ErrObject){
            alert("更新失败,请稍后重试!");
            hidePopLayer();
        }
    });

}

function CreateWebLink(obj,id){
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: "EditAct=CreateWebLink&EditID="+id,
        beforeSend: function(XMLHttpRequest){
            ShowLoading(obj,-250);
        },
        success: function(data, textStatus){
            __("popLayer").innerHTML=data;
        },
        complete: function(XMLHttpRequest, textStatus){},
        error: function(){
            alert("未知错误,请稍后重试!");
            hidePopLayer();
        }
    });
}


function CreateWebLinkSave(act,aid){
    var StaffGroupName=trim($("#LinkName").val());
    if(StaffGroupName==""){
        ChildPopMsg("请输入名称!");
        $("#LinkName").focus();
        return false;
    }
    var StaffGroupName=trim($("#LinkUrl").val());
    if(StaffGroupName==""){
        ChildPopMsg("请输入网址!");
        $("#LinkUrl").focus();
        return false;
    }
    var FormData=$('#editproductcorpForm1').formSerialize();
    $.ajax({
        type: "post",
        url: "admin_lzProduct_IncData.asp",
        data: FormData,
//			dataType: "script",
        beforeSend: function(XMLHttpRequest){
            //alert(FormData);
            //ShowLoading();
        },
        success: function(data, textStatus){
            //eval(data);
            var reloadpage = $("#reloadpage1").attr("checked");
            if(data=="1"){
                data = "添加成功!"
                if(reloadpage) {
                    ChildPopMsg(data);
                    self.location.reload();
                }else{
                    ChildPopMsg(data);
                }
            }else{
                ChildPopMsg(data);
            }



        },
        complete: function(XMLHttpRequest, textStatus){
            //HideLoading();
            //alert("HideLoading");
        },
        error: function(_XMLHttpRequest,_ErrMsg,_ErrObject){
            alert("更新失败,请稍后重试!");
            hidePopLayer();
        }
    });

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