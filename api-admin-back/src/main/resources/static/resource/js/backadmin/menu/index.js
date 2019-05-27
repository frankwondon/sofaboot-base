layui.config({
    base: '/resource/js/modules/' //你存放新模块的目录，注意，不是layui的模块目录
}).use(['form', 'request', 'table', 'dtree'], function () {
    var form = layui.form;
    $ = layui.jquery;
    var request = layui.request;
    var layer = layui.layer;
    var dtree = layui.dtree;
    var urlparam = request.getParam();
    var response = {
        statusName: "code", //返回标识（必填）
        statusCode: 200, //返回码（必填）
        message: "msg", //返回信息（必填）
        rootName: "result", //根节点名称（必填）
    };

    // 初始化树
    var tree = dtree.render({
        icon: "2",
        dataStyle: "layuiStyle",
        elem: "#dtree",
        // data: demoTree, // 使用data加载
        toolbar: true,
        record:true,
        toolbarBtn:[
            [
                {"label":"路径","name":"url","type":"text"},
            ],
            [
                {"label":"路径","name":"url","type":"text"}
            ]
        ],
        toolbarFun: {
            editTreeLoad: function(treeNode){
                var data=JSON.parse(treeNode.recordData);
                console.log(treeNode)
                tree.changeTreeNodeDone(data);
             },
            editTreeNode: function(treeNode, $div){
                request.post('/backadmin/menu/update',
                {id:treeNode.nodeId,title:treeNode.context,url:treeNode.url},
                    function (result) {
                        tree.changeTreeNodeEdit(true);
                    }
                )

            },
            loadToolbarBefore: function(buttons, param, $div){
                if(param.level==3){ // 如果是叶子节点
                    buttons.addToolbar = "";  // 取消新增功能
                }
                if (param.level==1){
                    buttons.delToolbar="";
                }
                return buttons; // 将按钮对象返回
            },
            addTreeNode: function(treeNode, $div){
                var data={pId:treeNode.parentId,title:treeNode.addNodeName,url:treeNode.url};
                request.post('/backadmin/menu/add',
                    data,
                    function (result) {
                        tree.changeTreeNodeAdd(result.result);
                    }
                )

            },
            delTreeNode: function(treeNode, $div){
                request.post('/backadmin/menu/del',
                    {id:treeNode.nodeId},
                    function (result) {
                        tree.changeTreeNodeDel(true);
                    }
                )
            }
        },
        dataFormat: "list",
        method: "get",
        checkbarType: "all",
        request: urlparam,
        response: response,
        url: "/backadmin/menu/list" // 使用url加载（可与data加载同时存在）
    });


    $("#save").click(function () {
        var params = dtree.getCheckbarNodesParam("dtree");
        var menuids = '';
        params.forEach(function (val) {
            menuids += val.nodeId + ','
        });
        request.post(
            '/backadmin/role/allotMenus',
            {
                menuIds: menuids,
                roleId: urlparam.roleId
            },
            function (result) {
                if (result.result) {
                    location.reload()
                }
            }
        )
    });

});