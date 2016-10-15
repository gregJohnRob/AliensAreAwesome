(function(){
  angular.$inject = ['$routeParams', 'gamelistService']
  angular.module('app').controller('gamelistController', gamelistController);
  function gamelistController($routeParams, gamelistService){
    var vm = this;
    vm.joingame = joingame;
    vm.create = create;
    activate();


    function activate(){
      vm.userid = $routeParams.userid;
      vm.mygames = gamelistService.mygames(vm.userid);
      gamelistService.allgames().$promise.then(function(promise) {
        vm.allgames = promise.Data.Entries;
        console.log(promise);
      });
      console.log(vm.mygames);
      console.log(vm.allgames)
    }

    function joingame(id){
      var view = "/game/"+ id + "/" + vm.userid;
      $location.path(view);
    }

    function create(){
      gamelistService.create().then(function(id){
        joingame(id);
      })
    }
  }
})()
