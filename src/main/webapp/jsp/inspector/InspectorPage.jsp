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
                <th class="th-sm"><fmt:message bundle="${common}" key="title"/></th>
                <th class="th-sm"><fmt:message bundle="${common}" key="client.name"/></th>
                <th class="th-sm"><fmt:message bundle="${common}" key="status"/></th>
                <th class="th-sm"></th>
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
                            <input type="hidden" name="reportId" value=${report.id}>

                            <c:choose>
                                <c:when test="${(report.reportStatus == 'NEW')}">
                                    <button type="submit" class="btn btn-primary">
                                        <fmt:message bundle="${common}" key="view"/>
                                    </button>
                                    <input type="hidden" name="command" value="get_inspector_new_report_page">
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-primary">
                                        <fmt:message bundle="${common}" key="view"/>
                                    </button>
                                    <input type="hidden" name="command" value="get_inspector_report_page">
                                </c:otherwise>
                            </c:choose>

                        </td>

                    </form>

                </tr>

            </c:forEach>

            </tbody>


            <tfoot>
            <tr>
                <th class="th-sm"><fmt:message bundle="${common}" key="title"/></th>
                <th class="th-sm"><fmt:message bundle="${common}" key="client.name"/></th>
                <th class="th-sm"><fmt:message bundle="${common}" key="status"/></th>
                <th class="th-sm"></th>
            </tr>
            </tfoot>
        </table>
    </div>
</div>

</body>
<%@include file="InspectorFooter.jsp" %>

</html>
