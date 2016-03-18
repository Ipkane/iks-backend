<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
Employee list
<table class="table">
    <thead>
    <c:forEach items="${grid.fields}" var="field">
    <th data-name="${field.name}"><c:out value="${field.label}"/></th>
        </c:forEach>
    </thead>
    <tbody>
    <tr ng-repeat="item in items">
        <td ng-repeat="field in grid.fields"> {{ item[field.name] }}</td>
    </tr>
    </tbody>
</table>
<div ng-init='grid = "${gridJson}"' style="display: none"></div>