<#macro referenceTableField element>
  <div class="reference-table"  id="${(element.id)!}" class="${(element.cssClass)!}" style="${(element.style)!}" ng-controller="AppObjListController as vm">
    <div ng-init='grid = "${element.toJson()}"; referenceGridId="${(element.referenceGrid)!}"' style="display: none"></div>
    <div class="panel-heading app-table-toolbar">
      <div class="hbox">
        <div class="btn btn-primary" ng-click="openOneToManyModal()">Add</div>
        <div class="btn btn-primary" ng-click="openDeleteOneToManyModal()" ng-disabled="!selectedItem">Delete</div>
        <#if element.filterPanel??>
          <div class="btn btn-success" ng-click="toggleFilterPanel()">Filter</div>
        </#if>
        <div class="btn btn-success" ng-click="refresh()">Refresh</div>
      </div>
    </div>
    <#if element.filterPanel??>
      <div class="panel-body app-table-filter-panel" ng-show="showFilterPanel">
        <@filterPanel element=element.filterPanel/>
        <#--<th:block th:include="${element.filterPanel.templatePath} :: ${element.filterPanel.templateName}" th:with="element=${element.filterPanel}">-->
        <#--</th:block>-->
      </div>
    </#if>
    <div class="hbox">
      <div class="spacer" style="flex:1"/>
      <uib-pagination total-items="totalItems" ng-model="currentPage" ng-change="pageChanged()" items-per-page="itemsPerPage"></uib-pagination>
    </div>
    <div>
      <@table element=element/>
      <#--<th:block th:include="app/table :: table"></th:block>-->
    </div>
    <div class="hbox">
      <div class="spacer" style="flex:1"/>
      <uib-pagination total-items="totalItems" ng-model="currentPage" ng-change="pageChanged()" items-per-page="itemsPerPage"></uib-pagination>
    </div>
  </div>
</#macro>
