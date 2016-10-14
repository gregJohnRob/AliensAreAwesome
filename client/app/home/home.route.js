(function() {
    'use strict';

    angular
        .module('app')
        .config(function ($routeProvider) {
           $routeProvider.when('/',{
             templateUrl:'app/home/home.html'
           })} )


})();
