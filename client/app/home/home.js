(function(){
  angular.$inject = ['homeService', '$location']
  angular.module('app').controller('homeController', homeController);
  function homeController(homeService, $location){
    var vm = this;
    vm.login = login

    function login(){
      homeService.login().get({username:vm.name}, function(response){
        $location.path("/gamelist/" + response.Data);
      });
    }
  }
})()
