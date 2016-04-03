<#macro tabbox element>
<uib-tabset id="${(element.id)!}" style="${(element.style)!}" class="${(element.cssClass)!}">
  <#list element.tabs as tabElement>
    <uib-tab heading="${tabElement.label}" index="${tabElement?index}">
    <#--<th:block th:include="gul/tab :: tab" th:with="element=${tab}"></th:block>-->
        <@tab element=tabElement/>
    </uib-tab>
  </#list>
</uib-tabset>
</#macro>
<#macro tab element>
<div class="vbox" id="${(element.id)!}" style="${(element.style)!}" class="${(element.cssClass)!}">
  <@container element=element/>
</div>
</#macro>