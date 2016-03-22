<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal-header">
    <h2 class="modal-title">Edit ${appObj.label}</h2>
</div>
<div class="modal-body">
    <form id="${appObj.name}Form" name="${appObj.name}Form" role="form" novalidate="novalidate" class="form-horizontal">
        <c:forEach items="${editView.elements}" var="element">
            <div class="form-group">
                <label for="${element.name}" class="col-sm-4 control-label">${element.label}</label>

                <div class="col-sm-8">
                    <c:choose>
                        <c:when test="${element.type == 'input'}">
                            <input type="text" class="form-control" id="${element.name}" ng-model="selectedItem.${element.name}"
                                   ng-required="${element.required}" ng-readonly="${element.readonly}">
                        </c:when>
                        <c:when test="${element.type == 'select'}">
                            <select type="text" class="form-control" id="${element.name}" ng-model="selectedItem.${element.name}"
                                    ng-required="${element.required}" ng-readonly="${element.readonly}">
                                <c:if test="${!element.required}">
                                    <option value=""></option>
                                </c:if>
                                <c:forEach items="${element.options}" var="option">
                                    <option value="${option.value}">${option.label}</option>
                                </c:forEach>
                            </select>
                        </c:when>
                        <c:when test="${element.type == 'referenceSelect'}">
                            <select type="text" class="form-control" id="${element.name}" ng-model="selectedItem.${element.name}"
                                    ng-required="${element.required}" ng-readonly="${element.readonly}">
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
    <div class="row">
        <!-- Alerts -->
        <div class="col-xs-12">
            <uib-alert ng-repeat="alert in alerts" type="{{ alert.type }}" close="closeAlert( $index )">
                <%--<span translate="{{ alert.message }}" translate-values="{{ alert.params }}"></span>--%>
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