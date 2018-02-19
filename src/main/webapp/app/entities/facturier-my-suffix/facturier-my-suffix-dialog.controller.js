(function() {
    'use strict';

    angular
        .module('hospitalApp')
        .controller('FacturierMySuffixDialogController', FacturierMySuffixDialogController);

    FacturierMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Facturier', 'Facture'];

    function FacturierMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Facturier, Facture) {
        var vm = this;

        vm.facturier = entity;
        vm.clear = clear;
        vm.save = save;
        vm.factures = Facture.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.facturier.id !== null) {
                Facturier.update(vm.facturier, onSaveSuccess, onSaveError);
            } else {
                Facturier.save(vm.facturier, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('hospitalApp:facturierUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
