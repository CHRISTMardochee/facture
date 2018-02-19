(function() {
    'use strict';

    angular
        .module('hospitalApp')
        .controller('FacturierMySuffixDetailController', FacturierMySuffixDetailController);

    FacturierMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Facturier', 'Facture'];

    function FacturierMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Facturier, Facture) {
        var vm = this;

        vm.facturier = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('hospitalApp:facturierUpdate', function(event, result) {
            vm.facturier = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
