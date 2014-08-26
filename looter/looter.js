var Rx = require('rx')
  , moment = require('moment')
  , _ = require('lodash')
  , TICK = 2000;

function sinPrice(min, max, x) { ;return Math.floor((Math.sin(x) + 1) / 2 * (max - min) + min); }

function add(x, d) { return x + d; }

function time() { return moment().format('DD.MM.YYYY hh:mm:ss'); }

var gameLoop
  = exports.gameLoop
  = Rx.Observable.interval(1000)

var timestampSource
  = exports.timestampSource
  = gameLoop
    .map(time)

var randomPriceSource
  = exports.randomPriceSource
  = gameLoop
    .map(_.partial(_.random, 900, 3500, false))

var sinPriceSource
  = exports.sinPriceSource
  = gameLoop
    .map(function(time) { return time / 10; })
    .map(_.partial(sinPrice, 900, 3501))

var balance
  = exports.balance
  = sinPriceSource
  .combineLatest(
    randomPriceSource,
    sinPriceSource,
    function(r, s) { return r + s; })

var randomGraph
  = exports.randomGraph
  = Rx.Observable.combineLatest(
    gameLoop,
    randomPriceSource,
    function(time, price) { return {time: time*10, price: price / 100}; })

var sinusGraph
  = exports.sinusGraph
  = Rx.Observable.combineLatest(
    gameLoop,
    sinPriceSource,
    function(time, price) { return {time: time, price: price / 100}; })

var balanceGraph
  = exports.balanceGraph
  = Rx.Observable.combineLatest(
    gameLoop,
    balance,
    function(time, balance) { return {time: time, balance: balance / 100}; })