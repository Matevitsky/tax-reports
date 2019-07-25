<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<html>
<head>
    <title>Inspector Report Page</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/local.css"/>

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


<body data-gr-c-s-loaded="true" style="">

<div id="wrapper">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/app?command=inspector_new_reports"/>">Main Page</a>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li><a href="<c:url value="/app?command=inspector_new_reports"/>"><i class="fa fa-plus"></i> New reports</a>
                </li>

                <li><a href="<c:url value="/app?command=inspector_get_all_reports"/>"><i class="fa fa-bars"></i> All
                    Reports</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right navbar-user">

                <li class="dropdown messages-dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-user-graduate"></i> Inspector Name </span> <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li class="divider"></li>

                        <li><a href="#"><i class="fas fa-exchange-alt"></i> Change Inspector</a></li>
                    </ul>
                </li>

                <li class="dropdown user-dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Steve Miller<b
                        class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><i class="fa fa-user"></i> Profile</a></li>
                        <li><a href="#"><i class="fa fa-gear"></i> Settings</a></li>
                        <li class="divider"></li>
                        <li><a href="#"><i class="fa fa-power-off"></i> Log Out</a></li>

                    </ul>
                </li>
                <li class="divider-vertical"></li>

            </ul>
        </div>
    </nav>

    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-6">

                <c:set var="report" value="${report}"/>

                <div class="form-group">

                    <label>Tittle</label>

                    <input class="form-control" name="tittle" placeholder="${report.tittle}">

                </div>

                <div class="form-group">
                    <label>Report content</label>
                    <textarea class="form-control" rows="3" name="content"> ${report.content} </textarea>
                </div>

                <div class="form-group">
                    <label>Reason To Reject</label>
                    <textarea class="form-control" rows="3" name="content">   </textarea>
                </div>


                <form action="/app" method="get" style="display: inline">
                    <button type="submit" class="btn btn-default">Accept</button>
                    <input type="hidden" name="command" value="inspector_accept_report">
                    <input type="hidden" name="reportId" value=${report.id}>
                </form>

                <form action="/app" method="get" style="display: inline">
                    <button type="submit" class="btn btn-default">Decline</button>
                    <input type="hidden" name="command" value="inspector_decline_report">
                </form>

                <form action="/app" method="get" style="display: inline">
                    <button type="submit" class="btn btn-default">Cancel</button>
                    <input type="hidden" name="command" value="inspector_new_reports">
                </form>

            </div>
        </div>

    </div>
</div>
</body>
</html>
