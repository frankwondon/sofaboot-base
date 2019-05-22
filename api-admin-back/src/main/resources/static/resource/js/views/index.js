layui.config({
    base: '/resource/js/modules/' //你存放新模块的目录，注意，不是layui的模块目录
}).use(['form', 'request','laytpl'], function () {
    var form = layui.form;
    var request = layui.request;
    var laytpl=layui.laytpl;

    var site=$("#site").val();
    request.getWithData('/backadmin/menu/loadMenu',
        {site:site},
        function (data) {
            var getTpl = template.innerHTML
                ,view = document.getElementById('nav');
            laytpl(getTpl).render(data.result, function(html){
                view.innerHTML = html;
            });
        }
    );
});