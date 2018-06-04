<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="at" uri="/WEB-INF/util_tag.tld"%>
<html>
<head>
    <title>千和健康云管理业务平台</title>
    <link id="home" href="${contextPath}/themes/css/base.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${contextPath}/res/layui/css/layui.css">
    <link href="${contextPath}/themes/css/login.css" rel="stylesheet" type="text/css">
</head>
<body onkeydown="keyLogin(event);">
<%--<div class="header">--%>
    <%--<h1>--%>
        <%--XX云用户登录--%>
    <%--</h1>--%>
<%--</div>--%>
<div class="content" >
    <div class="header">
        <h1>千和健康云管理业务平台</h1>
    </div>
    <div class="login-box">
    <form name="loginform" id="loginform"  class="layui-form" action="login.action">
        <h1 class="title">登录</h1>
        <div class="layui-form-item">
            <input type="hidden" name="type" value="1">
            <div class="layui-input-inline">
                <label class="lable-userName-bg"></label>
                <input type="tel" name="userName" id="userName" onkeydown="if(event.keyCode == 13){sub()};" lay-verify="required" autocomplete="off" placeholder="请输入电话号码" class="layui-input" value="">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-inline">
                <label class="label-ps-bg"></label>
               <input type="password" id="ps" name="password" onkeydown="if(event.keyCode == 13){sub()};" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="">
            </div>
        </div>
        <%--<div class="layui-form-item" style="${session.viewCode==1?'':'display:none;'}" id="viewCode">--%>
            <%--<div class="layui-input-inline">--%>
                <%--<input type="text" style="width: 87px; padding: 2px;" onkeydown="if(event.keyCode == 13){sub()};" id="icode"  name="code" name="password" lay-verify="required" placeholder="输入验证码" autocomplete="off" class="layui-input" value="">--%>
                <%--<img class="login-img" draggable="false" onclick="changeImg()" title="点击更换验证码" id="vimg" src="${contextPath}/getCode.action">--%>
            <%--</div>--%>
        <%--</div>--%>

        <div class="fi" style="color:red;margin: 5px;" id="msgTip">
        </div>
        <div class="fi">
            <input type="button" class="layui-btn layui-btn-warm" style="width: 190px;" default="true" value="登&nbsp;&nbsp;&nbsp;&nbsp;录" onclick="sub()"/>
            <%--<input type="button" class="layui-btn" value="注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;" onclick="sub()"/>--%>
        </div>
    </form>
    </div>

</div>
<div class="layui-footer" style="margin-top:20px;">
    <p style="text-align: center">昆明XX科技科技有限公司版权所有©2017</p>
    <p style="text-align: center">滇XXX备XXXX号</p>
</div>
<script type="text/javascript" src="${contextPath}/res/js/jquery.js"></script>
<script type="text/javascript" src="${contextPath}/res/js/common.js"></script>
<script type="text/javascript" src="${contextPath}/res/js/jquery.validationEngine.js"></script>
<script>
    //登录系统
    function sub(){
        if($("#userName").val()==null||$("#ps").val()==null){
            layer.msg("电话号码或密码不能为空");
            return;
        }
        var rel = $("#loginform").validationEngine('validate');
        if (rel) {
            startRequest("${contextPath}/userLogin.action",function(data){
                var d=JSON.parse(data);
                if(d.state == -1){//验证码错误
                    $('#msgTip').html(d.msg);
                    // changeImg();
                }else if(d.state == 0){//帐号或则密码错误
                    $('#msgTip').html(d.msg);
                    $('#ps').val('');
                    $('#ps').focus();
                }else if(d.state == 1){//登录成功！
                    window.location.href="pc/home.action";
                }else if(d.state==-2){//验证码错误3次以上
                    $('#msgTip').html(d.msg);
                    $('#ps').val('');
                    $("#viewCode").show();
                    // changeImg();
                }else if(d.state==-3){//错误达到10次以上
                    $('#msgTip').html(d.msg);
                    $('#ps').val('');
                    $('#ps').focus();
                }
            },$("#loginform").serialize());
        }
    }

    <%--//刷新验证码--%>
    <%--function changeImg() {--%>
        <%--var img = $('#vimg')--%>
        <%--if (img) {--%>
            <%--img.attr('src', '${contextPath}/getCode.action?rr=' + new Date());--%>
            <%--$('#icode').val('');--%>
            <%--$('#icode').focus();--%>
        <%--}--%>
    <%--}--%>
</script>
</body>
</html>
