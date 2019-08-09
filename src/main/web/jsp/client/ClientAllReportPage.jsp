<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="ClientHeader.jsp" %>
<html lang="en">

<body data-gr-c-s-loaded="true" style="">

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
                <th class="th-sm"></th>

            </tr>


            </thead>
            <tbody>
            <c:forEach items="${reports}" var="report">
                <tr>
                    <td>${report.tittle}</td>
                    <td>${report.status}</td>
                    <form action="/app" method="get">
                        <td>
                            <c:choose>
                                <c:when test="${(report.status == 'NEW')|| report.status == 'DECLINED' }">

                                    <button type="submit" class="btn btn-primary">
                                        <fmt:message bundle="${common}" key="edit"/>
                                    </button>

                                    <input type="hidden" name="command" value="client_get_edit_report_page">
                                    <input type="hidden" name="reportId" value=${report.id}>

                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-primary">
                                        <fmt:message bundle="${common}" key="view"/>
                                    </button>

                                    <input type="hidden" name="command" value="client_view_report_page">
                                    <input type="hidden" name="reportId" value=${report.id}>
                                </c:otherwise>
                            </c:choose>

                        </td>

                    </form>

                </tr>

            </c:forEach>

            </tbody>

            <tfoot>
            <tr>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="title"/>
                </th>
                <th class="th-sm">
                    <fmt:message bundle="${common}" key="status"/>
                </th>
                <th class="th-sm"></th>


            </tr>
            </tfoot>
        </table>
    </div>
</div>


</body>

<%@include file="ClientFooter.jsp" %>
</html>
