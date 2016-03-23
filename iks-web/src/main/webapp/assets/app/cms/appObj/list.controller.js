'use strict';
angular.module( 'app.cms' )//
  .controller( 'AppObjListController', AppObjListController )
;//
function AppObjListController( $scope, $log, $uibModal, $timeout, CoreService, GridHelper, _ ) {
  angular.extend( $scope, {
    filter      : {
      item: {}
    },
    gridName    : null,
    grid        : null,
    selectedItem: null,
    items       : []
  } );
  function init() {
    if ( angular.isString( $scope.grid ) ) {
      $scope.grid = angular.fromJson( $scope.grid );
    }
    reload();
  }

  function reload() {
    GridHelper.getGridData( $scope.gridName, $scope.filter.item ).then( function ( response ) {
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
  $scope.toggleFilterPanel = function() {
    $scope.showFilterPanel = !$scope.showFilterPanel;
  };
  $scope.search          = function () {
    reload();
  };
  $scope.refresh = function() {
    reload();
  };
  $scope.selectItem      = function ( item ) {
    $scope.selectedItem = item;
  };
  $scope.openEditModal   = function () {
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
  $scope.openAddModal    = function () {
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
  $scope.openDeleteModal = function () {
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
  $timeout( init, 0 );
};
