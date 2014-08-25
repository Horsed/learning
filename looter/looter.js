var Rx = require('rx')
  , moment = require('moment')
  , inquirer = require('inquirer')
  , _ = require('lodash')
  , ui = new inquirer.ui.BottomBar()
  , TICK = 2000;

function sinPrice(x, min, max) { return Math.floor((Math.sin(x) + 1) / 2 * (max - min) + min); }

function add(x, d) { return x + d; }

function time() { return moment().format('DD.MM.YYYY hh:mm:ss'); }

var randomPriceSource = Rx.Observable.generateWithRelativeTime(
  _.random(900, 1500),
  _.constant(true),
  _.constant(0),
  _.partial(_.random, 900, 1500, false),
  _.constant(TICK));

var sinPriceSource = Rx.Observable.generateWithRelativeTime(
  sinPrice(_.random(1, 3), 1501, 900),
  _.constant(true),
  _.partial(add, 0.1),
  _.partialRight(sinPrice, 900, 1501),
  _.constant(TICK));

var timestampSource = Rx.Observable.interval(100).map(time);

randomPriceSource

sinPriceSource
.map(function(value) {
  return ''+value;
})
.subscribe(function(value) {
  ui.updateBottomBar(value);
})

timestampSource
