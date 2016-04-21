<#include './app/all-app.ftl'/>
<html>
<head>
  <base href="/">
  <title>CMS</title>
  <link rel="stylesheet" type="text/css" href="node_modules/primeui/themes/delta/theme.css"/>
  <link rel="stylesheet" type="text/css" href="PATH/font-awesome.min.css"/>
  <link rel="stylesheet" type="text/css" href="node_modules/primeui/primeui-ng-all.css"/>

  <link href="assets/css/gul.css" rel="stylesheet"/>
  <link href="assets/css/main.css" rel="stylesheet"/>
  <link href="assets/css/dev.css" rel="stylesheet"/>
</head>
<body>
<app></app>
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
<script src="assets/js/lib/lodash.js"></script>
<script src="https://code.angularjs.org/2.0.0-beta.15/angular2.dev.js"></script>
<script src="node_modules/angular2/bundles/router.dev.js"></script>
<script src="node_modules/angular2/bundles/http.dev.js"></script>
<!-- JS for PrimeUI -->
<script src="node_modules/primeui/primeui-ng-all.min.js"></script>
<!-- 2. Configure SystemJS -->
<script>
  System.config({
    transpiler: 'typescript',
    typescriptOptions: {emitDecoratorMetadata: true},
    packages: {
      'app': {defaultExtension: 'ts'},
      'primeng': {defaultExtension: 'js'}
    },
    map: {
//      "angular2": "node_modules/angular2",
      "primeng": "node_modules/primeng",
      "app": "assets/app"
    }
  });
  System.import('app/boot')
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