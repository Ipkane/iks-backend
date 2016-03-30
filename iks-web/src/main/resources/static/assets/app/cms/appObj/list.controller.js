'use strict';
angular.module( 'app.cms' )//
  .controller( 'AppObjListController', AppObjListController )
;//
function AppObjListController( $scope, $log, $uibModal, $timeout, $rootScope, CoreService, GridHelper, _ ) {
  var vm = this;
  angular.extend( $scope, {
    filter      : {
      item: {}
    },
    grid        : null,
    selectedItem: null,
    searchGridId: null, // for  reference field
    orderBy     : 'id',
    orderAsc    : true,
    items       : [],
    itemsPerPage: 10,
    currentPage : 1,
    parentItemId: null,
    totalItems  : 0
  } );
  function init() {
    if ( angular.isString( $scope.grid ) ) {
      $scope.grid = angular.fromJson( $scope.grid );
    }
    $rootScope.grids[ $scope.grid[ 'id' ] ] = $scope;
    reload();
  }

  function reload() {
    if ( $scope.$parent.itemId ) {
      $scope.parentItemId = $scope.$parent.itemId;
    }
    CoreService.getGridData( {
      gridId  : $scope.grid.id, filter: $scope.filter.item, orderBy: ($scope.orderAsc ? '' : '-') + $scope.orderBy,
      parentId: $scope.parentItemId,
      page    : $scope.currentPage, limit: $scope.itemsPerPage
    } ).$promise.then( function ( response ) {
                         $scope.items          = response.success.result.items;
                         $scope.totalItems     = response.success.result.totalItems;
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
  $scope.pageChanged       = function () {
    reload();
  }
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
    openEditModal( false );
  };
  $scope.openAddModal      = function () {
    openEditModal( true );
  };
  function openEditModal( isNew ) {
    // open modal
    $uibModal.open(
      {
        animation   : true,
        templateUrl : 'view/editView?appObj=' + $scope.grid.appObj,
        controller  : 'GridEditController',
        controllerAs: 'vm',
        backdrop    : 'static',
        resolve     : {
          payload: { appObj: $scope.grid.appObj, itemId: isNew ? null : $scope.selectedItem.id, isNew: isNew }
        }
      }
    ).result.then( function ( updatedItem ) {
                     reload();
                   }, function () {
                   } );
  }

  $scope.openOneToManyModal       = function () {
    // open modal
    $uibModal.open(
      {
        animation   : true,
        templateUrl : 'view/referenceView?gridId=' + $scope.searchGridId,
        controller  : 'ReferenceListModalController',
        controllerAs: 'vm',
        backdrop    : 'static',
        resolve     : {
          payload: { parentGridId: $scope.grid.id, parentItemId: $scope.parentItemId }
        }
      }
    ).result.then( function ( selectedItem ) {
                     reload();
                   }, function () {
                   } );
  };
  $scope.openDeleteModal          = function () {
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
              return CoreService.deleteItem( { gridId: $scope.grid.id, itemId: $scope.selectedItem.id } ).$promise;
            }
          }
        }
      }
    ).result.then( function () {
                     reload();
                   }, function () {
                   } );
  };
  $scope.openDeleteOneToManyModal = function () {
    var selectedItemId;
    if ( $scope.grid.fieldName ) {
      selectedItemId = $scope.selectedItem[ $scope.grid.fieldName ][ 'id' ];
    }
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
            return "Are you sure, you want to delete item " + selectedItemId
          },
          onConfirm: function () {
            return function () {
              return CoreService.deleteOneToManyItem( { gridId: $scope.grid.id, parentItemId: $scope.parentItemId, itemId: selectedItemId } ).$promise;
            }
          }
        }
      }
    ).result.then( function () {
                     reload();
                   }, function () {
                   } );
  };
  $scope.setOrderBy               = function ( fieldName ) {
    if ( fieldName == $scope.orderBy ) {
      $scope.orderAsc = !$scope.orderAsc;
    } else {
      $scope.orderAsc = true;
    }
    $scope.orderBy = fieldName;
    $scope.items   = _.orderBy( $scope.items, [ $scope.orderBy ], [ $scope.orderAsc ? 'asc' : 'desc' ] );
  };
  vm.getField                     = function ( fieldName ) {
    return _.find( $scope.grid.fields, { fieldName: fieldName } );
  };
  $scope.formatItemValue          = function ( item, field ) {
    var parts = _.split( field.fieldName, '.' );
    if ( parts.length == 1 ) {
      return item[ field.fieldName ];
    } else if ( parts.length == 2 ) {
      return item[ parts[ 0 ] ][ parts[ 1 ] ];
    }
  };
  vm.getHeaderClass               = function ( fieldName ) {
    if ( fieldName == $scope.orderBy ) {
      return $scope.orderAsc ? 'asc' : 'desc';
    }
    return null;
  };
  $scope.clearFilter              = function () {
    $scope.filter.item = {};
    reload();
  };
  $timeout( init, 0 );
};
