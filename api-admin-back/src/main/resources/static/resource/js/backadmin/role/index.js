layui.config({
    base: '/resource/js/modules/' //你存放新模块的目录，注意，不是layui的模块目录
}).use(['form', 'request','table'], function () {
    var form = layui.form;
    var request = layui.request;
    var laytpl=layui.laytpl;

    console.log(111)
    var table=layui.table;
    table.render({
        elem: '#table'
        ,height: 500
        ,url: '/backadmin/role/list' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'id', title: 'ID', width:100, sort: true, fixed: 'left'}
            ,{field: 'title', title: '角色名称', width:200}
            ,{field: 'code', title: '角色码', width:200, sort: true}
            ,{field: 'remark1', title: '角色描述'}
        ]],
        response: {
            statusName: 'code' //规定数据状态的字段名称，默认：code
            ,statusCode: 200 //规定成功的状态码，默认：0
            ,msgName: 'msg' //规定状态信息的字段名称，默认：msg
            ,countName: 'total' //规定数据总数的字段名称，默认：count
            ,dataName: 'rows' //规定数据列表的字段名称，默认：data
        },
        parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.msg, //解析提示文本
                "total": res.result.total, //解析数据长度
                "rows": res.result.records //解析数据列表
            };
        }
    });

    layer.open({
        type: 1,
        content: $('#id') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
    });
});