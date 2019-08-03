<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="AdminHeader.jsp" %>
<html lang="en">
<title>Requests Page</title>
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
                    <fmt:message bundle="${common}" key="inspectors"/>
                </th>
                <th></th>

            </tr>


            </thead>
            <tbody>
            <div class="form-group">

                <c:forEach items="${clientList}" var="client">
                    <form action="/app" method="get">
                        <input type="hidden" name="clientId" value="${client.id}"/>
                        <tr>
                            <td>${client.firstName} ${client.lastName}</td>
                            <td>

                                <select class="form-control " id="inspectorList" name="inspectorId">

                                    <c:forEach var="inspector" items="${inspectorList}">

                                        <option value="${inspector.id}">${inspector.firstName} ${inspector.lastName}</option>

                                    </c:forEach>
                                </select>

                            </td>
                            <td>
                                <button type="submit" class="btn btn-default" name="command"
                                        value="admin_assign_inspector">
                                    <fmt:message bundle="${common}" key="assign.inspector"/>
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
                    <fmt:message bundle="${common}" key="inspectors"/>
                </th>
                <th></th>

            </tr>

            </tfoot>
        </table>
    </div>
</div>

</body>
</html>
