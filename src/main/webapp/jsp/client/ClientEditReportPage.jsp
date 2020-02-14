<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="ClientHeader.jsp" %>
<html lang="en">
<div id="wrapper">
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-6">


                <form employeeRole="form" action="/app" method="get">
                    <c:set var="report" value="${report}"/>
                    <input type="hidden" name="reportId" value=${report.id}>

                    <div class="form-group">
                        <label>
                            <fmt:message bundle="${common}" key="title"/>
                        </label>
                        <input class="form-control" name="tittle" value="${report.tittle}">
                    </div>

                    <div class="form-group">
                        <label>
                            <fmt:message bundle="${common}" key="report.content"/>
                        </label>
                        <textarea class="form-control" rows="3" name="content">${report.content} </textarea>
                    </div>

                    <c:if test="${not empty report.reasonToReject}">
                        <div class="form-group">
                            <label>
                                <fmt:message bundle="${common}" key="report.reason"/>
                            </label>
                            <textarea class="form-control" rows="3" name="reason">${report.reasonToReject} </textarea>
                        </div>
                    </c:if>

                    <button type="submit" class="btn btn-default" name="command" value="client_edit_report">
                        <fmt:message bundle="${common}" key="save"/>
                    </button>

                    <c:if test="${(report.status != 'ACCEPTED')}">
                        <button type="submit" class="btn btn-danger" name="command" value="client_delete_report">
                            <fmt:message bundle="${common}" key="delete"/>
                        </button>
                        <input type="hidden" name="reportId" value=${report.id}>
                    </c:if>

                    <button type="submit" class="btn btn-default" name="command" value="client_all_reports">
                        <fmt:message bundle="${common}" key="cancel"/>
                    </button>

                </form>

            </div>

        </div>
    </div>
</div>


</body>
</html>
