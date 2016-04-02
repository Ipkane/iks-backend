define([
  "../../dojo/_base/declare",
  "dojo/Evented",
  "app/Utils",
  "dojo/_base/lang",
  "dojo/on",
  "dijit/_WidgetBase",
  "dojo/dom-style",
  "ready!"
], function (declare, Evented, Utils, lang, on, _WidgetBase, domStyle) {
  return declare("app.grid.FilterPanel", [_WidgetBase, Evented], {
    searchButton: null,
    clearButton: null,
    form: null,
    visible: true,
    postCreate: function () {
      this.inherited(arguments);
      var self = this;
      this.set('visible', false);
    },
    startup: function () {
      this.inherited(arguments);
      var self = this;
      this.searchButton = Utils.getWidget('.searchButton', self.domNode);
      this.clearButton = Utils.getWidget('.clearButton', self.domNode);
      this.form = Utils.getWidget('.form', self.domNode);
      this.own(
          this.searchButton.on("click", lang.hitch(self, self.search)),
          this.clearButton.on("click", lang.hitch(self, self.clear))
      );
    },
    search: function (event) {
      var values = this.form.get('value');
      var newValues = {};
      _.forOwn(values, function (value, key) {
        if (_.isNil(value)) {
          return;
        }
        if (value === false) {
          // ignore false values
          return;
        }
        if (_.isString(value)) {
          value = _.trim(value);
          if (value.length > 0) {
            newValues[key] = value;
          }

        } else {
          newValues[key] = value;
        }
      });
      this.emit("search", {
        filter: newValues
      });
    },
    clear: function () {
      this.form.reset();
      this.emit("search", {
        filter: {}
      });
    },
    _setVisibleAttr: function (value) {
      this.inherited(arguments);
      domStyle.set(this.domNode, "display", value ? "block" : "none");
    }
  });
});