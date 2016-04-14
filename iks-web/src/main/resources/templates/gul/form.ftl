<#macro textbox element>
<div class="field-group ${(element.cssClass)!}" style="${(element.style)!}">
  <label for="${(element.fieldName)!}" class="control-label">${(element.label)!}</label>
  <input type="text" class="field k-textbox" id="${(element.id)!}" name="${(element.fieldName)!}"
         ng-readonly="${element.readonly?c}"
         ng-required="${element.required?c}"
         <#if element.fieldName??>ng-model="${modelName}.${element.fieldName}"</#if>/>
</div>
</#macro>
<#--ng-model="${element.fieldName} ? |${modelName}.${element.fieldName}| : ''"-->
<#macro checkbox element>
<div class="field-group">
  <input type="checkbox" id="${(element.id)!}" name="${(element.fieldName)!}" ng-readonly="${element.readonly?c}"
         class="k-checkbox ${(element.cssClass)!}" style="${(element.style)!}"
         <#if element.fieldName??>ng-model="${modelName}.${element.fieldName}"</#if> checked="${element.checked?c}"/>
  <label for="${(element.fieldName)!}" class="k-checkbox-label">${element.label}</label>
</div>
</#macro>
<#macro select element>
<div class="field-group ${(element.cssClass)!}" style="${(element.style)!}">
  <label for="${(element.fieldName)!}" class="control-label">${element.label}</label>
  <select kendo-drop-down-list type="text" id="${(element.id)!}" name="${(element.fieldName)!}"
          ng-readonly="${element.readonly?c}"
          ng-required="${element.required?c}" class="field "
          <#if element.fieldName??>ng-model="${modelName}.${element.fieldName}"</#if>>
    <#if !element.required>
      <option value=""></option>
    </#if>
    <#list element.options as option>
      <option value="${option.value}">${option.label}</option>
    </#list>
  </select>
</div>
</#macro>
<#macro referenceSelect element>
<div class="field-group ${(element.cssClass)!}" style="${(element.style)!}">
  <label for="${element.fieldName}" class="control-label">${element.label}</label>
  <select kendo-drop-down-list id="${(element.id)!}" name="${(element.fieldName)!}" ng-readonly="${element.readonly?c}"
          ng-required="${element.required?c}" class="field"
          <#if element.fieldName??>ng-model="${modelName}.${element.fieldName}.id"</#if>>
    <#if !element.required>
      <option value=""></option>
    </#if>
    <#if optionsMap??>
      <#list optionsMap[element.fieldName] as option>
        <option value="${option.value}">${option.label}</option>
      </#list>
    </#if>
  </select>
</div>
</#macro>
<#macro referenceField element>
<div class="field-group ${(element.cssClass)!}" style="${(element.style)!}">
  <label for="${element.fieldName}" class="control-label">${element.label}</label>
  <reference-field reference-grid="${(element.referenceGrid)!}" model="${modelName}" field-name="${element.fieldName}"
                   display-field="${element.displayField}" class="field">
  </reference-field>
</div>
</#macro>