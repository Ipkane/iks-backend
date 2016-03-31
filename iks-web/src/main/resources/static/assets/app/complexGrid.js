define( [
  "dojo/_base/declare", "dojo/dom-construct",
  "dijit/layout/ContentPane",
  "dgrid/Grid",
  "ready!"
], function ( declare, domConstruct, ContentPane, Grid ) {
  return declare( "app.ComplexGrid", [ ContentPane ], {
    postCreate: function () {
      var data = [
        { first: 'Bob', last: 'Barker', age: 89 },
        { first: 'Vanna', last: 'White', age: 55 },
        { first: 'Pat', last: 'Sajak', age: 65 }
      ];
      var grid = new Grid( {
        columns: {
          first: 'First Name',
          last : 'Last Name',
          age  : 'Age'
        }
      } );
      grid.renderArray( data );
      this.set( 'content', grid );
      this.inherited( arguments );
    }
  } );
} );