(function() {
    'use strict';

    angular
        .module('hospitalApp')
        .controller('FactureMySuffixController', FactureMySuffixController);

    FactureMySuffixController.$inject = ['Facture'];

    function FactureMySuffixController(Facture) {

        var vm = this;

        vm.factures = [];

        loadAll();

        function loadAll() {
            Facture.query(function(result) {
                vm.factures = result;
                vm.searchQuery = null;
            });
        }
    }
})();
