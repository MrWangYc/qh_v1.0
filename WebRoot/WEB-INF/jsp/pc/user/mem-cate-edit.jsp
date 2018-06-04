<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="at" uri="/WEB-INF/util_tag.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <input type="hidden" name="flag" value="${flag}">
    <input type="hidden" name="object.id" value="${object.id}">
    <input type="hidden" name="object.createDate" value="${object.createDate}">
    <input type="hidden" name="object.createEmp.id" value="${object.createEmp.id}">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
      <legend>会员信息
        <a class="layui-btn layui-btn-normal ${flag=='save'?'':'layui-hide'}" onclick="checkPerson()">选择会员信息</a></legend>
    </fieldset>
    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">会员名称</label>
        <div class="layui-input-block">
          <input type="text" id="mName" readonly="readonly"  lay-verify="required" class="layui-input"  value="${object.membership.name}">
          <input type="hidden" id="mId" class="layui-input" name="object.membership.id"  value="${object.membership.id}"/>
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">电话号码</label>
        <div class="layui-input-block">
          <input type="text" id="mPhone" readonly="readonly" class="layui-input"  value="${object.membership.phone}">
        </div>
      </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
      <legend>活动信息</legend>
    </fieldset>
    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">活动信息</label>
        <div class="layui-input-block ${flag=='save'?'':'layui-hide'}">
          <select lay-filter="category" >
            <option value=""></option>
            <s:iterator value="#request.cList" var="t">
              <option value="${id}#${type}#${amount}#${num}" ${object.category.id==id?"selected":""}>${name}[${type=="1"?"次数卡":(type=="2"?"月卡":(type=="3"?"季卡":("年卡")))}]</option>
            </s:iterator>
          </select>
          <input type="hidden" id="cId" name="object.category.id" value="${object.category.id}">
          <input type="hidden" id="cType" name="object.type" value="${object.type}">
        </div>
        <div class="layui-input-block ${flag!='save'?'':'layui-hide'}">
            <input type="text" style="width: 212px;" value="${object.category.name}" readonly="readonly" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
      <div id="date_div" class="layui-inline  ${object.type!='1'&& flag!='save'?'':'layui-hide'}">
        <label class="layui-form-label">开始时间</label>
        <div class="layui-input-block">
          <input type="text" class="layui-input" style="width: 212px;" id="test19" name="object.startDate" placeholder="yyyy-MM-dd">
        </div>
      </div>
      <div class="layui-inline ${object.type!='1'&& flag!='save'?'':'layui-hide'}">
        <label class="layui-form-label">截止时间</label>
        <div class="layui-input-block">
          <input type="text" class="layui-input" style="width: 212px;" id="test20" name="object.endDate" placeholder="yyyy-MM-dd">
        </div>
      </div>
      <div id="num_div"  class="layui-inline  ${object.type=='1'&& flag!='save'?'':'layui-hide'}"">
        <label class="layui-form-label">总次数</label>
        <div class="layui-input-block">
          <input type="number" style="width: 212px;" id="cNum" name="object.totalDegree" value="${object.totalDegree}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
      <div class="layui-inline ${object.type=='1'&& flag!='save'?'':'layui-hide'}">
        <label class="layui-form-label">剩余次数</label>
        <div class="layui-input-block">
          <input type="number" style="width: 212px;" name="object.remainder" value="${object.remainder}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
    </div>
    <div class="layui-form-item">
      <div  class="layui-inline ">
        <label class="layui-form-label">活动价格</label>
        <div class="layui-input-block">
          <input type="number" style="width: 212px;" id="cAccount" readonly="readonly"  lay-verify="required" value="${object.category.amount}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
      <div  class="layui-inline">
        <label class="layui-form-label">实付金额</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;"  name="object.amount"  lay-verify="required"  value="${object.amount}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
          <select  name="object.status" id="status">
            <option value=""></option>
            <option value="1" ${object.status==1||object.status==null?"selected":""}>启用</option>
            <option value="0" ${object.status==0?"selected":""}>禁用</option>
          </select>
        </div>
      </div>
    </div>
    <div style="Employee: absolute;bottom: 0px;width:100%;text-align:center;bottom: 2px;background-color: #eee">
        <button class="layui-btn ${flag=='save'?'':'layui-hide'}" lay-submit="" lay-filter="formDemo">立即提交</button>
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
            startRequest("mem-cate!edit.action", opHandler, data.field);
            return false;
        });
        form.on('select(category)',function (data) {
            if(data.value==null||data.value=="") {
                $("#num_div").addClass("layui-hide");
                $("#date_div").addClass("layui-hide");
                $("#cId").val("");
                $("#cType").val("");
                $("#cAccount").val("");
                return;
            }
           else if(data.value.split("#")[1]=="1"){
                $("#num_div").removeClass("layui-hide");
                $("#date_div").addClass("layui-hide");
            }else{
                $("#date_div").removeClass("layui-hide");
                $("#num_div").addClass("layui-hide");
            }
            $("#cId").val("");
            $("#cType").val("");
            $("#cAccount").val("");
            $("#cId").val(data.value.split("#")[0]);
            $("#cType").val(data.value.split("#")[1]);
            $("#cAccount").val(data.value.split("#")[2]);
            $("#cNum").val(parseInt(data.value.split("#")[3]));
        });

    });
    layui.use('laydate', function() {
        var laydate = layui.laydate;
        //初始赋值
        laydate.render({
            elem: '#test19'
            ,value: '<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />'
            ,isInitValue: true
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

    function checkPerson() {
        layer.open({
            type: 2,
            title: '选择会员信息',
            area: ['624px', '98%'],
            shade: 0.3,
            closeBtn: 1,
            shadeClose: false,
            content: 'membership!check.action'
        });
    }
</script>

</body>
</html>
