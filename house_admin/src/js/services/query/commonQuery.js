'use strict';
angular.module('adminApp').factory('CommonQueryService', function ($resource, ENV) {
    return $resource(ENV.apiUrl, {}, {
        'getProjectTagByProjectId': {
            url: ENV.apiUrl + '/commonQuery/getProjectTagByProjectId',
            method: 'GET'
        },
        'updateFocusStatusByProjectId': {
            url: ENV.apiUrl + '/commonQuery/updateFocusStatusByProjectId',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST'
        }
    });
});