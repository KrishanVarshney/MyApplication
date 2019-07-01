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
<title>ADD STUDENT</title>
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

<h1 style="color:#F00;margin-top:50px">ADD STUDENT DETAILS</h1>
<form id="form1" name="form1" method="post" action="AddStudent2.php" style="margin-top:60px">
<table height="332">
  <tr><th width="98">NAME :</th> 
    <td width="147"><input type="text" name="t1" id="t1" /></td></tr>
  <tr>
  <th>ROLL :</th> 
    <td><input type="text" name="t2" id="t2" />
  </td></tr>
  <tr>
  <th>BRANCH : </th>
    <td><select style="width:100px" name="s1" id="s1">
      <option>ece</option>
      <option>cse</option>
      <option>me</option>
      <option>ce</option>
    </select></td></tr>
  <tr>
  <th>YEAR : </th>
    <td><select style="width:100px" name="s2" id="s2">
      <option>1</option>
      <option>2</option>
      <option>3</option>
      <option>4</option>
      <option>5</option>
    </select>
  </td></tr>
  <tr>
  <th>
  CONTACT : </th><td>
    <input type="text" name="t4" id="t4" />
  </td></tr>
  <tr><th>EMAIL : </th>
    <td><input type="email" name="t5" id="t5" />
  </td></tr>
  <tr>
  <th>PASSWORD: </th>
    <td><input type="password" name="t6" id="t6" />
  </td></tr>
  </table>
  <p>
    <input type="submit" name="b1" id="b1" value="ADD" />
  </p>
  <p>&nbsp; </p>
</form>

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