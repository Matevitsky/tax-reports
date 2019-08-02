<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="AdminHeader.jsp" %>
<html lang="en">

<title>Admin Report Page</title>

<body>
<div id="wrapper">

    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-6">

                <form employeeRole="form" action="/app" method="get">
                    <c:set var="report" value="${report}"/>
                    <input type="hidden" name="reportId" value=${report.id}>

                    <div class="form-group">
                        <label>Tittle</label>
                        <input class="form-control" name="tittle" value="${report.tittle}">
                    </div>

                    <div class="form-group">
                        <label>Report content</label>
                        <textarea class="form-control" rows="3" name="content">${report.content} </textarea>
                    </div>

                    <button type="submit" class="btn btn-default" name="command" value="admin_save_report">Save</button>
                    <c:if test="${(report.status != 'ACCEPTED')}">
                        <button type="submit" class="btn btn-danger" name="command" value="admin_delete_report">Delete
                        </button>
                        <input type="hidden" name="reportId" value=${report.id}>
                    </c:if>

                    <button type="submit" class="btn btn-default" name="command" value="admin_cancel">Cancel</button>

                </form>

            </div>

        </div>
    </div>

</div>

</body>
</html>
