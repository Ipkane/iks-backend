'use strict';
angular.module('app.cms')//
    .directive('referenceField', ReferenceField)
;//
function ReferenceField() {
  return {
    restrict: 'E',
    templateUrl: 'assets/app/ui/templates/referenceField.html',
    replace: true,
    scope: {
      referenceGrid: '=',
      model: '=',
      fieldName: '=',
      displayField: '=',
      appObjLabel: '='
    },
    link: function (scope, element, attrs) {
      scope.referenceGrid = attrs['referenceGrid'];
      //scope.model = attrs['model'];
      scope.fieldName = attrs['fieldName'];
      scope.displayField = attrs['displayField'];
      scope.appObjLabel = attrs['appObjLabel'];
    },
    controller: function ($scope, $log, $kWindow) {
      $scope.openReferenceGrid = function () {
        $kWindow.open(
            {
              options: {
                modal: true,
                title: 'Select ' + $scope.appObjLabel,
                resizable: true,
                height: 300,
                width: 600,
                visible: false
              },
              templateUrl: 'view/referenceView?gridId=' + $scope.referenceGrid,
              controller: 'ReferenceGridModalController',
              controllerAs: 'vm',
              resolve: {
                payload: {}
              }
            }
        ).result.then(function (selectedItem) {
          $scope.model[$scope.fieldName] = selectedItem;
        }, function () {
        });
      };
      $scope.clear = function() {
        $scope.model[$scope.fieldName] = null;
      }
    }
  }
}