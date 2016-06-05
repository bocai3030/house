'use strict';
angular.module('adminApp').factory('CommonQueryService', function ($resource, ENV) {
    return $resource(ENV.apiUrl, {}, {
        'getProjectTagByProjectId': {
            url: ENV.apiUrl + '/commonQuery/getProjectTagByProjectId',
            method: 'GET'
        }
    });
});