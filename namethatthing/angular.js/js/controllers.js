var NTT = {things:[]};

function NewThingCtrl($scope) {
  $scope.thing = {id: -1, title : '', answers: [], tags : []};
  $scope.saveThing = function() {
    NTT.things.push($scope.thing);
  };
}

function ThingsCtrl($scope, Things) {
  NTT.things = Things.query();
  $scope.things = NTT.things;
}

function ThingsByTagCtrl($scope, $routeParams, ThingsByTag) {
  $scope.things = ThingsByTag.query({tagTitle: $routeParams.tagTitle});
}

function ThingCtrl($scope, $routeParams, Thing) {
  $scope.thing = Thing.get({thingId: $routeParams.thingId});
}