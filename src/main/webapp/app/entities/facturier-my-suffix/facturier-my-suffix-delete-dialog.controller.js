(function() {
    'use strict';

    angular
        .module('hospitalApp')
        .controller('FacturierMySuffixDeleteController',FacturierMySuffixDeleteController);

    FacturierMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'Facturier'];

    function FacturierMySuffixDeleteController($uibModalInstance, entity, Facturier) {
        var vm = this;

        vm.facturier = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Facturier.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
