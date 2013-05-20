var ua = window.navigator.userAgent;
var ie = /msie [56789]/i.test(ua);
var moz = /mozilla\/[56789]/i.test(ua);
var ie5 = /msie 5\.0/i.test(ua);

function progressorbar() {};

progressorbar.dir = 1;
progressorbar.width = 300;
progressorbar.height = 90;

progressorbar.initialize = function ()
{
    var x = progressorbar.fixPosition();
    var sHTML = '<div id="WebProgessorBar" style="z-index:999999;background:ThreeDFace;border:2px outset;-moz-border-left-colors:ThreeDLightShadow ThreeDHighLight;-moz-border-right-colors:ThreeDDarkShadow THreeDShadow;-moz-border-top-colors:ThreeDLightShadow ThreeDHighLight;-moz-border-bottom-colors:ThreeDDarkShadow ThreeDShadow;width:' +
        progressorbar.width + 'px;height:' +
        progressorbar.height + 'px;left:' +
        x.left + 'px;top:' +
        x.top + 'px;position:absolute"><table cellspacing=1 cellpadding=0 border=0 width=100% height=100% align=center><tr><td style="height:20px;vertical-align:top;text-align:left"><div style="padding:2px 0px 0px 2px;height:20px;overflow:hidden;background:ActiveCaption;font:Caption;color:CaptionText">Loading...</div></td></tr><tr><td style="text-align:center;vertical-align:'+(ie5?"top":"middle")+'"><div align=center><div style="width:80%;height:20px;border:1px solid ThreeDShadow;background:window;overflow:hidden;text-align:left;padding:1px;font-size:0px;-moz-appearance:progressbar"><div style="width:60px;height:16px;position:relative;left:0px;background:HighLight;overflow:hidden;-moz-appearance:progresschunk;font-size:0px">&nbsp;</div></div></div></td></tr></table></div>'
    document.write(sHTML);
};

progressorbar.start = function ()
{
    progressorbar.initialize();
    var p = document.getElementById("WebProgessorBar");
    if (p) {
        progressorbar.element = p;
        progressorbar.fixPosition();
        progressorbar.timer = window.setInterval(progressorbar.move, 1);
    }
    if (ie)
        window.attachEvent("onload", progressorbar.dispose);
    else
        window.addEventListener("load", progressorbar.dispose, false);
};

progressorbar.fixPosition = function ()
{
    var p = progressorbar.element;
    if (p) {
        var w =  document.body.clientWidth || window.innerWidth;
        var h = document.body.clientHeight || window.innerHeight;
        var x = ie ? document.body.scrollLeft : window.pageXOffset;
        var y = ie ? document.body.scrollTop : window.pageYOffset;
        var l = x + parseInt((w - progressorbar.width)/2);
        var t = y + parseInt((h - progressorbar.height)/2)
        p.style.left = l + 'px';
        p.style.top = t + 'px';
    }
    return {
        "left":	l,
        "top":	t
    }
};

progressorbar.move = function ()
{
    var p = progressorbar.element;
    if (p) {
        var v = p.getElementsByTagName("div")[2].firstChild;
        var l = parseInt(v.style.left);
        if (progressorbar.dir==1 && l >= v.parentNode.offsetWidth)
            progressorbar.dir = -1;
        else if (l <= -v.offsetWidth)
            progressorbar.dir = 1;
        v.style.left = l + progressorbar.dir * 2;
        if (ie) v.style.filter = "Alpha(" + (progressorbar.dir > 0 ? "Opacity=0,FinishOpacity=100" : "Opacity=100,FinishOpacity=0") + ",Style=1,StartX=0,StartY=0,FinishX=100,FinishY=0);"
        progressorbar.fixPosition();
    }
};

progressorbar.dispose = function ()
{
    var p = p = progressorbar.element;
    if (p) {
        p.parentNode.removeChild(p);
        clearInterval(progressorbar.timer);
    }
};