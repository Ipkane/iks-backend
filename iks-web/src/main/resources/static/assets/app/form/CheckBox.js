define( [
  "dojo/_base/declare",
  "dijit/form/CheckBox",
  "ready!"
], function ( declare, CheckBox ) {
  return declare( 'app.form.CheckBox', [CheckBox], {
    _getSubmitValue: function(/*String*/ value){
      return (value == null || value === "") ? false : true;
    }
  } );
} );