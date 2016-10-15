(function(){
  angular.$inject = ['homeService']
  angular.module('app').controller('homeController', homeController);
  function homeController(homeService){
    var vm = this;
    vm.login = login

    function login(){
      console.log("i got here")
      homeService.login().save({username:vm.name}, function(userid){
        vm.userid = userid;

      })
    }
  }
})()
