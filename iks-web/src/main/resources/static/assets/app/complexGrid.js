define( [
  "dojo/_base/declare",
  "dojo/Evented",
  "dojo/_base/lang",
  "dojo/json",
  "dojo/query",
  "dojo/on",
  "dijit/registry",
  "dojo/dom-construct",
  "app/Utils",
  "dijit/_WidgetBase",
  "dgrid/Selection",
  "dgrid/Keyboard",
  "dgrid/OnDemandGrid",
  'dgrid/extensions/ColumnResizer',
  'dgrid/extensions/ColumnHider',
  "app/GridStore",
  "ready!"
], function ( declare, Evented, lang, JSON, query, on, registry, domConstruct, Utils, _WidgetBase, Selection, Keyboard, OnDemandGrid, ColumnResizer, ColumnHider, GridStore ) {
  return declare( "app.ComplexGrid", [ _WidgetBase, Evented ], {
    grid             : null,
    gridStore: null,
    gridString       : null,
    filterPanel      : null,
    postCreate       : function () {
      this.inherited( arguments );
      var self  = this;
      var grid  = this._buildGrid();
      this.grid = grid;
    },
    startup          : function () {
      this.inherited( arguments );
      var gridNode     = query( '.grid', this.domNode )[ 0 ];
      domConstruct.place( this.grid.domNode, gridNode );
      this.filterPanel = Utils.getWidget( '.appFilterPanel', this.domNode );
      if ( this.filterPanel ) {
        this.filterPanel.on( "search", lang.hitch( this, this._onFilter ) );
      }
      this.grid.startup();
    },
    _buildGrid       : function () {
      var self        = this;
      this.gridStore       = new GridStore( {
        target: 'api/core/getGridData?gridId=' + self.get( 'id' )
      } );
      var gridOptions = parseGridString( this.gridString );
      var CustomGrid  = declare( [ OnDemandGrid, Keyboard, Selection, ColumnResizer, ColumnHider ] );
      var grid        = new CustomGrid( {
        columns       : gridOptions.columns,
        //className     : 'dgrid-autoheight',
        collection    : this.gridStore,
        selectionMode : 'single',
        cellNavigation: false,
        loadingMessage: 'Loading data...',
        noDataMessage : 'No results found.'
      } );
      this._handleGridEvents( grid );
      return grid;
    },
    _onFilter        : function ( e ) {
      console.log( e );
      this.grid.set( 'collection', this.gridStore.filter( e.filter) );
    },

    _handleGridEvents: function ( grid ) {
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
    _onRowSelect     : function ( event ) {
      //console.log( 'Row selected: ' );
      //console.log( event );
    },
    _onRowDeselect   : function ( event ) {
      //console.log( 'Row de-selected: ' );
      //console.log( event );
    }
  } );
  function parseGridString( gridString ) {
    var gridOptions = JSON.parse( gridString );
    var columns     = [];
    _.forEach( gridOptions.columns, function ( column ) {
      if ( column.fieldName == 'id' ) {
        return;
      }
      columns.push( {
        field: column.fieldName,
        label: column.label
      } );
    } );
    return {
      columns: columns
    }
  }
} );