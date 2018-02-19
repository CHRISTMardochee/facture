(function() {
    'use strict';

    angular
        .module('hospitalApp')
        .controller('FactureMySuffixDeleteController',FactureMySuffixDeleteController);

    FactureMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'Facture'];

    function FactureMySuffixDeleteController($uibModalInstance, entity, Facture) {
        var vm = this;

        vm.facture = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Facture.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
