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
                    successCallBack(data);
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
                    if (data.code == 200) {
                        successCallBack(data);
                    } else {
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
                    if (data.code == 200) {
                        successCallBack(data);
                    } else {
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
                    if (data.code == 200) {
                        window.location.href = '/html/site.html'
                    } else {
                        layer.msg(JSON.stringify(data));
                    }
                },
                error: function (data) {
                    toErrorPage(data);
                }
            });
        },
        getParam: function () {
            var result={};
            var params = location.search.slice(1);
            if (params.length > 0) {
                if (params.indexOf("&") != -1){
                    var  ps=params.split("&");
                    ps.forEach(function (value) {
                        var kv=value.split("=");
                        var k=kv[0];
                        var v=kv[1];
                        result[k]=v;
                    })
                }else {
                    var kv=params.split("=");
                    var k=kv[0];
                    var v=kv[1];
                    result[k]=v;
                }
            }
            return result;
        },
        getRoles:function () {

        }
    };

    function toErrorPage(data) {
        if (data.status == 302) {
            window.location.href = '/html/login.html'
        } else {
            layer.msg(JSON.stringify(data));
        }
    }

    //输出test接口
    exports('request', obj);
});


