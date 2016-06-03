'use strict';
angular.module('adminApp').factory('HouseService', function ($resource, ENV) {
    return $resource(ENV.apiUrl + '/v6/query', {}, {
        'getProjectDataByProjectId': {
            url: ENV.apiUrl + '/getProjectDataByProjectId',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByProjectNameLike': {
            url: ENV.apiUrl + '/getProjectDataByProjectNameLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByPreSellLicenseId': {
            url: ENV.apiUrl + '/getProjectDataByPreSellLicenseId',
            method: 'GET',
            isArray: true
        }
    });
});