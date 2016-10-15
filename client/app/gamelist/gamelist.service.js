(function(){
  angular.$inject = ['$resource'];
  angular.module('app').factory('gamelistService', gamelistService);

  function gamelistService($resource){
    var service = {
      mygames:mygames,
      allgames:allgames,
      create:create
    };

    return service;

    function mygames(id){
      return $resource('http://localhost:4567/player/game/').get({clientId:'d703932b-c9d1-45a7-bd5f-d86a18fb24c6'} , function(response){
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
