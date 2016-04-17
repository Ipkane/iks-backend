<#include './app/all-app.ftl'/>
<html>
<head>
  <title>CMS</title>

<#--<link href="assets/css/bootstrap.min.css" rel="stylesheet"/>-->
<#--<link href="assets/css/bootstrap-theme.min.css" rel="stylesheet"/>-->
<#--<link rel="stylesheet" href="http://kendo.cdn.telerik.com/2016.1.406/styles/kendo.common.min.css"/>-->
<#--<link rel="stylesheet" href="http://kendo.cdn.telerik.com/2016.1.406/styles/kendo.rtl.min.css"/>-->
<#--<link rel="stylesheet" href="http://kendo.cdn.telerik.com/2016.1.406/styles/kendo.default.min.css"/>-->
  <#--<link href="assets/bower_components/foundation-apps/dist/css/foundation-apps.min.css" rel="stylesheet"/>-->
  <link href="assets/css/kendo/kendo.common.min.css" rel="stylesheet"/>
  <link href="assets/css/kendo/kendo.silver.min.css" rel="stylesheet"/>
  <link href="assets/css/kendo/kendo.rtl.min.css" rel="stylesheet"/>
  <link href="assets/css/icon-font.css" rel="stylesheet"/>
  <link href="assets/css/gul.css" rel="stylesheet"/>
  <link href="assets/css/main.css" rel="stylesheet"/>
  <link href="assets/css/dev.css" rel="stylesheet"/>
</head>
<body ng-app="app">
<div class="grid-frame vertical">
  <div class="grid-block shrink">
    <div class="dev-panel" ng-controller="DevPanelController">
      <div ng-include="'assets/app/dev/dev-panel.html'"></div>
    </div>
  </div>
  <div kendo-splitter k-orientation="'horizontal'"
       k-panes="[{ collapsible: true, size: 200 },
                { collapsible: false }]"
       style="height: 100% !important">
    <div class="grid-block" style="border-radius: 0">
      <div class="grid-block k-content">
        <ul kendo-tree-view>
        <#list appObjList as appObj>
          <li>
            <a ui-sref="appObjListView({appObj:'${appObj.name}'})"
                     ui-sref-active="k-state-selected">${appObj.label}</a>
          </li>
        </#list>
        </ul>
      </div>
    </div>
    <div class="grid-block">
      <div class="grid-block vertical" ui-view="" style="border-radius: 0"></div>
    </div>
  </div>
</div>
<!-- libs -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<#--<script src="assets/js/lib/jquery-ui.min.js?timestamp=@timestamp@"></script>-->
<script src="assets/bower_components/angular/angular.js?timestamp=@timestamp@"></script>
<#--<script src="http://kendo.cdn.telerik.com/2016.1.406/js/kendo.all.min.js"></script>-->
<script src="assets/js/lib/kendo/kendo.all.min.js"></script>
<script src="assets/bower_components/angular-kendo-window/angular-kendo-window.js"></script>
<#--<script src="assets/bower_components/foundation-apps/dist/js/foundation-apps.js"></script>-->
<#--<script src="assets/bower_components/foundation-apps/dist/js/foundation-apps.min.js"></script>-->
<#--<script src="assets/bower_components/foundation-apps/dist/js/foundation-apps-templates.js"></script>-->
<#--<script src="assets/bower_components/foundation-apps/dist/js/foundation-apps-templates.min.js"></script>-->


<script src="assets/js/lib/angular-route.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/lodash.js?timestamp=@timestamp@"></script>
<#--<script src="assets/js/lib/bootstrap.min.js?timestamp=@timestamp@"></script>-->
<#--<script src="assets/js/lib/angular-file-upload-shim.min.js?timestamp=@timestamp@"></script>-->
<script src="assets/bower_components/angular-ui-router/release/angular-ui-router.js"></script>
<#--<script src="assets/js/lib/angular-permission.js?timestamp=@timestamp@"></script>-->
<#--<script src="assets/js/lib/angular-animate.min.js?timestamp=@timestamp@"></script>-->
<#--<script src="assets/js/lib/angular-cookies.min.js?timestamp=@timestamp@"></script>-->
<script src="assets/js/lib/angular-resource.min.js?timestamp=@timestamp@"></script>
<script src="assets/js/lib/angular-messages.min.js?timestamp=@timestamp@"></script>
<#--<script src="assets/js/lib/angular-translate.min.js?timestamp=@timestamp@"></script>-->
<#--<script src="assets/js/lib/angular-translate-loader-url.min.js?timestamp=@timestamp@"></script>-->
<#--<script src="assets/js/lib/ui-bootstrap-tpls.min.js?timestamp=@timestamp@"></script>-->
<!--<script src="assets/js/lib/ui-bootstrap.min.js?timestamp=@timestamp@"></script>-->
<#--<script src="assets/js/lib/angular-file-upload.min.js?timestamp=@timestamp@"></script>-->
<script src="assets/js/lib/angular-sanitize.min.js?timestamp=@timestamp@"></script>
<#--<script src="assets/js/lib/angular-confirm.js?timestamp=@timestamp@"></script>-->
<#--<script src="assets/js/lib/angular-slider.js?timestamp=@timestamp@"></script>-->
<#--<script src="assets/js/lib/bootstrap-slider.min.js?timestamp=@timestamp@"></script>-->
<#--<script src="assets/js/lib/spin.min.js?timestamp=@timestamp@"></script>-->
<#--<script src="assets/js/lib/angular-ui-numeric.js?timestamp=@timestamp@"></script>-->
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
<script src="assets/app/cms/appObj/referenceGrid.controller.js"></script>
<script src="assets/app/ui/referenceField.js"></script>
</body>
</html>