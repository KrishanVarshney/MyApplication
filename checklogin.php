<?php
$email=$_POST["t1"];
$password=$_POST["t2"];
require_once("include/MyLib.php");
$sql="select * from logindata where email='$email' AND password='$password'";
$result=mysql_query($sql,$cn);
$n=mysql_num_rows($result);
if($n>0)
{
	$rw=mysql_fetch_array($result);
	$utype=$rw["usertype"];
	//create session
	session_start();
	$_SESSION["email"]=$email;
	$_SESSION["usertype"]=$utype;
	//send to page
	if($utype=="admin")
	{
		header("Location:admin/ViewNotice.php");
		die();
	}
	else if($utype=="student")
	{
		header("Location:student/ViewNotice.php");
		die();
	}
	
}
else
	{
		header("Location:LoginError.php");
		die();
	}
?>