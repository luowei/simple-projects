/**
 * User: luowei
 * Date: 13-5-14
 * Time: 下午4:22
 */

var hostUrl = 'http://exp.oilchem.com';

function trim(s) {
    while (s.substring(0, 1) == ' ') {
        s = s.substring(1, s.length);
    }
    while (s.substring(s.length - 1, s.length) == ' ') {
        s = s.substring(0, s.length - 1);
    }
    return s;
}

// 表单提交（当远程上传完成后，触发此函数）
function doSubmit() {
    document.form1.submit();
}

function showLanMuRelation(udd, obj, myid, myname, rid, savebutton) {
    var MySelID = "";
    for (var i = 0; i < obj.length; ++i) {
        var e = obj[i];
        if (e.type == "checkbox" && e.name == "sendList_LanMuID") {
            if (e.checked) {
                if (MySelID == "") {
                    MySelID = e.value;
                } else {
                    MySelID += "," + e.value;
                }
            }
        }
    }
    if (MySelID.length > 1500) {
        MySelID = "";
    } else {
        MySelID = "&MySelID=" + MySelID;
    }
    showModalDialog(hostUrl+'/admin/lzLanMu11.asp?savebutton=' + savebutton + '&udd=' + udd + '&rid=' + rid + '&myid=' + myid + MySelID + '&myname=' + escape(myname) + '&load='
        + Math.random(), window, 'dialogWidth:500px;dialogHeight:500px;dialogTop:0px;dialogLeft:0px;help:no;scroll:yes;resizable:yes;status:no');
}

function CreatLanMuList(treeid, v, content, idName) {
    var oDiv;
    var idx = treeid.lastIndexOf("_");
    var id = treeid.substring(idx+1);
    if (document.getElementById(id) == null) {
        var tmpstr = "["+id+"]"+content + "<input type='checkbox' checked id='"+id+"'  name='" + idName + "' value='" + v + "' onclick='itemChanged(&apos;sLzLanMuList&apos;)' >";
        oDiv = window.document.createElement("li");
        oDiv.itemID = id;
        oDiv.innerHTML = tmpstr;
        document.all.sLzLanMuList.insertAdjacentElement("BeforeEnd", oDiv);
        saveLanmuOrder();
    }
}


function showProductRelation(udd, obj, myid, myname, savebutton) {
    var MySelID = "";
    for (var i = 0; i < obj.length; ++i) {
        var e = obj[i];
        if (e.type == "checkbox" && e.name == "sendList_ProductID") {
            if (e.checked) {
                if (MySelID == "") {
                    MySelID = e.value;
                } else {
                    MySelID += "," + e.value;
                }
            }
        }
    }
    if (MySelID.length > 1500) {
        MySelID = "";
    } else {
        MySelID = "&MySelID=" + MySelID;
    }
    showModalDialog(hostUrl+'/admin/lzproduct11.asp?inf=1&savebutton=' + savebutton + '&udd=' + udd + '&myid=' + myid + MySelID
        + '&myname=' + escape(myname) + '&load=' + Math.random(), window, 'dialogWidth:500px;dialogHeight:500px;dialogTop:0px;dialogLeft:0px;help:no;scroll:yes;resizable:yes;status:no');
}

function CreatProductList(treeid, v, content, idName) {
    var oDiv;
    var idx = treeid.lastIndexOf("_");
    var id = treeid.substring(idx+1);
    if (document.getElementById(id) == null) {
        var tmpstr = "["+id+"]"+content + "<input type='checkbox' checked id='"+id+"' name='" + idName + "' value='" + v + "' onclick='itemChanged(&apos;sLzProductList&apos;)' >";
        oDiv = window.document.createElement("li");
        oDiv.itemID = id;
        oDiv.innerHTML = tmpstr;
        document.all.sLzProductList.insertAdjacentElement("BeforeEnd", oDiv);
        saveProductOrder();
    }
}


function bindseletProducts(cid, pid, fm) {
    $("#" + cid).autocomplete(hostUrl+"/admin/admin_showlzproduct-utf-8.asp?inf=1&fm=" + fm, {
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
        parse: function (data) {
            return $.map(data, function (row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.ph.replace(/<\/?font[^>]*>/gi, "")
                }
            });
        },
        formatItem: function (item) {
            return item.ph;
        }
    }).result(function (event, item) {
            if (item.id) {
                CreatProductList("i_" + ((item.pr != "0") ? (item.pr + "_") : "") + item.id, item.id, item.ph, "sendList_ProductID");
            }
        });
}

function bindseletLanMu(cid, pid, fm) {
    $("#" + cid).autocomplete(hostUrl+"/admin/admin_showlzLanMu-utf-8.asp?rid=&fm=" + fm, {
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
        parse: function (data) {
            return $.map(data, function (row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.ph.replace(/<\/?font[^>]*>/gi, "")
                }
            });
        },
        formatItem: function (item) {
            return item.ph;
        }
    }).result(function (event, item) {
            if (item.id) {
                CreatLanMuList("i_" + ((item.pr != "0") ? (item.pr + "_") : "") + item.id, item.id, item.ph, "sendList_LanMuID");
            }
        });
}

function bindseletWebClass(cid, pid, fm) {
    $("#" + cid).autocomplete(hostUrl+"/admin/admin_showlzLanMu-utf-8.asp?dt=webclass&rid=&fm=" + fm, {
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
        parse: function (data) {
            return $.map(data, function (row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.ph.replace(/<\/?font[^>]*>/gi, "")
                }
            });
        },
        formatItem: function (item) {
            return item.ph;
        }
    }).result(function (event, item) {
            if (item.id) {
                CreatWebClassList("i_" + ((item.pr != "0") ? (item.pr + "_") : "") + item.id, item.id, item.ph, "sendList_WebClassID");
            }
        });
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

//--------------------------------------------------------------------------------
function showWebClassRelation(udd, obj, myid, myname, rid, savebutton) {
    var MySelID = "";
    for (var i = 0; i < obj.length; ++i) {
        var e = obj[i];
        if (e.type == "checkbox" && e.name == "sendList_WebClassID") {
            if (e.checked) {
                if (MySelID == "") {
                    MySelID = e.value;
                } else {
                    MySelID += "," + e.value;
                }
            }
        }
    }
    if (MySelID.length > 1500) {
        MySelID = "";
    } else {
        MySelID = "&MySelID=" + MySelID;
    }
    showModalDialog(hostUrl+'/admin/lzWebClass11.asp?savebutton=' + savebutton + '&udd=' + udd + '&rid=' + rid + '&myid=' + myid + MySelID + '&myname=' + escape(myname) + '&load='
        + Math.random(), window, 'dialogWidth:500px;dialogHeight:500px;dialogTop:0px;dialogLeft:0px;help:no;scroll:yes;resizable:yes;status:no');
}


function CreatWebClassList(treeid, v, content, idName) {
    var oDiv;
    var idx = treeid.lastIndexOf("_");
    var id = treeid.substring(idx+1);
    if (document.getElementById(id) == null) {
        var tmpstr = "["+id+"]"+content + "<input type='checkbox' checked id='"+id+"'  name='" + idName + "' value='" + v + "' onclick='itemChanged(&apos;sLzWebClassList&apos;)' >";
        oDiv = window.document.createElement("li");
        oDiv.itemID = id;
        oDiv.innerHTML = tmpstr;
        document.all.sLzWebClassList.insertAdjacentElement("BeforeEnd", oDiv);
        saveWebClassOrder();
    }
}

function DeleteWebClassList(Arr,obj){
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




//保存产品排序
function saveProductOrder() {
    var data = $("#sLzProductList li").map(
        function() {
            var checkbox = $(this).children('input:checkbox')
            if($(checkbox).attr("checked")==true){
                return $(checkbox).attr("id")
            }else{
                return
            }
        }
    ).get();
    $("#prodcutIds").val(data);
//    $("#prodcutIds").val(data.join("|"));
}

//保存栏目排序
function saveLanmuOrder() {
    var data = $("#sLzLanMuList li").map(
        function() {
            var checkbox = $(this).children('input:checkbox')
            if($(checkbox).attr("checked")==true){
                return $(checkbox).attr("id")
            }else{
                return
            }
        }
    ).get();
    $("#lanmuIds").val(data);
}

//保存包含页面排序
function saveWebClassOrder(){
    var data = $("#sLzWebClassList li").map(
        function() {
            var checkbox = $(this).children('input:checkbox')
            if($(checkbox).attr("checked")==true){
                return $(checkbox).attr("id")
            }else{
                return
            }
        }
    ).get();
    $("#webClassIds").val(data);
}

//check box 状态发生变化时
function itemChanged(type){
    if(type == 'sLzLanMuList'){
        saveLanmuOrder();
    }
    if(type == 'sLzProductList'){
        saveProductOrder();
    }
    if(type == 'sLzWebClassList'){
        saveWebClassOrder();
    }
    if(type=='sLzAdminList'){
        saveAdminOrder();
    }
}

var updateWebClass = function(url){
    var postData=$('#form1').formSerialize();
    $.ajax({
        url: url,
        type:"POST",
        data: postData,
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        dataType: "json",
        cache: false,
        success: function(data){
            if(data.code==1){
                alert('更新成功')
            }else{
                alert('更新失败')
            }
        }
    });
}


function loadCheckBox(id){
    $.ajax({
        url: ctx+"/webClass/loadCheckBox.lz?id="+id,
        dataType: "json",
        cache: false,
        success: function(data){
            $.each(data.lanMus, function (i, item){
                createCheckbox(document.all.sLzLanMuList,item,'sLzLanMuList','sendList_LanMuID')
            });
            $.each(data.products, function (i, item){
                createCheckbox(document.all.sLzProductList,item,'sLzProductList','sendList_ProductID')
            });
            $.each(data.webClasses, function (i, item){
                createCheckbox(document.all.sLzWebClassList,item,'sLzWebClassList','sendList_WebClassID')
            });
            $.each(data.admins,function(i,item){
                createCheckbox(document.all.sLzAdminList,item,'sLzAdminList','sendList_AdminID')
            });
        }
    });
}

function createCheckbox(list,item,type,idName){
    var oDiv;
    var id = item.id;
    var content = item.name;
    if (document.getElementById(id) == null) {
        var tmpstr = "["+id+"]"+content + "<input type='checkbox' checked id='"+id+"'  name='" + idName
            + "' value='" + id + "' onclick='itemChanged(&apos;"+type+"&apos;)' >";
        oDiv = window.document.createElement("li");
        oDiv.itemID = id;
        oDiv.innerHTML = tmpstr;
        list.insertAdjacentElement("BeforeEnd", oDiv);
        saveLanmuOrder();
        saveProductOrder();
        saveWebClassOrder();
        saveAdminOrder();
    }
}

function autocompleteAdmin(){
    $("#selAdmin").autocomplete("http://exp.oilchem.com/admin/admin_showadminname-utf-8.asp?sn=1", {
        minChars: 0,
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
                    result: row.id + '.' +row.name.replace(/<\/?font[^>]*>/gi,"")
                }
            });
        },
        formatItem: function(item) {
            return format(item);
        }
    }).result(function (event, item) {
            if (item.id) {
                CreatAdminList( item.id, item.name, "sendList_AdminID");
            }
        })
}

function format(user) {
    var colo = "";
    if(user.id<=0){
        colo = "red";
    }else if(user.ds=="离职"){
        colo = "gray";
    }
    return ((colo!="")?"<font color="+colo+">":"" )+ user.name + " (" + user.py + ")" + " (" + user.ds + ")" +( (colo!="")?"</font>":"");
}

function CreatAdminList(id, content, idName) {
    var oDiv;
    if (document.getElementById(id) == null) {
        var tmpstr = "["+id+"]"+content + "<input type='checkbox' checked id='"+id+"'  name='" + idName + "' value='" + id + "' onclick='itemChanged(&apos;sLzAdminList&apos;)' >";
        oDiv = window.document.createElement("li");
        oDiv.itemID = id;
        oDiv.innerHTML = tmpstr;
        document.all.sLzAdminList.insertAdjacentElement("BeforeEnd", oDiv);
        saveAdminOrder();
    }
}

//保存包含负责人排序
function saveAdminOrder(){
    var data = $("#sLzAdminList li").map(
        function() {
            var checkbox = $(this).children('input:checkbox')
            if($(checkbox).attr("checked")==true){
                return $(checkbox).attr("id")
            }else{
                return
            }
        }
    ).get();
    $("#adminIds").val(data);
}



$().ready(function () {
    bindseletWebClass('selWebClass', 'sLzWebClassList', 2);
    bindseletLanMu('selLanMu', 'sLzLanMuList', 2);
    bindseletProducts('selProducts', 'sLzProductList', 1);
    autocompleteAdmin();

    loadCheckBox(webClassId);
//    loadCheckBox("sLzProductList",webClassId);
//    loadCheckBox("sLzWebClassList",webClassId);
});


