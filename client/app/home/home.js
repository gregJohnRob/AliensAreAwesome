(function(){
  angular.$inject = ['homeService', '$location']
  angular.module('app').controller('homeController', homeController);
  function homeController(homeService ,$location){
    var vm = this;
    vm.login = login

    function login(){
      homeService.login().save({username:vm.name}, function(userid){
        vm.userid = userid;
        var view = "/gamelist/" + userid
        $location.path(view);
      })
      //temp
      var view = "/gamelist/" + 1
      $location.path(view);

    }
  }
})()
