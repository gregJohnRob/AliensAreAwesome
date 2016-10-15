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
      return $resource('player/game/:id').query({id:id} , function(responce){
        return responce.data;
      })
    }

    function allgames(){
      return $resource('game/list').query(function(responce){
        return responce.data;
      })
    }

    function create(){
      return $resource('game/create').get();
    }
  }
})();
