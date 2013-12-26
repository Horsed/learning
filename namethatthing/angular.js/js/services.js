angular.module('thingsServices', ['ngResource']).
  factory('Things', function($resource) {
    return $resource('things/all.json', {}, {
      query: {method:'GET', isArray:true}
    });
  }).
  factory('ThingsByTag', function($resource) {
    return $resource('things/byTag/:tagTitle.json', {}, {
      query: {method:'GET', isArray:true}
    });
  }).
  factory('Thing', function($resource) {
    return $resource('things/:thingId.json', {}, {
      query: {method:'GET', isArray:false}
    });
  });

angular.module('things', ['thingsServices']).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.
      when('/', {templateUrl: 'partials/things.html',   controller: ThingsCtrl}).
      when('/things/bytag/:tagTitle', {templateUrl: 'partials/things.html', controller: ThingsByTagCtrl}).
      when('/things/:thingId', {templateUrl: 'partials/thing.html', controller: ThingCtrl}).
      otherwise({redirectTo: '/'});
  }]);