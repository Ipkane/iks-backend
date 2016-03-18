<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="${gridName}" name="${gridName}" role="form" ng-submit="save()" novalidate="novalidate">
    <div class="modal-header">
        <h2 class="modal-title">${gridName}</h2>
    </div>
    <div class="modal-body">
    </div>
    <div class="modal-footer">
        <div class="row">
            <div class="col-xs-3 col-xs-offset-3">
                <button type="submit" class="btn btn-danger">Save</button>
            </div>
            <div class="col-xs-3">
                <button class="btn btn-success" ng-click="cancel()">Cancel</button>
            </div>
        </div>
    </div>
</form>