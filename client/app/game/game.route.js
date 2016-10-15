(function() {
    'use strict';

    angular
    .module('app')
    .config(function ($routeProvider) {
      $routeProvider.when('/game/:gameid/:userid',{
        templateUrl:'app/game/game.html',
        controller: 'gameController',
        controllerAs: 'vm'
      })
    })
})();
