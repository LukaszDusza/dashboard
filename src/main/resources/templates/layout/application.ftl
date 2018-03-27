<#macro layout title>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Layout</title>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.5/css/bootstrap.min.css'>
    <!-- <link rel='stylesheet' href='/webjars/bootstrap/3.3.5/js/bootstrap.min.js'> -->
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/email">example email template</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <h6>${title}</h6>
		<#nested>
</div>
</body>
</html>
</#macro>
