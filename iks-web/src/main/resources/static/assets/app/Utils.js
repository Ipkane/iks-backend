define( [
  "dojo/_base/declare",
  "dojo/_base/lang",
  "dojo/query",
  "dojo/on",
  "dijit/registry",
  "dojo/dom-construct",
  "ready!"
], function ( declare, lang, query, on, registry, domConstruct, _WidgetBase ) {
  return {
    getWidget: function ( queryString, domNode ) {
      var node = query( queryString, domNode )[ 0 ];
      return node ? registry.byNode( node ) : null;
    }
  };
} );