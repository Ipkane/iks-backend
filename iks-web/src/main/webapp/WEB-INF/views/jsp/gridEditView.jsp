<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal-header">
    <h2 class="modal-title">${gridName}</h2>
</div>
<div class="modal-body">
    <form id="${gridName}" name="${gridName}" role="form" novalidate="novalidate" class="form-horizontal">
        <c:forEach items="${grid.fields}" var="field">
            <div class="form-group">
                <label for="${field.name}" class="col-sm-4 control-label">${field.label}</label>

                <div class="col-sm-8">
                    <input type="text" class="form-control" id="${field.name}" placeholder="${field.label}" ng-model="selectedItem.${field.name}">
                </div>
            </div>
        </c:forEach>
    </form>
</div>
<div class="modal-footer">
    <div class="row">
        <div class="col-xs-3 col-xs-offset-3">
            <button type="submit" class="btn btn-danger" ng-click="save()">Save</button>
        </div>
        <div class="col-xs-3">
            <button class="btn btn-success" ng-click="cancel()">Cancel</button>
        </div>
    </div>
</div>