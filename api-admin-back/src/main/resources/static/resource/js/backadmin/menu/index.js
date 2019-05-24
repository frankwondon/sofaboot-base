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
    var demoTree = [
        {"id": "001", "title": "湖南省", "checkArr": "1", "parentId": "0"},
        {"id": "002", "title": "湖北省", "checkArr": "0", "parentId": "0"},
        {"id": "003", "title": "广东省", "checkArr": "0", "parentId": "0"},
        {"id": "004", "title": "浙江省", "checkArr": "0", "parentId": "0"},
        {"id": "005", "title": "福建省", "checkArr": "0", "parentId": "0"},
        {"id": "001001", "title": "长沙市", "checkArr": "0", "parentId": "001"},
        {"id": "001002", "title": "株洲市", "checkArr": "1", "parentId": "001"},
        {"id": "001003", "title": "湘潭市", "checkArr": "0", "parentId": "001"},
        {"id": "001004", "title": "衡阳市", "checkArr": "0", "parentId": "001"},
        {"id": "001005", "title": "郴州市", "checkArr": "0", "iconClass": "dtree-icon-caidan_xunzhang", "parentId": "001"}
    ]
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
                data['title']=treeNode.context;
                tree.changeTreeNodeDone(data)
            },
            editTreeNode: function(treeNode, $div){
                request.post('/backadmin/menu/update',
                {id:treeNode.nodeId,title:treeNode.context,url:treeNode.url},
                    function (result) {
                        if (result.code==200){
                            tree.changeTreeNodeEdit(true);
                        }
                    }
                )

            },
            loadToolbarBefore: function(buttons, param, $div){
                if(param.level==3){ // 如果是叶子节点
                    buttons.addToolbar = "";  // 取消新增功能
                }
                return buttons; // 将按钮对象返回
            },
            addTreeNode: function(treeNode, $div){

                // var data={pId:treeNode.parentId};
                //
                // request.post('/backadmin/menu/add',
                //     {id:treeNode.nodeId,title:treeNode.context,url:data.url},
                //     function (result) {
                //         if (result.code==200){
                //             tree.changeTreeNodeEdit(true);
                //         }
                //     }
                // )
                // $.ajax({
                //     type: "post",
                //     data: treeNode,
                //     url: "/DTreeHelper/toolbar/insert",
                //     success: function(result){
                //         //DTree1.changeTreeNodeAdd(treeNode.nodeId); // 添加成功，返回ID
                //         //DTree1.changeTreeNodeAdd(true); // 添加成功
                //         //DTree1.changeTreeNodeAdd(result.data); // 添加成功，返回一个JSON对象
                //         //DTree1.changeTreeNodeAdd("refresh"); // 添加成功，局部刷新树
                //     },
                //     error: function(){
                //         //DTree1.changeTreeNodeAdd(false); // 添加失败
                //     }
                // });
            },
        },
        dataFormat: "list",
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