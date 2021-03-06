'use strict';
angular.module('adminApp').config(function ($stateProvider) {
    $stateProvider.state('site.complicate', {
        url: 'query/complicate',
        templateUrl: 'views/query/complicateQuery.html',
        controller: 'ComplicateQueryController'
    });
}).controller('ComplicateQueryController', function ($scope, toasty, CommonQueryService, ComplicateQueryService, $http) {
    $scope.title = '复杂房产查询';

    $scope.qc = {
        byAll: true
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
            countryName: false,
            countryId: false,
            agreeName: false,
            agreeId: false,
            layoutName: false,
            layoutId: false,
            projectAddress: true,
            developer: true,
            developerId: false,
            division: true,
            sectionId: false,
            totalCostArea: false,
            totalBuildArea: false,
            qualificationLicenceNo: false,
            usagee: false,
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
                        $scope.projectData[index].projectTag = data;
                    } else {
                        $scope.projectData[index].projectTag = {};
                        $scope.projectData[index].projectTag.projectId = $scope.projectData[index].projectBasicData.projectId;
                    }
                }
            })(i));
        }
    };

    $scope.changeProjectTagEditStatus = function(projectTag) {
        projectTag.editStatus = !projectTag.editStatus;
    };
    $scope.updateFocusStatusByProjectId = function(projectTag) {
        CommonQueryService.updateFocusStatusByProjectId($.param({projectId:projectTag.projectId, focusStatus:projectTag.focusStatus})).$promise.then(function (data) {
            if('status' in data) {
                toasty.pop.info({
                    title: '操作成功',
                    msg: '更新成功！',
                    sound: true
                });
                CommonQueryService.getProjectTagByProjectId({projectId:projectTag.projectId}).$promise.then(function (data) {
                    projectTag.focusStatus=data.focusStatus;
                }, function (data) {
                    toasty.pop.error({
                        title: '操作失败',
                        msg: '对不起，查询失败，请重试！',
                        sound: true
                    });
                });
            }
        }, function (data) {
            toasty.pop.error({
                title: '操作失败',
                msg: '对不起，更新失败，请重试！',
                sound: true
            });
        });
        $scope.changeProjectTagEditStatus(projectTag);
    };

    $scope.queryParams = {
        division: null,
        borrowFrom: null,
        borrowTo: null,
        focusStatus: null,
        onlyResidential: true
    };
    $scope.getProjectData = function () {
        if (!$scope.querying) {
            $scope.querying = true;
            ComplicateQueryService.getProjectData($scope.queryParams).$promise.then(function (data) {
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

    $scope.focusStatuses = [];
    $scope.getAllFocusStatuses = function () {
        ComplicateQueryService.getAllFocusStatuses().$promise.then(function (data) {
            $scope.focusStatuses = data;
        }, function (data) {
        });
    };
    $scope.updateFocusStatus = function (newFocusStatus) {
        $scope.queryParams.focusStatus = newFocusStatus;
    };
    $scope.clearFocusStatus = function () {
        $scope.queryParams.focusStatus = null;
    };
    $scope.getAllFocusStatuses();
});
