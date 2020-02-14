<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="ClientHeader.jsp" %>
<html lang="en">

<body>
<div id="wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1>Tax system
                    <%--  <small>Statistics and more</small>--%>
                </h1>
                </br>
                </br>

                <c:choose>
                    <c:when test="${result}">

                        <div class="alert alert-success" srole="alert">
                            The request successfully added

                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>

                    </c:when>
                </c:choose>


            </div>
        </div>


        <table id="allReports" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
            <thead>

            <tr>
                <th class="th-sm"><fmt:message bundle="${common}" key="title"/></th>
                <th class="th-sm"><fmt:message bundle="${common}" key="status"/></th>
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

                            <c:if test="${(report.status == 'NEW')
                            || (report.status == 'DECLINED')}">

                            <button type="submit" class="btn btn-primary">
                                EDIT
                            </button>
                            <input type="hidden" name="command" value="client_get_edit_report_page">
                            <input type="hidden" name="reportId" value=${report.id}>

                        </td>
                        </c:if>
                    </form>

                </tr>

            </c:forEach>

            </tbody>


            <tfoot>
            <tr>
                <th class="th-sm"><fmt:message bundle="${common}" key="title"/></th>
                <th class="th-sm"><fmt:message bundle="${common}" key="status"/></th>
                <th class="th-sm"></th>

            </tr>
            </tfoot>
        </table>


    </div>
</div>


</body>
<%@include file="ClientFooter.jsp" %>

</html>
