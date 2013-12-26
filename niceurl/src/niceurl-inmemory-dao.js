var dao = {
  mappings: [],
  put: function(name, url) {
    var urlRegex = /((([A-Za-z]{3,9}:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)/;
    if(urlRegex.test(url)) this.mappings.push({name: name, url: url});
  },
  remove: function(name) {
    var newMappings = [];
    for(var i = 0, len = this.mappings.length; i < len; i++) {
      if(this.mappings[i].name != name) newMappings.push(this.mappings[i]);
    }
    this.mappings = newMappings;
  },
  get: function(name) {
    var url = '';
    for(var i = 0, len = this.mappings.length; i < len; i++) {
      if(this.mappings[i].name == name) url = this.mappings[i].url;
    }
    return url;
  },
  getAll: function() {
    return this.mappings;
  },
  clear: function() {
    this.mappings = [];
  }
};

exports.niceurlDao = dao;