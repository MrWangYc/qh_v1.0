<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" import="java.io.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
  <title>XX管理系统</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
</head>
<body>
<div class="box">
  <div class="title" >
    <span id="title">哎呀！系统发生错误</span>
  </div>
  <div class="memo">
    Message:<br/>
    <%=exception.getClass()%>:<%=exception.getMessage()%><br/>
    StackTrace:<br/>
    <span id="memo">
    <%
      StringWriter stringWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(stringWriter);
      exception.printStackTrace(printWriter);
     // String[] str=stringWriter.toString().split("at ");
    //  out.println(str[0]+"  \n"+str[1]);
      out.println(stringWriter);
      printWriter.close();
      stringWriter.close();
    %>
    </span>
  </div>
</div>
</body>
</html>