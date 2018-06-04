<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="at" uri="/WEB-INF/util_tag.tld"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>${flag=='save'?' 新增':'修改'}活动</title>
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
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
      <legend>会员信息
      </legend>
    </fieldset>
    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">会员名称</label>
        <div class="layui-input-block">
          <input type="text" id="mName" readonly="readonly"  lay-verify="required" class="layui-input"  value="${object.memCate.membership.name}">
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">电话号码</label>
        <div class="layui-input-block">
          <input type="text" id="mPhone" readonly="readonly" class="layui-input"  value="${object.memCate.membership.phone}">
        </div>
      </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
      <legend>活动信息</legend>
    </fieldset>
    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">活动信息</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" value="${object.memCate.category.name}" readonly="readonly" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
      <div id="date_div" class="layui-inline  ${object.memCate.type!='1'?'':'layui-hide'}">
        <label class="layui-form-label">开始时间</label>
        <div class="layui-input-block">
          <input type="text" class="layui-input" readonly="readonly"  style="width: 212px;" value="${object.memCate.startDate}" placeholder="yyyy-MM-dd">
        </div>
      </div>
      <div class="layui-inline ${object.memCate.type!='1'?'':'layui-hide'}">
        <label class="layui-form-label">截止时间</label>
        <div class="layui-input-block">
          <input type="text" class="layui-input" readonly="readonly"  style="width: 212px;" value="${object.memCate.endDate}" placeholder="yyyy-MM-dd">
        </div>
      </div>
      <div id="num_div"  class="layui-inline  ${object.memCate.type=='1'?'':'layui-hide'}">
        <label class="layui-form-label">总次数</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" id="cNum" readonly="readonly"  value="${object.memCate.totalDegree}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
      <div class="layui-inline ${object.memCate.type=='1'?'':'layui-hide'}">
        <label class="layui-form-label">剩余次数</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" readonly="readonly"  value="${object.memCate.remainder}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
      <legend>消费登记</legend>
    </fieldset>
    <div class="layui-form-item">
      <label class="layui-form-label">日期</label>
      <div class="layui-input-block">
        <input type="text" class="layui-input" style="width: 212px;" id="test19" name="object.cDate" value="${object.cDate}"readonly="readonly" placeholder="yyyy-MM-dd">
      </div>
    </div>
    <div class="layui-form-item layui-form-text">
      <label class="layui-form-label">情况说明</label>
      <div class="layui-input-block">
        <textarea placeholder="请输入内容" class="layui-textarea" name="object.remark" readonly="readonly">${object.remark}</textarea>
      </div>
    </div>
    <div style="Employee: absolute;bottom: 0px;width:100%;text-align:center;bottom: 2px;background-color: #eee">
        <%--<button class="layui-btn" lay-submit="" lay-filter="formDemo">立即提交</button>--%>
        <%--<button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
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
            startRequest("consumption!edit.action", opHandler, data.field);
            return false;
        });

        form.on('select(type)', function(data){
            var str = data.value;
            if(str == 1){
                $("#num_div").removeClass("layui-hide");
                $("#num").attr("lay-verify","required");
            }else {
                $("#num_div").addClass("layui-hide");
                $("#num").attr("lay-verify","");
            }

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
