/**
 * Created by liuchanglu on 15-3-2.
 */
angular.module('adminApp').config(function ($stateProvider) {
    $stateProvider
        .state('error', {
            parent: 'site',
            url: 'error',
            templateUrl: 'views/error.html',
            access: {
                pageTitle: 'Error Page',
                authorizedRoles: []
            }
        })
        .state('denied', {
            parent: 'site',
            url: 'denied',
            templateUrl: 'views/denied.html',
            access: {
                authorizedRoles: []
            }
        })
});