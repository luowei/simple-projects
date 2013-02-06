function subform(obj, pageNumber, productName, area) {

    if (undefined != area && area != 'null' && (area != null || area != "")) {
        $("#area").val(area);
    } else if (area == 'null') {
        $("#area").val('');
    }

    if (undefined != productName && (productName != null || productName != "")) {
        $("#productName").val(productName);
    }

    if (undefined != pageNumber && (pageNumber != null || pageNumber != "")) {
        $("#pageNumber").val(pageNumber);
    }
    var href = '${ctx}${contextUrl}/';

    $("#pageForm").attr("action", $(obj).attr("href1")).submit();
}

Date.prototype.format = function (format) {
    var o =
    {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}