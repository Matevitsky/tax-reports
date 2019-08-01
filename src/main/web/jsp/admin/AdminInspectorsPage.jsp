<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../admin/AdminHeader.jsp" %>
<html lang="en">
<title>Admin Inspectors Page</title>
<body>
<div id="wrapper">

    <div id="page-wrapper">

        <table id="allReports" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
            <thead>

            <tr>

                <th class="th-sm">Inspector Name</th>
                <th class="th-sm">Email</th>
                <th>Clients</th>

            </tr>


            </thead>
            <tbody>
            <div class="form-group">

                <c:forEach items="${inspectors}" var="inspector">
                    <form action="/app" method="get">
                        <input type="hidden" name="inspectorId" value="${inspector.id}"/>
                        <input type="hidden" name="inspectorFirstName" value="${inspector.firstName}"/>
                        <input type="hidden" name="inspectorLastName" value="${inspector.lastName}"/>

                        <tr>
                            <td>${inspector.firstName} ${inspector.lastName}</td>

                            <td>${inspector.email}</td>

                            <td>
                                <button type="submit" class="btn btn-default" name="command"
                                        value="admin_inspector_clients">
                                    Inspector Clients
                                </button>
                            </td>
                        </tr>
                    </form>
                </c:forEach>

            </div>
            </tbody>


            <tfoot>
            <tr>

                <th class="th-sm">Inspector Name</th>
                <th class="th-sm">Email</th>
                <th>Clients</th>
            </tr>

            </tfoot>
        </table>
    </div>
</div>

</body>
</html>
