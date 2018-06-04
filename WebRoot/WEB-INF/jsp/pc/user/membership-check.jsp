<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="at" uri="/WEB-INF/util_tag.tld"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>会员信息</title>
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
<div class="layui-form-item demoTable" style="margin-top: 15px;">
  <div class="layui-input-inline">
    <input type="text" id="phone" lay-verify="title" autocomplete="on" placeholder="关键信息" class="layui-input">
  </div>
  <button class="layui-btn">搜索</button>
  <button class="layui-btn" data-type="next">确认</button>
</div>
<table class="layui-hide" id="LAY_table_user" lay-filter="user"></table>

<script>
    layui.use('table', function() {
        var table = layui.table;
        //方法级渲染
        table.render({
            elem: '#LAY_table_user'
            , url: 'membership!list.action?flag=json&s.type=-2'
            , cols: [[
                {checkbox: true, fixed: true}
                , {field: 'name', title: '姓名', width: 200}
                , {field: 'phone', title: '电话号码', width: 180}
                , {field: 'status', title: '状态', width: 80, sort: true}
            ]]
            , id: 'tableData'
            , page: true
            , height: 450
            , loading: true
            ,limit:10

        });
        //各类事件的绑定
        var $ = layui.$, active = {
            //查询数据
            //查询数据
            reload: function () {
                var demoReload = $('#demoReload');
                table.reload('tableData', {
                    where: {
                        "s.phone":encodeURI($('#phone').val()),
                        "s.type":-1
                    }
                });
            },
            next: function () {
                var checkStatus = table.checkStatus('tableData')
                    ,data = checkStatus.data;
                if(data.length>1) {
                    layer.alert("只能选择一条数据!");
                    return;
                }
                if(data.length==0){
                    layer.alert("请选择数据!");
                    return;
                }
                parent.$("#mName").val(data[0].name);
                parent.$("#mId").val(data[0].id);
                parent.$("#mPhone").val(data[0].phone);
                //获取当前页面的index，用于关闭当前窗口
                var index=parent.layer.getFrameIndex(window.name);
                layer.close(index);
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $("#phone").on('keyup', function () {
            table.reload('tableData', {
                where: {
                    "s.phone":encodeURI($('#phone').val()),
                    "s.type":-1
                }
            });
        });


    });
</script>

</body>
</html>
