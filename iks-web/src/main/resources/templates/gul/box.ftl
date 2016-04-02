<#import "./element.ftl" as el>
<#macro container element>
  <#list element.elements as childElement >
    <@el.element element=childElement/>
  </#list>
</#macro>
<#macro box element>
<div class="box" style="${element.style}" class="${element.cssClass}">
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
<div class="box fieldbox" style="${element.style}" class="${element.cssClass}">
  <#list element.elements as childElement>
    <div class="hbox form-group">
      <@element element=childElement/>
    </div>
  </#list>
</div>
</#macro>