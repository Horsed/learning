var dao = require('./niceurl-inmemory-dao').niceurlDao;

exports.should_save_mapping = function(test) {
  dao.clear();
  dao.put('name', 'http://www.horsed.org');
  test.equal(dao.get('name'), 'http://www.horsed.org');
  test.done();
};

exports.should_not_save_mapping_with_invalid_url = function(test) {
  dao.clear();
  dao.put('name', 'url');
  test.equal(dao.get('name'), '');
  test.done();
};

exports.should_remove_saved_mapping = function(test) {
  dao.clear();
  dao.put('name', 'http://www.horsed.org');
  dao.remove('name');
  test.equal(dao.get('name'), '');
  test.done();
};

exports.should_return_list_of_mappings = function(test) {
  dao.clear();
  dao.put('name1', 'http://www.horsed.org');
  dao.put('name2', 'http://www.horsed.org/niceurl');
  test.equal(dao.getAll()[0].name, 'name1');
  test.equal(dao.getAll()[0].url, 'http://www.horsed.org');
  test.equal(dao.getAll()[1].name, 'name2');
  test.equal(dao.getAll()[1].url, 'http://www.horsed.org/niceurl');
  test.done();
};

exports.should_clear_mappings = function(test) {
  dao.clear();
  dao.put('name1', 'http://www.horsed.org');
  dao.put('name2', 'http://www.horsed.org/niceurl');
  dao.clear();
  test.equal(dao.get('name1'), '');
  test.equal(dao.get('name2'), '');
  test.done();
};