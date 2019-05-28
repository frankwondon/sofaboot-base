layui.config({
    base: '/resource/js/modules/' //你存放新模块的目录，注意，不是layui的模块目录
}).use(['form', 'request','table'], function () {
    var form = layui.form;
    $ = layui.jquery;
    var request = layui.request;
    var layer = layui.layer;

    //监听提交
    form.on('submit(add)',function(data) {
        if (data.field.newPwd===data.field.newPwdR){
            request.post('/backadmin/user/updatePwd',data.field,function (data) {
                parent.location.href="/html/login.html"
            });
        }else {
            layer.msg("两次密码不一致")
        }
        return false;
    });
});


