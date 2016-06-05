'use strict';
angular.module('adminApp').config(function ($stateProvider) {
    $stateProvider.state('site.simple', {
        url: 'query/simple',
        templateUrl: 'views/query/simpleQuery.html',
        controller: 'SimpleQueryController'
    });
}).controller('SimpleQueryController', function ($scope, toasty, CommonQueryService, SimpleQueryService, $http) {
    $scope.title = '简单房产查询';

    $scope.qc = {
        byProjectId: true,
        byProjectNameLike: true,
        byPreSellLicenseId: true,
        byProjectAddressLike: false,
        byDeveloperLike: false,
        byDivision: false,
        byEarthBorrowFromBetween: true
    };
    $scope.mc = {
        pbd: true,
        psld: true,
        ebd: true
    };
    $scope.fc = {
        pt: {
            focusStatus: true
        },
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
        },
        ebd: {
            earthLicenseId: true,
            projectId: true,
            earthLicenseNo: true,
            location: false,
            userr: false,
            earthNo: false,
            graphNo: false,
            usagee: true,
            levell: false,
            borrowFrom: true,
            useRightKind: false,
            useArea: false,
            shareArea: false,
            licenseOffice: false,
            licenseIssueDate: true,
            createTime: false,
            updateTime: false
        }
    };

    $scope.projectData = null;
    $scope.tmpEarthBasicData = [];
    $scope.setTmpEarthBasicData = function () {
        $scope.tmpEarthBasicData = [];
        for(var i = 0; i < $scope.projectData.length; ++i) {
            $scope.tmpEarthBasicData = $scope.tmpEarthBasicData.concat($scope.projectData[i].earthBasicDatas);
            $scope.projectData[i].projectTag = {};
            CommonQueryService.getProjectTagByProjectId({projectId:$scope.projectData[i].projectBasicData.projectId}).$promise.then((function(index){
                return function(data) {
                    if('focusStatus' in data) {
                        $scope.projectData[index].projectTag.focusStatus = data.focusStatus;
                    }
                }
            })(i));
        }
    };

    $scope.queryParamsByProjectId = {
        projectId: null
    };
    $scope.getProjectDataByProjectId = function () {
        if (!$scope.querying) {
            $scope.querying = true;
            SimpleQueryService.getProjectDataByProjectId($scope.queryParamsByProjectId).$promise.then(function (data) {
                $scope.querying = false;
                $scope.projectData = data;
                $scope.setTmpEarthBasicData();
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

    $scope.queryParamsByProjectNameLike = {
        projectNameLike: null
    };
    $scope.getProjectDataByProjectNameLike = function () {
        if (!$scope.querying) {
            $scope.querying = true;
            SimpleQueryService.getProjectDataByProjectNameLike($scope.queryParamsByProjectNameLike).$promise.then(function (data) {
                $scope.querying = false;
                $scope.projectData = data;
                $scope.setTmpEarthBasicData();
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

    $scope.queryParamsByPreSellLicenseId = {
        preSellLicenseId: null
    };
    $scope.getProjectDataByPreSellLicenseId = function () {
        if (!$scope.querying) {
            $scope.querying = true;
            SimpleQueryService.getProjectDataByPreSellLicenseId($scope.queryParamsByPreSellLicenseId).$promise.then(function (data) {
                $scope.querying = false;
                $scope.projectData = data;
                $scope.setTmpEarthBasicData();
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

    $scope.queryParamsByProjectAddressLike = {
        projectAddressLike: null
    };
    $scope.getProjectDataByProjectAddressLike = function () {
        if (!$scope.querying) {
            $scope.querying = true;
            SimpleQueryService.getProjectDataByProjectAddressLike($scope.queryParamsByProjectAddressLike).$promise.then(function (data) {
                $scope.querying = false;
                $scope.projectData = data;
                $scope.setTmpEarthBasicData();
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

    $scope.queryParamsByDeveloperLike = {
        developerLike: null
    };
    $scope.getProjectDataByDeveloperLike = function () {
        if (!$scope.querying) {
            $scope.querying = true;
            SimpleQueryService.getProjectDataByDeveloperLike($scope.queryParamsByDeveloperLike).$promise.then(function (data) {
                $scope.querying = false;
                $scope.projectData = data;
                $scope.setTmpEarthBasicData();
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

    $scope.queryParamsByDivision = {
        division: null
    };
    $scope.getProjectDataByDivision = function () {
        if (!$scope.querying) {
            $scope.querying = true;
            SimpleQueryService.getProjectDataByDivision($scope.queryParamsByDivision).$promise.then(function (data) {
                $scope.querying = false;
                $scope.projectData = data;
                $scope.setTmpEarthBasicData();
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

    $scope.queryParamsByEarthBorrowFromBetween = {
        borrowFrom: null,
        borrowTo: null
    };
    $scope.getProjectDataByEarthBorrowFromBetween = function () {
        if (!$scope.querying) {
            $scope.querying = true;
            SimpleQueryService.getProjectDataByEarthBorrowFromBetween($scope.queryParamsByEarthBorrowFromBetween).$promise.then(function (data) {
                $scope.querying = false;
                $scope.projectData = data;
                $scope.setTmpEarthBasicData();
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
