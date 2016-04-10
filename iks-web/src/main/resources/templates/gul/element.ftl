<#macro gulElement element>
<#--<th:block th:fragment="element">-->
  <#--<th:block th:include="${element.templatePath} :: ${element.templateName}"></th:block>-->
<#--</th:block>-->
<#assign macroName=element.templateName/>
<@.vars[macroName] element=element/>
</#macro>
<#macro spacer element>
<div id="${(element.id)!}" th:classappend="${element.cssClass}" th:styleappend="${element.style}"></div>
</#macro>
<#macro label element>
  <label id="${(element.id)!}"class="${(element.cssClass)!}" style="${(element.style)!}" for="${(element.control)!}">${element.value}</label>
</#macro>
<#macro script element>
  <script>
    ${element.text}
  </script>
</#macro>
<#macro button element>
<kendo-button id="${(element.id)!}" class="" class="${(element.cssClass)!}" style="${(element.style)!}">${element.label}</kendo-button>
</#macro>
<#macro markup element>
  ${element.content}
</#macro>