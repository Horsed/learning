var Rx = require('rx')
  , moment = require('moment')
  , _ = require('lodash')
  , TICK = 2000;

function sinPrice(x, min, max) { return Math.floor((Math.sin(x) + 1) / 2 * (max - min) + min); }

function add(x, d) { return x + d; }

function time() { return moment().format('DD.MM.YYYY hh:mm:ss'); }

exports.timestampSource = Rx.Observable.interval(100).map(time);

exports.randomPriceSource = Rx.Observable.generateWithRelativeTime(
  _.random(900, 1500),
  _.constant(true),
  _.constant(0),
  _.partial(_.random, 900, 1500, false),
  _.constant(TICK));

exports.sinPriceSource = Rx.Observable.generateWithRelativeTime(
  sinPrice(_.random(1, 3), 1501, 900),
  _.constant(true),
  _.partial(add, 0.1),
  _.partialRight(sinPrice, 900, 1501),
  _.constant(TICK));