// ----------------------------------------------------------
// ******************  风声 JS 窗口 V1.00  ******************
// 作者：风声
// 版本：1.00
// 网站：http://www.fonshen.com
// 邮件：webmaster@fonshen.com
// 版权：版权全体,源代码公开,各种用途均可免费使用
// **********************************************************
// ----------------------------------------------------------
function _window(features)
{
	this.id=this.name="_W_"+(++_window.wIndex);
	this.string="_window.windows."+this.id;
	this.zIndex=++_window.zIndex;
	this.className=_window.getFeature(features,"class")||_window.ClassName;
	this.bodyWidth=parseInt(_window.getFeature(features,"width"))||_window.Width;
	this.bodyHeight=parseInt(_window.getFeature(features,"height"))||_window.Height;
	this.left=_window.getFeature(features,"left");
	this.top=_window.getFeature(features,"top");
	this.right=_window.getFeature(features,"right");
	this.bottom=_window.getFeature(features,"bottom");
	this.move=_window.getFeature(features,"move").toLowerCase()||"move";
	this.isModal=_window.getFeature(features,"ismodal")?true:false;
	this.button=_window.getFeature(features,"button").toUpperCase();
	this.resize=_window.getFeature(features,"resize").toLowerCase()||"resize";
	this.buttons={};
	_window.windows[this.id]=this;
}

_window.Version="Fonshen JS Window V1.00.08.01.03";
_window.ClassName="NOKIA";
_window.Width=400;
_window.Height=-1;
_window.Timer=10;
_window.windows={};
_window.wIndex=0;
_window.zIndex=0;
_window.clientWidth=0;
_window.clientHeight=0;
_window.onmousemove=null;
_window.onmouseup=null;
_window.onselectstart=null;

_window.getFeature=function(source,name)
{
	var reg=new RegExp("(^|,|\\s)"+ name +"\\s*=\\s*([^,]*)(\\s|,|$)","i");
	if (reg.test(source)) return RegExp.$2;
	return "";
};
_window.getMaxNumber=function()
{
	var num=0;
	for(var i=0;i<arguments.length;i++) if(arguments[i]>num) num=arguments[i];
	return num;
};

_window.Open=function(content,title,features)
{
	var obj=new _window(features);
	obj.Creat(content,title);
	return obj;
};

_window.CloseWindow=function(obj)
{
    obj.Hidden();
    delete _window.windows[obj.id];
    for(var key in obj) delete obj[key];
};

_window.Alert=function(content,title,features)
{
	var obj=new _window(features);
	obj.button="OK";
	obj.isModal = true;
	obj.Creat(content,title||"Alert");
	obj.buttons["OK"].focus();
	return obj;
};
_window.Confirm=function(content,title,features)
{
	var obj=new _window(features);
	obj.button="OK|CANCEL";
	obj.isModal = true;
	obj.Creat(content,title||"Confirm");
	obj.buttons["OK"].focus();
	return obj;
};

_window.prototype.Creat=function(content,title)
{
	if(this.isModal)
	{
		this.modal=document.createElement("div");
		this.modal.className="MODAL";
		this.modal.style.position="absolute";
		this.modal.style.zIndex=this.zIndex;
		this.modal.style.width=document.documentElement.scrollWidth+"px";
		this.modal.style.height=document.documentElement.scrollHeight+"px";
		this.modal.style.left="0px";
		this.modal.style.top="0px";
		document.body.appendChild(this.modal);
		this.zIndex=++_window.zIndex;
	}
	this.board=document.createElement("div");
	this.board.className=this.className;
	this.board.style.position="absolute";
	this.board.style.zIndex=this.zIndex;
	this.board.style.visibility="hidden";
	document.body.appendChild(this.board);

	if(!_window.clientHeight)
	{
		this.board.style.left = "100%";
		this.board.style.top = "100%";
		_window.clientWidth = this.board.offsetLeft;
		_window.clientHeight = this.board.offsetTop;
	}
	var styles=new Array("LEFT_TOP","TOP","RIGHT_TOP","RIGHT","RIGHT_BOTTOM","BOTTOM","LEFT_BOTTOM","LEFT","CONTENT","TITLE");
	this.sides=new Array();
	if(this.button)
	{
		styles[4]="RIGHT_BOTTOM_BY_BUTTON";
		styles[5]="BOTTOM_BY_BUTTON";
		styles[6]="LEFT_BOTTOM_BY_BUTTON";
		styles[styles.length]="BUTTON";
	}
	if(this.resize!="no") styles[styles.length]="RESIZE";
	styles[styles.length]="CLOSE";
	var sAttachFocus="obj.onmousedown=function(e){ "+this.string+".Focus();};";
	var sAttachPreMove=this.move=="no"?sAttachFocus:"obj.onmousedown=function(e){ "+this.string+".PreMove(e?e:window.event);};";
	for(var i=0;i<styles.length;i++)
	{
		var obj=document.createElement("div");
		obj.className=styles[i];
		obj.style.position="absolute";
		this.board.appendChild(obj);
		switch(styles[i])
		{
			case "CONTENT":
				this.contentCase=obj;
				obj.style.width=this.bodyWidth+"px";
				this.SetContent(content);
				obj.style.height=this.bodyHeight+"px";
				obj.style.left=(this.sides[7].width+obj.offsetLeft)+"px";
				obj.style.top=(this.sides[1].height+obj.offsetTop)+"px";
				eval(sAttachFocus);
				this.width=this.bodyWidth+this.sides[3].width+this.sides[7].width;
				this.height=this.bodyHeight+this.sides[1].height+this.sides[5].height;
				this.board.style.height=this.height+"px";
				this.board.style.width=this.width+"px";
				this.minWidth=_window.getMaxNumber(this.sides[0].width+this.sides[2].width,this.sides[3].width+this.sides[7].width,this.sides[4].width+this.sides[6].width)+20;
				this.minHeight=_window.getMaxNumber(this.sides[0].height+this.sides[6].height,this.sides[1].height+this.sides[5].height,this.sides[2].height+this.sides[4].height)+2;
				break;

			case "TITLE":
				this.titleCase=obj;
				obj.style.width=this.width+"px";
				this.titleCase.dx=obj.offsetWidth-this.width;
				if(this.minWidth<this.titleCase.dx) this.minWidth=this.titleCase.dx;
				if(this.width>this.titleCase.dx) obj.style.width=(this.width-this.titleCase.dx)+"px";
				this.SetTitle(title);
				eval(sAttachPreMove);
				break;

			case "BUTTON":
				this.buttonCase=obj;
				obj.style.width=this.width+"px";
				obj.style.bottom="0px";
				this.buttonCase.dx=obj.offsetWidth-this.width;
				if(this.minWidth<this.buttonCase.dx) this.minWidth=this.buttonCase.dx;
				if(this.width>this.buttonCase.dx) obj.style.width=(this.width-this.buttonCase.dx)+"px";
				var buttons=this.button.split("|");
				for(var j=0;j<buttons.length;j++)
				{
					var o=document.createElement("button");
					o.className=buttons[j];
					o.title=buttons[j];
					obj.appendChild(o);
					eval("o.onclick=function(e){ "+this.string+".On"+buttons[j]+"();};");
					this.buttons[buttons[j]]=o;
				}
				break;

			case "RESIZE":
				obj.style.cursor="se-resize";
				eval("obj.onmousedown=function(e){ "+this.string+".PreResize(e?e:window.event);};");
				break;

			case "CLOSE":
				eval("obj.onclick=function(e){ "+this.string+".Close();};");
				break;

			default:
				this.sides[i]=obj;
				this.sides[i].width=obj.offsetWidth;
				this.sides[i].height=obj.offsetHeight;
				eval(sAttachPreMove);
				break;
		}
	}
	this.sides[1].dx=this.sides[0].width+this.sides[2].width;
	if(this.width>this.sides[1].dx) this.sides[1].style.width=(this.width-this.sides[1].dx)+"px";
	this.sides[3].dy=this.sides[2].height+this.sides[4].height;
	if(this.height>this.sides[3].dy) this.sides[3].style.height=(this.height-this.sides[3].dy)+"px";
	this.sides[5].dx=this.sides[4].width+this.sides[6].width;
	if(this.width>this.sides[5].dx) this.sides[5].style.width=(this.width-this.sides[5].dx)+"px";
	this.sides[7].dy=this.sides[6].height+this.sides[0].height;
	if(this.height>this.sides[7].dy) this.sides[7].style.height=(this.height-this.sides[7].dy)+"px";

	this.sides[0].style.left="0px";
	this.sides[0].style.top="0px";
	this.sides[1].style.left=this.sides[0].width+"px";
	this.sides[1].style.top="0px";
	this.sides[2].style.right="0px";
	this.sides[2].style.top="0px";
	this.sides[3].style.right="0px";
	this.sides[3].style.top=this.sides[2].height+"px";
	this.sides[4].style.right="0px";
	this.sides[4].style.bottom="0px";
	this.sides[5].style.left=this.sides[6].width+"px";
	this.sides[5].style.bottom="0px";
	this.sides[6].style.left="0px";
	this.sides[6].style.bottom="0px";
	this.sides[7].style.left="0px";
	this.sides[7].style.top=this.sides[0].height+"px";

	this.left=this.left?parseInt(this.left):(this.right?_window.clientWidth-this.width-parseInt(this.right):parseInt((_window.clientWidth-this.width)/2));
	this.top=this.top?parseInt(this.top):(this.bottom?_window.clientHeight-this.height-parseInt(this.bottom):parseInt((_window.clientHeight-this.height)/2));
	if(this.left<0) this.left=0;
	if(this.top<0) this.top=0;
	this.left+=document.documentElement.scrollLeft;
	this.top+=document.documentElement.scrollTop;
	this.board.style.left=this.left+"px";
	this.board.style.top=this.top+"px";
	this.board.style.visibility="visible";
	this.status=1;
};

_window.prototype.SetContent=function(content)
{
	var type=content.slice(0,5),tent=content.slice(5);
	if(this.oldcase)
	{
		this.oldcase.appendChild(this.oldcontent);
		this.oldcase = null;
	}
	if(type=="[url]")
	{
		if(this.iframe)
		{
			if(this.contentCase.firstChild!=this.iframe) this.contentCase.replaceChild(this.iframe,this.contentCase.firstChild);
			this.iframe.src=tent;
		}
		else
		{
			var str=(this.bodyHeight<0)?"onload=\"if("+this.string+".bh<2)"+this.string+".ResizeBy(0,frames[frames.length-1].document.documentElement.scrollHeight);\"":"";
			var id="_F"+this.id;
			this.contentCase.innerHTML="<iframe id=\""+id+"\" name=\""+id+"\" "+str+" onfocus=\""+this.string+".Focus()\" src=\""+tent+"\" frameBorder='0' marginHeight='0' marginWidth='0' width='100%' height='100%'></iframe>";
			if(this.bodyHeight<0) this.bodyHeight=1;
			this.iframe=document.getElementById(id);
			this.frame=eval(id);
		}
	}
	else
	{
		if(this.form)
		{
			if(this.contentCase.firstChild!=this.form) this.contentCase.replaceChild(this.form,this.contentCase.firstChild);
		}
		else
		{
			this.form=document.createElement("form");
			this.form.className="FORM";
			this.form.method="post";
			eval("this.form.onsubmit=function(e){ return false;}");
			if(this.contentCase.hasChildNodes())this.contentCase.replaceChild(this.form,this.contentCase.firstChild);
			else this.contentCase.appendChild(this.form);
		}
		if(type=="[obj]"||content.slice(0,4)=="[id]")
		{
			if(type=="[obj]")this.oldcontent=eval(tent);
			else this.oldcontent=document.getElementById(content.slice(4));
			this.oldcase=this.oldcontent.parentNode;
			this.form.appendChild(this.oldcontent);
		}
		else
		{
			this.form.innerHTML=content;
		}
		if(this.bodyHeight<0) this.bodyHeight=this.contentCase.offsetHeight+1;
		this.contentCase.style.overflow="auto";
	}
	this.content=content;
};
_window.prototype.SetTitle=function(title)
{
	title=title||"Fonshen JS Window";
	if(this.title==title) return false;
	this.titleCase.innerHTML=title;
	this.title=title;
};

_window.prototype.Focus=function()
{
	if(this.zIndex<_window.zIndex) this.board.style.zIndex=this.zIndex=++_window.zIndex;
};
_window.prototype.Close=function()
{
	this.Hidden();
	delete _window.windows[this.id];
	for(var key in this) delete this[key];
};

_window.prototype.Hidden=function()
{
	if (typeof(this.MoveTimer)!="undefined") clearTimeout(this.MoveTimer);
	if (typeof(this.ResizeTimer)!="undefined") clearTimeout(this.ResizeTimer);
	document.body.removeChild(this.board);
	if(this.isModal) document.body.removeChild(this.modal);
	if(this.oldcase) this.oldcase.appendChild(this.oldcontent);
	this.status=0;
};
_window.prototype.Show=function()
{
	if(this.oldcase) this.form.appendChild(this.oldcontent);
	if(this.isModal) document.body.appendChild(this.modal);
	document.body.appendChild(this.board);
	this.status=1;
};

_window.prototype.Duplicate=function()
{
	this.copy=document.createElement("DIV");
	document.body.appendChild(this.copy);
	this.copy.style.cssText="position:absolute;left:"+(this.left-2)+"px;top:"+(this.top-2)+"px;width:"+this.width+"px;height:"+this.height+"px;border:2px dotted #000000;";
	this.copy.style.zIndex=this.zIndex+1;
};
_window.prototype.DetachEvent=function(e)
{
	document.body.style.cursor="default";
	document.onmousemove=_window.onmousemove;
	document.onmouseup=_window.onmouseup;
	document.onselectstart=_window.onselectstart;
	if(this.board.releaseCapture) this.board.releaseCapture();
};
_window.prototype.AttachEvent=function(e)
{
	_window.onmousemove=document.onmousemove;
	_window.onmouseup=document.onmouseup;
	_window.onselectstart=document.onselectstart;
	if(e.preventDefault) e.preventDefault();
	else
	{
		document.onselectstart=function(){return false;};
		if(this.board.setCapture) this.board.setCapture();
	}
};

_window.prototype.PreResize=function(e)
{
	if (typeof(this.ResizeTimer)!="undefined") clearTimeout(this.ResizeTimer);
	this.Focus();
	this.Duplicate();
	this.resizeX=e.clientX-this.width-4;
	this.resizeY=e.clientY-this.height-4;
	document.body.style.cursor="se-resize";
	this.AttachEvent(e);
	eval("document.onmousemove=function(e){"+this.string+".Resize(e?e:window.event);};");
	eval("document.onmouseup=function(e){"+this.string+".Resized(e?e:window.event);};");
};
_window.prototype.Resize=function(e)
{
	if(this.resize!="resize-y")
	{
		var w=e.clientX-this.resizeX;
		if(w >= this.minWidth) this.copy.style.width=w+"px";
	}
	if(this.resize!="resize-x")
	{
		var h=e.clientY-this.resizeY;
		if(h >= this.minHeight) this.copy.style.height=h+"px";
	}
};
_window.prototype.Resized=function(e)
{
	this.DetachEvent(e);
	var dx=0,dy=0;
	if(this.resize!="resize-y")
	{
		var w=e.clientX-this.resizeX-4;
		w=(w>this.minWidth)?w:this.minWidth;
		dx=w-this.width;
	}
	if(this.resize!="resize-x")
	{
		var h=e.clientY-this.resizeY-4;
		var h=(h>this.minHeight)?h:this.minHeight;
		dy=h-this.height;
	}
	if(_window.Timer>0&&this.width >= this.minWidth&&this.height >= this.minHeight) this.ActResizeBy(dx,dy);
	else  this.ResizeBy(dx,dy);
	document.body.removeChild(this.copy);
	this.copy=null;
};
_window.prototype.ResizeBy=function(dx,dy)
{
	if(dx)
	{
		this.width+=dx;
		this.board.style.width=this.width+"px";
		this.sides[1].style.width=(this.width-this.sides[1].dx)+"px";
		this.sides[5].style.width=(this.width-this.sides[5].dx)+"px";
		this.titleCase.style.width=(this.width-this.titleCase.dx)+"px";
		if(this.buttonCase) this.buttonCase.style.width=(this.width-this.buttonCase.dx)+"px";
		this.bodyWidth+=dx;
		this.contentCase.style.width=this.bodyWidth+"px";
	}
	if(dy){this.height+=dy;
	this.board.style.height=this.height+"px";
	this.sides[3].style.height=(this.height-this.sides[3].dy)+"px";
	this.sides[7].style.height=(this.height-this.sides[7].dy)+"px";
	this.bodyHeight+=dy;
	this.contentCase.style.height=this.bodyHeight+"px";
	}
};
_window.prototype.ActResizeBy=function(dx,dy)
{
	if(dx||dy)
	{
		var x=dx/10;
		x=(x>0)?Math.ceil(x):Math.floor(x);
		var y=dy/10;
		y=(y>0)?Math.ceil(y):Math.floor(y);
		this.ResizeBy(x,y);
		dx-=x;
		dy-=y;
		this.ResizeTimer=window.setTimeout(this.string+".ActResizeBy("+dx+","+dy+")",_window.Timer);
	}
};

_window.prototype.PreMove=function(e)
{
	if (typeof(this.MoveTimer)!="undefined") clearTimeout(this.MoveTimer);
	this.Focus();
	this.Duplicate();
	this.moveX=e.clientX-this.left+2;
	this.moveY=e.clientY-this.top+2;
	document.body.style.cursor="move";
	this.AttachEvent(e);
	eval("document.onmousemove=function(e){"+this.string+".Move(e?e:window.event);};");
	eval("document.onmouseup=function(e){"+this.string+".Moved(e?e:window.event);};");
};
_window.prototype.Move=function(e)
{
	if(this.move!="move-y") this.copy.style.left=(e.clientX-this.moveX)+"px";
	if(this.move!="move-x") this.copy.style.top=(e.clientY-this.moveY)+"px";
};
_window.prototype.Moved=function(e)
{
	this.DetachEvent(e);
	var tx=(this.move=="move-y")?null:(e.clientX-this.moveX+2);
	var ty=(this.move=="move-x")?null:(e.clientY-this.moveY+2);
	if(_window.Timer>0) this.ActMoveTo(tx,ty);
	else  this.MoveTo(tx,ty);
	document.body.removeChild(this.copy);
	this.copy=null;
};
_window.prototype.MoveTo=function(tx,ty)
{
	if(tx!=null)
	{
		this.left=tx;
		this.board.style.left=tx+"px";
	}
	if(ty!=null)
	{
		this.top=ty;
		this.board.style.top=ty+"px";
	}
};
_window.prototype.ActMoveTo=function(tx,ty)
{
	if((tx==null||tx==this.left)&&(ty==null||ty==this.top)) return;
	if(tx!=null)
	{
		var dx=(tx-this.left)/10;
		dx=(dx>0)?Math.ceil(dx):Math.floor(dx);
		this.left+=dx;
		}if(ty!=null){var dy=(ty-this.top)/10;
		dy=(dy>0)?Math.ceil(dy):Math.floor(dy);
		this.top+=dy;
	}
	this.MoveTo(this.left,this.top);
	this.MoveTimer=window.setTimeout(this.string+".ActMoveTo("+tx+","+ty+")",_window.Timer);
};

_window.prototype.DisableButton=function(name,style)
{
	name=name.toUpperCase();
	this.buttons[name].disabled=true;
	this.buttons[name].className=(style?style:"DISABLED")+" "+name;
};
_window.prototype.EnableButton=function(name)
{
	name=name.toUpperCase();
	this.buttons[name].disabled=false;
	this.buttons[name].className=name;
};

_window.prototype.OnBACK=function()
{
	if(!this.step) this.step=0;
	if(this.OnBACKS){ if(this.step>0) this.OnBACKS[--this.step]();}
};
_window.prototype.OnNEXT=function()
{
	if(!this.step) this.step=0;
	if(this.OnNEXTS){ if(this.step<this.OnNEXTS.length) this.OnNEXTS[this.step++]();}
};
_window.prototype.OnOK=function()
{
	this.Close();
};
_window.prototype.OnCANCEL=function()
{
	this.Close();
};
