//获取项目根目录
function sy(){
    var curWwwPath = window.document.location.href;
    var pathName =  window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    var localhostPaht = curWwwPath.substring(0,pos);
    var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return projectName;
};
//默认加载layer.js 弹出层
document.write("<script language='javascript' src='"+sy()+"/res/layui/layui.js'></script>");
var layer;
$(function () {
    layui.use('layer', function(){
        layer = layui.layer;
    });
});

/**
 *
 * @param url	请求的url
 * @param handler	回调函数
 * @param p		传递的参数
 * @param dataType	请求的类型
 * @return
 */
function startRequest(url, handler, p, dataType) {
    var index = layer.load(0,{
        shade: [0.1,'#000'] //遮罩
    });
    var param = {
        type : "POST",
        url : url,
        data : p,
        async :true ,
        success : function(data) {
            window.status = "";
            layer.close(index);
            handler(data);
        },
        error : function(req, textStatus, errorThrown) {
            layer.close(index);
            var html=$(req.responseText);
            var tip=html.find("#title").text();
            if(req.status == '404'){
                html.find("li").each(function(){
                    tip+="\n"+$(this).text();
                });
            }else{
                tip+="\n"+html.find("#memo").text();
            }
            alert(tip);
        }
    };
    if(dataType) param.dataType = dataType;
    $.ajax(param);
}

function re() {
    location.reload();
}