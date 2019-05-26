layui.config({
    base: '/resource/js/modules/' //你存放新模块的目录，注意，不是layui的模块目录
}).use(['form', 'request','table'], function () {
    var form = layui.form;
    $ = layui.jquery;
    var request = layui.request;
    var layer = layui.layer;
    var table=layui.table;
    table.render({
        elem: '#list'
        ,id:'list'
        ,height: 500
        ,url: '/backadmin/user/list' //数据接口
        ,page: true //开启分页
        ,totalRow: true //开启合计行
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'left'},
             {field: 'id', title: 'ID', width:100, sort: true, fixed: 'left'}
            ,{field: 'username', title: '用户名', width:200}
            ,{field: 'cellPhoneNum', title: '手机号', width:200, sort: true}
            ,{field: 'role', title: '角色'}
            ,{field: 'userType', title: '角色描述'}
            ,{field: 'remark1', title: '描述'}
            ,{title: '操作',toolbar: '#allotMenu'}
        ]],
        response: {
            statusName: 'code' //规定数据状态的字段名称，默认：code
            ,statusCode: 200 //规定成功的状态码，默认：0
            ,countName: 'total' //规定数据总数的字段名称，默认：count
        },
        parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.msg, //解析提示文本
                "total": res.result.total, //解析数据长度
                "data": res.result.records //解析数据列表
            };
        }
    });

    //监听工具条
    table.on('tool(test)', function(obj){
        console.log(obj)
        // if(obj.event === 'detail'){
        //     layer.msg('ID：'+ data.id + ' 的查看操作');
        // } else if(obj.event === 'del'){
        //     layer.confirm('真的删除行么', function(index){
        //         obj.del();
        //         layer.close(index);
        //     });
        // } else if(obj.event === 'edit'){
        //     layer.alert('编辑行：<br>'+ JSON.stringify(data))
        // }
    });

    form.on('switch(locked)', function(data){
        var locked;
        if (data.elem.checked){
            locked=0;
            request.post('/backadmin/user/locked',{userId:data.value,locked:locked},function () {
                layer.msg("操作成功")
            });
        }else {
            layer.confirm('确定要锁定该用户吗？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                locked=1;
                request.post('/backadmin/user/locked',{userId:data.value,locked:locked},function () {
                    layer.msg("操作成功")
                });
            }, function(){
                return;
            });
        }
    });



    //监听提交
    form.on('submit(add)',function(data) {
        request.post('/backadmin/role/add',data.field,function (data) {
            if (data.code==200){
                layer.alert("增加成功", {
                        icon: 6
                    },
                    function() {
                        //关闭当前frame
                        xadmin.close();
                        // 可以对父窗口进行刷新
                        xadmin.father_reload();
                    });
            }
        });
        return false;
    });
});