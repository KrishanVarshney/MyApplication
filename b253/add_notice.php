<?php 
require_once("mylib.php");
$title=$_REQUEST["title"];
$content=$_REQUEST["content"];
$uemail=$_REQUEST["by"];
$t=time();
$sql="insert INTO notice values(0,'$title','all','all','all','$content',$t,'$uemail')";
mysql_query($sql,$cn);
$n=mysql_affected_rows();
if($n==1 )
{
	echo "Add Notice Successfully";
}
else
{
	echo "Error";
}
?>