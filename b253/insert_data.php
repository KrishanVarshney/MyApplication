<?php 
require_once("mylib.php");
$name=$_REQUEST["name"];
$branch=$_REQUEST["branch"];
$year=$_REQUEST["year"];
$roll=$_POST["roll"];
$contact=$_POST["contact"];
$email=$_POST["email"];
$usertype="student";
$password=$_POST["password"];
$sql="insert into student values('$name','$branch','$year','$roll','$contact','$email')";
$s2="insert into logindata values('$email','$password','$usertype')";
mysql_query($sql,$cn);
$n=mysql_affected_rows();
mysql_query($s2,$cn);
$m=mysql_affected_rows();
if($n==1 )
{
	if($m==1)
		echo "Data Saved and Login Created";
	else echo "Data Saved";
}
else
{
	if($m==1)	echo "Not Saved and Login created";
	else echo "Error : Try again";
}
?>