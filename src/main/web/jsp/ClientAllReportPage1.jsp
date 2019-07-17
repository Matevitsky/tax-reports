<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 2019-07-17
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
    <thead>

    <tr>
        <th class="th-sm">Tittle</th>
        <th class="th-sm">Status</th>
        <th class="th-sm">Button</th>

    </tr>


    </thead>
    <tbody>
    <c:forEach items="${activityList}" var="activity">

        <tr>
            <form action="/app" method="get">
                <td>${activity.tittle}</td>
                <td>${activity.status}</td>
                    <%--<td>${activity.description}</td>
                    <td>${activity.duration}

                        <input type="hidden" name="command" value="admin_remove_activity">
                        <input type="hidden" name="id" value="${activity.id}"/>


                    </td>--%>
            </form>


        </tr>

    </c:forEach>

    </tbody>


    <tfoot>
    <tr>
        <th class="th-sm">Tittle</th>
        <th class="th-sm">Status</th>
        <th class="th-sm">Button</th>

    </tr>
    </tfoot>
</table>
</body>
</html>
