<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="AdminHeader.jsp" %>
<html lang="en">

<title>Admin Clients Page</title>

<body>
<div id="wrapper">
    <div id="page-wrapper">

        <table id="allReports" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
            <thead>

            <tr>

                <th class="th-sm">
                    <fmt:message bundle="${common}" key="client.name"/>
                </th>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="email"/>
                </th>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="company.name"/>
                </th>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="inspector.name"/>
                </th>

                <th class="th-sm">
                    <fmt:message bundle="${common}" key="reports"/>
                </th>


            </tr>


            </thead>
            <tbody>
            <div class="form-group">

                <c:forEach items="${clients}" var="client">
                    <form action="/app" method="get">
                        <input type="hidden" name="clientId" value="${client.id}"/>
                        <input type="hidden" name="clientName" value="${client.firstName} ${client.lastName}"/>

                        <tr>
                            <td>${client.firstName} ${client.lastName}
                            </td>

                            <td>${client.email}</td>

                            <td>${client.companyName}</td>

                            <td>${client.inspectorFirstName} ${client.inspectorLastName}</td>

                            <td>
                                <button type="submit" class="btn btn-default" name="command"
                                        value="admin_customer_reports">
                                    <fmt:message bundle="${common}" key="clients.report"/>
                                </button>
                            </td>

                        </tr>
                    </form>
                </c:forEach>

            </div>
            </tbody>


            <tfoot>
            <tr>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="client.name"/>
                </th>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="email"/>
                </th>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="company.name"/>
                </th>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="inspector.name"/>
                </th>

                <th class="th-sm">
                    <fmt:message bundle="${common}" key="reports"/>
                </th>

            </tr>

            </tfoot>
        </table>
    </div>
</div>

</body>
</html>
