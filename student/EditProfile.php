<?php 
session_start();
if(!isset($_SESSION["usertype"]))
{
	header("Location:../autherror.php");
	die();
}
$el=$_SESSION["email"];
$ut=$_SESSION["usertype"];
if($ut!="student")
{
	header("Location:../autherror.php");
	die();
}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body style="background:#FFC">

<?php
require_once("../include/MyLib.php");
$sql="select * from student where email='$el'";
$result=mysql_query($sql,$cn);
$n=mysql_num_rows($result);
if($n>0)
{
	$rw=mysql_fetch_array($result);
	$name=$rw["name"];
	$branch=$rw["branch"];
	$roll=$rw["roll"];
	$contact=$rw["contact"];
	?>
    <center>
<form style="margin-top:200px" id="form1" name="form1" method="post" action="EditProfile2.php">
  	<p>Name : 
    	<input type="text" value="<?php echo $name; ?>" name="t1" id="t1" />
  	</p>
  	<p>Contact : 
    	<input type="text" value="<?php echo $contact ;?>" name="t2" id="t2" />
  	</p>
  	<p>Branch:
  	  <input type="text" readonly="readonly" name="t4" value="<?php echo $branch ;?>" id="t4" />
  	</p>
  	<p>ROLL:
  	  <input type="text" name="t5"  readonly="readonly" value="<?php echo $roll ;?>" id="t5" />
  	</p>
  	<p>Email : 
   	  <input readonly="readonly" type="text"value="<?php echo $el ;?>" name="t3" id="t3" />
  	</p>
  	<p>
    	<input type="submit" name="b1" id="b1" value="UPDATE" />
  	</p>
</form>
</center>
<?php
}
else
{
	?>
<h3 style=" color:#F00">NO DATA FOUND</h3>
    <p>
      <?php
}
?>
      </p>
      <center>
    <p><a href="profile.php">BACK</a></p>
    <p><a href="ViewNotice.php">HOME</a></p>
	</center>
</body>
</html>