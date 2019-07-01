<?php
require_once("mylib.php");
$email=$_REQUEST["email"];
$sql="select * from student where email='$email'";
$result=mysql_query($sql,$cn);
$n=mysql_num_rows($result);
if($n>0)
{
	$rw=mysql_fetch_array($result);
$name=$rw["name"];
$contact=$rw["contact"];
$branch=$rw["branch"];
$year=$rw["year"];
$roll=$rw["roll"];
echo "Name- ".$name." \nRoll No- ".$roll." \nBranch- ".$branch." \nYear- ".$year." \nContact- ".$contact;
}
else
{
	echo "invalid";
}
?>