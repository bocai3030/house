'use strict';
angular.module('adminApp', ['LocalStorageModule', 'eehNavigation', 'ngResource', 'ngCookies', 'ngCacheBuster', 'ui.bootstrap', 'ui.router', 'angular-loading-bar', 'toasty', 'config', 'angularUtils.directives.dirPagination','datetimepicker'])
    .run(function ($rootScope, $location, $window, $http, $state,ENV) {
        $rootScope.ENV = ENV;
        $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
            $rootScope.toState = toState;
            $rootScope.toStateParams = toStateParams;
        });
        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            var curTitle = 'HOUSE管理后台';
            $rootScope.previousStateName = fromState.name;
            $rootScope.previousStateParams = fromParams;
          /*  if (toState.access.pageTitle) {
                curTitle = toState.access.pageTitle;
            }
            $window.document.title = curTitle;*/
        });
        $rootScope.back = function () {
            if ($rootScope.previousStateName === 'activate' || $state.get($rootScope.previousStateName) === null) {
                $state.go('home');
            } else {
                $state.go($rootScope.previousStateName, $rootScope.previousStateParams);
            }
        };
    }).config(function ($stateProvider, $urlRouterProvider, $httpProvider, eehNavigationProvider, httpRequestInterceptorCacheBusterProvider) {
        httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api.*/], true);
        $urlRouterProvider.otherwise('/');
        $stateProvider
            .state('site', {
                abstract: true,
                url: '/',
                templateUrl: 'views/main.html',
                controller: 'MainCtrl'
            });
        eehNavigationProvider.menuItem('foo.user', {
                iconClass: 'fa-user',
                text: "me",
                click: function () {
                }
            });
    });