<#macro appTable element>
<div class="app-table grid-block vertical" id="${element.id}" class="${element.cssClass}" style="${element.style}"
              ng-controller="AppGridController as vm">
  <div ng-init='grid = "${element.toJson()}"' style="display: none"></div>
  <div kendo-toolbar k-options="toolbarOptions">
    <kendo-button k-icon="'plus'" ng-click="openAddModal()">New</kendo-button>
    <kendo-button k-icon="'pencil'" ng-click="openEditModal()" ng-disabled="!selectedItem">Edit</kendo-button>
    <kendo-button k-icon="'trash'" ng-click="openDeleteModal()" ng-disabled="!selectedItem">Delete</kendo-button>
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
  <#--<div class="hbox">-->
    <#--<div class="spacer" style="flex:1"/>-->
    <#--<uib-pagination total-items="totalItems" ng-model="currentPage" ng-change="pageChanged()"-->
                    <#--items-per-page="itemsPerPage"></uib-pagination>-->
  <#--</div>-->
  <div class="grid-block">
    <@table element=element/>
  </div>
  <#--<div class="hbox">-->
    <#--<div class="spacer" style="flex:1"/>-->
    <#--<uib-pagination total-items="totalItems" ng-model="currentPage" ng-change="pageChanged()"-->
                    <#--items-per-page="itemsPerPage"></uib-pagination>-->
  <#--</div>-->
</div>
</#macro>