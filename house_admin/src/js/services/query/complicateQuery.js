'use strict';
angular.module('adminApp').factory('ComplicateQueryService', function ($resource, ENV) {
    return $resource(ENV.apiUrl, {}, {
        'getProjectData': {
            url: ENV.apiUrl + '/complicateQuery/getProjectData',
            method: 'GET',
            isArray: true
        }
    });
});