<#macro textbox element>
<label for="${(element.fieldName)!}" class="control-label">${(element.label)!}</label>
<input type="text" class="form-control" id="${(element.id)!}" name="${(element.fieldName)!}"
       ng-readonly="${element.readonly?c}"
       ng-required="${element.required?c}" class="${(element.cssClass)!}" style="${(element.style)!}"
       <#if element.fieldName??>ng-model="${modelName}.${element.fieldName}"</#if>/>
</#macro>
<#--ng-model="${element.fieldName} ? |${modelName}.${element.fieldName}| : ''"-->
<#macro checkbox element>
<label for="${(element.fieldName)!}" class="control-label">${element.label}</label>
<input type="checkbox" id="${(element.id)!}" name="${(element.fieldName)!}" ng-readonly="${element.readonly?c}"
       class="checkbox ${(element.cssClass)!}" style="${(element.style)!}"
       <#if element.fieldName??>ng-model="${modelName}.${element.fieldName}"</#if> checked="${element.checked?c}"/>
</#macro>
<#macro select element>
<label for="${(element.fieldName)!}" class="control-label">${element.label}</label>
<select type="text" id="${(element.id)!}" name="${(element.fieldName)!}" ng-readonly="${element.readonly?c}"
        ng-required="${element.required?c}" class="form-control ${(element.cssClass)!}" style="${(element.style)!}"
        <#if element.fieldName??>ng-model="${modelName}.${element.fieldName}"</#if>>
  <#if !element.required>
    <option value=""></option>
  </#if>
  <#list element.options as option>
    <option value="${option.value}">${option.label}</option>
  </#list>
</select>
</#macro>
<#macro referenceSelect element>
<label for="${element.fieldName}" class="control-label">${element.label}</label>
<select id="${(element.id)!}" name="${(element.fieldName)!}" ng-readonly="${element.readonly?c}"
        ng-required="${element.required?c}" class="form-control ${(element.cssClass)!}" style="${(element.style)!}"
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
</#macro>