define([
  "../../dojo/_base/declare",
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
  "app/grid/GridStore",
  "ready!"
], function (declare, Evented, lang, JSON, query, on, registry, domConstruct, Utils, _WidgetBase, Selection, Keyboard, OnDemandGrid, ColumnResizer, ColumnHider, GridStore) {
  return declare("app.grid.ComplexGrid", [_WidgetBase, Evented], {
    grid: null,
    gridStore: null,
    gridString: null,
    filterPanel: null,
    toolbar: null,
    postCreate: function () {
      this.inherited(arguments);
      var self = this;
      var grid = this._buildGrid();
      this.grid = grid;
    },
    startup: function () {
      this.inherited(arguments);
      var gridNode = query('.grid', this.domNode)[0];
      this.grid.startup();
      domConstruct.place(this.grid.domNode, gridNode);
      setTimeout(lang.hitch(this, this._init), 0);
    },
    _init: function () {
      this.filterPanel = Utils.getWidget('.appFilterPanel', this.domNode);
      this.toolbar = Utils.getWidget('#' + this.id + '_toolbar', this.domNode);
      if (this.filterPanel) {
        this.filterPanel.on("search", lang.hitch(this, this._onFilter));
      }
      if (this.toolbar) {
        if (this.toolbar.newButton) {
          this.toolbar.newButton.on("click", lang.hitch(this, this.openNewDialog));
        }
        if (this.toolbar.editButton) {
          this.toolbar.editButton.on("click", lang.hitch(this, this.openEditDialog));
        }
        if (this.toolbar.deleteButton) {
          this.toolbar.deleteButton.on("click", lang.hitch(this, this.openDeleteDialog));
        }
        if (this.toolbar.filterButton) {
          this.toolbar.filterButton.on("click", lang.hitch(this, this.toggleFilterPanel));
        }
        if (this.toolbar.refreshButton) {
          this.toolbar.refreshButton.on("click", lang.hitch(this, this.refresh));
        }
      }
    },
    _buildGrid: function () {
      var self = this;
      this.gridStore = new GridStore({
        target: 'api/core/getGridData?gridId=' + self.get('id')
      });
      var gridOptions = parseGridString(this.gridString);
      var CustomGrid = declare([OnDemandGrid, Keyboard, Selection, ColumnResizer, ColumnHider]);
      var grid = new CustomGrid({
        columns: gridOptions.columns,
        //className     : 'dgrid-autoheight',
        collection: this.gridStore,
        selectionMode: 'single',
        cellNavigation: false,
        loadingMessage: 'Loading data...',
        noDataMessage: 'No results found.'
      });
      this._handleGridEvents(grid);
      return grid;
    },
    _onFilter: function (e) {
      console.log(e);
      this.grid.set('collection', this.gridStore.filter(e.filter));
    },
    _handleGridEvents: function (grid) {
      var self = this;
      grid.on('dgrid-select', lang.hitch(this, self._onRowSelect));
      grid.on('dgrid-deselect', lang.hitch(this, self._onRowDeselect));
      grid.on('dgrid-error', function (event) {
        // Display an error message above the grid when an error occurs.
        //messageNode.className = 'errorMessage';
        //messageNode.innerHTML = event.error.message;
      });
      grid.on('dgrid-refresh-complete', function (event) {
        // Hide any previous error message when a refresh
        // completes successfully.
        //messageNode.className = 'errorMessage hidden';
        //messageNode.innerHTML = '';
      });
    },
    _onRowSelect: function (event) {
      //console.log( 'Row selected: ' );
      //console.log( event );
    },
    _onRowDeselect: function (event) {
      //console.log( 'Row de-selected: ' );
      //console.log( event );
    },
    toggleFilterPanel: function () {
      this.filterPanel.set('visible', this.toolbar.filterButton.get('checked'));
    },
    refresh: function() {
      this.grid.refresh();
    }
  });
  function parseGridString(gridString) {
    var gridOptions = JSON.parse(gridString);
    var columns = [];
    _.forEach(gridOptions.columns, function (column) {
      if (column.fieldName == 'id') {
        return;
      }
      columns.push({
        field: column.fieldName,
        label: column.label
      });
    });
    return {
      columns: columns
    }
  }
});