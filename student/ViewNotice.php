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
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/master2.dwt.php" codeOutsideHTMLIsLocked="false" -->
<head>
<script type="text/javascript" src="../bootstrap-4.1.1/dist/js/bootstrap.bundle.js"></script>
<link rel="stylesheet" type="text/css" href="../bootstrap-4.1.1/dist/css/bootstrap.css">
<script type="text/javascript" src="../bootstrap-4.1.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="../jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>Untitled Document</title>
<!-- InstanceEndEditable -->
<link rel="stylesheet" type="text/css" href="../CSS3%20Menu_files/css3menu1/style.css">
<style>
#css3menu1
{
width:100%;
}
#container
{
	height:720px;
	width:100%;
	
	
}
#collnav
{
	width:100%;
	position:relative;
	z-index:99999 !important;
}
img{
	opacity:.5;
	background-size:cover;
	height:100%;
	width:100%;
	
}
#con
{
	top:25%;
	left:10%;
	background: #FFC;
	overflow:auto;
	height:70%;
	width:80%;
	position:absolute;
	box-shadow:0px 0px 10px 3px #000;
	border-radius:20px;
	z-index:9999 !important;
}
body
{
	position:relative;
}
</style>
<script>
$(document).ready(function(e) {
    $("#b2").click(function(e) {
        $("#collnav").slideToggle(1000);
    });
});
</script>
</head>

<body style="background:#FFC">
<center>
<div>
<div class="header">
<h1 style="color:#F00">Welcome to STUDENT Home</h1>
</div>
<div class="container" style="width:100%">
<nav class="navbar navbar-expand-md bg-dark navbar-dark" style="width:100%;"> 
<button class="navbar-toggler" type="button" id="b2">
<span class="navbar-toggler-icon"></span>
</button> 
<div style="margin-top:10px;" class="collapse navbar-collapse text-center " id="collnav">
<ul id="css3menu1" class="navbar-nav" style="text-align:center; width:100%;">
	<li class="nav-item"><a href="profile.php" style="height:35px;line-height:35px;">PROFILE</a></li>
	<li class="nav-item"><a href="ViewNotice.php" style="height:35px;line-height:35px;">VIEW NOTICE</a></li>
	<li class="nav-item"><a href="ChangePassword.php" style="height:35px;line-height:35px;">CHANGE PASSWORD</a></li>
	<li class="nav-item"><a href="../logout.php" style="height:35px;line-height:35px;">LOGOUT</a></li>
</ul>
</div>
</nav>
</div>
<div id="container">
<img src="../download.jpg" />
<div id="con">
<!-- InstanceBeginEditable name="head" -->
<h1 style="color:#F00">ANOUNCEMENT</h1>
<?php 
require_once("../include/MyLib.php");
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
			?>
  <h3 style="color:#F00">NO DATA FOUND</h3>
	<p>
	  <?php
		}
$sql="select * from notice where nfor='$el' or nfor='all' and nyear='$syear' or nyear='all' and nbranch='$sbranch' or nbranch='all' order by ndate desc;";
		$result=mysql_query($sql,$cn);
		$n=mysql_num_rows($result);
if($n>0)
{
	while($rw=mysql_fetch_array($result))
			{
				$title=$rw["title"];
				$contant=$rw["content"];
				$date=$rw["ndate"];
				$by=$rw["nby"];
				?>
            <table style="margin-top:50px; width:100%; box-shadow:0px 0px 10px 3px #000000;" cellspacing="3" cellpadding="10" border="1px thin">
            <tr style="background-color:#FCF">
            <th style="width:50px">Title : </th>
            <td> <?php echo $title; ?></td>
            </tr>
            <tr style="background-color:#DFE3E1">
            <td colspan="2"> <?php echo $contant;?><br><br>
uploded on - <?php echo date("d-m-Y H:m:i",$date); ?><br>uploded by - <?php echo $by;?>
</td>
            </tr>
            </table>
            <?php
			}
}
else
{
	?>
  <h3 style="color:#F00">NO DATA FOUND</h3>
	<p>
	  <?php
}
?>
	  
  </p>


<!-- InstanceEndEditable -->
</div>
</div>
<div id="footer">
<p>&copy; Krishan Varshney||Notice Board Manegment System</p>
</div>
</div>
</center>
</body>
<!-- InstanceEnd --></html>