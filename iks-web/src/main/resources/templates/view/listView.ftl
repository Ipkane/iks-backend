<#import "../gul/box.ftl" as box>
<h1>${appObj.label}</h1>
<@box.container element=listView/>
<#--<th:block th:include="gul/box :: container" th:with="element=${listView}"/>-->