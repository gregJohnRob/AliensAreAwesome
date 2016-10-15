(function(){
  angular.$inject = ['$routeParams', 'gamelistService']
  angular.module('app').controller('gamelistController', gamelistController);
  function gamelistController($routeParams, gamelistService){
    var vm = this;
    vm. joingame = joingame;
    activate();


    function activate(){
      vm.userid = $routeParams.userid;
      vm.mygames = gamelistService.mygames(vm.userid);
      vm.allgames = gamelistService.allgames()
    }

    function joingame(id){
      var view = "/game/"+ id;
      $location.path(view);
    }
  }
})()
