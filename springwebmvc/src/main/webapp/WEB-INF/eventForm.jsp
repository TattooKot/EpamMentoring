<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>User</title>
</head>
<body>
<div class="container" style="margin-top: 10%; width: 30%">
    <h3>Enter event data</h3>
    <form method="post" action=${pageContext.request.contextPath.concat('/event')}>
        <div class="form-group">
            <input id="titleInput" name="title" type="text" class="form-control" placeholder="Title"/>
        </div>
        <div class="form-group">
            <input id="dateInput" name="date" type="date" class="form-control" placeholder="date"/>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

</body>
</html>
