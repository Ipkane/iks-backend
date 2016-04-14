<#macro filterPanel element>
<div class="hbox">
  <form role="form" novalidate="novalidate" class="form" style="width: 100%">
    <#assign modelName="filter.item"/>
    <#assign optionsMap=element.getOptionsMap()/>
      <@container element=element/>
  </form>
</div>
<div class="k-edit-buttons">
  <kendo-button ng-click="search()">Search</kendo-button>
  <kendo-button ng-click="clearFilter()">Clear</kendo-button>
</div>
</#macro>
