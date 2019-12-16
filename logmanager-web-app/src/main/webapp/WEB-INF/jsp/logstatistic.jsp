<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.css"/>
    <title>Log statistics form</title>
</head>
<body style="margin: 60px;">
<main role="main" class="container">
    <form method="get" action="${pageContext.request.contextPath}/log-statistic/overview">
        <div class="container">
            <p style="margin: 0;">Select time period:</p>
            <div class="row">
                <div class="col-xs-4">
                    <div class="form-group">
                        <label>Select "from" date: </label>
                        <div id="datetimepicker" class="input-group date">
                            <input name="fromDate" class="form-control" type="text" required/>
                            <span class="input-group-addon">
                                <i class="glyphicon glyphicon-calendar"></i>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <div class="form-group">
                        <label>Select "to" date: </label>
                        <div id="datetimepicker1" class="input-group date">
                            <input name="toDate" class="form-control" type="text" required/>
                            <span class="input-group-addon">
                                <i class="glyphicon glyphicon-calendar"></i>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <p style="margin: 0;">Please, select grouping parameter:</p>
            <div class="row">
                <div class="col-xs-4">
                    <select name="groupingParameter" style="margin: 10px 5px 5px 0; height: auto;" id="select_parameter"
                            class="custom-select">
                        <option selected value="USER_NAME">User name</option>
                        <option value="MESSAGE_TYPE">Message type</option>
                    </select>
                    <div id="content_box"></div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary">Get statistic</button>
                </div>
            </div>
        </div>
    </form>
</main>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<script data-require="MomentJS@2.10.0" data-semver="2.10.0"
        src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#datetimepicker").datetimepicker({
            format: 'DD/MM/YYYY HH:mm'
        });
        $("#datetimepicker1").datetimepicker({
            useCurrent: false,
            format: 'DD/MM/YYYY HH:mm'
        });
        $("#datetimepicker").on("dp.change", function (e) {
            $('#datetimepicker1').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepicker1").on("dp.change", function (e) {
            $('#datetimepicker').data("DateTimePicker").maxDate(e.date);
        });
    });
</script>
</body>
</html>