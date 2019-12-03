<html>
<head>
    <title> Upload Email List CSV </title>
</head>
<body>
<h2>Employee Details</h2>
<!-- <form method="post" action="/trigger">  -->

<form method="post" action="/trigger" enctype="multipart/form-data">  
    Enter EmailID : <input type="text" name="userName"/>
    Enter Email Password: <input type="password" name="password">
	Select file: <br />
    <input type="file" name="file" />
    <input type="submit" value="Submit">    
</form>

</body>
</html>