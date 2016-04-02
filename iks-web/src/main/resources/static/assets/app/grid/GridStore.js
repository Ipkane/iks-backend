define([
  "../../dojo/_base/declare",
  "dstore/Request",
  'dojo/json',
  "ready!"
], function (declare, Request, JSON) {
  return declare('app.grid.GridStore', [Request], {
    parse: function (response) {
      var responseJson = JSON.parse(response);
      return responseJson.success.result;
    }
  });
});