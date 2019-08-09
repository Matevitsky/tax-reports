<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="AdminHeader.jsp" %>
<html lang="en">
<title>Admin Client's Report Page</title>

<body>
<div id="wrapper">
    <div id="page-wrapper">

        <table id="allReports" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
            <thead>

            <tr>

                <th class="th-sm">
                    <fmt:message bundle="${common}" key="title"/>
                </th>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="status"/>
                </th>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="client.name"/>
                </th>
                <th class="th-sm"></th>


            </tr>


            </thead>
            <tbody>
            <div class="form-group">

                <c:forEach items="${reports}" var="report">
                    <form action="/app" method="get">
                        <input type="hidden" name="reportId" value="${report.id}"/>
                        <input type="hidden" name="clientName" value="${clientName}"/>
                        <tr>
                            <td>${report.tittle}</td>

                            <td>${report.status}</td>

                            <td>${clientName}</td>

                            <td>
                                <button type="submit" class="btn btn-default" name="command"
                                        value="admin_edit_report">
                                    <fmt:message bundle="${common}" key="view"/>
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
                    <fmt:message bundle="${common}" key="title"/>
                </th>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="status"/>
                </th>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="client.name"/>
                </th>
                <th class="th-sm"></th>

            </tr>

            </tfoot>
        </table>
    </div>
</div>

</body>
<%@include file="AdminFooter.jsp" %>
</html>
