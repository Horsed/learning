var NTT = (function() {

  var store = [ {
    id: 1,
    title : '1',
    answers: [ {
      text: 'bullshit'
    } ],
    tags : [ {
      title : 'java'
    } ]
  }, {
    id: 2,
    title : '2',
    answers: [],
    tags : [ {
      title : 'javascript'
    } ]
  }, {
    id: 3,
    title : '3',
    answers: [],
    tags : [ {
      title : 'javascript'
    } ],
  } ];
  
  function emptyThing() {
    return {
      id: -1,
      title : '',
      answers: [],
      tags : []
    };
  }
  
  return {
    ractives: {
      form: new Ractive({
        debug : true,
        el : 'form',
        template : '#form-tmpl',
        data : {
          thing: emptyThing(),
          NTT: NTT
        },
        complete: function() {
          this.on({
            "add thing": function(event) {
              var thing = this.get('thing');
              thing.id = 4;
              store.push(thing);
            },
            "clear new thing": function(event) {
              this.set('thing', emptyThing());
            }
          });
        }
      }),
      things: new Ractive({
        debug : true,
        el : 'things',
        template : '#things-tmpl',
        data : {
          things : store,
          NTT: NTT
        }
      }),
      thing: new Ractive({
        debug : true,
        el : 'thing',
        template : '#thing-tmpl',
        data : {
          thing : emptyThing()
        }
      }),
      tag: new Ractive({
        debug : true,
        el : 'tag',
        template : '#tag-tmpl',
        data : {
          tag: {
            title : ''
          }
        }
      })
    },
    showThings: function() {
      this.clearView();
      this.ractives.things.set('things', store);
    },
    showThing: function(id) {
      this.clearView();
      this.ractives.thing.set('thing', store[id-1]);
    },
    showTag: function(title) {
      this.clearView();
      this.ractives.tag.set('tag', {
        title : title
      });
    },
    clearView: function() {
      this.ractives.things.set('things', []);
      this.ractives.thing.set('thing', emptyThing());
      this.ractives.tag.set('tag', { title : '' });
    }
  };
  
}(NTT));