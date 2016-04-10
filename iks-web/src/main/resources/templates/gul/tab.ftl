<#macro tabbox element>
<div kendo-tab-strip id="${(element.id)!}" style="${(element.style)!}" class="${(element.cssClass)!}" k-animation="false">
  <ul>
    <#list element.tabs as tabElement>
      <li <#if tabElement?index == 0>class="k-state-active"</#if>>${tabElement.label}</li>
    </#list>
  </ul>
  <#list element.tabs as tabElement>
    <div>
      <@tab element=tabElement/>
    </div>
  </#list>
</div>
</#macro>
<#macro tab element>
<div class="vbox" id="${(element.id)!}" style="${(element.style)!}" class="${(element.cssClass)!}">
  <@container element=element/>
</div>
</#macro>