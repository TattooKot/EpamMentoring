<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>User</title>
</head>
<body>

<div class="jumbotron">
    <h1 class="display-4">Event</h1>
    <p class="lead">This pages generates an account data for you.</p>
    <hr class="my-4">
    <p>Name: ${event.title}</p>
    <p>Email: ${event.date}</p>
    <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath.concat('/addEvent')}" role="button">Create event</a>
    <a class="btn btn-secondary btn-lg" onclick="history.back()" role="button">Back</a>
    <p><a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/" role="button">Home</a></p>
</div>
</body>
</html>
