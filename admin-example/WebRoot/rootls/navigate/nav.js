//*************初始化数据*************
var _JsonDataUrl_tree = ctx+"/getTreeDatas.do";
var _editNav=ctx+"/webClass/edit.lz";

var tree;
var viewport;
var RightClickMenu;
//*************初始化开始*************
Ext.onReady(function(){
	Ext.QuickTips.init();
	// 树
	tree = new Ext.tree.TreePanel({
        useArrows: true,
        animate: true,
        enableDD: true,
        containerScroll: true,
        border: false,
        dataUrl: _JsonDataUrl_tree,
        //hlColor : 'red',
        lines :true,
        root: {
            nodeType: 'async',
            text: '导航位置列表',
            draggable: true,
            id: '0'
        },
        listeners : {
            'click' : function(node, event) {
                if (node.isLeaf()) {
                    // 为叶子节点时，点击进入链接
                    showLink(node);
//                    EditByNode(node.id);
                    event.stopEvent();
                }
            }
        }
    });

//    tree.on('click', function(node, e) {
//    	EditByNode(node.id);
//    });

    var msForm = new Ext.FormPanel({
		align : 'center',
        frame:true,
    	bodyStyle:'padding:5px 5px 0;',
    	layout:'form',
//    	width: 600,
//        height:430,
    	items:[{
            title: '编辑页',
            autoScroll:true,
            autoHeight:true,
            closable:true,
            html:'<iframe id="editFrame" src='+_editNav+'?id=1 scrolling="auto" height="600px" width="100%"></iframe>'
        }]
	})

	var west = new Ext.Panel({
        region:'west',
        margins:'5 0 5 5',
        id: 'west-panel', // see Ext.getCmp() below
        title: '导航列表管理',
        split: true,
        width: 150,
        minSize: 150,
        maxSize: 400,
        collapsible: true,
        collapseMode:'mini',
        autoScroll:true,
        items: [tree]
    });

  	var main = new Ext.Panel({
        region:'center',
        layout:'fit',
        margins:'5 5 5 0',
        autoScroll:true,
        id: 'main-panel',
        items:[msForm]
    });

	viewport = new Ext.Viewport({
        layout:'border',
        items:[west, main]
	});

	tree.expandPath('/0');
	tree.render();

});

function showLink(node){

    var id = node.id;

    var iFrame=document.getElementById("editFrame");
    iFrame.src=_editNav+"?id="+id;
//    alert(_editNav+"?id="+id);
}



