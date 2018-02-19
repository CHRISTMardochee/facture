(function() {
    'use strict';

    angular
        .module('hospitalApp')
        .controller('FactureMySuffixDetailController', FactureMySuffixDetailController);

    FactureMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Facture', 'Facturier'];

    function FactureMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Facture, Facturier) {
        var vm = this;

        vm.facture = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('hospitalApp:factureUpdate', function(event, result) {
            vm.facture = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
