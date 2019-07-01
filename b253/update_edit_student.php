<?php
require_once("mylib.php");
$name=$_REQUEST["name"];
$roll=$_REQUEST["roll"];
$branch=$_REQUEST["branch"];
$year=$_REQUEST["year"];
$contact=$_REQUEST["contact"];
$email=$_REQUEST["email"];
$sql="update student SET name='$name',roll='$roll',branch='$branch',year='$year',contact='$contact' where email='$email' ";
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
?>