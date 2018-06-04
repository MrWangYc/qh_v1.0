<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="at" uri="/WEB-INF/util_tag.tld"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>数据字典列表</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${contextPath}/res/layui/css/layui.css"  media="all">
  <script src="${contextPath}/res/layui/layui.js" charset="utf-8"></script>
  <script src="${contextPath}/res/js/jquery.js" charset="utf-8"></script>
  <script src="${contextPath}/res/js/common.js" charset="utf-8"></script>
</head>
<body>
<div>
  <div class="layui-btn-group demoTable" style="margin-top: 15px;">
    <at:popedom cid="18">
    <button class="layui-btn" onclick="add()">增加</button>
    </at:popedom>
    <at:popedom cid="19">
    <button class="layui-btn " data-type="getCheckData">编辑</button>
    </at:popedom>
    <at:popedom cid="24">
    <button class="layui-btn" data-type="delCheckData">删除</button>
    </at:popedom>
  </div>
</div>
<div class="layui-form-item demoTable" style="margin-top: 15px;">
  <div class="layui-input-inline">
    <input type="text" id="name" lay-verify="title" autocomplete="on" placeholder="编号/数据字典名称" class="layui-input">
  </div>
  <div class="layui-input-inline">
    <input type="text" id="no" lay-verify="title" autocomplete="on" placeholder="编号" class="layui-input">
  </div>
  <button class="layui-btn" data-type="reload">搜索</button>
</div>
<table class="layui-hide" id="LAY_table_user" lay-filter="user"></table>

<script>
  layui.use('table', function() {
      var table = layui.table;
      //方法级渲染
       table.render({
          elem: '#LAY_table_user'
          , url: 'sys-code!list.action?flag=json&id=${id}'
          , cols: [[
              {checkbox: true, fixed: true}
//              ,{field:'id', width:80, sort: true, fixed: true}
              , {field: 'parent', title: '上级名称', width: 100, sort: true}
              , {field: 'name', title: '名称', width: 180, sort: true}
              , {field: 'param1', title: 'param1', width: 180, sort: true}
              , {field: 'param2', title: 'param2', width: 180, sort: true}
              , {field: 'orderNo', title: '顺序', sort: true, width: 80}
              , {field: 'status', title: '状态', width: 80}
          ]]
          , id: 'testReload'
          , page: true
          , height: 695
      });




      var $ = layui.$, active = {
          reload: function () {
              table.reload('testReload', {
                  where: {
                      "s.name":$('#name').val(),
                      "s.no":$("#no").val()
                  }
              });
          },
          getCheckData: function(){ //获取选中数据
              var checkStatus = table.checkStatus('testReload')
                  ,data = checkStatus.data;
              console.info(data.length);
              if(data.length>1) {
                  layer.alert("只能选择一条数据!");
                  return;
              }
              layer.open({
              type: 2,
              title: '修改数据字典',
              area: ['730px', '540px'],
              shade: 0.3,
              closeBtn: 1,
              shadeClose: false,
              content: 'sys-code!edit.action?flag=edit&object.id='+data[0].id
              });
          },
          delCheckData:function () {
              var checkStatus = table.checkStatus('testReload')
                  ,data = checkStatus.data;
              if(data.length>1) {
                  layer.alert("只能选择一条数据!");
                  return;
              }
              layer.confirm('真的删除行么', function(index){
                  layer.close(index);
                  //向服务端发送删除指令
                  startRequest("sys-code!del.action", function(text){
                      layer.alert(text);
                      window.location.reload();
                      }, {"object.id":data[0].id});
              });
          }
      };

      $('.demoTable .layui-btn').on('click', function(){
          var type = $(this).data('type');
          active[type] ? active[type].call(this) : '';
      });

  });

  //增加
  function add() {
      layer.open({
          type: 2,
          title: '新增功能',
          area: ['730px', '540px'],
          shade: 0.3,
          closeBtn: 1,
          shadeClose: false,
          content: 'sys-code!edit.action?flag=add&object.parent.id=${id}'
      });
  }
</script>

</body>
</html>
