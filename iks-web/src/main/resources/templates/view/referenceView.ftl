<#include "../app/all-app.ftl"/>
<div ng-init='childGridId = "${grid.id!}"'>
  <@appTable element=grid/>
  <#--<th:block th:include="app/appTable :: appTable" th:with="element=${grid}"/>-->
</div>
<div>
  <div class="row">
    <div class="col-xs-3 col-xs-offset-3">
      <kendo-button ng-click="ok()">Select</kendo-button>
    </div>
    <div class="col-xs-3">
      <kendo-button ng-click="cancel()">Cancel</kendo-button>
    </div>
  </div>
</div>