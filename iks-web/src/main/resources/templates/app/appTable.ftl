<#macro appTable element>
<div class="app-table" id="${element.id}" class="${element.cssClass}" style="${element.style}"
     ng-controller="AppObjListController as vm">
  <div ng-init="grid = '${element.toJson()}'" style="display: none"></div>
  <#if element.showToolbar>
    <div class="panel-heading app-table-toolbar">
      <div class="hbox">
        <div class="btn btn-primary" ng-click="openAddModal()">New</div>
        <div class="btn btn-primary" ng-click="openEditModal()" ng-disabled="!selectedItem">Edit</div>
        <div class="btn btn-primary" ng-click="openDeleteModal()" ng-disabled="!selectedItem">Delete</div>
        <div class="btn btn-success" ng-click="toggleFilterPanel()" th:if="${element.filterPanel != null}">Filter</div>
        <div class="btn btn-success" ng-click="refresh()">Refresh</div>
      </div>
    </div>
  </#if>
  <#--<#if element.filterPanel != null>-->
<#--<div class="panel-body app-table-filter-panel" ng-show="showFilterPanel">-->
<#--<th:block th:include="${element.filterPanel.templatePath} :: ${element.filterPanel.templateName}"-->
<#--th:with="element=${element.filterPanel}">-->
<#--</th:block>-->
<#--</div>-->
<#--</#if>-->
<#--<div class="hbox">-->
<#--<div class="spacer" style="flex:1"/>-->
<#--<uib-pagination total-items="totalItems" ng-model="currentPage" ng-change="pageChanged()"-->
<#--items-per-page="itemsPerPage"></uib-pagination>-->
<#--</div>-->
<#--<div>-->
<#--<th:block th:include="app/table :: table"></th:block>-->
<#--</div>-->
<#--<div class="hbox">-->
<#--<div class="spacer" style="flex:1"/>-->
<#--<uib-pagination total-items="totalItems" ng-model="currentPage" ng-change="pageChanged()"-->
<#--items-per-page="itemsPerPage"></uib-pagination>-->
<#--</div>-->
</div>
</#macro>