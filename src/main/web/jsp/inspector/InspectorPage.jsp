<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="InspectorHeader.jsp" %>
<html lang="en">
<title>Inspector Page</title>
<body>

<div id="page-wrapper">
    <div id="wrapper">

        <table id="allReports" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
            <thead>

            <tr>
                <th class="th-sm">Tittle</th>
                <th class="th-sm">Client Name</th>
                <th class="th-sm">Report Status</th>
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


                            <button type="submit" class="btn btn-primary">
                                View
                            </button>
                            <input type="hidden" name="command" value="get_inspector_report_page">
                            <input type="hidden" name="reportId" value=${report.id}>

                        </td>

                    </form>

                </tr>

            </c:forEach>

            </tbody>


            <tfoot>
            <tr>
                <th class="th-sm">Tittle</th>
                <th class="th-sm">Client Name</th>
                <th class="th-sm">Report Status</th>
                <th class="th-sm">Button</th>
            </tr>
            </tfoot>
        </table>
    </div>
</div>

</body>
</html>
