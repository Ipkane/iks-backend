<#macro element element>
<#--<th:block th:fragment="element">-->
  <#--<th:block th:include="${element.templatePath} :: ${element.templateName}"></th:block>-->
<#--</th:block>-->
<#assign macroName=element.templateName/>
<@.vars[macroName]/>
</#macro>
<#macro spacer element>
<div id="${element.id}" th:classappend="${element.cssClass}" th:styleappend="${element.style}"></div>
</#macro>
<#macro label element>
  <label id="${element.id}" th:classappend="${element.cssClass}" th:styleappend="${element.style}" th:for="${element.control}">${element.value}</label>
</#macro>
<#macro script element>
  <script>
    ${element.text}
  </script>
</#macro>
<#macro button element>
<button id="${element.id}" class="btn btn-default"  th:classappend="${element.cssClass}" th:styleappend="${element.style}">${element.label}</button>
</#macro>
<#macro markup element>
  ${element.content}
</#macro>