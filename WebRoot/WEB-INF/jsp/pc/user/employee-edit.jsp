<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="at" uri="/WEB-INF/util_tag.tld"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>${flag=='save'?' 新增':'修改'}人员</title>
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
    <input type="hidden" name="object.createDate" value="${object.createDate}">
    <input type="hidden" name="object.createEmployee.id" value="${object.createEmployee.id}">
    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" name="object.name" lay-verify="title" required="required" value="${object.name}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
          <select  name="object.status" >
            <option value=""></option>
            <option value="1" ${object.status==1||object.status==null?"selected":""}>启用</option>
            <option value="0" ${object.status==0?"selected":""}>禁用</option>
          </select>
        </div>
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
          <select  name="object.sex" >
            <option value=""></option>
            <option value="1" ${object.sex==1||object.sex==null?"selected":""}>男</option>
            <option value="0" ${object.sex==0?"selected":""}>女</option>
          </select>
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">电话</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" name="object.phone" lay-verify="title" value="${object.phone}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">身份证号</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" name="object.certificateNo" lay-verify="title" value="${object.certificateNo}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">职务</label>
        <div class="layui-input-block">
          <select name="object.position.id" >
            <option value="">--请选择--</option>
            <s:iterator var="s" value="#request.roleList">
              <option value="${id}" ${object.position.id==id?"selected":""}>${name}</option>
            </s:iterator>
          </select>
        </div>
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">公司</label>
        <div class="layui-input-block">
          <select id="role" name="object.company.id" >
            <option value="">--请选择--</option>
            <s:iterator var="s" value="#request.companyList">
              <option value="${id}" ${object.company.id==id?"selected":""}>${name}</option>
            </s:iterator>
          </select>
        </div>
      </div>
    </div>
    <div style="Employee: absolute;bottom: 0px;width:100%;text-align:center;bottom: 2px;background-color: #eee">
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
            startRequest("employee!edit.action", opHandler, data.field);
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
