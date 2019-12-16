<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <title>Log statistics page</title>
</head>
<body style="margin: 60px;">
<main role="main" class="container">
    <h5 style="margin: 0;">Log statistics:</h5>
    <table class="table table-bordered table-hover">
        <thead class="thead-light">
        <tr>
            <th scope="col">Date</th>
            <th scope="col">Grouped field</th>
            <th scope="col">Count</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="logStatsDto" items="${statistic_list}">
            <tr>
                <th scope="row">${logStatsDto.date}</th>
                <td>${logStatsDto.groupedByField}</td>
                <td>${logStatsDto.rowCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="${pageContext.request.contextPath}/log-statistic" class="btn btn-primary">Back to the
        form</a>
    <a arget="_blank" href="${pageContext.request.contextPath}/log-statistic/file?fromDate=${from_date_filter}&toDate=${to_date_filter}">
        <input class="btn btn-primary" type="button" value="Download file"/>
    </a>
</main>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>