<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c-rt" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<html>
<head>
    <title>Create Report</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="fonts/glyphicons-halflings-regular.woff"/>
    <link rel="stylesheet" type="text/css" href="css/local.css"/>
    <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css"/>


    <script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script src="https://kit.fontawesome.com/fa9ccce587.js"></script>


    <script type="text/javascript" charset="utf8"
            src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>


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
            <a class="navbar-brand" href="/app?command=get_client_page">Main Page</a>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li><a href="/app?command=get_create_report_page"><i class="fa fa-plus"></i> Add new report</a></li>

                <li><a href="/app?command=all_reports"><i class="fa fa-bars"></i> All Reports</a></li>
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

        <table id="allReports" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
            <thead>

            <tr>
                <th class="th-sm">Tittle</th>
                <th class="th-sm">Status</th>
                <th class="th-sm">Button</th>

            </tr>


            </thead>
            <tbody>
            <c:forEach items="${reports}" var="report">
                <tr>
                    <td>${report.tittle}</td>
                    <td>${report.status}</td>
                    <form action="/app" method="get">
                        <td>
                            <c:if test="${(report.status == 'NEW')|| report.status == 'DECLINED' }">

                                <button type="submit" class="btn btn-primary">
                                    EDIT
                                </button>

                                <input type="hidden" name="command" value="get_edit_report_page">
                                <input type="hidden" name="reportId" value=${report.id}>

                            </c:if>
                            <c:if test="${report.status == 'ACCEPTED' }">

                                <button type="submit" class="btn btn-primary">
                                    VIEW
                                </button>

                                <input type="hidden" name="command" value="get_view_report_page">
                                <input type="hidden" name="reportId" value=${report.id}>
                            </c:if>

                        </td>

                    </form>

                </tr>

            </c:forEach>

            </tbody>

            <tfoot>
            <tr>
                <th class="th-sm">Tittle</th>
                <th class="th-sm">Status</th>
                <th class="th-sm">Button</th>

            </tr>
            </tfoot>
        </table>
    </div>
</div>

<%--<div class="modal fade" id="Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     style="display: none;" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-notify modal-info" role="document">
        <!--Content-->
        <div class="modal-content">

            <!--Header-->
            <div class="modal-header">
                <p class="heading lead">Report</p>
            </div>

            <!--Body-->
            <div class="modal-body">
                <div class="text-left">

                    <div class="row">

                        <div class="form-group">
                            <label>Tittle</label>
                            <input class="form-control" name="tittle" placeholder="${report.tittle}">

                        </div>

                        <div class="form-group">
                            <label>Report content</label>
                            <textarea class="form-control" rows="3" name="content">${report.id}
                            </textarea>
                        </div>

                    </div>
                </div>
            </div>

            <!--Footer-->
            <div class="modal-footer">
                <button type="submit" class="btn btn-info waves-effect waves-light">Save</button>

                <a type="button" class="btn btn-outline-info waves-effect" data-dismiss="modal">Cancel</a>
            </div>
        </div>
        <!--/.Content-->
    </div>
</div>--%>


</body>
<script>
    $(document).ready(function () {
        $('#allReports').DataTable();
    });
</script>

</html>
