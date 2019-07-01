<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="bootstrap-4.1.1/dist/css/bootstrap.css"/>
<script type="text/javascript" src="bootstrap-4.1.1/dist/js/bootstrap.bundle.js"></script>
<script type="text/javascript" src="bootstrap-4.1.1/dist/js/bootstrap.bundle.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<script type="text/javascript">
<!--script for check login --> 
function logincheck(str)
{
	var xmlhttp;
	if (str.length==0)
  	{ 
  		document.getElementById("s1").innerHTML="";
  		return;
  	}
	if (window.XMLHttpRequest)
  	{// code for IE7+, Firefox, Chrome, Opera, Safari
  		xmlhttp=new XMLHttpRequest();
  	}
	else
  	{// code for IE6, IE5
  		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  	}
	
	xmlhttp.onreadystatechange=function()
  	{
  		if (xmlhttp.readyState==4 && xmlhttp.status==200)
    		{
    				document.getElementById("s1").innerHTML=xmlhttp.responseText;
    		}
  	}
	xmlhttp.open("GET","logiincheck.php?q="+str,true);
	xmlhttp.send();
}

<!-- End Checkuser script -->

</script>
<style>
body{
	margin:0;
	padding:0;
	position:relative}
#aa
{
	height:100%;
	width:100%;
	position:fixed;
	padding:0;
	margin:0;
	}

img{
	opacity:.5;
	background-size:cover;
	height:100%;
	width:100%;
	z-index:1;
}
	
#login
{
	margin-top:-450px;
	margin-left:-20px;
	background: #FFC;
	min-height:30%;
	width:300px;
	position:relative;
	box-shadow:0px 0px 10px 3px #000;
	border-radius:20px;
	z-index:9999 !important;
}
</style>

</head>
<body>
<center>
<div class="container-fluid" id="aa">
<img src="5756817eb80e1stryimg" class="figure-img"/>
<div id="login" class="figure-img">
<h1 style="color:#F00">Login</h1>
<form id="form1" name="form1" method="post" action="checklogin.php">
<table>
<tr>
  <th>Email :  </th>
   <td> <input  type="email" name="t1" id="t1" onkeyup="logincheck(this.value)" />
    <span id="s1" style="color:#00C;"></span>
  </td>
  </tr>
  <tr>
  <th height="38">Password :  </th>
   <td> <input  type="password" name="t2" id="t2" />
  </td>
  </tr>
  </table>
  <p>
    <input type="submit" name="b1" id="b1" value="Submit" />
  </p>
</form>
</div>
</div>
</center>
</body>
</html>