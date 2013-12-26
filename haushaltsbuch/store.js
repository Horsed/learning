exports.store = {
  get: function(key) {
    return key && this.map[key] ? this.map[key] : this.map;
  },
  save: function(key, value) {
    this.map[key] = value;
  },
  remove: function(key) {
    delete this.map[key];
  },
  map: {}
};