<#macro referenceTableField element>
<div class="reference-table grid-block vertical" id="${(element.id)!}" class="${(element.cssClass)!}" style="${(element.style)!}"
     ng-controller="AppGridController as vm">
  <div ng-init='grid = "${element.toJson()}"; referenceGridId="${(element.referenceGrid)!}"; referenceAppObjLabel="${element.referenceAppObjLabel}"'
       style="display: none"></div>
  <div kendo-toolbar>
    <kendo-button k-icon="'plus'" ng-click="openOneToManyModal()">Add</kendo-button>
    <kendo-button k-icon="'cancel'" ng-click="openDeleteOneToManyModal()" ng-disabled="!selectedItem">Delete</kendo-button>
    <#if element.filterPanel??>
      <kendo-button k-icon="'funnel'" ng-click="toggleFilterPanel()">Filter</kendo-button>
    </#if>
    <kendo-button k-icon="'refresh'" ng-click="refresh()">Refresh</kendo-button>
  </div>
  <#if element.filterPanel??>
    <div class="panel-body app-table-filter-panel" ng-show="showFilterPanel">
      <@filterPanel element=element.filterPanel/>
    </div>
  </#if>
  <div class="grid-block">
    <@table element=element/>
  </div>
</div>
</#macro>
