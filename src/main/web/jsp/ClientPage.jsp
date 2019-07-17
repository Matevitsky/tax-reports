<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../jsp/ClientHeader.jsp" %>
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

        <table id="ReportTable" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">

            <thead>
            <tr>
                <th class="th-sm">Title</th>
                <th class="th-sm">Status</th>
                <th class="th-sm">Button</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${reports}" var="report">
                <tr>
                    <td>${report.tittle}</td>
                    <td>${report.content}</td>
                    <td>${report.status}</td>
                        <%-- <td>

                             <form action="/app" method="get">
                                 <input type="time" id="duration" name="duration" input placeholder=
                                     <fmt:message bundle="${common}" key="time"/>
                                         min="59:00" max="23:00" required autofocus/>


                                 <input type="hidden" name="command" value="user_activity_remove_request">
                                 <input type="hidden" name="id" value="${reports.id}"/>
                                 <input type="submit" value="<fmt:message bundle="${common}" key="delete.activity"/>" name="remove">
                             </form>

                         </td>--%>
                </tr>
            </c:forEach>
            </tbody>


            <tfoot>
            <tr>
                <th class="th-sm">Title</th>
                <th class="th-sm">Status</th>
                <th class="th-sm">Button</th>

            </tr>
            </tfoot>
        </table>

    </div>
</div>
<!-- /#wrapper -->


</body>
</html>
