<#macro table element>
<div kendo-grid k-options="gridOptions" k-rebind="gridOptions.selectable"
     k-on-change="itemSelected(data, dataItem, columns)"></div>
<#--<table class="table table-bordered table-hover table-condensed grid-table">-->
  <#--<thead>-->
    <#--<#list element.columns as column>-->
    <#--<th ng-click="setOrderBy('${column.fieldName}')">${column.label}-->
      <#--<span class="glyphicon glyphicon glyphicon-chevron-down" aria-hidden="true"-->
            <#--ng-show="vm.getHeaderClass('${column.fieldName}') == 'asc'"></span>-->
      <#--<span class="glyphicon glyphicon glyphicon-chevron-up" aria-hidden="true"-->
            <#--ng-show="vm.getHeaderClass('${column.fieldName}') == 'desc'"></span>-->
    <#--</th>-->
    <#--</#list>-->
  <#--</thead>-->
  <#--<tbody>-->
  <#--<tr ng-repeat="item in items" ng-click="selectItem(item)" ng-class="{'success': item == selectedItem}">-->
    <#--<td ng-repeat="column in grid.columns"> {{ formatItemValue( item, column) }}</td>-->
  <#--</tr>-->
  <#--</tbody>-->
<#--</table>-->
</#macro>