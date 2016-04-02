<html>
<div class="modal-header">
  <h2 class="modal-title">Edit ${appObj.label}</h2>
</div>
<div class="modal-body">
  <form id="${appObj.name}Form" role="form" novalidate="novalidate" class="form">

    <#--<th:block th:each="element : ${editView.elements}">-->
      <#--<th:block th:include="${element.templatePath} :: ${element.templateName}" th:with="modelName='selectedItem'">-->
      <#--</th:block>-->
    <#--</th:block>-->
  </form>
  <div class="row">
    <!-- Alerts -->
    <div class="col-xs-12">
      <uib-alert ng-repeat="alert in alerts" type="{{ alert.type }}" close="closeAlert( $index )">
        <span>{{ alert.message }}</span>
      </uib-alert>
    </div>
  </div>
</div>
<div class="modal-footer">
  <div class="row">
    <div class="col-xs-3 col-xs-offset-3">
      <button type="submit" class="btn btn-danger btn-block" ng-click="save(${appObj.name}Form.$valid)">Save</button>
    </div>
    <div class="col-xs-3">
      <button class="btn btn-success btn-block" ng-click="cancel()">Cancel</button>
    </div>
  </div>
</div>
</html>