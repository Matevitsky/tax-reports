<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="ClientHeader.jsp" %>
<html lang="en">

<body data-gr-c-s-loaded="true" style="">

<div id="wrapper">


    <div id="page-wrapper">

        <div class="row">
            <div class="col-lg-6">

                <form employeeRole="form" action="/app" method="get">
                    <c:set var="report" value="${report}"/>
                    <input type="hidden" name="command" value="client_create_report">

                    <div class="form-group">
                        <label>Tittle</label>
                        <input class="form-control" name="tittle" placeholder="${report.tittle}">
                    </div>

                    <div class="form-group">
                        <label>Report content</label>
                        <textarea class="form-control" name="content">${report.content}</textarea>
                    </div>

                    <button type="submit" class="btn btn-default">Save</button>
                    <button type="reset" class="btn btn-default">Cancel</button>

                </form>

            </div>

        </div>
    </div>
</div>


</body>
</html>
