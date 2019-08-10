<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/i18n.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/local.css"/>


    <link href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.1.0/css/flag-icon.min.css" rel="stylesheet">


    <link rel="stylesheet" type="text/css" media="screen"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.9.3/css/bootstrap-select.min.css">


    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.9.3/js/bootstrap-select.min.js"></script>

    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script src="https://kit.fontawesome.com/fa9ccce587.js"></script>
    <script src="https://cdnjs.com/libraries/popper.js "></script>


    <link rel="stylesheet" type="text/css"
          href="http://www.shieldui.com/shared/components/latest/css/light-bootstrap/all.min.css"/>
    <link id="gridcss" rel="stylesheet" type="text/css"
          href="http://www.shieldui.com/shared/components/latest/css/dark-bootstrap/all.min.css"/>

    <script type="text/javascript"
            src="http://www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script>
    <script type="text/javascript" src="http://www.prepbootstrap.com/Content/js/gridData.js"></script>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top" employeeRole="navigation">

    <div class="navbar-header">

        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/app?command=get_client_page"><fmt:message bundle="${common}"
                                                                                 key="main.page"/> </a>
    </div>
    <div class="collapse navbar-collapse navbar-ex1-collapse">

        <ul id="active" class="nav navbar-nav side-nav">


            <li><a href="/app?command=client_get_create_report_page"><i class="fa fa-plus"></i> <fmt:message
                    bundle="${common}"
                    key="create.new.report"/></a>
            </li>

            <li><a href="/app?command=client_all_reports"><i class="fa fa-bars"></i> <fmt:message bundle="${common}"
                                                                                                  key="all.reports"/></a>
            </li>

        </ul>


        <ul class="nav navbar-nav navbar-right navbar-user">


            <li class="dropdown messages-dropdown">

                <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <c:set var="inspector" value="${inspector}"/>
                    <i class="fas fa-user-graduate"></i> ${inspector.firstName}  ${inspector.lastName}</span> <b
                        class="caret"></b></a>
                <ul class="dropdown-menu">

                    <li><a href="/app?command=client_change_supervisor"><i class="fas fa-exchange-alt"></i>
                        <fmt:message bundle="${common}" key="change.inspector"/>

                    </a></li>
                </ul>
            </li>

            <li class="dropdown user-dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${clientName}<b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <%--  <li><a href="#"><i class="fa fa-user"></i> Profile</a></li>--%>


                    <li><a href="/app?command=log_out"><i class="fa fa-power-off"></i>
                        <fmt:message bundle="${common}" key="log.out"/>
                    </a></li>

                </ul>
            </li>
            <li class="divider-vertical"></li>
            <li>

                <form action="/app" method="POST">
                    <input type="hidden" name="command" value="change_locale">

                    <select class="selectpicker picker" data-size="3" style="width: 60%" name="locale"
                            onchange="submit()">
                        <option data-content='<span class="flag-icon flag-icon-us"></span> ENGLISH'
                                value="en-US" ${locale == 'en-US' ? 'selected' : ''}><fmt:message bundle="${common}"
                                                                                                  key="language.en"/></option>

                        <option data-content='<span class="flag-icon flag-icon-ru"></span> RUSSIAN'
                                value="ru-RU" ${locale == 'ru-RU' ? 'selected' : ''}><fmt:message bundle="${common}"
                                                                                                  key="language.ru"/></option>
                    </select>
                </form>


            </li>
        </ul>
    </div>

</nav>

</body>

</html>
