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

                        <label>Tittle</label>

                        <input class="form-control" name="tittle" placeholder="${report.tittle}">

                    </div>

                    <div class="form-group">
                        <label>Report content</label>
                        <textarea class="form-control" rows="3" name="content"> ${report.content} </textarea>
                    </div>

                    <div class="form-group">
                        <label>Reason To Reject</label>
                        <textarea class="form-control" rows="3"
                                  name="reasonToReject">${report.reasonToReject} </textarea>
                    </div>


                    <form action="/app" method="get" style="display: inline">
                        <button type="submit" class="btn btn-default" name="command" value="inspector_accept_report">
                            Accept
                        </button>

                        <input type="hidden" name="reportId" value=${report.id}>

                        <button type="submit" class="btn btn-default" name="command" value="inspector_decline_report">
                            Decline
                        </button>

                        <button type="submit" class="btn btn-default" name="command" value="inspector_new_reports">
                            Cancel
                        </button>

                    </form>
            </div>
        </div>

    </div>
</div>
</body>
</html>
