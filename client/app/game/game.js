(function(){
  angular.$inject = ['$routeParams', '$location', 'gameService']
  angular.module('app').controller('gameController', gameController);

  function gameController('$routeParams', '$location', 'gameService'){
    var vm = this;

    activate();

    function activate(){


    }
  }
})
