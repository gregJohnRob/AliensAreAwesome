(function(){
  angular.$inject = ['$resource'];
  angular.module('app').factory('gamelistService', gamelistService);

  function gamelistService($resource){
    var service = {
      mygames:mygames,
      allgames:allgames
    };

    return service;

    function mygames(id){
      return $resource('player/game/:id').query({id:id} , function(responce){
        return responce;
      })
    }

    function allgames(){
      return $resource('game/list').query(function(responce){
        return responce;
      })
    }
  }
})();
