define( [
  "dojo/_base/declare",
  "dojo/_base/lang",
  "dojo/json",
  "dojo/query",
  "dojo/dom-construct",
  "dijit/_WidgetBase",
  "dgrid/Grid",
  "dgrid/Selection",
  "dgrid/Keyboard",
  "dgrid/OnDemandGrid",
  "app/GridStore",
  "ready!"
], function ( declare, lang, JSON, query, domConstruct, _WidgetBase, Grid, Selection, Keyboard, OnDemandGrid, GridStore ) {
  return declare( "app.ComplexGrid", [ _WidgetBase ], {
    grid          : null,
    gridString    : null,
    postCreate    : function () {
      this.inherited( arguments );
      var self        = this;
      var store       = new GridStore( {
        target: 'api/core/getGridData?gridId=' + self.get( 'id' )
      } );
      var gridOptions = parseGridString( this.gridString );
      var CustomGrid  = declare( [ OnDemandGrid, Keyboard, Selection ] );
      var grid        = new CustomGrid( {
        columns       : gridOptions.columns,
        //className     : 'dgrid-autoheight',
        collection    : store,
        selectionMode : 'single',
        cellNavigation: false,
        loadingMessage: 'Loading data...',
        noDataMessage : 'No results found.'
      } );
      this.grid       = grid;
      this._handleEvents( grid );

      var gridNode = query('.grid', this.domNode)[0];
      domConstruct.place(grid.domNode, gridNode);
    },
    startup: function() {
      this.inherited(arguments);
      this.grid.startup();
    },
    _handleEvents : function ( grid ) {
      var self = this;
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
      //console.log( 'Row selected: ' );
      //console.log( event );
    },
    _onRowDeselect: function ( event ) {
      //console.log( 'Row de-selected: ' );
      //console.log( event );
    }
  } );
  function parseGridString( gridString ) {
    var gridOptions = JSON.parse( gridString );
    var columns     = [];
    _.forEach( gridOptions.columns, function ( column ) {
      if (column.fieldName == 'id') {
        return;
      }
      columns.push( {
        field : column.fieldName,
        label: column.label
      } );
    } );
    return {
      columns: columns
    }
  }
} );