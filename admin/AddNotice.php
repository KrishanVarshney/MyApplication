<?php 
session_start();
if(!isset($_SESSION["usertype"]))
{
	header("Location:../autherror.php");
	die();
}
$e1=$_SESSION["email"];
$ut=$_SESSION["usertype"];
if($ut!="admin")
{
	header("Location:../autherror.php");
	die();
}
?>
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/master.dwt.php" codeOutsideHTMLIsLocked="false" -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><link rel="stylesheet" type="text/css" href="../bootstrap-4.1.1/dist/css/bootstrap.css">
<script type="text/javascript" src="../bootstrap-4.1.1/dist/js/bootstrap.bundle.js"></script>
<link rel="stylesheet" type="text/css" href="../bootstrap-4.1.1/dist/css/bootstrap.css">

<script type="text/javascript" src="../bootstrap-4.1.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="../CSS3%20Menu_files/css3menu1/style.css">
<script type="text/javascript" src="../jquery.js"></script>

<!-- InstanceBeginEditable name="doctitle" -->
<title>Add Notice</title>
<script type="text/javascript" src="../jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(e) {
	$("#one").hide();
	$("#form2").hide();
	$("#form1").hide();
	$("#bb").click(function(e) {
		$("#one").toggle(1000);
		$("#branch").toggle(1000);
	    $("#all").toggle(1000);
    });
});
$(document).ready(function(e) {
	$("#b3").click(function(e) {
        $("#form2").toggle(1000)
		$("#one_all").toggle(1000);
	    $("#all").toggle(1000);
    });
});
$(document).ready(function(e) {
	$("#b6").click(function(e) {
        $("#form1").toggle(1000)
		$("#branch").toggle(1000);
	    $("#one_all").toggle(1000);
    });
});
</script>
<!-- InstanceEndEditable -->
<style>
#css3menu1
{
	width:100%;
}
#collnav
{
	width:100%;
	position:relative;
	z-index:99999 !important;
}
#container
{
	height:720px;
	width:100%;
	
	
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
<div >
<div id="header">
<h1 style="color:#F00">Welcome to ADMIN Workspace</h1>
</div>
<div>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
<div class="container">
<button class="navbar-toggler" type="button" id="b2">
<span class="navbar-toggler-icon"></span>
</button>
<div style="margin-top:10px" class="collapse navbar-collapse " id="collnav">
<ul id="css3menu1" class="navbar-nav ">
	<li class="nav-item"><a href="profile.php" style="height:32px;line-height:32px;"> PROFILE</a></li>
	<li class="nav-item"><a href="AddStudent.php" style="height:32px;line-height:32px;"> ADD STUDENT</a></li>
	<li class="nav-item"><a href="AddAdmin.php" style="height:32px;line-height:32px;"> ADD ADMIN</a></li>
	<li class="nav-item"><a href="AddNotice.php" style="height:32px;line-height:32px;"> ADD NOTICE</a></li>
	<li class="nav-item"><a href="ViewNotice.php" style="height:32px;line-height:32px;"> VIEW NOTICE</a></li>
	<li class="nav-item"><a href="ViewAdmins.php" style="height:32px;line-height:32px;"> VIEW ADMINS</a></li>
	<li class="nav-item"><a href="ViewStudent.php" style="height:32px;line-height:32px;"> VIEW STUDENTS</a></li>
	<li class="nav-item"><a href="ChangePassword.php" style="height:32px;line-height:32px;"> CHANGE PASSWORD</a></li>
	<li class="nav-item"><a href="../logout.php" style="height:32px;line-height:32px;"> LOGOUT</a></li>
</ul>
</div>
</div>
</nav>
</div>
<div id="container">
<img src="../download.jpg" />
<div id="con">
<!-- InstanceBeginEditable name="head" -->
<h1 style="color:#F00; margin-top:30px">ADD NOTICE</h1>
<div id="one_all">
<h4 style="color:#F00; margin-top:30px">FOR PARTICULAR STUDENT</h4>
<p style="color:#F00; margin-top:30px">
  <input type="submit" name="bb" id="bb" value="Show">
</p>
<div id="one">
<?php 
require_once("../include/MyLib.php");
$sql="select * from student";
		$result=mysql_query($sql,$cn);
		$n=mysql_num_rows($result);
if($n>0)
{?>
	<table style="margin-top:50px; width:80%" cellspacing="3" cellpadding="10" border="1px thin">
    <tr>
    <th>NAME</th>
    <th>BRANCH</th>
    <th>YEAR</th>
    <th>ROLL NO</th>
    <th>CONTACT</th>
    <th>EMAIL</th>
    </tr>
	<?php
	while($rw=mysql_fetch_array($result))
			{
				$name=$rw["name"];
				$branch=$rw["branch"];
				$year=$rw["year"];
				$roll=$rw["roll"];
				$contact=$rw["contact"];
				$email=$rw["email"];
				?>
            
            <tr>
            <td ><?php echo $name; ?> </td>
            <td> <?php echo $branch; ?></td>
            <td> <?php echo $year; ?></td>
            <td> <?php echo $roll;?></td>
            <td> <?php echo $contact; ?></td>
            <td> <a href="AddNotice3.php?semail=<?php echo $email;?>&sbranch=<?php echo $branch;?>&syear=<?php echo $year;?>"><?php echo $email;?></a></td>
            </tr>
            
            <?php
			}
			?>
  </table>
            <p>
              <?php
}
else
{
	?>
            </p>
            <p>&nbsp; </p>
          <h3 style="color:#F00">NO DATA FOUND</h3>
	<p>
	  <?php
}
?>
	</p>
</div>
</div>
<div id="branch">
<h4 style="color:#F00; margin-top:30px">FOR PARTICULAR BRANCH OR YEAR</h4>
<p style="color:#F00; margin-top:30px">
  <input type="submit" name="b3" id="b3" value="show">
</p>
<form name="form2" id="form2" method="post" action="AddNotice6.php">
  <p>BRANCH :
    <select style="width:100px" name="s1" id="s1">
      <option>ece</option>
      <option>cse</option>
      <option>me</option>
      <option>ce</option>
      <option>all</option>
    </select>
  </p>
  <p>YEAR : 
    <select style="width:100px" name="s2" id="s2">
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
      <option>all</option>
    </select>
  </p>
  <p>
    <input type="submit" name="b4" id="b4" value="go">
  </p>
</form>
</div>
<div id="all">
<h4 style="color:#F00; margin-top:30px">FOR ALL STUDENT</h4>
<p style="color:#F00; margin-top:30px">
  <input type="submit" name="b6" id="b6" value="show">
</p>
<form id="form1" name="form1" method="post" action="AddNotice2.php">
  <p>TITLE :</p>
  <p>
    <input style="width:500px" type="text" name="t1" id="t1" />
  </p>
  <p>CONTENT : </p>
  <p>
    <textarea style="width:500px; height:400px" name="t2" id="t2" cols="45" rows="5" ></textarea>
  </p>
  <p>&nbsp;</p>
  <p>
    <input type="submit" name="b1" id="b1" value="ADD" />
  </p>
</form>
</div>


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