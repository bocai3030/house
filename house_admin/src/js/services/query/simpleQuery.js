'use strict';
angular.module('adminApp').factory('SimpleQueryService', function ($resource, ENV) {
    return $resource(ENV.apiUrl, {}, {
        'getProjectDataByProjectId': {
            url: ENV.apiUrl + '/simpleQuery/getProjectDataByProjectId',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByProjectNameLike': {
            url: ENV.apiUrl + '/simpleQuery/getProjectDataByProjectNameLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByPreSellLicenseId': {
            url: ENV.apiUrl + '/simpleQuery/getProjectDataByPreSellLicenseId',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByProjectAddressLike': {
            url: ENV.apiUrl + '/simpleQuery/getProjectDataByProjectAddressLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByDeveloperLike': {
            url: ENV.apiUrl + '/simpleQuery/getProjectDataByDeveloperLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByDivision': {
            url: ENV.apiUrl + '/simpleQuery/getProjectDataByDivision',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByEarthBorrowFromBetween': {
            url: ENV.apiUrl + '/simpleQuery/getProjectDataByEarthBorrowFromBetween',
            method: 'GET',
            isArray: true
        }
    });
});