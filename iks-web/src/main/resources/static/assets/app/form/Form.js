define( [
  "dojo/_base/declare",
  "dojo/_base/array",
  "dojo/_base/lang",
  "dijit/form/Form",
  "ready!"
], function ( declare, array, lang, Form ) {
  return declare( 'app.form.Form', [ Form ], {
    _getValueAttr: function () {
      // summary:
      //		Returns Object representing form values.   See description of `value` for details.
      // description:
      // Override _CheckBoxMixin
      // checkbox return boolean
      var obj = {};
      array.forEach( this._getDescendantFormWidgets(), function ( widget ) {
        var name = widget.name;
        if ( !name || widget.disabled ) {
          return;
        }
        // Single value widget (checkbox, radio, or plain <input> type widget)
        var value = widget.get( 'value' );
        // Store widget's value(s) as a scalar, except for checkboxes which are automatically arrays
        if ( typeof widget.checked == 'boolean' ) {
          if ( /Radio/.test( widget.declaredClass ) ) {
            // radio button
            if ( value !== false ) {
              lang.setObject( name, value, obj );
            } else {
              // give radio widgets a default of null
              value = lang.getObject( name, false, obj );
              if ( value === undefined ) {
                lang.setObject( name, null, obj );
              }
            }
          } else {
            // checkbox/toggle button
            lang.setObject( name, value, obj );
          }
        } else {
          var prev = lang.getObject( name, false, obj );
          if ( typeof prev != "undefined" ) {
            if ( lang.isArray( prev ) ) {
              prev.push( value );
            } else {
              lang.setObject( name, [ prev, value ], obj );
            }
          } else {
            // unique name
            lang.setObject( name, value, obj );
          }
        }
      } );
      return obj;
    }
  } );
} );