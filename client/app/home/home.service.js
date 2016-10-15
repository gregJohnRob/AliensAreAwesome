(function(){
  angular.$inject = ['$resource'];
  angular.module('app').factory('homeService', homeService);

  function homeService($resource){
    var service = {login:login};

    return service;

    function login(){
      return $resource('http://localhost:4567/player/login')
    }
  }
})();
