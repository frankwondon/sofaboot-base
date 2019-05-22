
layui.config({
    base: '/resource/js/modules/' //你存放新模块的目录，注意，不是layui的模块目录
}).use(['form', 'request'], function () {
    var form = layui.form;
    var request = layui.request;
    request.get('/backadmin/menu/loadSite',function (data) {
        app.sites=data.result;
    })
    var app = new Vue({
        el: '#site-list',
        data: {
            sites: []
        },
        methods: {
            toIndex: function (url) {
                window.location.href='/index?site='+url;
            }
        }
    });
});