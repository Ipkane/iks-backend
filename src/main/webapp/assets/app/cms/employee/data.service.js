'use strict';
angular.module( 'app.cms' ) //
  .factory( 'EmployeeService', EmployeeService ) //
;
function EmployeeService( $resource, ApiService ) {
  var service = $resource( '/app/employee/:action', {}, {

  } );
  return service;
}