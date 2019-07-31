<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Edit Report</title>

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
<body>
<div id="wrapper">
    <nav class="navbar navbar-inverse navbar-fixed-top" employeeRole="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/app?command=admin_main_page"/>">Main Page</a>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">

                <li>
                    <c:set var="requestAmount" value="${clientList}"></c:set>

                    <a href="/app?command=admin_main_page"/><i class="fa fa-inbox"></i> Requests <span
                    class="badge"> ${fn:length(requestAmount)} </span></a></a>
                    </a>
                </li>


                <li><a href="<c:url value="/app?command=admin_get_all_inspectors"/>">
                    <i class="fas fa-user-graduate"></i> Inspectors </a></li>

                <li><a href="<c:url value="/app?command=admin_get_all_clients"/>"><i class="fa fa-users"></i>
                    Clients </a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right navbar-user">

                <li class="dropdown messages-dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-user-graduate"></i> Client Name <b class="caret"></b></a>
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

                <form employeeRole="form" action="/app" method="get">
                    <c:set var="report" value="${report}"/>
                    <input type="hidden" name="reportId" value=${report.id}>

                    <div class="form-group">
                        <label>Tittle</label>
                        <input class="form-control" name="tittle" placeholder="${report.tittle}">
                    </div>

                    <div class="form-group">
                        <label>Report content</label>
                        <textarea class="form-control" rows="3" name="content">${report.content} </textarea>
                    </div>

                    <button type="submit" class="btn btn-default" name="command" value="edit_report">Save</button>
                    <c:if test="${(report.status != 'ACCEPTED')}">
                        <button type="submit" class="btn btn-danger" name="command" value="delete_report">Delete
                        </button>
                        <input type="hidden" name="reportId" value=${report.id}>
                    </c:if>

                    <button type="submit" class="btn btn-default" name="command" value="admin_cancel">Cancel</button>

                </form>

            </div>

        </div>
    </div>

</div>

</body>
</html>
