layui.define(['layer', 'jquery'], function (exports) { //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
    var layer = layui.layer;
    var $ = layui.jquery;
    var obj = {
        post: function (url, data, successCallBack) {
            $.ajax({
                type: 'POST',
                url: url,
                data: data,
                success: function (data) {
                    callback(data);
                },
                error: function (data) {
                    toErrorPage(data);
                }
            });
        },
        getWithData: function (url, data, successCallBack) {
            $.ajax({
                type: 'GET',
                url: url,
                data: data,
                success: function (data) {
                    if (data.code == 200){
                        successCallBack(data);
                    }else {
                        layer.msg(JSON.stringify(data));
                    }
                },
                error: function (data) {
                    toErrorPage(data);
                }
            });
        },
        get: function (url, successCallBack) {
            $.ajax({
                type: 'GET',
                url: url,
                success: function (data) {
                    if (data.code == 200){
                        successCallBack(data);
                    }else {
                        layer.msg(JSON.stringify(data));
                    }
                },
                error: function (data) {
                    toErrorPage(data);
                }
            });
        },
        login: function (data) {
            $.ajax({
                type: 'POST',
                url: '/login',
                data: data,
                success: function (data) {
                    if (data.code == 200){
                        window.location.href='/html/site.html'
                    }else {
                        layer.msg(JSON.stringify(data));
                    }
                },
                error: function (data) {
                    toErrorPage(data);
                }
            });
        }
    };
    function toErrorPage(data){
        if (data.status == 302){
            window.location.href='/html/login.html'
        }else {
            layer.msg(JSON.stringify(data));
        }
    }

    //输出test接口
    exports('request', obj);
});


