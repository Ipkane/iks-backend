<#macro filterPanel element>
<div class="hbox">
  <form role="form" novalidate="novalidate" class="form" style="width: 100%">
    <#assign modelName="filter.item"/>
    <#assign optionsMap=element.getOptionsMap()/>
      <@container element=element/>
  </form>
</div>
<div class="hbox">
  <div class="btn btn-primary" ng-click="search()">Search</div>
  <div class="btn btn-success" ng-click="clearFilter()">Clear</div>
</div>
</#macro>
