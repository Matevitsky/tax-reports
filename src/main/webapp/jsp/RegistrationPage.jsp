<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/i18n.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta firstName="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>

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
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="../css/local.css"/>

    <script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>

    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>

    <style>

        div {
            padding-bottom: 20px;
        }

    </style>
</head>
<body>

<div id="wrapper">
    <nav class="navbar navbar-inverse navbar-fixed-top" employeeRole="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.jsp">
                <fmt:message bundle="${common}" key="main.page"/>
            </a>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">


            </ul>
            <ul class="nav navbar-nav navbar-right navbar-user">
                <form action="/app" method="POST">
                    <input type="hidden" name="command" value="change_locale">
                    <input type="hidden" name="uri" value="${pageContext.request.requestURI}">

                    <select class="selectpicker picker" data-size="3" style="width: 60%" name="locale"
                            onchange="submit()">
                        <option data-content='<span class="flag-icon flag-icon-us"></span> ENGLISH'
                                value="en-US" ${locale == 'en-US' ? 'selected' : ''}>
                            <fmt:message bundle="${common}" key="language.en"/></option>

                        <option data-content='<span class="flag-icon flag-icon-ru"></span> RUSSIAN'
                                value="ru-RU" ${locale == 'ru-RU' ? 'selected' : ''}>
                            <fmt:message bundle="${common}" key="language.ru"/></option>
                    </select>
                </form>


            </ul>
        </div>
    </nav>
    <form action="/app" method="post">
        <input type="hidden" name="command" value="register">
        <div>
            <div class="row text-center">
                <h2>
                    <fmt:message bundle="${common}" key="client.registration"/>
                </h2>
            </div>
            <div>
                <label for="firstname" class="col-md-2">
                    <fmt:message bundle="${common}" key="first.name"/>
                </label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="firstName" id="firstName"
                           placeholder="  <fmt:message bundle="${common}" key="first.name"/>">
                </div>
                <div class="col-md-1">
                    <i class="fa fa-lock fa-2x"></i>
                </div>
            </div>
            <div>
                <label for="lastName" class="col-md-2">
                    <fmt:message bundle="${common}" key="last.name"/>
                </label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="lastName" id="lastName"
                           placeholder=" <fmt:message bundle="${common}" key="last.name"/>">
                </div>
                <div class="col-md-1">
                    <i class="fa fa-lock fa-2x"></i>
                </div>
            </div>
            <div>
                <label for="emailAddress" class="col-md-2">
                    <fmt:message bundle="${common}" key="email"/>
                </label>
                <div class="col-md-9">
                    <input type="email" class="form-control" name="emailAddress" id="emailAddress"
                           placeholder=" <fmt:message bundle="${common}" key="email"/>">
                    <p class="help-block">
                        <fmt:message bundle="${common}" key="example"/> yourname@domain.com
                    </p>
                </div>
                <div class="col-md-1">
                    <i class="fa fa-lock fa-2x"></i>
                </div>
            </div>
            <div>
                <label for="password" class="col-md-2">
                    <fmt:message bundle="${common}" key="password"/>
                </label>
                <div class="col-md-9">
                    <input type="password" class="form-control" name="password" id="password"
                           placeholder=" <fmt:message bundle="${common}" key="password"/>">

                </div>
                <div class="col-md-1">
                    <i class="fa fa-lock fa-2x"></i>
                </div>
            </div>

            <div>
                <label for="companyName" class="col-md-2">
                    <fmt:message bundle="${common}" key="company.name"/>
                </label>
                <div class="col-md-9">
                    <input type="companyName" class="form-control" name="companyName" id="companyName"
                           placeholder="  <fmt:message bundle="${common}" key="company.name"/>">

                </div>

                <div class="col-md-1">
                    <i class="fa fa-lock fa-2x"></i>
                </div>
            </div>


            <div class="row">
                <div class="col-md-2">
                </div>


                <div class="col-md-10">
                    <input type="submit" class="btn btn-primary"
                           value=" <fmt:message bundle="${common}" key="sign.up"/>">

                </div>
            </div>
        </div>
    </form>
</div>


</body>

</html>
