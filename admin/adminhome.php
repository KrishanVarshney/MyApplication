<?php 
session_start();
if(!isset($_SESSION["usertype"]))
{
	header("Location:../autherror.php");
	die();
}
$e1=$_SESSION["email"];
$ut=$_SESSION["usertype"];
if($ut!="admin")
{
	header("Location:../autherror.php");
	die();
}
?>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><link rel="stylesheet" type="text/css" href="../bootstrap-4.1.1/dist/css/bootstrap.css">

<link rel="stylesheet" type="text/css" href="../CSS3 Menu_files/css3menu1/style.css">


<title>Admin home</title>
</head>

<body style="background:#FFC">
<center>

<h1 style="color:#F00">Welcome to ADMIN Home</h1>
<div id="menu">
<ul id="css3menu1" class="topmenu">
	<li class="topfirst"><a href="profile.php" style="height:32px;line-height:32px;"><img src="CSS3 Menu_files/css3menu1/service.png" alt=""/> PROFILE</a></li>
	<li class="topmenu"><a href="AddStudent.php" style="height:32px;line-height:32px;"><img src="CSS3 Menu_files/css3menu1/256-1.png" alt=""/> ADD STUDENT</a></li>
	<li class="topmenu"><a href="AddAdmin.php" style="height:32px;line-height:32px;"><img src="CSS3 Menu_files/css3menu1/256-11.png" alt=""/> ADD ADMIN</a></li>
	<li class="topmenu"><a href="AddNotice.php" style="height:32px;line-height:32px;"><img src="CSS3 Menu_files/css3menu1/256sub1.png" alt=""/> ADD NOTICE</a></li>
	<li class="topmenu"><a href="ViewNotice.php" style="height:32px;line-height:32px;"><img src="CSS3 Menu_files/css3menu1/256subdown.png" alt=""/> VIEW NOTICE</a></li>
	<li class="topmenu"><a href="ViewAdmins.php" style="height:32px;line-height:32px;"><img src="CSS3 Menu_files/css3menu1/256base-open-over.png" alt=""/> VIEW ADMINS</a></li>
	<li class="topmenu"><a href="ViewStudent.php" style="height:32px;line-height:32px;"><img src="CSS3 Menu_files/css3menu1/256base-open-over1.png" alt=""/> VIEW STUDENTS</a></li>
	<li class="topmenu"><a href="ChangePassword.php" style="height:32px;line-height:32px;"><img src="CSS3 Menu_files/css3menu1/btour.png" alt=""/> CHANGE PASSWORD</a></li>
	<li class="toplast"><a href="../logout.php" style="height:32px;line-height:32px;"><img src="CSS3 Menu_files/css3menu1/bregister.png" alt=""/> LOGOUT</a></li>
</ul>
</div>

</center>
</body>
</html>