<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="at" uri="/WEB-INF/util_tag.tld"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>会员卡管理</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="${contextPath}/res/layui/css/layui.css"  media="all">
  <script src="${contextPath}/res/layui/layui.js" charset="utf-8"></script>
  <script src="${contextPath}/res/js/jquery.js" charset="utf-8"></script>
  <script src="${contextPath}/res/js/common.js" charset="utf-8"></script>
  <style>
    .layui-table-view{
      margin: 0;
    }
  </style>
</head>
<body style="padding:15px;">
<div>
  <div class="layui-btn-group demoTable">
    <at:popedom cid="6">
      <button class="layui-btn" onclick="add()">增加</button>
    </at:popedom>
    <at:popedom cid="7">
      <button class="layui-btn " data-type="getCheckData">编辑</button>
    </at:popedom>
    <at:popedom cid="8">
      <button class="layui-btn" data-type="delCheckData">删除</button>
    </at:popedom>
    <button class="layui-btn" onclick="re()">刷新</button>
  </div>
</div>
<div class="layui-form-item demoTable" style="margin-top: 15px;">
  <div class="layui-input-inline">
    <input type="text" id="name" lay-verify="title" autocomplete="on" placeholder="人员名称" class="layui-input">
  </div>
  <div class="layui-input-inline">
    <input type="text" id="phone" lay-verify="title" autocomplete="on" placeholder="电话号码" class="layui-input">
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
            , url: 'membership!list.action?flag=json'
            , cols: [[
                {checkbox: true, fixed: true}
                , {field: 'name', title: '姓名', width: 200}
                , {field: 'phone', title: '电话号码', width: 180}
                , {field: 'status', title: '状态', width: 180, sort: true}
                , {field: 'createEmp', title: '创建人', width: 180, sort: true}
                , {field: 'createTime', title: '创建时间', width: 180, sort: true}
            ]]
            , id: 'testReload'
            , page: true
            , height: 707
            , loading: true
            ,limit:10

        });
        //各类事件的绑定
        var $ = layui.$, active = {
            //查询数据
            reload: function () {
                var demoReload = $('#demoReload');
                table.reload('testReload', {
                    where: {
                        "s.name":encodeURI($('#name').val()),
                        "s.phone":encodeURI($('#phone').val())
                    }
                });
            },
            getCheckData: function(){ //获取选中数据
                var checkStatus = table.checkStatus('testReload')
                    ,data = checkStatus.data;
                if(data.length>1) {
                    layer.alert("只能选择一条数据!");
                    return;
                }
                layer.open({
                    type: 2,
                    title: '修改功能',
                    area: ['1524px', '640px'],
                        shade: 0.3,
                    closeBtn: 1,
                    shadeClose: false,
                    content: 'membership!edit.action?flag=edit&object.id='+data[0].id
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
                    startRequest("membership!del.action", function(text){
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
            title: '新增会员卡信息',
            area: ['1524px', '640px'],
            shade: 0.3,
            closeBtn: 1,
            shadeClose: false,
            content: 'membership!edit.action?flag=add'
        });
    }
</script>

</body>
</html>
