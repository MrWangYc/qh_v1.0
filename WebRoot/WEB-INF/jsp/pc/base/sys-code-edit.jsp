<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="at" uri="/WEB-INF/util_tag.tld"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>${flag=='save'?' 新增':'修改'}数据字典</title>
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
    <input type="hidden" name="flag" value="${flag}">
    <input type="hidden" name="object.id" value="${object.id}">
    <input type="hidden" name="object.parent.id" value="${object.parent.id}">
    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">模块名称</label>
        <div class="layui-input-block">
          <input type="text" style="width: 548px;" name="object.name" lay-verify="title" value="${object.name}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>

    </div>
    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">顺序</label>
        <div class="layui-input-block">
          <input type="number" style="width: 212px;" name="object.orderNo" lay-verify="title" autocomplete="off"  value="${object.orderNo}" class="layui-input">
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
          <select id="type" name="object.status" >
            <option value=""></option>
            <option value="1" ${object.status==1?"selected":""}>启用</option>
            <option value="0" ${object.status==0?"selected":""}>禁用</option>
          </select>
        </div>
      </div>
    </div>
    <div class="layui-form-item" >
        <label class="layui-form-label">自定义值1</label>
        <div class="layui-input-block">
          <input type="url" name="object.param1" lay-verify="title" autocomplete="off" style="width: 550px;" value="${object.param1}" class="layui-input" maxlength="500">
        </div>
    </div>
    <div class="layui-form-item" >
      <label class="layui-form-label">自定义值2</label>
      <div class="layui-input-block">
        <input type="url" name="object.param2" lay-verify="title" autocomplete="off" style="width: 550px;" value="${object.param2}" class="layui-input" maxlength="500">
      </div>
    </div>
    <div class="layui-form-item" >
      <label class="layui-form-label">备注</label>
      <div class="layui-input-block">
        <input type="url" name="object.remarks" lay-verify="title" autocomplete="off" style="width: 550px;" value="${object.remarks}" class="layui-input" maxlength="500">
      </div>
    </div>
    <div style="position: absolute;bottom: 0px;width:100%;text-align:center;bottom: 2px;background-color: #eee">
        <button class="layui-btn" lay-submit="" lay-filter="formDemo">立即提交</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </form>


<script>
    layui.use('form', function(){
        var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
        //但是，如果你的HTML是动态生成的，自动渲染就会失效
        //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
        form.render();
        //监听提交
        form.on('submit(formDemo)', function(data){
            startRequest("sys-code!edit.action", opHandler, data.field);
            return false;
        });
    });

    function opHandler(data, textStatus) {
        layer.msg("操作成功，可以继续添加！");
        //刷新当前界面的父级窗口
        window.parent.location.reload();
        //获取当前页面的index，用于关闭当前窗口
        var index=parent.layer.getFrameIndex(window.name);
        layer.close(index);
    }

</script>

</body>
</html>
