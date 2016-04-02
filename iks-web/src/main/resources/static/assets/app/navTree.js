define( [
  "dojo/_base/declare", "dojo/dom", "dijit/registry", "dojo/hash", "dojo/topic",
  "dojo/store/Memory", "dojo/store/Observable",
  "dijit/tree/ObjectStoreModel", "dojo/request", "dojo/_base/array", "dijit/Tree",
  "ready!"
], function ( declare, dom, registry, hash, topic, Memory, Observable, ObjectStoreModel, request, arrayUtil, Tree ) {
  return declare( "app.NavTree", [ Tree ], {
    showRoot      : false,
    store         : null,
    _pageContents : {},
    postCreate    : function () {
      var self    = this;
      self.store  = new Memory( {
        data       : [ { id: 'root' } ],
        getChildren: function ( object ) {
          return this.query( { parent: object.id } );
        }
      } );
      self.store  = new Observable( self.store );
      var myModel = new ObjectStoreModel( {
        store: self.store,
        query: { id: 'root' }
      } );
      this.set( 'model', myModel );
      this.inherited( arguments );
      topic.subscribe( "/dojo/hashchange", function ( hash ) {
        self.loadPage( hash );
      } );
      this._loadItems();
    }
    , _loadItems  : function () {
      var self = this;
      request.get( "api/core/getNavData", {
        handleAs: "json"
      } ).then( function ( response ) {
        if ( response.isSuccess ) {
          // Create test store, adding the getChildren() method required by ObjectStoreModel
          arrayUtil.forEach( response.success.navItems, function ( item ) {
            self.store.add( item );
          } );
        }
      } );
    }
    , getIconClass: function ( /*dojo.store.Item*/ item, /*Boolean*/ opened ) {
      return item.directory ? (opened ? "dijitFolderOpened" : "dijitFolderClosed") : "dijitLeaf";
    }
    , onClick     : function ( item ) {
      if ( !item.directory ) {
        //hash( item.url );
        this.loadPage( item.url );
      }
    },
    loadPage      : function ( url ) {
      // todo show loading. cancel request if switch page
      var content;
      var self = this;
      if (self._pageContents[url]) {
        content = this._pageContents[url];
        registry.byId( 'mainContent' ).set( 'content', content );
      } else {
        request.get( url, {
          handleAs: "text"
        } ).then( function ( content ) {
          self._pageContents[url] = content;
          registry.byId( 'mainContent' ).set( 'content', content );
        } );
      }
    }
  } );
} );