<#macro gridLayout element>
<div class="grid vbox" style="${(element.style)!}" class="${(element.cssClass)!}">
  <#list element.rows as row>
    <#assign rowIndex = row?index/>
    <div class="grid-row hbox">
      <#list element.columns as column>
        <#assign columnIndex = column?index/>
        <div class="grid-column vbox" style="${column.style}" class="${column.cssClass}">
        <#--th:if="${#lists.size(row.elements) > columnStat.index}"-->
      <#--<th:block th:include="gul/element :: element"  th:with="element=${row.elements[columnStat.index]}"/>-->
      <@gulElement element=row.elements[columnIndex]/>
        </div>
      </#list>
    </div>
  </#list>
</div>
</#macro>