<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Client Page</title>

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/local.css"/>


    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.1.0/css/flag-icon.min.css" rel="stylesheet">


    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script src="https://kit.fontawesome.com/fa9ccce587.js"></script>


    <!-- you need to include the shieldui css and js assets in order for the charts to work -->
    <link rel="stylesheet" type="text/css"
          href="http://www.shieldui.com/shared/components/latest/css/light-bootstrap/all.min.css"/>
    <link id="gridcss" rel="stylesheet" type="text/css"
          href="http://www.shieldui.com/shared/components/latest/css/dark-bootstrap/all.min.css"/>

    <script type="text/javascript"
            src="http://www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script>
    <script type="text/javascript" src="http://www.prepbootstrap.com/Content/js/gridData.js"></script>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top" employeeRole="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/app?command=get_client_page">Main Page</a>
    </div>
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul id="active" class="nav navbar-nav side-nav">


            <li><a href="/app?command=get_create_report_page"><i class="fa fa-plus"></i> Add new report</a></li>

            <li><a href="/app?command=all_reports"><i class="fa fa-bars"></i> All Reports</a></li>

        </ul>


        <ul class="nav navbar-nav navbar-right navbar-user">


            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown09" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false"><span class="flag-icon flag-icon-us"> </span> English</a>
                <div class="dropdown-menu" aria-labelledby="dropdown09">

                    <a class="dropdown-item" href="#ru"><span class="flag-icon flag-icon-ru"> </span> Russian</a>
                </div>
            </li>
            <li class="dropdown messages-dropdown">

                <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <c:set var="inspector" value="${inspector}"/>
                    <i class="fas fa-user-graduate"></i> ${inspector.firstName}  ${inspector.lastName}</span> <b
                        class="caret"></b></a>
                <ul class="dropdown-menu">

                    <li><a href="/app?command=client_change_inspector"><i class="fas fa-exchange-alt"></i> Change
                        Inspector</a></li>
                </ul>
            </li>

            <li class="dropdown user-dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${clientName}<b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#"><i class="fa fa-user"></i> Profile</a></li>

                    <li class="divider"></li>
                    <li><a href="/app?command=log_out"><i class="fa fa-power-off"></i> Log Out</a></li>

                </ul>
            </li>
            <li class="divider-vertical"></li>

        </ul>
    </div>
</nav>
</body>
<script>
    $(function () {
        $('.selectpicker').selectpicker();
    });
</script>
</html>
