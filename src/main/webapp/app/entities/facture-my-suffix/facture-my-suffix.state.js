(function() {
    'use strict';

    angular
        .module('hospitalApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('facture-my-suffix', {
            parent: 'entity',
            url: '/facture-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'hospitalApp.facture.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/facture-my-suffix/facturesmySuffix.html',
                    controller: 'FactureMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('facture');
                    $translatePartialLoader.addPart('regle');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('facture-my-suffix-detail', {
            parent: 'facture-my-suffix',
            url: '/facture-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'hospitalApp.facture.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/facture-my-suffix/facture-my-suffix-detail.html',
                    controller: 'FactureMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('facture');
                    $translatePartialLoader.addPart('regle');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Facture', function($stateParams, Facture) {
                    return Facture.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'facture-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('facture-my-suffix-detail.edit', {
            parent: 'facture-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/facture-my-suffix/facture-my-suffix-dialog.html',
                    controller: 'FactureMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Facture', function(Facture) {
                            return Facture.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('facture-my-suffix.new', {
            parent: 'facture-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/facture-my-suffix/facture-my-suffix-dialog.html',
                    controller: 'FactureMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                numero: null,
                                type: null,
                                date: null,
                                montant: null,
                                regler: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('facture-my-suffix', null, { reload: 'facture-my-suffix' });
                }, function() {
                    $state.go('facture-my-suffix');
                });
            }]
        })
        .state('facture-my-suffix.edit', {
            parent: 'facture-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/facture-my-suffix/facture-my-suffix-dialog.html',
                    controller: 'FactureMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Facture', function(Facture) {
                            return Facture.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('facture-my-suffix', null, { reload: 'facture-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('facture-my-suffix.delete', {
            parent: 'facture-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/facture-my-suffix/facture-my-suffix-delete-dialog.html',
                    controller: 'FactureMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Facture', function(Facture) {
                            return Facture.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('facture-my-suffix', null, { reload: 'facture-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
