(function(){
  angular.$inject = ['$routeParams', 'gamelistService', '$location']
  angular.module('app').controller('gamelistController', gamelistController);
  function gamelistController($routeParams, gamelistService, $location){
    var vm = this;
    vm.joingame = joingame;
    vm.create = create;
    activate();


    function activate(){
      vm.userid = $routeParams.userid;
      vm.mygames = gamelistService.mygames(vm.userid);
      gamelistService.allgames().$promise.then(function(promise) {
        vm.allgames = promise.Data.Entries;
      });
    }

    function joingame(id){
      console.log("hi");
      var view = "game/"+ id + "/" + vm.userid;
      $location.path(view);
    }

    function create(){
      gamelistService.create().then(function(id){
        joingame(id);
      })
    }
  }
})()
