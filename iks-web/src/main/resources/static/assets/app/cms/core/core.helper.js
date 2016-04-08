(function ( ng ) {
  'use strict';
  angular.module( 'app.cms' ) //
    .factory( 'GridHelper', GridHelper ) //
    .factory( 'ModalHelper', ModalHelper ) //
    .factory( '_', LodashService ) //
  ;
  function LodashService() {
    return _;
  }

  function GridHelper( CoreService, $q, $log ) {
    var grids = {};
    return {
      getGrid    : getGrid,
      getGridData: getGridData
    };
    function getGrid( name ) {
      var deferred = $q.defer();
      if ( !grids[ name ] ) {
        CoreService.getGrid( {
          appObj: 'employee'
        }, function ( response ) {
          if ( response.isSuccess ) {
            grids[ name ] = response.success.grid;
            deferred.resolve( grids[ name ] );
          } else {
            deferred.reject( response );
          }
        }, function ( response ) {
          deferred.reject( response );
        } );
      } else {
        deferred.resolve( grids[ name ] );
      }
      return deferred.promise;
    }

    function getGridData( request ) {
      return CoreService.getGridData( request ).$promise;
    }
  }

  function ModalHelper( $uibModal ) {
    return {
      openConfirmModal: openConfirmModal,
      showErrorModal  : showErrorModal
    };
    function openConfirmModal( params ) {
      $uibModal.open(
        {
          animation   : true,
          templateUrl : 'assets/app/cms/common/templates/confirm-modal.html',
          controller  : 'ConfirmModalController',
          controllerAs: 'vm',
          backdrop    : 'static',
          resolve     : {
            title    : function () {
              return params.title
            },
            message  : function () {
              return params.message
            },
            onConfirm: function () {
              return params.onConfirm
            }
          }
        }
      ).result.then( function () {
                       if ( angular.isFunction( params.onSuccess ) ) {
                         params.onSuccess();
                       }
                     }, function () {
                       if ( angular.isFunction( params.onCancel ) ) {
                         params.onCancel();
                       }
                     } );
    }

    function showErrorModal( response ) {
      $uibModal.open(
        {
          animation   : true,
          templateUrl : 'assets/app/cms/common/templates/error-modal.html',
          controller  : 'ErrorModalController',
          controllerAs: 'vm',
          backdrop    : 'static',
          resolve     : {
            response: response
          }
        }
      );
    }
  }
})( angular );