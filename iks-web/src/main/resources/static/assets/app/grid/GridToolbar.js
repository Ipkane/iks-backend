define([
  "../../dojo/_base/declare",
  "dojo/Evented",
  "app/Utils",
  "dojo/_base/lang",
  "dojo/on",
  "dijit/Toolbar",
  "ready!"
], function (declare, Evented, Utils, lang, on, Toolbar) {
  return declare("app.grid.GridToolbar", [Toolbar, Evented], {
    newButton: null,
    editButton: null,
    deleteButton: null,
    filterButton: null,
    refreshButton: null,
    postCreate: function() {
      this.inherited(arguments);
    },
    startup: function () {
      this.inherited(arguments);
      var self = this;
      this.newButton = Utils.getWidget('.newButton', self.domNode);
      this.editButton = Utils.getWidget('.editButton', self.domNode);
      this.deleteButton = Utils.getWidget('.deleteButton', self.domNode);
      this.filterButton = Utils.getWidget('.filterButton', self.domNode);
      this.refreshButton = Utils.getWidget('.refreshButton', self.domNode);
    }
  });
});