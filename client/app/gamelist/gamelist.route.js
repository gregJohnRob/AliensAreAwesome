(function() {
    'use strict';

    angular
    .module('app')
    .config(function ($routeProvider) {
      $routeProvider.when('/gamelist/:userid',{
        templateUrl:'app/gamelist/gamelist.html',
        controller: 'gamelistController',
        controllerAs: 'vm'
      })
    })
})();
