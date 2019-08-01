<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="AdminHeader.jsp" %>
<html lang="en">
<body>
<div id="wrapper">
    <div id="page-wrapper">

        <table id="allReports" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
            <thead>

            <tr>

                <th class="th-sm">Name</th>
                <th class="th-sm">Email</th>
                <th class="th-sm">Company Name</th>
                <th class="th-sm">Inspector Name</th>
                <th class="th-sm">Reports</th>


            </tr>


            </thead>
            <tbody>
            <div class="form-group">

                <c:forEach items="${clients}" var="client" varStatus="status">
                    <form action="/app" method="get">
                        <input type="hidden" name="clientId" value="${client.id}"/>
                        <input type="hidden" name="clientName" value="${client.firstName} ${client.lastName}"/>

                        <tr>
                            <td>${client.firstName} ${client.lastName}</td>

                            <td>${client.email}</td>

                            <td>${client.companyName}</td>
                            <td> ${inspectors[status.index].firstName} ${inspectors[status.index].lastName}</td>
                            <td>
                                <button type="submit" class="btn btn-default" name="command"
                                        value="admin_client_reports">
                                    Client's Reports
                                </button>
                            </td>

                        </tr>
                    </form>
                </c:forEach>

            </div>
            </tbody>


            <tfoot>
            <tr>
                <th class="th-sm">Name</th>
                <th class="th-sm">Email</th>
                <th class="th-sm">Company Name</th>
                <th class="th-sm">Inspector Name</th>
                <th class="th-sm">Reports</th>
            </tr>

            </tfoot>
        </table>
    </div>
</div>

</body>
</html>
