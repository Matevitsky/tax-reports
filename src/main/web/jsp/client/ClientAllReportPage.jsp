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
                <th class="th-sm">Tittle</th>
                <th class="th-sm">Status</th>
                <th class="th-sm">Button</th>

            </tr>


            </thead>
            <tbody>
            <c:forEach items="${reports}" var="report">
                <tr>
                    <td>${report.tittle}</td>
                    <td>${report.status}</td>
                    <form action="/app" method="get">
                        <td>
                            <c:if test="${(report.status == 'NEW')|| report.status == 'DECLINED' }">

                                <button type="submit" class="btn btn-primary">
                                    EDIT
                                </button>

                                <input type="hidden" name="command" value="get_edit_report_page">
                                <input type="hidden" name="reportId" value=${report.id}>

                            </c:if>
                            <c:if test="${report.status == 'ACCEPTED' }">

                                <button type="submit" class="btn btn-primary">
                                    VIEW
                                </button>

                                <input type="hidden" name="command" value="get_view_report_page">
                                <input type="hidden" name="reportId" value=${report.id}>
                            </c:if>

                        </td>

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
    </div>
</div>

<%--<div class="modal fade" id="Modal" tabindex="-1" employeeRole="dialog" aria-labelledby="myModalLabel"
     style="display: none;" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-notify modal-info" employeeRole="document">
        <!--Content-->
        <div class="modal-content">

            <!--Header-->
            <div class="modal-header">
                <p class="heading lead">Report</p>
            </div>

            <!--Body-->
            <div class="modal-body">
                <div class="text-left">

                    <div class="row">

                        <div class="form-group">
                            <label>Tittle</label>
                            <input class="form-control" name="tittle" placeholder="${report.tittle}">

                        </div>

                        <div class="form-group">
                            <label>Report content</label>
                            <textarea class="form-control" rows="3" name="content">${report.id}
                            </textarea>
                        </div>

                    </div>
                </div>
            </div>

            <!--Footer-->
            <div class="modal-footer">
                <button type="submit" class="btn btn-info waves-effect waves-light">Save</button>

                <a type="button" class="btn btn-outline-info waves-effect" data-dismiss="modal">Cancel</a>
            </div>
        </div>
        <!--/.Content-->
    </div>
</div>--%>


</body>
<script>
    $(document).ready(function () {
        $('#allReports').DataTable();
    });
</script>

</html>
