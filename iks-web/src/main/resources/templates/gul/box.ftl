<#macro container element>
  <#list element.elements as childElement >
    <@gulElement element=childElement/>
  </#list>
</#macro>
<#macro box element>
<div class="grid-block" style="${element.style}" class="${element.cssClass}">
  <@container element=element/>
</div>
</#macro>
<#macro vbox element>
  <@box element=element/>
</#macro>
<#macro hbox element>
  <@box element=element/>
</#macro>
<#macro fieldbox element>
<div class="fieldbox ${element.cssClass}" style="${element.style}">
  <#list element.elements as childElement>
      <@gulElement element=childElement/>
  </#list>
</div>
</#macro>