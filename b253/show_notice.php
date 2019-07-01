<?php
require_once("mylib.php");
$id=$_REQUEST["id"];
$sql="select * from notice where nid='$id'";
$result=mysql_query($sql,$cn);
$n=mysql_num_rows($result);
if($n>0)
{
	$rows=array();
	while($rw=mysql_fetch_assoc($result))
	{
		$rows[]=$rw;
	}
	print json_encode($rows);
}
else
{
	echo "invalid";
}
?>