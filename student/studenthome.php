<?php 
session_start();
if(!isset($_SESSION["usertype"]))
{
	header("Location:../autherror.php");
	die();
}
$el=$_SESSION["email"];
$ut=$_SESSION["usertype"];
if($ut!="student")
{
	header("Location:../autherror.php");
	die();
}
?>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link rel="stylesheet" type="text/css" href="../CSS3 Menu1_files/css3menu1/style.css">
</head>

<body style="background:#FFC">
<center>
<h1 style="color:#F00">Welcome to STUDENT Home</h1>
<div id="menu" style="margin-top:10px">
<ul id="css3menu1" class="topmenu">
	<li class="topfirst"><a href="profile.php" style="height:18px;line-height:18px;">PROFILE</a></li>
	<li class="topmenu"><a href="ViewNotice.php" style="height:18px;line-height:18px;">VIEW NOTICE</a></li>
	<li class="topmenu"><a href="ChangePassword.php" style="height:18px;line-height:18px;">CHANGE PASSWORD</a></li>
	<li class="toplast"><a href="../logout.php" style="height:18px;line-height:18px;">LOGOUT</a></li>
</ul>
</div>
</center>
</body>
</html>