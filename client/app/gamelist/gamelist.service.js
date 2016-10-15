(function(){
  angular.$inject = ['$resource', '$location'];
  angular.module('app').factory('gamelistService', gamelistService);

  function gamelistService($resource, $location){
    var service = {
      mygames:mygames,
      allgames:allgames,
      create:create
    };

    return service;

    function mygames(id){
      return $resource('http://localhost:4567/player/game/').get({clientId: id} , function(response){
        return response;
      })
    }

    function allgames(){
      return $resource('http://localhost:4567/game/list').get({},function(response){
        return response.Data.Entries;
      })
    }

    function create(){
      return $resource('http://localhost:4567/game/create').get(function(response){
        return response.Data.Id;
      });
    }
  }
})();
