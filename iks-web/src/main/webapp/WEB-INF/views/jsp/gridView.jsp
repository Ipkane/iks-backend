<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${appObj.label}</h1>
<div class="grid-edit-panel">
    <div class="btn btn-primary" ng-click="openAddModal()">New</div>
    <div class="btn btn-primary" ng-click="openEditModal()">Edit</div>
    <div class="btn btn-primary" ng-click="openDeleteModal()">Delete</div>
</div>
<table class="table table-bordered table-hover table-condensed">
    <thead>
    <c:forEach items="${grid.fields}" var="field">
        <th data-name="${field.name}"><c:out value="${field.label}"/></th>
    </c:forEach>
    </thead>
    <tbody>
    <tr ng-repeat="item in items" ng-click="selectItem(item)" ng-class="{'success': item == selectedItem}">
        <td ng-repeat="field in grid.fields"> {{ item[field.name] }}</td>
    </tr>
    </tbody>
</table>
<div ng-init='gridName="${gridName}"; grid = "${gridJson}"' style="display: none"></div>