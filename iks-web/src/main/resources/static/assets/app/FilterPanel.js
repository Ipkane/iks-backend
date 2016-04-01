define( [
  "dojo/_base/declare",
  "dojo/Evented",
  "app/Utils",
  "dojo/_base/lang",
  "dojo/on",
  "dijit/_WidgetBase",
  "ready!"
], function ( declare, Evented, Utils, lang, on, _WidgetBase ) {
  return declare( "app.FilterPanel", [ _WidgetBase, Evented ], {
    searchButton: null,
    clearButton : null,
    postCreate  : function () {
      this.inherited( arguments );
      var self = this;
    },
    startup     : function () {
      this.inherited( arguments );
      var self          = this;
      this.searchButton = Utils.getWidget( '.searchButton', self.domNode );
      this.clearButton  = Utils.getWidget( '.clearButton', self.domNode );
      this.own(
        this.searchButton.on("click", lang.hitch( self, self.search ) )
      );
    },
    search      : function ( event ) {
      this.emit( "search", {
        msg: 'Hello'
      } );
    }
  } );
} );