<#include './app/all-app.ftl'/>
<html>
<head>
  <title>CMS</title>

  <link href="assets/css/gul.css" rel="stylesheet"/>
  <link href="assets/css/main.css" rel="stylesheet"/>
  <link href="assets/css/dev.css" rel="stylesheet"/>
</head>
<body>
<app></app>
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
<!-- 1. Load libraries -->
<!-- IE required polyfills (from CDN), in this exact order -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/es6-shim/0.35.0/es6-shim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/systemjs/0.19.26/system-polyfills.js"></script>
<script src="https://npmcdn.com/angular2/es6/dev/src/testing/shims_for_IE.js"></script>
<script src="https://code.angularjs.org/tools/system.js"></script>
<script src="https://code.angularjs.org/tools/typescript.js"></script>
<script src="https://code.angularjs.org/2.0.0-beta.15/angular2-polyfills.js"></script>
<script src="https://code.angularjs.org/2.0.0-beta.15/Rx.js"></script>
<script src="https://code.angularjs.org/2.0.0-beta.15/angular2.dev.js"></script>
<script src="assets/js/lib/lodash.js?timestamp=@timestamp@"></script>

<!-- 2. Configure SystemJS -->
<script>
  System.config({
    transpiler: 'typescript',
    typescriptOptions: { emitDecoratorMetadata: true },
    packages: {'assets/app': {defaultExtension: 'ts',
    }}
  });
  System.import('assets/app/main')
      .then(null, console.error.bind(console));
</script>



<#--<script src="assets/app/app.module.js"></script>-->
<#--<script src="assets/app/app.js"></script>-->
<#--<script src="assets/app/app.config.js"></script>-->
<#--<script src="assets/app/app.state.js"></script>-->
<#--<script src="assets/app/dev/dev.controller.js"></script>-->
<#--<script src="assets/app/dev/data.service.js"></script>-->
<#--<script src="assets/app/cms/core/core.service.js"></script>-->
<#--<script src="assets/app/cms/core/core.helper.js"></script>-->
<#--<script src="assets/app/cms/home/home.controller.js"></script>-->
<#--<script src="assets/app/cms/common/modal.controller.js"></script>-->
<#--<script src="assets/app/cms/appObj/list.controller.js"></script>-->
<#--<script src="assets/app/cms/appObj/edit.controller.js"></script>-->
<#--<script src="assets/app/cms/appObj/referenceGrid.controller.js"></script>-->
<#--<script src="assets/app/ui/referenceField.js"></script>-->
</body>
</html>