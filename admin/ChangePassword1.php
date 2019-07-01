<?php 
session_start();
if(!isset($_SESSION["usertype"]))
{
	header("Location:../autherror.php");
	die();
}
$el=$_SESSION["email"];
$ut=$_SESSION["usertype"];
if($ut!="admin")
{
	header("Location:../autherror.php");
	die();
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body style="background:#FFC">
<center>
<h1 style="color:#F00">Change Password</h1>
<?php 
$oldpassword=$_POST["T1"];
$password=$_POST["T2"];
$cpassword=$_POST["T3"];
require_once("../include/MyLib.php");
$sql="update logindata set password='$password' where email='$el' AND password='$oldpassword'";
if($cpassword==$password)
{
mysql_query($sql,$cn);
$n=mysql_affected_rows();
if($n>0)
{
	?>
	<h3>Password Changed</h3>
	<?php
}
else
{
	?>
	<h3>Error: Cannot Change Password </h3>
	<?php
}
}
else
{
	
	?>
	<h3>Error:Password does'nt match </h3>
	<p>
	  <?php

	
}
?>
  </p>
	<p><a href="ViewNotice.php">HOME</a></p>
</center>
</body>
</html>