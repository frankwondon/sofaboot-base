layui.config({
    base: '/resource/js/modules/' //你存放新模块的目录，注意，不是layui的模块目录
}).use(['form', 'request','table'], function () {
    var form = layui.form;
    $ = layui.jquery;
    var request = layui.request;
    var layer = layui.layer;
    var urlparam = request.getParam();
    request.get('/backadmin/role/listAll',function (res) {
        var html='';
        res.result.forEach(function (v) {
            html+="<option value='"+v.id+"'>"+v.title+"</option>";
        })
        $('#role').append(html);
        form.render('select', 'example')
        if (urlparam.userId){
            request.getWithData('/backadmin/user/getUser',{uid:urlparam.userId},function (res) {
                //表单初始赋值
                form.val('example', {
                    "id": res.result.id
                    ,"username":  res.result.username
                    ,"cellPhoneNum": res.result.cellPhoneNum
                    ,"roleId": res.result.roleId
                    ,"userType": res.result.userType
                    ,"remark1": res.result.remark1
                })
            })
        }
    });


    //监听提交
    form.on('submit(add)',function(data) {
        request.post('/backadmin/user/addUser',data.field,function (data) {
            if (data.code===200){
                layer.alert("操作成功", {
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


