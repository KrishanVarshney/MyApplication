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

<body>
<?php
$title=$_POST["t1"];
$contant=$_POST["t2"];
require_once("../include/MyLib.php");
$t=time();
$sql="insert INTO notice values(0,'$title','all','all','all','$contant',$t,'$el')";
mysql_query($sql,$cn);
$m=mysql_affected_rows();
if($m>0)
{
	?>
    <h3 style="color:#0F0;">ADDED SUCCESSFULL</h3>
    <?php
}
else
{
	?>
<h3 style="color:#F00"> ERROR</h3>
	<p>
	  <?php
}
?>
</p>
	<p><a href="AddNotice.php">ADD MORE</a></p>
	<p><a href="ViewNotice.php">HOME</a> </p>
</body>
</html>