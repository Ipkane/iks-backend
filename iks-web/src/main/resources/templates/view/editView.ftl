<#include "../app/all-app.ftl"/>
<div class="k-edit-form-container" style="width: 100%">
  <form id="${appObj.name}Form" role="form" novalidate="novalidate" class="form">
  <#assign modelName="selectedItem"/>
  <@container element=editView/>
    <#--<th:block th:each="element : ${editView.elements}">-->
      <#--<th:block th:include="${element.templatePath} :: ${element.templateName}" th:with="modelName='selectedItem'">-->
      <#--</th:block>-->
    <#--</th:block>-->
  </form>
  <div class="row">
    <!-- Alerts -->
    <div class="col-xs-12">
      <uib-alert ng-repeat="alert in alerts" type="{{ alert.type }}" close="closeAlert( $index )">
        <span>{{ alert.message }}</span>
      </uib-alert>
    </div>
  </div>

  <div class="k-edit-buttons">
      <kendo-button type="submit" class="" ng-click="save(${appObj.name}Form.$valid)">Save</kendo-button>
      <kendo-button class="" ng-click="cancel()">Cancel</kendo-button>
  </div>
</div>
