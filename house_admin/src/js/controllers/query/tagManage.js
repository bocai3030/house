'use strict';
angular.module('adminApp').config(function ($stateProvider) {
    $stateProvider.state('site.tag', {
        url: 'query/tag',
        templateUrl: 'views/query/tagManage.html',
        controller: 'TagManageController'
    });
}).controller('TagManageController', function ($scope, toasty, CommonQueryService, ComplicateQueryService, $http) {
    $scope.title = '房产标签管理';

    $scope.fc = {
        pbd: {
            projectId: true,
            projectName: true,
            projectAddress: true
        },
        pt: {
            focusStatus: true,
            remark: true
        }
    };

    $scope.tagDatas = null;
    $scope.queryParams = {
        focusStatus: null
    };
    $scope.getProjectHasTags = function () {
        if (!$scope.querying) {
            $scope.querying = true;
            CommonQueryService.getProjectHasTags($scope.queryParams).$promise.then(function (data) {
                $scope.querying = false;
                $scope.tagDatas = data;
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
    $scope.getAllFocusStatuses();
    $scope.updateFocusStatus = function (newFocusStatus) {
        $scope.queryParams.focusStatus = newFocusStatus;
    };

    $scope.setFocusStatusInEdit = function(projectTag) {
        projectTag.saveFocusStatus = projectTag.focusStatus;
        projectTag.editStatus1 = true;
    };
    $scope.setRemarkInEdit = function(projectTag) {
        projectTag.saveRemark = projectTag.remark;
        projectTag.editStatus2 = true;
    };

    $scope.updateParam = {
        projectId: null,
        focusStatus: null,
        remark: null
    };
    $scope.updateProjectTagByProjectId = function (tagData) {
        $scope.updateParam.projectId = tagData.projectId;
        $scope.updateParam.focusStatus = tagData.projectTag.saveFocusStatus;
        $scope.updateParam.remark = tagData.projectTag.saveRemark;
        CommonQueryService.updateProjectTagByProjectId($scope.updateParam).$promise.then(function (data) {
            toasty.pop.info({
                title: '操作成功',
                msg: data.status,
                sound: true
            });
            $scope.cancelEdit(tagData.projectTag);
            CommonQueryService.getProjectHasTag({projectId:tagData.projectId}).$promise.then(function (data) {
                tagData.projectTag = data.projectTag;
            }, function (data) {
                toasty.pop.error({
                    title: '操作失败',
                    msg: '对不起，拉取更新数据失败，请重试！',
                    sound: true
                });
            });
        }, function (data) {
            toasty.pop.error({
                title: '操作失败',
                msg: '对不起，更新失败，请重试！',
                sound: true
            });
        });
    };
    $scope.cancelEdit = function(projectTag) {
        projectTag.saveFocusStatus = null;
        projectTag.saveRemark = null;
        projectTag.editStatus1 = false;
        projectTag.editStatus2 = false;
    };
});
