<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" ng-app="app">
<head>
    <title>CMS</title>

    <link href="assets/css/main.css" rel="stylesheet"/>
    <link href="assets/css/dev.css" rel="stylesheet"/>
    <link href="assets/css/libs/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="dev-panel" ng-controller="DevPanelController">
    <div ng-include src="'assets/app/dev/dev-panel.html'"></div>
</div>
<div class="container">

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <ul class="nav navbar-nav">
                <li ui-sref-active="active"><a ui-sref="home">Home</a></li>
                <li ui-sref-active="active"><a ui-sref="employeeList">Employees</a></li>
            </ul>
        </div>
    </nav>
    <div class="row">
        <%--<div class="col-md-12">--%>
            <%--<nav class="navbar navbar-inverse">--%>
                <%--<div class="container">--%>
                    <%--<div class="navbar-header">--%>
                        <%--<a class="navbar-brand" href="#">Project Name</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<nav>--%>
                    <%--<li>--%>
                        <%--<a ui-sref="home">Home</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a ui-sref="employeeList">Employees</a>--%>
                    <%--</li>--%>
                <%--</nav>--%>
            <%--</nav>--%>
        <%--</div>--%>
        <div class="col-md-12" ui-view>
        </div>
        <div class="col-md-12">
            <footer>
                <p>&copy; IKaynov.ru 2016</p>
            </footer>
        </div>
    </div>
</div>
<!-- libs -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/lib/angular.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/angular-route.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/lodash.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/bootstrap.min.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/angular-file-upload-shim.min.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/angular-ui-router.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/angular-permission.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/angular-animate.min.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/angular-cookies.min.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/angular-resource.min.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/angular-messages.min.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/angular-translate.min.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/angular-translate-loader-url.min.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/ui-bootstrap-tpls.min.js?timestamp=@timestamp@"></script>
<!--<script type="text/javascript" src="assets/js/lib/ui-bootstrap.min.js?timestamp=@timestamp@"></script>-->
<script type="text/javascript" src="assets/js/lib/angular-file-upload.min.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/angular-sanitize.min.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/angular-confirm.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/angular-slider.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/bootstrap-slider.min.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/spin.min.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/jquery-ui.min.js?timestamp=@timestamp@"></script>
<script type="text/javascript" src="assets/js/lib/angular-ui-numeric.js?timestamp=@timestamp@"></script>

<script src="assets/app/app.module.js"></script>
<script src="assets/app/app.js"></script>
<script src="assets/app/app.config.js"></script>
<script src="assets/app/app.state.js"></script>
<script src="assets/app/dev/dev.controller.js"></script>
<script src="assets/app/dev/data.service.js"></script>
<script src="assets/app/cms/core/core.service.js"></script>
<script src="assets/app/cms/core/core.helper.js"></script>
<script src="assets/app/cms/home/home.controller.js"></script>
<script src="assets/app/cms/appObj/list.controller.js"></script>
<script src="assets/app/cms/appObj/edit.controller.js"></script>
<script src="assets/app/cms/appObj/data.service.js"></script>
</body>
</html>