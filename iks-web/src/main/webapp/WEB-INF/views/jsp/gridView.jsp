<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>${appObj.label}</h1>

<div class="panel panel-default">
    <div class="panel-heading grid-edit-panel">
        <div class="btn btn-primary" ng-click="openAddModal()">New</div>
        <div class="btn btn-primary" ng-click="openEditModal()" ng-disabled="!selectedItem">Edit</div>
        <div class="btn btn-primary" ng-click="openDeleteModal()" ng-disabled="!selectedItem">Delete</div>
        <div class="btn btn-success" ng-click="toggleFilterPanel()">Filter</div>
        <div class="btn btn-success" ng-click="refresh()">Refresh</div>
    </div>
    <div class="panel-body grid-filter-panel" ng-show="showFilterPanel">
        <div class="row">
            <form id="${appObj.name}Form" name="${appObj.name}Form" role="form" novalidate="novalidate" class="form">
                <c:forEach items="${gridView.filterPanel.elements}" var="element">
                    <div class="col-xs-4">
                        <div class="form-group">
                            <label for="${element.name}" class="control-label">${element.label}</label>
                            <c:choose>
                                <c:when test="${element.type == 'input'}">
                                    <input type="text" class="form-control" id="${element.name}" ng-model="filter.item.${element.name}">
                                </c:when>
                                <c:when test="${element.type == 'select'}">
                                    <select type="text" class="form-control" id="${element.name}" ng-model="filter.item.${element.name}">
                                        <c:if test="${!element.required}">
                                            <option value=""></option>
                                        </c:if>
                                        <c:forEach items="${element.options}" var="option">
                                            <option value="${option.value}">${option.label}</option>
                                        </c:forEach>
                                    </select>
                                </c:when>
                                <c:when test="${element.type == 'referenceSelect'}">
                                    <select type="text" class="form-control" id="${element.name}" ng-model="filter.item.${element.name}">
                                        <c:if test="${!element.required}">
                                            <option value=""></option>
                                        </c:if>
                                        <c:forEach items="${optionsMap[element.name]}" var="option">
                                            <option value="${option.value}">${option.label}</option>
                                        </c:forEach>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    Wrong input type!!!!!
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
            </form>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="btn btn-primary" ng-click="search()">Search</div>
            </div>
        </div>
    </div>
    <table class="table table-bordered table-hover table-condensed">
        <thead>
        <c:forEach items="${gridView.grid.fields}" var="field">
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
</div>