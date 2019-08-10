<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="InspectorHeader.jsp" %>
<html lang="en">
<title>Inspector Report Page</title>
<body>

<div id="page-wrapper">
    <div id="wrapper">

        <div class="row">
            <div class="col-lg-6">

                <form action="/app" method="get">
                    <c:set var="report" value="${report}"/>

                    <div class="form-group">

                        <label>
                            <fmt:message bundle="${common}" key="title"/>
                        </label>

                        <input class="form-control" name="tittle" placeholder="${report.tittle}">

                    </div>

                    <div class="form-group">
                        <label>
                            <fmt:message bundle="${common}" key="report.content"/>
                        </label>
                        <textarea class="form-control" rows="3" name="content"> ${report.content} </textarea>
                    </div>


                    <div class="form-group">
                        <label>

                            <fmt:message bundle="${common}" key="reason.to.reject"/>

                        </label>

                        <textarea class="form-control" rows="3"
                                  name="reasonToReject">
                                           <c:if test="${report.reasonToReject != 'null'}">
                                               ${report.reasonToReject}
                                           </c:if>
                        </textarea>

                    </div>


                    <form action="/app" method="get" style="display: inline">
                        <button type="submit" class="btn btn-default" name="command" value="inspector_accept_report">
                            <fmt:message bundle="${common}" key="accept"/>
                        </button>

                        <input type="hidden" name="reportId" value=${report.id}>

                        <button type="submit" class="btn btn-default" name="command" value="inspector_decline_report">
                            <fmt:message bundle="${common}" key="decline"/>
                        </button>

                        <button type="submit" class="btn btn-default" name="command" value="inspector_get_all_reports">
                            <fmt:message bundle="${common}" key="cancel"/>
                        </button>

                    </form>
            </div>
        </div>

    </div>
</div>
</body>
</html>
