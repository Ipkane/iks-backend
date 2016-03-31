define( [
  "dojo/_base/declare",
  "dojo/_base/lang",
  "dojo/dom-construct",
  "dijit/layout/ContentPane",
  "dgrid/Grid",
  "dgrid/Selection",
  "dgrid/Keyboard",
  "dgrid/OnDemandGrid",
  "app/GridStore",
  "ready!"
], function ( declare, lang, domConstruct, ContentPane, Grid, Selection, Keyboard, OnDemandGrid, GridStore ) {
  return declare( "app.ComplexGrid", [ ContentPane ], {
    grid          : null,
    postCreate    : function () {
      var self  = this;
      var store = new GridStore( {
        target: 'api/core/getGridData?gridId=' + self.get( 'id' )
      } );
      //var data       = [
      //  { first: 'Bob', last: 'Barker', age: 89 },
      //  { first: 'Vanna', last: 'White', age: 55 },
      //  { first: 'Pat', last: 'Sajak', age: 65 }
      //];
      var CustomGrid = declare( [ OnDemandGrid, Keyboard, Selection ] );
      var grid       = new CustomGrid( {
        columns       : [
          { field: 'fio', label: 'First Name/Last Name' },
          { field: 'gender', label: 'Gender' }
        ],
        className     : 'dgrid-autoheight',
        collection    : store,
        selectionMode : 'single',
        cellNavigation: false,
        loadingMessage: 'Loading data...',
        noDataMessage : 'No results found.'
      } );
      //grid.renderArray( data );
      grid.startup();
      this._handleEvents( grid );
      this.set( 'content', grid );
      this.grid      = grid;
      this.inherited( arguments );
    },
    _handleEvents : function ( grid ) {
      grid.on( 'dgrid-select', lang.hitch( this, self._onRowSelect ) );
      grid.on( 'dgrid-deselect', lang.hitch( this, self._onRowDeselect ) );
      grid.on( 'dgrid-error', function ( event ) {
        // Display an error message above the grid when an error occurs.
        //messageNode.className = 'errorMessage';
        //messageNode.innerHTML = event.error.message;
      } );
      grid.on( 'dgrid-refresh-complete', function ( event ) {
        // Hide any previous error message when a refresh
        // completes successfully.
        //messageNode.className = 'errorMessage hidden';
        //messageNode.innerHTML = '';
      } );
    },
    _onRowSelect  : function ( event ) {
      // Report the item from the selected row to the console.
      console.log( 'Row selected: ' );
      console.log( event );
    },
    _onRowDeselect: function ( event ) {
      console.log( 'Row de-selected: ' );
      console.log( event );
    }
  } );
} );