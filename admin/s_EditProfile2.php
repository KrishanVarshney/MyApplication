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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<?php
$nbranch=$_POST["t4"];
$nroll=$_POST["t5"];
$nyear=$_POST["t6"];
$nname=$_POST["t1"];
$semail=$_POST["t3"];
$ncontact=$_POST["t2"];
require_once("../include/MyLib.php");
$sql="update student SET name='$nname',contact='$ncontact',year='$nyear',roll='$nroll',branch='$nbranch' where email='$semail'";
mysql_query($sql,$cn);
$n=mysql_affected_rows();
if($n>0)
{
	?>
    <h3 style="color:#0F0">DATA SAVED</h3>
    <?php
}
else
{
	?>
<h3 style="color:#F00">ERROR : Cannot save</h3>
    <p>
      <?php
}
?>
    </p>
    <p><a href="ViewNotice.php">HOME</a></p>
</body>
</html>