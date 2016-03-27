'use strict';
angular.module( 'app.cms' )//
  .controller( 'AppObjListController', AppObjListController )
;//
function AppObjListController( $scope, $log, $uibModal, $timeout, CoreService, GridHelper, _ ) {
  var vm = this;
  angular.extend( $scope, {
    filter      : {
      item: {}
    },
    gridName    : null,
    grid        : null,
    selectedItem: null,
    orderBy     : 'id',
    orderAsc    : true,
    items       : []
  } );
  function init() {
    if ( angular.isString( $scope.grid ) ) {
      $scope.grid = angular.fromJson( $scope.grid );
    }
    $log.debug( $scope.grid );
    reload();
  }

  function reload() {
    GridHelper.getGridData( $scope.gridName, $scope.filter.item, ($scope.orderAsc ? '' : '-') + $scope.orderBy ).then( function ( response ) {
      $scope.items          = response.success.items;
      var foundSelectedItem = false;
      if ( $scope.selectedItem ) {
        _.each( $scope.items, function ( item ) {
          if ( item.id == $scope.selectedItem.id ) {
            $scope.selectedItem = item;
            foundSelectedItem   = true;
          }
        } );
        if ( !foundSelectedItem ) {
          $scope.selectedItem = null;
        }
      }
    } ).catch( function ( response ) {
      $log.debug( response );
    } );
  };
  $scope.toggleFilterPanel = function () {
    $scope.showFilterPanel = !$scope.showFilterPanel;
  };
  $scope.search            = function () {
    reload();
  };
  $scope.refresh           = function () {
    reload();
  };
  $scope.selectItem        = function ( item ) {
    $scope.selectedItem = item;
  };
  $scope.openEditModal     = function () {
    // open modal
    $uibModal.open(
      {
        animation   : true,
        templateUrl : 'view/gridEditView?appObj=' + $scope.gridName,
        controller  : 'GridEditController',
        controllerAs: 'vm',
        backdrop    : 'static',
        resolve     : {
          payload: { appObj: $scope.gridName, itemId: $scope.selectedItem.id }
        }
      }
    ).result.then( function ( updatedItem ) {
                     reload();
                   }, function () {
                   } );
  };
  $scope.openAddModal      = function () {
    // open modal
    $uibModal.open(
      {
        animation   : true,
        templateUrl : 'view/gridEditView?appObj=' + $scope.gridName,
        controller  : 'GridEditController',
        controllerAs: 'vm',
        backdrop    : 'static',
        resolve     : {
          payload: { appObj: $scope.gridName, itemId: null, isNew: true }
        }
      }
    ).result.then( function ( updatedItem ) {
                     reload();
                   }, function () {
                   } );
  };
  $scope.openDeleteModal   = function () {
    // open modal
    $uibModal.open(
      {
        animation   : true,
        templateUrl : 'assets/app/cms/common/templates/confirm-modal.html',
        controller  : 'ConfirmModalController',
        controllerAs: 'vm',
        backdrop    : 'static',
        resolve     : {
          title    : function () {
            return "Confirm deletion"
          },
          message  : function () {
            return "Are you sure, you want to delete item " + $scope.selectedItem.id
          },
          onConfirm: function () {
            return function () {
              return CoreService.deleteItem( { appObj: $scope.gridName, itemId: $scope.selectedItem.id } ).$promise;
            }
          }
        }
      }
    ).result.then( function () {
                     reload();
                   }, function () {
                   } );
  };
  $scope.setOrderBy        = function ( fieldName ) {
    if ( fieldName == $scope.orderBy ) {
      $scope.orderAsc = !$scope.orderAsc;
    } else {
      $scope.orderAsc = true;
    }
    $scope.orderBy = fieldName;
    $scope.items   = _.orderBy( $scope.items, [ $scope.orderBy ], [ $scope.orderAsc ? 'asc' : 'desc' ] );
  };
  vm.getField = function(fieldName) {
    return _.find($scope.grid.fields, {name: fieldName});
  };
  $scope.formatItemValue   = function ( item, fieldName ) {
    var field = vm.getField(fieldName);
    if (!field.displayField) {
      return item[fieldName];
    } else {
      return item[field.name][field.displayField];
    }
  };
  vm.getHeaderClass        = function ( fieldName ) {
    if ( fieldName == $scope.orderBy ) {
      return $scope.orderAsc ? 'asc' : 'desc';
    }
    return null;
  };
  $timeout( init, 0 );
};
