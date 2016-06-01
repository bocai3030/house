'use strict';
angular.module('adminApp').config(function ($stateProvider) {
    $stateProvider.state('site.house', {
        url: 'house',
        templateUrl: 'views/query/house.html',
        controller: 'HouseController'
    });
}).controller('HouseController', function ($scope, toasty, HouseService, $http) {
    $scope.title = '房产查询';

    $scope.fieldStates = {
        projectId: true,
        projectName: true,
        preSellLicenseId: true,
        createTime: true,
        updateTime: true
    };
    $scope.projectData = null;
    $scope.queryParamsByProjectId = {
        projectId: null
    };
    $scope.queryParamsByProjectNameLike = {
        projectNameLike: null
    };

    $scope.getProjectDataByProjectId = function () {
        if (!$scope.querying) {
            $scope.querying = true;
            HouseService.getProjectDataByProjectId($scope.queryParamsByProjectId).$promise.then(function (data) {
                $scope.querying = false;
                $scope.projectData = data;
            }, function (data) {
                $scope.querying = false;
                toasty.pop.error({
                    title: '操作失败',
                    msg: '对不起，查询失败，请重试！',
                    sound: true
                });
            });
        }
    };
    $scope.getProjectDataByProjectNameLike = function () {
        if (!$scope.querying) {
            $scope.querying = true;
            HouseService.getProjectDataByProjectNameLike($scope.queryParamsByProjectNameLike).$promise.then(function (data) {
                $scope.querying = false;
                $scope.projectData = data;
            }, function (data) {
                $scope.querying = false;
                toasty.pop.error({
                    title: '操作失败',
                    msg: '对不起，查询失败，请重试！',
                    sound: true
                });
            });
        }
    };

});
