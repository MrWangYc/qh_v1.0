<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="at" uri="/WEB-INF/util_tag.tld"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>${flag=='save'?' 新增':'修改'}职务</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${contextPath}/res/layui/css/layui.css"  media="all">
  <script src="${contextPath}/res/layui/layui.js" charset="utf-8"></script>
  <script src="${contextPath}/res/js/jquery.js" charset="utf-8"></script>
  <script src="${contextPath}/res/js/common.js" charset="utf-8"></script>
</head>
<body style="margin-top: 14px;">
  <form class="layui-form" action="">
    <input type="hidden" name="flag" value="save"/>
    <input type="hidden" name="id" value="${id}"/>
    <div class="layui-tab">
      <div class="layui-tab-content">
        <s:iterator value="#request.fList" status="v">
              <div class="layui-form-item" pane="">
                <s:iterator status="t" value="#request.sysFunction">
                    <input type="checkbox" name="fun[${id}].id" id="fun_${id}" lay-skin="primary" title="${functionName}" value='${id}' style="border-right: solid 1px #ccc">[菜单]&nbsp;&nbsp;&nbsp;&nbsp;
                  <s:iterator status="s" value="#request.sysFunction">
                      <input type="checkbox" name="fun[${id}].id" id="fun_${id}" value="${id}" lay-skin="primary" title="${functionName}">
                  </s:iterator>
                  <hr/>
                </s:iterator>
              </div>

        </s:iterator>
      </div>
    </div>
    </div>
    <div style="position: absolute;bottom: 0px;width:100%;text-align:center;bottom: 2px;background-color: #eee">
        <button class="layui-btn" lay-submit="" lay-filter="formDemo">保存</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </form>


<script>
    <s:iterator value="#request.afList">
    if($("#fun_${fun.id}").is('input')){
        $("#fun_${fun.id}").attr("checked", true);
    }
    </s:iterator>

    layui.use('element', function(){
        var element = layui.element;

    });
    layui.use('form', function(){
        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
        //但是，如果你的HTML是动态生成的，自动渲染就会失效
        //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
        form.render();
        //监听提交
        form.on('submit(formDemo)', function(data){
            startRequest("position!auth.action", opHandler, data.field);
            return false;
        });
    });

    function opHandler(data, textStatus) {
        layer.alert("操作成功，可以继续添加");
        //获取当前页面的index，用于关闭当前窗口
        var index=parent.layer.getFrameIndex(window.name);
        //刷新当前界面的父级窗口
        window.parent.location.reload();
        layer.close(index);
    }

</script>

</body>
</html>
