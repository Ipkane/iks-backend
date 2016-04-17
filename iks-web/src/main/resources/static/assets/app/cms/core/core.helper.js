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

  function ModalHelper( $kWindow ) {
    return {
      openConfirmModal: openConfirmModal,
      showErrorModal  : showErrorModal
    };
    function openConfirmModal( params ) {
      $kWindow.open(
        {
          options: {
            modal: true,
            width: 300,
            height: 150,
            title: params.title
          },
          templateUrl : 'assets/app/cms/common/templates/confirm-modal.html',
          controller  : 'ConfirmModalController as vm',
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
      $kWindow.open(
        {
          options: {
            modal: true,
            title: 'Error',
            resizable: true,
            height: 300,
            width: 500,
            visible: false
          },
          templateUrl : 'assets/app/cms/common/templates/error-modal.html',
          controller  : 'ErrorModalController as vm',
          resolve     : {
            response: response
          }
        }
      );
    }
  }
})( angular );