<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="at" uri="/WEB-INF/util_tag.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>${flag=='save'?' 新增':'修改'}会员卡信息</title>
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
    <input type="hidden" name="object.pinyin" value="${object.pinyin}">
    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" name="object.name" lay-verify="required" value="${object.name}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
          <select  name="object.sex" >
            <option value="" ></option>
            <option value="1" ${object.sex==1 || objece.sex == null?"selected":""}>男</option>
            <option value="0" ${object.sex==0?"selected":""}>女</option>
          </select>
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">电话号码</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" name="object.phone" lay-verify="required|phone"value="${object.phone}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
    </div>
    <div class="layui-form-item">

      <div class="layui-inline">
        <label class="layui-form-label">生日</label>
        <div class="layui-input-block">
          <input type="text" class="layui-input" style="width: 212px;" id="test19" name="object.birthday" placeholder="yyyy-MM-dd">
          <%--<input type="text" style="width: 212px;" name="object.certificateNo" lay-verify="title" value="${object.certificateNo}" autocomplete="off"  class="layui-input" maxlength="20">--%>
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">身高</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" name="object.height" value="${object.height}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">体重</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" name="object.weight" value="${object.weight}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
    </div>

    <div class="layui-form-item">

      <div class="layui-inline">
        <label class="layui-form-label">血压</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" name="object.bloodP" value="${object.bloodP}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">血脂</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" name="object.bloodF" value="${object.bloodF}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">血糖</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" name="object.bloodS" value="${object.bloodS}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
    </div>

    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">健康状况</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" name="object.healthStatus" value="${object.healthStatus}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">遗传病史</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" name="object.healthH" value="${object.healthH}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
      <div class="layui-inline">
        <label class="layui-form-label">过敏史</label>
        <div class="layui-input-block">
          <input type="text" style="width: 212px;" name="object.allergicH" value="${object.allergicH}" autocomplete="off"  class="layui-input" maxlength="20">
        </div>
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-inline">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
          <select  name="object.status" style="width: 212px;">
            <option value=""></option>
            <option value="1" ${object.status==1||object.status==null?"selected":""}>启用</option>
            <option value="0" ${object.status==0?"selected":""}>禁用</option>
          </select>
        </div>
      </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
      <legend>家庭成员(成人)</legend>
    </fieldset>
    <table class="layui-table">
      <colgroup>
        <col width="130">
        <col width="120">
        <col width="200">
        <col width="110">
        <col width="100">
        <col width="60">
        <col width="130">
        <col width="120">
        <col width="200">
        <col width="110">
        <col width="100">
        <col width="60">
        <col width="60">
        <col>
      </colgroup>
      <thead>
      <tr>
        <th>姓名</th>
        <th>性别</th>
        <th>电话号码</th>
        <th>出生日期</th>
        <th>与主卡关系</th>
        <th>身高</th>
        <th>体重</th>
        <th>血压</th>
        <th>血脂</th>
        <th>血糖</th>
        <th>健康状况</th>
        <th>遗传病史</th>
        <th>过敏史</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody id="tbody1">
        <s:iterator value="#request.cList" var="t">
          <tr>
            <td>
              <input type="text" name="cList[${id}].name" lay-verify="required" value="${name}" autocomplete="off" style="width: 120px;" class="layui-input">
              <input type="hidden" name="cList[${id}].id" value="${id}" autocomplete="off" style="width: 120px;" class="layui-input">
              <input type="hidden" name="cList[${id}].cType" lay-verify="title" value="${cType}" autocomplete="off" style="width: 50px;" class="layui-input">
            </td>
            <td>
              <select name="cList[${id}].sex" lay-filter="goodsSelect">
                <option value="" >--请选择--</option>
                <option value="1" ${sex==1 ?"selected":""}>男</option>
                <option value="0" ${sex==0 ?"selected":""}>女</option>
              </select>
            </td>
            <td>
              <input type="text" name="cList[${id}].phone" lay-verify="title" value="${phone}" autocomplete="off" style="width: 180px;" class="layui-input">
            </td>
            <td>
              <input type="text" class="layui-input" style="width: 100px;" id="d${id}" name="cList[${id}].birthday" placeholder="yyyy-MM-dd">
            </td>
            <td>
              <select id="goods_i" name="cList[${id}].type" lay-filter="goodsSelect">
                <option value="">--请选择--</option>
                <option value="1" ${type==1 ?"selected":""}>配偶</option>
                <option value="2" ${type==2 ?"selected":""}>父母</option>
                <option value="3" ${type==3 ?"selected":""}>子女</option>
                <option value="4" ${type==4 ?"selected":""}>其他</option>
              </select>
            </td>
            <td>
            <input type="text" name="cList[${id}].height" lay-verify="title" value="${height}" autocomplete="off" style="width: 50px;" class="layui-input">
            </td>
            <td>
            <input type="text" name="cList[${id}].weight" lay-verify="title" value="${weight}"autocomplete="off" style="width: 50px;" class="layui-input">
            </td>
            <td>
            <input type="text" name="cList[${id}].bloodP" lay-verify="title" value="${bloodP}" autocomplete="off" style="width: 50px;" class="layui-input">
            </td>
            <td>
            <input type="text" name="cList[${id}].bloodF" lay-verify="title" value="${bloodH}" autocomplete="off" style="width: 50px;" class="layui-input">
            </td>
            <td>
            <input type="text" name="cList[${id}].bloodS" lay-verify="title" value="${bloodS}"  autocomplete="off" style="width: 50px;" class="layui-input">
            </td>
            <td>
            <input type="text" name="cList[${id}].healthStatus" lay-verify="title" value="${healthStatus}" autocomplete="off" style="width: 100px;" class="layui-input">
            </td>
            <td>
            <input type="text" name="cList[${id}].healthH" lay-verify="title" value="${healthH}" autocomplete="off" style="width: 100px;" class="layui-input">
            </td>
            <td>
            <input type="text" name="cList[${id}].allergicH" lay-verify="title" value="${allergicH}" autocomplete="off" style="width: 100px;" class="layui-input">
            </td>
            <td>
              <button class="layui-btn layui-btn-sm layui-btn-normal" onclick="delRow(this,${id})"><i class="layui-icon"></i> 删除
              </button>
            </td>
          </tr>
        </s:iterator>
      </tbody>
    </table>
      <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend>家庭成员(儿童)</legend>
      </fieldset>
      <table class="layui-table">
        <colgroup>
          <col width="130">
          <col width="150">
          <col width="200">
          <col width="110">
          <col width="100">
          <col width="60">
          <col width="130">
          <col width="120">
          <col width="200">
          <col width="110">
          <col width="100">
          <col>
        </colgroup>
        <thead>
        <tr>
          <th>姓名</th>
          <th>性别</th>
          <th>电话号码</th>
          <th>出生日期</th>
          <th>出生体重</th>
          <th>身长</th>
          <th>是否足月</th>
          <th>健康状况</th>
          <th>遗传病史</th>
          <th>过敏史</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody2">
        <s:iterator value="#request.eList" var="t">
          <tr>
            <td>
              <input type="text" name="cList[${id}].name" lay-verify="required" value="${name}" autocomplete="off" style="width: 120px;" class="layui-input">
              <input type="hidden" name="cList[${id}].id" value="${id}" autocomplete="off" style="width: 120px;" class="layui-input">
              <input type="hidden" name="cList[${id}].cType" lay-verify="title" value="${cType}" autocomplete="off" style="width: 50px;" class="layui-input">
            </td>
            <td>
              <select name="cList[${id}].sex" lay-filter="goodsSelect">
                <option value="" >--请选择--</option>
                <option value="1" ${sex==1 ?"selected":""}>男</option>
                <option value="0" ${sex==0 ?"selected":""}>女</option>
              </select>
            </td>
            <td>
              <input type="text" name="cList[${id}].phone" lay-verify="title" value="${phone}" autocomplete="off" style="width: 180px;" class="layui-input">
            </td>
            <td>
              <input type="text" class="layui-input" style="width: 100px;" id="d${id}" name="cList[${id}].birthday" placeholder="yyyy-MM-dd">
            </td>
            <td>
            <input type="text" name="cList[${id}].birthWeight" lay-verify="title" value="${birthWeight}" autocomplete="off" style="width: 50px;" class="layui-input">
            </td>
            <td>
            <input type="text" name="cList[${id}].birthHeight" lay-verify="title"  value="${birthHeight}" autocomplete="off" style="width: 50px;" class="layui-input">
            </td>
            <td>
            <select id = "goods_${id}" name="cList[${id}].ifMonth" lay-filter="goodsSelect">
              <option value="">--请选择--</option>
              <option value="0" ${ifMonth==0?'selected':''}>否</option>
              <option value="1" ${ifMonth==1?'selected':''}>是</option>
              </select>
            </td>
            <td>
            <input type="text" name="cList[${id}].healthStatus" lay-verify="title" value="${healthStatus}" autocomplete="off" style="width: 50px;" class="layui-input">
            </td>
            <td>
            <input type="text" name="cList[${id}].healthH" lay-verify="title" value="${healthH}" autocomplete="off" style="width: 50px;" class="layui-input">
            </td>
            <td>
            <input type="text" name="cList[${id}].allergicH" lay-verify="title" value="${allergicH}" autocomplete="off" style="width: 100px;" class="layui-input">
            </td>
            <td>
              <button class="layui-btn layui-btn-sm layui-btn-normal" onclick="delRow(this,${id})"><i class="layui-icon"></i> 删除
              </button>
            </td>
          </tr>
        </s:iterator>
        </tbody>
      </table>
    <div style="membership: absolute;bottom: 0px;width:100%;text-align:center;bottom: 2px;background-color: #eee">
      <a href="#" class="layui-btn" onclick="add(1)">增加成人</a>
      <a href="#" class="layui-btn" onclick="add(2)">增加儿童</a>
        <button class="layui-btn" lay-submit="" lay-filter="formDemo">立即提交</button>
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
            startRequest("membership!edit.action", opHandler, data.field);
            return false;
        });
    });
    var i = 0;
    layui.use('laydate', function() {
        var laydate = layui.laydate;
        //初始赋值
        laydate.render({
            elem: '#test19'
            ,value: '2018-05-25'
            ,isInitValue: true
        });

        <s:iterator value="#request.cList" var="t">
          laydate.render({
              elem: '#d${id}'
              ,value: '<fmt:formatDate value="${birthday}" pattern="yyyy-MM-dd" />'
              ,isInitValue: true
          });
          if(i<${id}){
              i=${id};
          }
        </s:iterator>
        <s:iterator value="#request.eList" var="t">
        laydate.render({
            elem: '#d${id}'
            ,value: '<fmt:formatDate value="${birthday}" pattern="yyyy-MM-dd" />'
            ,isInitValue: true
        });
        if(i<${id}){
            i=${id};
        }
        </s:iterator>

    });
        function opHandler(data, textStatus) {
        layer.msg("操作成功，可以继续添加！");
        //刷新当前界面的父级窗口
        window.parent.location.reload();
        //获取当前页面的index，用于关闭当前窗口
        var index=parent.layer.getFrameIndex(window.name);
        layer.close(index);
    }

    function add(type) {
            if(type==1) {
                var thtml = '<tr>' +
                    ' <td>' +
                    '<input type="text" name="cList[' + i + '].name" lay-verify="required" autocomplete="off" style="width: 120px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<select name="cList[' + i + '].sex" lay-filter="goodsSelect">' +
                    '<option value="">--请选择--</option>' +
                    '<option value="1" >男</option>' +
                    '<option value="0" >女</option>' +
                    '</select>' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].phone" lay-verify="title" autocomplete="off" style="width: 110px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" class="layui-input" style="width: 100px;" id="date' + i + '" name="cList[' + i + '].birthday" placeholder="yyyy-MM-dd">' +
                    '</td>' +
                    '<td>' +
                    '<select id = "goods_' + i + '" name="cList[' + i + '].type" lay-filter="goodsSelect">' +
                    '<option value="">--请选择--</option>' +
                    '<option value="1">配偶</option>' +
                    '<option value="2">父母</option>' +
                    '<option value="3">子女</option>' +
                    '<option value="4">其他</option>' +
                    '</select>' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].height" lay-verify="title" autocomplete="off" style="width: 50px;" class="layui-input">' +
                    '<input type="hidden" name="cList[' + i + '].cType" lay-verify="title" value="1" autocomplete="off" style="width: 50px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].weight" lay-verify="title" autocomplete="off" style="width: 50px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].bloodP" lay-verify="title" autocomplete="off" style="width: 50px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].bloodF" lay-verify="title" autocomplete="off" style="width: 50px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].bloodS" lay-verify="title" autocomplete="off" style="width: 50px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].healthStatus" lay-verify="title" autocomplete="off" style="width: 100px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].healthH" lay-verify="title" autocomplete="off" style="width: 100px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].allergicH" lay-verify="title" autocomplete="off" style="width: 100px;" class="layui-input">' +
                    '</td>' +
                    ' <td>' +
                    '<button class="layui-btn layui-btn-sm layui-btn-normal" onclick = "delRow(this,null)"><i class="layui-icon"></i> 删除</button>' +
                    ' </td>' +
                    '</tr>';
                $("#tbody1").append(thtml);
                var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
                form.render();
                var laydate = layui.laydate;
                laydate.render({
                    elem: '#date' + i
                    , value: '2018-05-25'
                    , isInitValue: true
                });
            }
            else if(type==2){
                var thtml = '<tr>' +
                    ' <td>' +
                    '<input type="text" name="cList[' + i + '].name" lay-verify="required" autocomplete="off" style="width: 120px;" class="layui-input">' +
                    '<input type="hidden" name="cList[' + i + '].cType" lay-verify="title" value="2" autocomplete="off" style="width: 50px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<select name="cList[' + i + '].sex" lay-filter="goodsSelect">' +
                    '<option value="">--请选择--</option>' +
                    '<option value="1" >男</option>' +
                    '<option value="0" >女</option>' +
                    '</select>' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].phone" lay-verify="title" autocomplete="off" style="width: 110px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" class="layui-input" style="width: 100px;" id="date' + i + '" name="cList[' + i + '].birthday" placeholder="yyyy-MM-dd">' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].birthWeight" lay-verify="title" autocomplete="off" style="width: 50px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].birthHeight" lay-verify="title" autocomplete="off" style="width: 50px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<select id = "goods_' + i + '" name="cList[' + i + '].ifMonth" lay-filter="goodsSelect">' +
                    '<option value="">--请选择--</option>' +
                    '<option value="0">否</option>' +
                    '<option value="1">是</option>' +
                    '</select>' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].healthStatus" lay-verify="title" autocomplete="off" style="width: 50px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].healthH" lay-verify="title" autocomplete="off" style="width: 50px;" class="layui-input">' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" name="cList[' + i + '].allergicH" lay-verify="title" autocomplete="off" style="width: 100px;" class="layui-input">' +
                    '</td>' +
                    ' <td>' +
                    '<button class="layui-btn layui-btn-sm layui-btn-normal" onclick = "delRow(this,null)"><i class="layui-icon"></i> 删除</button>' +
                    ' </td>' +
                    '</tr>';
                $("#tbody2").append(thtml);
                var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
                form.render();
                var laydate = layui.laydate;
                laydate.render({
                    elem: '#date' + i
                    , value: '2018-05-25'
                    , isInitValue: true
                });
            }

        i = i+1;
    }

    function delRow(obj,id)
    {
        if(window.confirm("是否要确认删除？ ")){
            var Row=obj.parentNode; //tr
            while(Row.tagName.toLowerCase()!="tr")
            {
                Row=Row.parentNode;
            }
            Row.parentNode.removeChild(Row); //删除行
            if(id!="null"&&id!=null){
                $.ajax({
                    type: "GET",
                    url: "${contextPath}/pc/report/price-forms!delChild.action",
                    data: {ids:id},
                    dataType: "json",
                    success: function(data){
                        alert("删除成功!");
                    }
                });
            }
        }
    }
</script>

</body>
</html>
