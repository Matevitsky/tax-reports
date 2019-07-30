<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Page</title>

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
            <a class="navbar-brand" href="<c:url value="/app?command=inspector_new_reports"/>">Main Page</a>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">

                <li>
                    <a href="#"/><i class="fa fa-inbox"></i> Requests<span class="badge"> 3 </span></a></a>
                    </a>
                </li>


                <li><a href="<c:url value="/app?command=inspector_get_all_reports"/>">
                    <i class="fas fa-user-graduate"></i> Inspectors </a></li>

                <li><a href="<c:url value="/app?command=inspector_get_all_reports"/>"><i class="fa fa-users"></i>
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

        <table id="allReports" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
            <thead>

            <tr>

                <th class="th-sm">Client Name</th>
                <th class="th-sm">Inspectors</th>
                <th class="th-sm">Button</th>
            </tr>


            </thead>
            <tbody>
            <c:forEach items="${reports}" var="report">

                <tr>
                    <td>${report.tittle}</td>
                    <td>${report.clientFullName}</td>
                    <td>${report.reportStatus}</td>
                    <form action="/app" method="get">
                        <td>
                            <div class="btn-group">
                                <button type="button" class="btn btn-default">Default</button>
                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"
                                        aria-expanded="false"><span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">Separated link</a></li>
                                </ul>
                            </div>


                            <select id="requestList" name="selectedRecord">

                                <c:forEach var="inspector" items="${inspectorList}">

                                    <option value="${inspector}">${inspectors.name}</option>

                                </c:forEach>

                            </select>

                        </td>

                    </form>

                </tr>

            </c:forEach>

            </tbody>


            <tfoot>
            <tr>

                <th class="th-sm">Client Name</th>
                <th class="th-sm">Inspectors</th>
                <th class="th-sm">Button</th>
            </tr>

            </tfoot>
        </table>
    </div>
</div>

</body>
</html>
