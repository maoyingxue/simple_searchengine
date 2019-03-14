<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>知研搜索引擎</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
*{
	margin:0;
	padding:0;
}

body{
	font-size:13px;
	color:#eee;
	font-family:Arial, Helvetica, sans-serif;
	background:url('bg.jpg') repeat-x #718693;
}

#page{
	/* The main container div */
	width:620px;
	margin:100px auto 0;
}

#searchForm{
	/* The search form. */
	background-color:#4C5A65;
	padding:50px 50px 30px;
	margin:80px 0;
	position:relative;

	-moz-border-radius:16px;
	-webkit-border-radius:16px;
	border-radius:16px;
}
fieldset{
	border:none;
}
#s{
	/* The search text box. */
	
	border:none;
	color:#888888;
	background:url("searchBox.png") no-repeat;
	
	float:left;
	font-family:Arial,Helvetica,sans-serif;
	font-size:15px;
	height:36px;
	line-height:36px;
	margin-right:12px;
	outline:medium none;
	padding:0 0 0 35px;
	text-shadow:1px 1px 0 white;
	width:385px;
}
/* The submit button */
#submitButton{
	background:url('buttons.png') no-repeat;
	width:83px;
	height:36px;
	text-indent:-9999px;
	overflow:hidden;
	text-transform:uppercase;
	border:none;
	cursor:pointer;
}
#submitButton:hover{
	background-position:left bottom;
}
.type{
 color:#000000;
 font-family:"楷体";
 font-size:96pt;
 text-align:center;
}
      </style>
</head>

<body>

	<div id="page">
		<form action="SearchServlet" method="post" id="searchForm">
		<fieldset>
			<input type="text" name="key" id="s" size="46" />
			<input type="submit" value="Search" id="submitButton" />
			</fieldset>
		</form>
		<p class="type">
		知研</p>
	</div>
</body>
</html>
