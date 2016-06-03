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
        },
        'getProjectDataByProjectAddressLike': {
            url: ENV.apiUrl + '/getProjectDataByProjectAddressLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByDeveloperLike': {
            url: ENV.apiUrl + '/getProjectDataByDeveloperLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByDivision': {
            url: ENV.apiUrl + '/getProjectDataByDivision',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByEarthBorrowFromBetween': {
            url: ENV.apiUrl + '/getProjectDataByEarthBorrowFromBetween',
            method: 'GET',
            isArray: true
        }
    });
});