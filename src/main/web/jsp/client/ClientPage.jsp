<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="ClientHeader.jsp" %>
<html lang="en">

<body>
<div id="wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1>Dashboard
                    <small>Statistics and more</small>
                </h1>
                <div class="alert alert-dismissable alert-warning">
                    <button data-dismiss="alert" class="close" type="button">&times;</button>
                    Welcome to the admin dashboard! Feel free to review all pages and modify the layout to your needs.
                    <br/>
                    This theme uses the <a href="https://www.shieldui.com">ShieldUI</a> JavaScript library for the
                    additional data visualization and presentation functionality illustrated here.
                </div>
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
                            <input type="hidden" name="command" value="get_edit_report_page">
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
</html>
