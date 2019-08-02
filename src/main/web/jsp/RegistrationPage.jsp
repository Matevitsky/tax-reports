
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta firstName="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Dark Admin</title>

    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="../css/local.css"/>

    <script type="text/javascript" src="../js/jquery-1.10.2.min.js"></script>
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
            <a class="navbar-brand" href="index.html">Back to Admin</a>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li><a href="index.html"><i class="fa fa-bullseye"></i> Dashboard</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right navbar-user">

                <li class="dropdown user-dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Steve Miller<b
                        class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><i class="fa fa-user"></i> Profile</a></li>
                        <li><a href="#"><i class="fa fa-gear"></i> Settings</a></li>
                        <li class="divider"></li>
                        <li><a href="#"><i class="fa fa-power-off"></i> Log Out</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <form action="/app" method="post">
        <input type="hidden" name="command" value="register">
        <div>
            <div class="row text-center">
                <h2>New Registration</h2>
            </div>
            <div>
                <label for="firstname" class="col-md-2">
                    First Name:
                </label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="firstName" id="firstName"
                           placeholder="Enter First Name">
                </div>
                <div class="col-md-1">
                    <i class="fa fa-lock fa-2x"></i>
                </div>
            </div>
            <div>
                <label for="lastName" class="col-md-2">
                    Last Name:
                </label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Enter Last Name">
                </div>
                <div class="col-md-1">
                    <i class="fa fa-lock fa-2x"></i>
                </div>
            </div>
            <div>
                <label for="emailAddress" class="col-md-2">
                    Email address:
                </label>
                <div class="col-md-9">
                    <input type="email" class="form-control" name="emailAddress" id="emailAddress"
                           placeholder="Enter email address">
                    <p class="help-block">
                        Example: yourname@domain.com
                    </p>
                </div>
                <div class="col-md-1">
                    <i class="fa fa-lock fa-2x"></i>
                </div>
            </div>
            <div>
                <label for="password" class="col-md-2">
                    Password:
                </label>
                <div class="col-md-9">
                    <input type="password" class="form-control" name="password" id="password"
                           placeholder="Enter Password">

                </div>
                <div class="col-md-1">
                    <i class="fa fa-lock fa-2x"></i>
                </div>
            </div>

            <div>
                <label for="companyName" class="col-md-2">
                    Comapany:
                </label>
                <div class="col-md-9">
                    <input type="companyName" class="form-control" name="companyName" id="companyName"
                           placeholder="Enter Company Name">

                </div>

                <div class="col-md-1">
                    <i class="fa fa-lock fa-2x"></i>
                </div>
            </div>


            <div class="row">
                <div class="col-md-2">
                </div>


                <div class="col-md-10">
                    <input type="submit" class="btn btn-primary" value="Sign Up">

                </div>
            </div>
        </div>
    </form>
</div>


</body>

</html>
