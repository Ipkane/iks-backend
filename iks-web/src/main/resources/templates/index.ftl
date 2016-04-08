<#include './app/all-app.ftl'/>
<html>
<head>
  <title>CMS</title>

  <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
  <link href="assets/css/bootstrap-theme.min.css" rel="stylesheet"/>
  <link href="assets/css/gul.css" rel="stylesheet"/>
  <link href="assets/css/main.css" rel="stylesheet"/>
  <link href="assets/css/dev.css" rel="stylesheet"/>
</head>
<body ng-app="app">
<div class="dev-panel" ng-controller="DevPanelController">
  <div ng-include="'assets/app/dev/dev-panel.html'"></div>
</div>
<div class="container">
  <div class="row">
    <div class="col-xs-2">
      <nav class="sidebar-nav">
        <ul class="nav nav-pills nav-stacked">
        <#list appObjList as appObj>
          <li ui-sref-active="active">
            <a ui-sref="appObjListView({appObj:'${appObj.name}'})">${appObj.label}</a>
          </li>
        </#list>
        </ul>
      </nav>
    </div>
    <div class="col-md-10" ui-view="">
    </div>
  </div>
  <footer>
    <p>&copy; IKaynov.ru 2016</p>
  </footer>
</div>
<!-- libs -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="assets/bower_components/angular/angular.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/angular-route.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/lodash.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/bootstrap.min.js?timestamp=@timestamp@""></script>
<script src="assets/js/lib/angular-file-upload-shim.min.js?timestamp=@timestamp@"></script>
<script src="assets/bower_components/angular-ui-router/release/angular-ui-router.js"></script>
<script src="assets/js/lib/angular-permission.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/angular-animate.min.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/angular-cookies.min.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/angular-resource.min.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/angular-messages.min.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/angular-translate.min.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/angular-translate-loader-url.min.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/ui-bootstrap-tpls.min.js?timestamp=@timestamp@"></script>
<!--<script src="assets/js/lib/ui-bootstrap.min.js?timestamp=@timestamp@"></script>-->
<script src="assets/js/lib/angular-file-upload.min.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/angular-sanitize.min.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/angular-confirm.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/angular-slider.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/bootstrap-slider.min.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/spin.min.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/jquery-ui.min.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/angular-ui-numeric.js?timestamp=@timestamp@"></script>
<script src="assets/app/app.module.js"></script>
<script src="assets/app/app.js"></script>
<script src="assets/app/app.config.js"></script>
<script src="assets/app/app.state.js"></script>
<script src="assets/app/dev/dev.controller.js"></script>
<script src="assets/app/dev/data.service.js"></script>
<script src="assets/app/cms/core/core.service.js"></script>
<script src="assets/app/cms/core/core.helper.js"></script>
<script src="assets/app/cms/home/home.controller.js"></script>
<script src="assets/app/cms/common/modal.controller.js"></script>
<script src="assets/app/cms/appObj/list.controller.js"></script>
<script src="assets/app/cms/appObj/edit.controller.js"></script>
<script src="assets/app/cms/appObj/referenceList.controller.js"></script>
<script src="assets/app/cms/appObj/data.service.js"></script>
</body>
</html>