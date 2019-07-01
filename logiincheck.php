<?php
$q=$_GET["q"];
require_once("include/MyLib.php");
$sql="select * from logindata where email='$q'";
$result=mysql_query($sql,$cn);
$n=mysql_num_rows($result);
if($n>0)
{
	echo "valid";
}
else
{
	echo "invalid";
}

?>