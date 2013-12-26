(function(NTT) {
  var Router = Backbone.Router.extend({

    routes: {
      "":            "things",
      "!thing/:id":  "thing",
      "!tag/:tag": "tag"
    },
    things: function() {
      NTT.showThings();
    },
    thing: function(id) {
      NTT.showThing(id);
    },
    tag: function(tag) {
      NTT.showTag(tag);
    }

  });
  
  new Router();
  Backbone.history.start();
}(NTT));