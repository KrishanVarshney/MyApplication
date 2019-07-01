<?php
require_once("mylib.php");
$sql="select * from notice order by ndate desc";
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