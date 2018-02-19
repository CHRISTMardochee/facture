(function() {
    'use strict';

    angular
        .module('hospitalApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('facturier-my-suffix', {
            parent: 'entity',
            url: '/facturier-my-suffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'hospitalApp.facturier.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/facturier-my-suffix/facturiersmySuffix.html',
                    controller: 'FacturierMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('facturier');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('facturier-my-suffix-detail', {
            parent: 'facturier-my-suffix',
            url: '/facturier-my-suffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'hospitalApp.facturier.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/facturier-my-suffix/facturier-my-suffix-detail.html',
                    controller: 'FacturierMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('facturier');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Facturier', function($stateParams, Facturier) {
                    return Facturier.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'facturier-my-suffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('facturier-my-suffix-detail.edit', {
            parent: 'facturier-my-suffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/facturier-my-suffix/facturier-my-suffix-dialog.html',
                    controller: 'FacturierMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Facturier', function(Facturier) {
                            return Facturier.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('facturier-my-suffix.new', {
            parent: 'facturier-my-suffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/facturier-my-suffix/facturier-my-suffix-dialog.html',
                    controller: 'FacturierMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                firstname: null,
                                streetAddress: null,
                                postalCode: null,
                                city: null,
                                stateProvince: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('facturier-my-suffix', null, { reload: 'facturier-my-suffix' });
                }, function() {
                    $state.go('facturier-my-suffix');
                });
            }]
        })
        .state('facturier-my-suffix.edit', {
            parent: 'facturier-my-suffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/facturier-my-suffix/facturier-my-suffix-dialog.html',
                    controller: 'FacturierMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Facturier', function(Facturier) {
                            return Facturier.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('facturier-my-suffix', null, { reload: 'facturier-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('facturier-my-suffix.delete', {
            parent: 'facturier-my-suffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/facturier-my-suffix/facturier-my-suffix-delete-dialog.html',
                    controller: 'FacturierMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Facturier', function(Facturier) {
                            return Facturier.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('facturier-my-suffix', null, { reload: 'facturier-my-suffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
