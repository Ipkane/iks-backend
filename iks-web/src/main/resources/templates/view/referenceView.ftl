<#include "../app/all-app.ftl"/>
<div ng-init='childGridId = "${grid.id!}"'>
  <@appTable element=grid/>
  <#--<th:block th:include="app/appTable :: appTable" th:with="element=${grid}"/>-->
</div>
<div class="k-edit-buttons">
     <kendo-button ng-click="ok()">Select</kendo-button>
      <kendo-button ng-click="cancel()">Cancel</kendo-button>
</div>