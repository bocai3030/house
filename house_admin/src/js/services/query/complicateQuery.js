'use strict';
angular.module('adminApp').factory('ComplicateQueryService', function ($resource, ENV) {
    return $resource(ENV.apiUrl, {}, {
        'getProjectData': {
            url: ENV.apiUrl + '/jpa/complicateQuery/getProjectData',
            method: 'GET',
            isArray: true
        },
        'getAllFocusStatuses': {
            url: ENV.apiUrl + '/jpa/complicateQuery/getAllFocusStatuses',
            method: 'GET',
            isArray: true
        }
    });
});