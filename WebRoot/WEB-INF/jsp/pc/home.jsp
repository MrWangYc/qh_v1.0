<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="at" uri="/WEB-INF/util_tag.tld"%>
<html>
<head>
    <title>千和健康云管理业务平台</title>
    <link rel="stylesheet" href="${contextPath}/res/layui/css/layui.css">
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo" style="width: 300px;">千和健康${companyName}会员管理平台</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    管理员
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="###" onclick="exitSys()">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">

        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item">
                    <a href="javascript:;">活动</a>
                    <dl class="layui-nav-child">
                        <at:popedom cid="5">
                            <dd><a href="javascript:;" id="fun_6" lang="${contextPath}/pc/user/membership!list.action" onclick="loadPage('${contextPath}/pc/user/membership!list.action',6)">会员管理</a></dd>
                        </at:popedom>
                        <at:popedom cid="9">
                            <dd><a href="javascript:;" id="fun_7" lang="${contextPath}/pc/user/category!list.action" onclick="loadPage('${contextPath}/pc/user/category!list.action',7)">活动管理</a></dd>
                        </at:popedom>
                        <at:popedom cid="13">
                            <dd><a href="javascript:;" id="fun_8" lang="${contextPath}/pc/user/mem-cate!list.action" onclick="loadPage('${contextPath}/pc/user/mem-cate!list.action',8)">办卡</a></dd>
                        </at:popedom>
                        <at:popedom cid="17">
                            <dd><a href="javascript:;" id="fun_9" lang="${contextPath}/pc/user/consumption!list.action" onclick="loadPage('${contextPath}/pc/user/consumption!list.action',9)">消费登记</a></dd>
                        </at:popedom>
                    </dl>
                </li>
                <%--<at:popedom cid="19">--%>
                <li class="layui-nav-item">
                    <a href="javascript:;">系统基础设置</a>
                    <dl class="layui-nav-child">
                        <at:popedom cid="19">
                            <dd><a href="javascript:;" id="fun_1" lang="${contextPath}/pc/user/company!list.action" onclick="loadPage('${contextPath}/pc/user/company!list.action',1)">公司管理</a></dd>
                        </at:popedom>
                        <at:popedom cid="20">
                            <dd><a href="javascript:;" id="fun_2" lang="${contextPath}/pc/base/position!list.action" onclick="loadPage('${contextPath}/pc/base/position!list.action',2)">角色管理</a></dd>
                        </at:popedom>
                        <at:popedom cid="21">
                            <dd><a href="javascript:;" id="fun_3" lang="${contextPath}/pc/user/employee!list.action" onclick="loadPage('${contextPath}/pc/user/employee!list.action',3)">用户管理</a></dd>
                        </at:popedom>
                        <%--<dd><a href="javascript:;" id="fun_4" lang="${contextPath}/pc/base/function!list.action" onclick="loadPage('${contextPath}/pc/base/function!list.action',4)">权限管理</a></dd>--%>
                    </dl>
                </li>
            <%--</at:popedom>--%>
            </ul>
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <%--<ul class="layui-nav layui-nav-tree"  lay-filter="test">--%>
<%--&lt;%&ndash;                <li class="layui-nav-item layui-nav-itemed">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <a class="" href="javascript:;">管理超市</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </li>&ndash;%&gt;--%>
                <%--<s:iterator value="#request.fList" status="v">--%>

                        <%--<li class="layui-nav-item">--%>
                            <%--<a href="#">${functionName}</a>--%>
                            <%--<s:if test="#request.sysFunction.size()>0">--%>
                            <%--<dl class="layui-nav-child">--%>
                                <%--<s:iterator value="#request.sysFunction" status="s">--%>
                                    <%--<s:if test="#request.type==1">--%>
                                        <%--<dd><a href="#" id="fun_${id}" lang="${contextPath}${url}" onclick="loadPage('',${id})">${functionName}</a></dd> %>
                                    <%--</s:if>--%>
                                <%--</s:iterator>--%>
                            <%--</dl>--%>
                            <%--</s:if>--%>
                        <%--</li>--%>
                <%--</s:iterator>--%>
            <%--</ul>--%>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div id="mainBody">

        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © XX科技 版权所有
    </div>
</div>
<script type="text/javascript" src="${contextPath}/res/layui/layui.js"></script>
<script type="text/javascript" src="${contextPath}/res/js/jquery.js"></script>
<script type="text/javascript" src="${contextPath}/res/js/common.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });

	//加载iframe页面
    function loadPage(url,id){
        var mainId=  $("#main_"+id);
        var mainTd = $("#mainBody");
        var a = $("#fun_"+id)[0];
        console.info(a.lang);
        mainTd.find("iframe").hide()
        if(mainId.size() == 0){
            var str = '<iframe src="'+(url ? url : a.lang) +'" frameborder="0" id="main_'+id+'" style="width: 100%; height: 100%;"></iframe>';
            $("#mainBody").append(str);
        }else {
            <%--if(url)--%>
                <%--mainId.src="${contextPath}"+url;--%>
            mainId.show();
        }
    }

    //退出系统
    function exitSys(){
        //询问框
        layer.confirm('是否确认退出系统？', {
            btn: ['确认退出','取消退出'] //按钮
        }, function(){
            // alert(123);
            startRequest("${contextPath}/exit.action",function(data){
                var d=JSON.parse(data);
                //  alert(d.state);
                if(d.state == 1){//注销成功！
                    window.location.href="${contextPath}/login.action";
                }
            },null);
        }, function(){

        });
    }


</script>
</body>
</html>
