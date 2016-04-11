<#include "../app/all-app.ftl"/>
<div class="grid-block vertical" style="height: 100%">
  <div class="grid-block" ng-init='childGridId = "${grid.id!}"'>
    <@appTable element=grid/>
  </div>
  <div class="grid-block shrink">
    <div class="k-edit-buttons">
      <kendo-button ng-click="ok()" k-ng-disabled="!childGridScope.selectedItem">Select</kendo-button>
      <kendo-button ng-click="cancel()">Cancel</kendo-button>
    </div>
  </div>
</div>