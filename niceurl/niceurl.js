exports.start = function(app, contextPath, basePath) {
  var fs = require('fs'),
      express = require('express'),
      Handlebars = require('handlebars'),
      dao = require(basePath + '/niceurl-inmemory-dao').niceurlDao;
  
  app.use(express.bodyParser());
  
  app.get(contextPath, function(req, res) {
    res.set('Content-Type', 'text/html');
    getListOfMappings(function(data) {
      res.send(data);
    });
  });
  
  app.get(contextPath + '/:name', function(req, res) {
    res.redirect(301, dao.get(req.params.name));
  });
  
  app.post(contextPath, function(req, res) {
    dao.put(req.body.name, req.body.url);
  
    res.set('Content-Type', 'text/html');
    getListOfMappings(function(data) {
      res.send(data);
    });
  });
  
  function getListOfMappings(callback) {
    fs.readFile(basePath + "/template.html", 'utf8', function(err, data) {
      if (err) throw err;
      var content = Handlebars.compile(data)({"mappings": dao.getAll()});
      callback(content);
    });
  }
}