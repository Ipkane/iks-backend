'use strict';
angular.module('app.cms')//
    .controller('AppGridController', AppGridController)
;//
function AppGridController($scope, $log, $kWindow, $timeout, $q, $rootScope, CoreService, GridHelper, ModalHelper, _) {
  var vm = this;
  angular.extend($scope, {
    filter: {
      item: {}
    },
    grid: null,
    selectedItem: null,
    referenceGridId: null, // for  reference field
    sortField: 'id',
    sortDir: 'asc',
    items: [],
    itemsPerPage: 50,
    currentPage: 1,
    parentItemId: null,
    totalItems: 0,
    gridOptions: null,
    dataSource: null
  });

  function setGridOptions(grid) {
    var columns = [];
    $scope.grid = grid;
    _.each(grid.columns, function (column) {
      columns.push({
        field: column.fieldName,
        title: column.label
      })
    });
    $scope.dataSource = new kendo.data.DataSource({
      pageSize: $scope.itemsPerPage,
      serverSorting: true,
      serverPaging: true,
      transport: {
        read: function (e) {
          var sort = e.data.sort;
          $scope.sortField = sort && sort[0] ? sort[0].field : 'id';
          $scope.sortDir = sort && sort[0] ? sort[0].dir : 'asc';
          _reload().then(function (items) {
            e.success(items);
          });
        }
      }
    });

    $scope.gridOptions = {
      sortable: true,
      selectable: true,
      scrollable: {
        virtual: true
      },
      resizable: true,
      columns: columns,
      dataSource: $scope.dataSource,
      dataBound: function (e) {
        //$log.debug($scope.dataSource);
      }
    };
  }

  function init() {
    if (angular.isString($scope.grid)) {
      var grid = angular.fromJson($scope.grid);
      setGridOptions(grid);
    }
    //reload();
  }

  function reload() {
    $scope.dataSource.read();
  }

  function _reload() {
    var deferred = $q.defer();
    if ($scope.$parent.itemId) {
      $scope.parentItemId = $scope.$parent.itemId;
    }
    CoreService.getGridData({
      gridId: $scope.grid.id, filter: $scope.filter.item, sortField: $scope.sortField, sortDir: $scope.sortDir,
      parentId: $scope.parentItemId,
      page: $scope.currentPage, limit: $scope.itemsPerPage
    }).$promise.then(
        function (response) {
          if (!response.isSuccess) {
            ModalHelper.showErrorModal(response);
            return;
          }
          $scope.items = response.success.result.items;
          $scope.totalItems = response.success.result.totalItems;
          var foundSelectedItem = false;
          if ($scope.selectedItem) {
            _.each($scope.items, function (item) {
              if (item.id == $scope.selectedItem.id) {
                $scope.selectedItem = item;
                foundSelectedItem = true;
              }
            });
            if (!foundSelectedItem) {
              $scope.selectedItem = null;
            }
          }
          deferred.resolve($scope.items);
        }).catch(function (response) {
      ModalHelper.showErrorModal(response);
      deferred.reject(response);
    });
    return deferred.promise;
  }

  $scope.pageChanged = function () {
    reload();
  };
  $scope.toggleFilterPanel = function () {
    $scope.showFilterPanel = !$scope.showFilterPanel;
  };
  $scope.search = function () {
    reload();
  };
  $scope.refresh = function () {
    reload();
  };
  $scope.itemSelected = function (data, dataItem, columns) {
    $scope.selectedItem = dataItem;
  };
  $scope.openEditModal = function () {
    openEditModal(false);
  };
  $scope.openAddModal = function () {
    openEditModal(true);
  };
  function openEditModal(isNew) {
    // open modal
    $kWindow.open(
        {
          options: {
            modal: true,
            title: 'Edit Content',
            resizable: true,
            width: $scope.grid.editView.width || 600,
            height: $scope.grid.editView.height || 400,
            visible: false
          },
          templateUrl: 'view/editView?appObj=' + $scope.grid.appObj,
          controller: 'GridEditController as vm',
          resolve: {
            payload: {appObj: $scope.grid.appObj, itemId: isNew ? -1 : $scope.selectedItem.id, isNew: isNew}
          }
        }
    ).result.then(function (updatedItem) {
      reload();
    }, function () {
    });
  }

  $scope.openOneToManyModal = function () {
    // open modal
    $kWindow.open(
        {
          options: {
            modal: true,
            title: 'Select item',
            resizable: true,
            height: 300,
            width: 600,
            visible: false
          },
          templateUrl: 'view/referenceView?gridId=' + $scope.referenceGridId,
          controller: 'ReferenceGridModalController',
          resolve: {
            payload: {parentGridId: $scope.grid.id, parentItemId: $scope.parentItemId}
          }
        }
    ).result.then(function (selectedItem) {
      CoreService.addGridItem({
        gridId: $scope.grid.id,
        parentItemId: $scope.parentItemId,
        itemId: selectedItem.id
      }, function (response) {
        if (response.isSuccess) {
          reload();
        } else {
          ModalHelper.showErrorModal(response);
        }
      }, function (response) {
        ModalHelper.showErrorModal(response);
      });
    });
  };
  $scope.openDeleteModal = function () {
    ModalHelper.openConfirmModal(
        {
          title: "Confirm deletion",
          message: "Are you sure, you want to delete item " + $scope.selectedItem.id,
          onConfirm: function () {
            return CoreService.deleteItem({gridId: $scope.grid.id, itemId: $scope.selectedItem.id}).$promise;
          },
          onSuccess: reload
        }
    );
  };
  $scope.openDeleteOneToManyModal = function () {
    var selectedItemId;
    if ($scope.grid.fieldName) {
      selectedItemId = $scope.selectedItem[$scope.grid.fieldName]['id'];
    }
    ModalHelper.openConfirmModal(
        {
          title: "Confirm deletion",
          message: "Are you sure, you want to delete item " + selectedItemId,
          onConfirm: function () {
            return CoreService.deleteOneToManyItem({
              gridId: $scope.grid.id,
              parentItemId: $scope.parentItemId,
              itemId: selectedItemId
            }).$promise;
          },
          onSuccess: reload
        }
    );
  };
  vm.getField = function (fieldName) {
    return _.find($scope.grid.fields, {fieldName: fieldName});
  };
  $scope.formatItemValue = function (item, field) {
    var parts = _.split(field.fieldName, '.');
    if (parts.length == 1) {
      return item[field.fieldName];
    } else if (parts.length == 2) {
      return item[parts[0]][parts[1]];
    }
  };
  vm.getHeaderClass = function (fieldName) {
    if (fieldName == $scope.orderBy) {
      return $scope.orderAsc ? 'asc' : 'desc';
    }
    return null;
  };
  $scope.clearFilter = function () {
    $scope.filter.item = {};
    reload();
  };
  $timeout(init, 0);
};
