<?php
require_once("mylib.php");
$email=$_REQUEST["email"];
$old=$_REQUEST["old"];
$newp=$_REQUEST["newp"];
$confirm=$_REQUEST["confirm"];
if($newp==$confirm)
{
	$sql="update logindata SET password='$newp' where email='$email' AND password='$old'";
	mysql_query($sql,$cn);
	$n=mysql_affected_rows();
	if($n>0)
	{
		echo "Update successfull";
	}
	else
	{
		echo "invalid";
	}
}
else
{
	echo"new password and confirm poassword is not match";
}


?>