var fs = require('fs'),
    express = require('express'),
    Handlebars = require('handlebars'),
    dao = require('./niceurl-inmemory-dao').niceurlDao,
    app = express();

app.use(express.bodyParser());

app.get('/', function(req, res) {
  res.set('Content-Type', 'text/html');
  getListOfMappings(function(data) {
    res.send(data);
  });
});

app.get('/:name', function(req, res) {
  res.redirect(301, dao.get(req.params.name));
});

app.post('/', function(req, res) {
  dao.put(req.body.name, req.body.url);

  res.set('Content-Type', 'text/html');
  getListOfMappings(function(data) {
    res.send(data);
  });
});

app.listen(8080);
console.log('Niceurl server started on port 8080');

function getListOfMappings(callback) {
  var source = fs.readFile("./template.html", 'utf8', function(err, data) {
    if (err) throw err;
    var content = Handlebars.compile(data)({"mappings": dao.getAll()});
    callback(content);
  });
}