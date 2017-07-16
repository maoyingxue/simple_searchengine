<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	border:1px solid #000000;
	width:500px;
	-->
	<style type="text/css">


	#frameContent{
	
	height:3000px;
line-height:20px;
font-size:14px;

overflow-pageINdex:hidden;
overflow-y:hidden;
word-break:break-all;
	}
	</style>
  </head>
  
  <body>
  <div id="page">
		<form action="SearchServlet" method="post">
			<input type="text" name="key" size="46" />
			<input type="submit" value="Search" />
		</form>
	</div>
	<div id="frameContent">
  	<c:forEach items="${model }" var="item" >
	    <div>
	    	<h3><a href="${item.url }">${item.title }</a></h3>
	    	<div>${item.abstract1 }</div>
	    	<div>${item.id }</div>
	    	<div>${item.url } </div>
	  </div>  
    </c:forEach>  
     </div>
    <p>
<div id="pages"></div>
<script language="javascript">
var obj=document.getElementById("frameContent");
var pages=document.getElementById("pages");
window.onload=function()
{
var allpages=Math.ceil(parseInt(obj.scrollHeight)/parseInt(obj.offsetHeight));
pages.innerHTML="<b>共"+allpages+"页</b>";
for (var i=1;i<=allpages;i++){
pages.innerHTML+="<a href=\"javascript:showPage('"+i+"');\">第"+i+"页</a>&nbsp;";
}};
function showPage(pageINdex){
obj.scrollTop=(pageINdex-1)*parseInt(obj.offsetHeight);
}
</script>
  </body>
</html>
