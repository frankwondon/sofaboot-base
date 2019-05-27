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
    var DemoTree = dtree.render({
        icon: "2",
        dataStyle: "layuiStyle",
        elem: "#dtree",
        checkbar: true,
        dataFormat: "list",
        method: "get",
        checkbarType: "all",
        request: urlparam,
        response: response,
        url: "/backadmin/role/findRoleMenus" // 使用url加载（可与data加载同时存在）
    });

    // 绑定节点点击
    dtree.on("node('demoTree')", function (obj) {
        layer.msg(JSON.stringify(obj.param));
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
                if (result.result){
                    location.reload()
                }
            }
        )
    });

});