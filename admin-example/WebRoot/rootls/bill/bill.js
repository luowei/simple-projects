/**
 * 发票管理
 * User: luowei
 * Date: 13-3-27
 * Time: 下午3:04
 */


//由[id,bill]构成的map
var billMap = []

//弹窗句柄
var win = null;

//当前页
var currentPage = 1;

/**
 * 选择下拉列表，动态创建输入框
 * @param obj  下拉列表对象
 */
var changbind = function (obj) {
    var input = document.getElementById('keyword')
    input.name = obj.value
    if (obj.value == 'sendTime') {
        $("#keyword").after("<span id='keywordtip'>(日期格式:2013-03-05)</span> ")
    } else {
        $('#keywordtip').remove();
    }
}

/**
 * 表格体 tbody
 * @param page
 */
var appendtbody = function (page,ctx) {
    billMap = []
    var tbody = ""
    $.each(page.content, function (i, bill) {
        billMap[bill.id] = bill
        var trClass = i % 2 == 0 ? 'tdclass0' : 'tdclass1'
        tbody = tbody + "  \
                            <tr align='center'class='" + trClass + "' >    \
                            <td align='left'>" + (bill.receipt != null ? bill.receipt : '--') + "</td>      \
                            <td align='left'>" + (bill.name != null ? bill.name : '--') + "</td> \
                            <td align='left'>" + (bill.htbianhao != null ? bill.htbianhao : '--') + "</td>   \
                            <td align='left'>" + (bill.guestType != null ? bill.guestType : '--') + "</td> \
                            <td align='left'>" + (bill.qymc != null ? bill.qymc : '--') + "</td>      \
                            <td align='left' nowrap>" + (bill.receiptdate != null ? bill.receiptdate : '--') + "</td>  \
                            <td align='left'>" + (bill.shouruxianmu != null ? bill.shouruxianmu : '--') + "</td>  \
                            <td align='left'>" + (bill.recieptsum != null ? bill.recieptsum : '--') + "</td>   \
                            <td align='left'>" + (bill.kuanxianglx == 1 ? '应收' : '--') + "</td>   \
                            <td align='left'>" + (bill.huikuandi != null ? bill.huikuandi : '--') + "</td>    \
                            <td align='left'>" + (bill.trackname != null ? bill.trackname : '--') + "</td>  \
                             <td align='left'>                           \
                                <a title='aaaaaa' href='javascript:billshow(" + bill.id + ")'>查看</a>     \
                                <a title='aaaaaa' href='javascript:billedit(" + bill.id + ",&quot;"+ctx+"&quot;)'>编辑</a>   \
                             </td>  \
                            </tr>"
    })
//    $("##table-1 tbody").html("");
    $("#table-1 tbody > tr").remove();
    $("#table-1 tbody").append(tbody);
};


/**
 * 显示导入窗体
 */
var showImport = function () {
    win =  _window.Open("[id]billImport", "导入发票的Excel文件",
        "isModal=yes,class=NOKIA,width=420,height=60");
}

/**
 * 上传并导入发票
 */
var billImport = function (ctx) {
    $("#importlog").remove();
    $.ajaxFileUpload({
            url: ctx+'/bill/impExl.lz',
            secureuri: false,
            fileElementId: 'importExcel',
            dataType: 'json',
            success: function (data, status) {
                if(data.code==1){
                    alert("导入完成!")
                    _window.CloseWindow(win)
                    submitSearchForm(currentPage,ctx);
                    var getLogurl = ctx+"/bill/showlog.lz"
                    $("#import").after("<input id='importlog' type='button' value='查看导入日志' \
                        onclick='showlog(&quot;" + getLogurl + "&quot;)'>")
                }else{
                    alert('导入失败')
                }
            }
        })
}

/**
 * 查看日志
 */
var showlog = function(geturl){
    var content = "";
    $.ajax({
        url: geturl,
        dataType: "json",
        success: function (logs) {
            $.each(logs, function (i, log) {
                content = content+log+"<br/>"
            })
            $("#showlog p").text('');
            $("#showlog p").append(content);
        }
    })
    _window.Open("[id]showlog", "查看导入日志",
        "isModal=yes,button=OK,class=NOKIA,width=600,height=400");
}

/**
 * 查看发票
 * @param id
 */
var billshow = function (id) {
    //发票状态
    var receipttype = null;
    if(billMap[id].receipttype==1){
        receipttype = "有效"
    }
    if(billMap[id].receipttype==-1){
        receipttype = "作废"
    }

    //邮寄状态
    var sendtype = null
    if(billMap[id].sendtype==0){
        sendtype = "未寄出"
    }
    if(billMap[id].sendtype==1){
        sendtype = "已寄出"
    }
    if(billMap[id].sendtype==2){
        sendtype = "已到达"
    }
    if(billMap[id].sendtype==-1){
        sendtype = "退回"
    }

    var content = " \
        <table>  \
        <tr>  \
            <td><b>合同编号：</b>" + billMap[id].htbianhao + "</td>  \
            <td><b>开票时间：</b>" + billMap[id].receiptdate + "</td>   \
        </tr>    \
        <tr>  \
            <td><b>发票号：</b>" + billMap[id].receipt + "</td>   \
            <td><b>发票状态：</b>" + receipttype + "</td>  \
        </tr>    \
        <tr>  \
            <td><b>邮寄状态：</b>" + sendtype + "</td>   \
            <td><b>收入项目：</b>" + billMap[id].shouruxianmu + "</td>   \
        </tr>    \
        <tr>  \
            <td><b>发票金额：</b>" + billMap[id].receiptsum + "</td>    \
            <td><b>邮寄地址：</b>" + billMap[id].sendAddr + "</td>   \
        </tr>    \
        <tr>  \
            <td><b>邮编：</b>" + billMap[id].sendZip + "</td>    \
            <td><b>联系人：</b>" + billMap[id].sendLxr + "</td>   \
        </tr>    \
        <tr>  \
            <td><b>电话：</b>" + billMap[id].sendPhone + "</td>   \
            <td><b>手机：</b>" + billMap[id].sendMobile + "</td>   \
            </tr>    \
        <tr>  \
            <td><b>邮寄时间：</b>" + billMap[id].sendTime + "</td>   \
            <td><b>邮寄内容：</b>" + billMap[id].sendContent + "</td>   \
        </tr>   \
        </table>  \
        "

    win = _window.Open(content, "查看发票",
        "isModal=yes,button=OK,class=NOKIA,width=420,height=200");
}

/**
 * 编辑发票
 * @param id
 */
var billedit = function (id,ctx) {
    var updateUrl = ctx+'/bill/updatejson.lz'

    //发票状态
    var receipttype = "<select name='receipttype' ><option value=''>--</option><option value='1'";
    if(billMap[id].receipttype==1){
        receipttype = receipttype+" selected "
    }
    receipttype = receipttype+" >有效</option><option value='2' "
    if(billMap[id].receipttype==-1){
        receipttype = receipttype+" selected "
    }
    receipttype = receipttype+" >作废</option></select>"

    //邮寄状态
    var sendtype = "<select name='sendtype' ><option value=''>--</option><option value='0'";
    if(billMap[id].sendtype==0){
        sendtype = sendtype+" selected "
    }
    sendtype = sendtype+" >未寄出</option><option value='1' "
    if(billMap[id].sendtype==1){
        sendtype = sendtype+" selected "
    }
    sendtype = sendtype+" >已寄出</option><option value='2' "
    if(billMap[id].sendtype==2){
        sendtype = sendtype+" selected "
    }
    sendtype = sendtype+" >已到达</option><option value='-1' "
    if(billMap[id].sendtype==-1){
        sendtype = sendtype+" selected "
    }
    sendtype = sendtype+" >退回</option></select>"

    var content = " \
        <table>  \
        <tr>  \
            <td><b>合同编号：</b><input name='id' value='"+billMap[id].id+"' type='hidden'/> \
                                <input name='gid' value='"+billMap[id].gid+"' type='hidden'/> \
                                <input name='htbianhao' value='"+billMap[id].htbianhao+"' type='hidden'/> \
                                " + billMap[id].htbianhao + "</td>  \
            <td><b>开票时间：</b><input name='receiptdate' value='" + (billMap[id].receiptdate==null?'':billMap[id].receiptdate) + "'\
                                    onclick='WdatePicker()'></td>   \
        </tr>    \
        <tr>  \
            <td><b>发票号：</b><input name='receipt' value='" + billMap[id].receipt + "'></td>   \
            <td><b>发票状态：</b>" + receipttype + "</td>  \
        </tr>    \
        <tr>  \
            <td><b>邮寄状态：</b>" + sendtype + "</td>   \
            <td><b>收入项目：</b><input name='shouruxianmu' value='" + billMap[id].shouruxianmu + "'></td>   \
        </tr>    \
        <tr>  \
            <td><b>发票金额：</b><input name='receiptsum' value='" + billMap[id].receiptsum + "'></td>    \
            <td><b>邮寄地址：</b><input name='sendAddr' value='" + billMap[id].sendAddr + "'></td>   \
        </tr>    \
        <tr>  \
            <td><b>邮编：</b><input name='sendZip' value='" + billMap[id].sendZip + "'></td>    \
            <td><b>联系人：</b><input name='sendLxr' value='" + billMap[id].sendLxr + "'></td>   \
        </tr>    \
        <tr>  \
            <td><b>电话：</b><input name='sendPhone' value='" + billMap[id].sendPhone + "'></td>   \
            <td><b>手机：</b><input name='sendMobile' value='" + billMap[id].sendMobile + "'></td>   \
            </tr>    \
        <tr>  \
            <td><b>邮寄时间：</b><input name='sendTime' value='" + (billMap[id].sendTime==null?'':billMap[id].sendTime) + "' \
                                    onclick='WdatePicker()'></td>   \
            <td><b>邮寄内容：</b><input name='sendContent' value='" + billMap[id].sendContent + "'></td>   \
        </tr>   \
        </table><br/>\
         <p align='center'><input type='button' value='更新' style='width:50px;heigt:36px' onclick='updatebill(&quot;" + updateUrl + "&quot;,&quot;" + ctx + "&quot;)'> \
         <input type='button' value='取消' style='width:50px;heigt:36px' onclick='cancle()'></p> \
        "

    win = _window.Open(content, "编辑发票",
        "isModal=yes,class=NOKIA,width=450,height=230");
}

/**
 * 更新bill
 */
var updatebill = function(url,ctx){

    var postData = $('.FORM').serialize()
//    var getUrl = encodeURI(url+"?"+postData)
//    alert($('.FORM').serialize())

    $.ajax({
        url: url+"?"+postData,
//        data: postData,
        dataType: "json",
        success: function(data){
            if(data.code==1){
             alert('更新成功')
             submitSearchForm(currentPage,ctx);
             _window.CloseWindow(win)
            }else{
                alert('更新失败')
            }
        }
    });
}

/**
 * 取消
 */
var cancle = function(){
    _window.CloseWindow(win)
}

/**
 * 分页条
 * @param page   page对象
 * @param ctx   应用路径
 */
var pagebar = function (page, ctx) {
    var currentIndex = page.pageNumber;
    var beginIndex = Math.max(1, currentIndex - 4);
    var endIndex = Math.min(beginIndex + 4, page.totalPage);
    var url = ctx + "/bill/listjson.lz"

    var tpage = "<td valign=middle>页次:<b>" + page.pageNumber + "</b>/<b>" + page.totalPage + "</b>页 \
                    本页<b>" + page.pageSize + " </b>  总:<b>" + page.totalCount + "</b></td>"

    tpage = tpage + "<td valign='middle' align='right'>分页:<b>"
    if (currentIndex != 1 && currentIndex != 'null') {
        tpage = tpage + "<a href='javascript:submitSearchForm(1,&quot;" + ctx + "&quot;)'>首页</a>&nbsp;"
    }
    for (var i = beginIndex; i <= endIndex; i++) {
        if (i == currentIndex || i == 0) {
            tpage = tpage + "<font style='color : #FF0000;text-decoration : underline;'><b>" + i + "</b></font>&nbsp;"
        } else {
            tpage = tpage + "<a href='javascript:submitSearchForm(" + i + ",&quot;" + ctx + "&quot;)'>" + i + "</a>&nbsp;"
        }
    }
    if (currentIndex != page.totalPage) {
        tpage = tpage + "<a href='javascript:submitSearchForm(" + page.totalPage + ",&quot;" + ctx + "&quot;)'>尾页</a>&nbsp;"
    }
    tpage = tpage + "</b>转到:<input type='text' id='gopage' size='3' maxlength='10' value='" + currentIndex + "'> \
                            <input type='button' value='Go' \
                            onclick='submitSearchForm(document.getElementById(&quot;gopage&quot;).value,&quot;" + ctx + "&quot;)'>"
    $("#tpage tr td").remove();
    $("#tpage tr").append(tpage);
};

/**
 * 提交查询
 * @param pageNumber
 * @param ctx
 */
var submitSearchForm = function (pageNumber, ctx) {
    var url = ctx + "/bill/listjson.lz?pageNumber=" + pageNumber + "&" + $('#searchForm').serialize()
    getpage(ctx, url)
    currentPage = pageNumber
}

/**
 * 提交导出
 * @param pageNumber
 * @param ctx
 */
var submitExport = function (ctx) {
    var url = ctx + "/bill/expExl.lz?pageNumber=" + currentPage + "&" + $('#searchForm').serialize()
    window.open(url,"_blank")
}


/**
 * 获得页面数据
 * @param ctx
 */
var getpage = function (ctx, geturl) {
    $.ajax({
        url: geturl,
        dataType: "json",
        success: function (page) {
            appendtbody(page,ctx);
            pagebar(page, ctx);
        }
    })
}

/**
 * input 自动补全
 * @param input
 */
var inputAutocomplete = function (ctx,input) {
    getuser(ctx, function (output) {
        $(input).autocomplete(output);
    });
}

/**
 * 获取跟踪人
 * @param ctx
 */
function getuser(ctx, handle) {
    $.ajax({
        url: ctx + "/bill/getuser.lz",
        dataType: "json",
        success: function (data) {
            handle(data);
        }
    });
}

