var connect = require('connect'),
    express = require('express'),
    app = express(),
    store = require('./store.js').store;

app
  .use(connect.static(__dirname))
  .use(express.bodyParser());

app.get('/:account', function(req, res) {
  res.json(store.get(req.params.account));
});

app.post('/', function(req, res) {
  store.save(req.body.key, req.body.value);
  res.json(true);
});

app.listen(3000);