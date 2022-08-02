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
    <form method="get" action=${pageContext.request.contextPath.concat('/event/date')}>
        <div class="form-group">
            <input id="dateInput" name="date" type="date" class="form-control" placeholder="date"/>
        </div>
        <div class="form-group">
            <input id="sizeInput" name="size" type="number" class="form-control" placeholder="Page size"/>
        </div>
        <div class="form-group">
            <input id="numberInput" name="num" type="number" class="form-control" placeholder="Page number"/>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

</body>
</html>
