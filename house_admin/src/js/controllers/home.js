'use strict';
angular.module('adminApp').config(function ($stateProvider) {
    $stateProvider.state('home', {
        parent: 'site',
        url: '',
        templateUrl: 'views/home.html'
    });
});
