'use strict';
angular.module('adminApp').config(function ($stateProvider) {
    $stateProvider.state('site.house', {
        url: 'house',
        templateUrl: 'views/query/house.html',
        controller: 'HouseController'
    });
}).controller('HouseController', function ($scope, toasty, HouseService, $http) {
    $scope.title = '房产查询';

    $scope.fc = {
        pbd: {
            projectId: true,
            projectName: true,
            preSellLicenseId: true,
            countryName: true,
            countryId: true,
            agreeName: false,
            agreeId: false,
            layoutName: false,
            layoutId: false,
            projectAddress: true,
            developer: true,
            developerId: false,
            division: true,
            sectionId: false,
            totalCostArea: true,
            totalBuildArea: true,
            qualificationLicenceNo: false,
            usagee: true,
            createTime: false,
            updateTime: false
        },
        psld: {
            preSellLicenseId: true,
            buildingCount: true,
            buildingHouse: true,
            builtHouse: true,
            currentPhase: false,
            currentPhaseBuildingArea: false,
            areaUpGround: false,
            areaUnderGround: false,
            unitCount: true,
            totalBuidingArea: false,
            contactPersion: false,
            mortgage: true,
            supportingArea: true,
            validateFrom: false,
            validateTo: false,
            licenseIssueDate: true,
            distributeOfResidentialCount: true,
            distributeOfResidentialArea: false,
            distributeOfBussinessCount: false,
            distributeOfBussinessArea: false,
            distributeOfOfficeCount: false,
            distributeOfOfficeArea: false,
            distributeOfParkingCount: false,
            distributeOfParkingArea: false,
            distributeOfOtherCount: false,
            distributeOfOtherArea: false,
            createTime: false,
            updateTime: false
        }
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
