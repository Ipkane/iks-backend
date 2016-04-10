<#include "../app/all-app.ftl"/>
<div ng-init='childGridId = "${grid.id!}"'>
  <@appTable element=grid/>
  <#--<th:block th:include="app/appTable :: appTable" th:with="element=${grid}"/>-->
</div>
<div>
  <div class="row">
    <div class="col-xs-3 col-xs-offset-3">
      <button class="btn btn-danger btn-block" ng-click="ok()">Select</button>
    </div>
    <div class="col-xs-3">
      <button class="btn btn-success btn-block" ng-click="cancel()">Cancel</button>
    </div>
  </div>
</div>