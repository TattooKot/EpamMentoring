<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>User</title>
</head>
<body>

<div class="jumbotron" align="center">
    <h1 class="display-4">Welcome</h1>
    <p class="lead">2-3 тижні, і томкат запрацює :D</p>
    <hr class="my-4">
    <a class="btn btn-primary btn-lg" href=${pageContext.request.contextPath.concat('/searchByTitle')} role="button">Find by title</a>
    <a class="btn btn-primary btn-lg" href=${pageContext.request.contextPath.concat('/searchByDate')} role="button">Find by date</a>
    <a class="btn btn-secondary btn-lg" onclick="history.back()" role="button">Back</a>
</div>
</body>
</html>
