<#include "../app/all-app.ftl"/>
<div class="grid-block vertical" style="height: 100%">
  <div class="grid-block">
    <form id="${appObj.name}Form" role="form" novalidate="novalidate" class="form grid-block vertical">
    <#assign modelName="selectedItem"/>
  <@container element=editView/>
    <#--<th:block th:each="element : ${editView.elements}">-->
      <#--<th:block th:include="${element.templatePath} :: ${element.templateName}" th:with="modelName='selectedItem'">-->
      <#--</th:block>-->
    <#--</th:block>-->
    </form>
    <div>
      <!-- Alerts -->
      <div>
        <uib-alert ng-repeat="alert in alerts" type="{{ alert.type }}" close="closeAlert( $index )">
          <span>{{ alert.message }}</span>
        </uib-alert>
      </div>
    </div>
  </div>
  <div class="grid-block shrink">
    <div class="k-edit-buttons" style="">
      <kendo-button type="submit" class="" ng-click="save(${appObj.name}Form.$valid)">Save</kendo-button>
      <kendo-button class="" ng-click="cancel()">Cancel</kendo-button>
    </div>
  </div>
</div>
