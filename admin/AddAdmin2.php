<?php
session_start();
if(!isset($_SESSION["usertype"]))
{
	header("Location:../autherror.php");
	die();
}
$el=$_SESSION["email"];
$ut=$_SESSION["usertype"];
if($ut!="admin")
{
	header("Location:../autherror.php");
	die();
}
?>
<html>
<body>
<center>
<?php
$name=$_POST["t1"];
$contact=$_POST["t4"];
$email=$_POST["t5"];
$password=$_POST["t6"];
$utype="admin";

require_once("../include/MyLib.php");
	$sql="insert INTO admindata values('$name','$contact','$email')";
    $sq1l="insert INTO logindata values('$email','$password','$utype')";
	
	mysql_query($sq1l,$cn);
	$n=mysql_affected_rows();
	mysql_query($sql,$cn);
	$m=mysql_affected_rows();
	if($n==1)
	{
		if($m==1)
		{
			?>
            <h3 style="color:#0F0">login cereated and admin added</h3>
            <?php
            
		}
		else
		{
			?>
<h3 style="color:#9F0">only login created</h3>
            <?php
		}
	}
	else
	{
		if($m==1)
		{
			?>
            <h3 style="color:#9F0">admin added and login does not created</h3>
            <?php
		}
		else
		{
			?>
<h3 style="color:#F00">Error</h3>
            <p>
              <?php
			
		}
	}	
?>
              
            </p>
            <p><a href="AddAdmin.php">BACK</a></p>
            <p><a href="ViewNotice.php">HOME</a></p>
            </center>
</body>
</html>