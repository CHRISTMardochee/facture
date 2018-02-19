(function() {
    'use strict';

    angular
        .module('hospitalApp')
        .controller('FacturierMySuffixController', FacturierMySuffixController);

    FacturierMySuffixController.$inject = ['Facturier'];

    function FacturierMySuffixController(Facturier) {

        var vm = this;

        vm.facturiers = [];

        loadAll();

        function loadAll() {
            Facturier.query(function(result) {
                vm.facturiers = result;
                vm.searchQuery = null;
            });
        }
    }
})();
