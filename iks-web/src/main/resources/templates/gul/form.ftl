<#--texbox -->
<#macro textbox element>
<div class="field-group ${(element.cssClass)!}" style="${(element.style)!}">
  <label for="${(element.id)!}" class="control-label">${(element.label)!}</label>
  <input type="text" class="field k-textbox" id="${(element.id)!}" name="${(element.fieldName)!}"
         ng-readonly="${element.readonly?c}"
         ng-required="${element.required?c}"
         <#if element.fieldName??>ng-model="${modelName}.${element.fieldName}"</#if>/>
</div>
</#macro>

<#--datebox-->
<#macro datebox element>
<div class="field-group ${(element.cssClass)!}" style="${(element.style)!}">
  <label for="${(element.id)!}" class="control-label">${(element.label)!}</label>
  <input kendo-date-picker type="date" class="field" id="${(element.id)!}" name="${(element.fieldName)!}"
         ng-readonly="${element.readonly?c}"
         ng-required="${element.required?c}"
         style="width: 100%;"
         <#if element.fieldName??>k-ng-model="${modelName}.${element.fieldName}"</#if>/>
</div>
</#macro>

<#--Timebox-->
<#macro timebox element>
<div class="field-group ${(element.cssClass)!}" style="${(element.style)!}">
  <label for="${(element.id)!}" class="control-label">${(element.label)!}</label>
  <input kendo-time-picker class="field" id="${(element.id)!}" name="${(element.fieldName)!}"
         ng-readonly="${element.readonly?c}"
         ng-required="${element.required?c}"
         style="width: 100%;"
         <#if element.fieldName??>k-ng-model="${modelName}.${element.fieldName}"</#if>/>
</div>
</#macro>

<#--Checkbox-->
<#macro checkbox element>
<div class="field-group">
  <input type="checkbox" id="${(element.id)!}" name="${(element.fieldName)!}" ng-readonly="${element.readonly?c}"
         class="k-checkbox ${(element.cssClass)!}" style="${(element.style)!}"
         <#if element.fieldName??>ng-model="${modelName}.${element.fieldName}"</#if> checked="${element.checked?c}"/>
  <label for="${(element.id)!}" class="k-checkbox-label">${element.label}</label>
</div>
</#macro>

<#--Select-->
<#macro select element>
<div class="field-group ${(element.cssClass)!}" style="${(element.style)!}">
  <label for="${(element.id)!}" class="control-label">${element.label}</label>
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

<#--ReferenceSelect-->
<#macro referenceSelect element>
<div class="field-group ${(element.cssClass)!}" style="${(element.style)!}">
  <label for="${element.id}" class="control-label">${element.label}</label>
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

<#--ReferenceField-->
<#macro referenceField element>
<div class="field-group ${(element.cssClass)!}" style="${(element.style)!}">
  <label for="${element.id}" class="control-label">${element.label}</label>
  <reference-field reference-grid="${(element.referenceGrid)!}" app-obj-label="${element.getReferenceAppObjLabel()}" model="${modelName}" field-name="${element.fieldName}"
                   display-field="${element.displayField}" class="field">
  </reference-field>
</div>
</#macro>