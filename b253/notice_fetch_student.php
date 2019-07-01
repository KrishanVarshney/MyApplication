<?php
require_once("mylib.php");
$el=$_REQUEST["email"];
$sql1="select * from student where email='$el'";
		$result=mysql_query($sql1,$cn);
		$m=mysql_num_rows($result);
		if($m>0)
		{
			$rw=mysql_fetch_array($result);
			$syear=$rw["year"];
		    $sbranch=$rw["branch"];
		}
		else
		{
			echo "no data found";
		}
$sql="select * from notice where nfor='$el' or nfor='all' and nyear='$syear' or nyear='all' and nbranch='$sbranch' or nbranch='all' order by ndate desc";
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