<?php 
require_once("mylib.php");
$email=$_REQUEST["email"];
$sql="delete from student where email='$email'";
$sql2="delete from logindata where email='$email'";
mysql_query($sql,$cn);
$n=mysql_affected_rows();
mysql_query($sql2,$cn);
$m=mysql_affected_rows();
if($n==1 )
{
	if($m==1)
		echo "Data Erased";
	else echo "Student profile deleted";
}
else
{
	if($m==1)	echo "Login deleted";
	else echo "Error : Try again";
}
?>