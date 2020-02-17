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

                <th class="th-sm">
                    <fmt:message bundle="${common}" key="inspector.name"/>
                </th>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="email"/>
                </th>
                <th>
                    <fmt:message bundle="${common}" key="clients"/>
                </th>

            </tr>


            </thead>
            <tbody>
            <div class="form-group">

                <c:forEach items="${inspectors}" var="inspector">


                    <tr>
                        <form action="/app" method="get">
                            <input type="hidden" name="inspectorId" value="${inspector.id}"/>
                            <input type="hidden" name="inspectorFirstName" value="${inspector.firstName}"/>
                            <input type="hidden" name="inspectorLastName" value="${inspector.lastName}"/>
                            <td>${inspector.firstName} ${inspector.lastName}</td>

                            <td>${inspector.email}</td>

                            <td>
                                <button type="submit" class="btn btn-default" name="command"
                                        value="admin_customer_list">
                                    <fmt:message bundle="${common}" key="inspector.clients"/>
                                </button>
                            </td>
                        </form>
                    </tr>

                </c:forEach>

            </div>
            </tbody>


            <tfoot>
            <tr>

                <th class="th-sm">
                    <fmt:message bundle="${common}" key="inspector.name"/>
                </th>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="email"/>
                </th>
                <th>
                    <fmt:message bundle="${common}" key="clients"/>
                </th>
            </tr>

            </tfoot>
        </table>
    </div>
</div>

</body>
<%@include file="AdminFooter.jsp" %>
</html>
