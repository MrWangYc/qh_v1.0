<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="at" uri="/WEB-INF/util_tag.tld"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>功能模块</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${contextPath}/res/layui/css/layui.css"  media="all">
</head>
<body>  
<div style="margin-bottom: 5px;">          

</div>

<div style="display: flex;">
  <div style="display:inline-block;width: 200px;border-right:solid 1px #c2c2c2;height: 830px;margin-top: 10px;">
    <ul id="tree"></ul>
  </div>
  <div style="flex: 1;height: 837px;margin-left: 2px;" id="demo">
  </div>
</div>

<script src="${contextPath}/res/layui/layui.js" charset="utf-8"></script>
<script src="${contextPath}/res/js/jquery.js" charset="utf-8"></script>
<script>
    loadPage(1);
    layui.use(['tree', 'layer'], function(){
        var layer = layui.layer
            ,$ = layui.jquery;
        layui.tree({
            elem: '#tree' //指定元素
            ,target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
            ,click: function(item){ //点击节点回调
                loadPage(item.id);
            }
            ,nodes: [ //节点
                {name:'所有模块'
                    ,id:1
                    ,children:[
                    <s:iterator status="v" value="#request.fList">
                      <s:if test="#request.type==0">
                        {name: '${functionName}'
                            ,id: ${id}
                            <s:if test="#request.sysFunction.size()>0">
                              ,children: [
                                <s:iterator value="#request.sysFunction" status="s">
                                  {
                                      name: '${functionName}'
                                      ,id: ${id}
                                  },
                              </s:iterator>
                          ]
                            </s:if>
                        },
                      </s:if>
                    </s:iterator>
                  ] }]
        });
    });

    function loadPage(id) {
        if(id){
            var f= ' <iframe width="100%" height="100%" name="fList" id="fList" scrolling="auto" src="function!list.action?flag=list&id='+id+'" frameborder="0"></iframe>';
            var fIframe = $("#fList");
            if(fIframe){
                fIframe.remove();
            }
            $("#demo").append(f);
        }
    }
</script>
</body>
</html>
