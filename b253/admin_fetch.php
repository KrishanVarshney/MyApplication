<?php
require_once("mylib.php");
$email=$_REQUEST["email"];
$sql="select * from admindata where email='$email'";
$result=mysql_query($sql,$cn);
$n=mysql_num_rows($result);
if($n>0)
{
	$rw=mysql_fetch_array($result);
$name=$rw["name"];
$contact=$rw["contact"];
echo $name." \n".$contact;
}
else
{
	echo "invalid";
}
?>