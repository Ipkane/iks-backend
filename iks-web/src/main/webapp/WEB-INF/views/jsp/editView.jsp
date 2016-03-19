<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal-header">
    <h2 class="modal-title">Edit ${appObj.label}</h2>
</div>
<div class="modal-body">
    <form id="${appObj.name}" name="${appObj.name}" role="form" novalidate="novalidate" class="form-horizontal">
        <c:forEach items="${editView.elements}" var="element">
            <div class="form-group">
                <label for="${element.name}" class="col-sm-4 control-label">${element.label}</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="${element.name}" placeholder="${element.label}" ng-model="selectedItem.${element.name}">
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