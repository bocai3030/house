'use strict';
angular.module('adminApp').factory('SimpleQueryService', function ($resource, ENV) {
    return $resource(ENV.apiUrl, {}, {
        'getProjectDataByProjectId': {
            url: ENV.apiUrl + '/simplequery/getProjectDataByProjectId',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByProjectNameLike': {
            url: ENV.apiUrl + '/simplequery/getProjectDataByProjectNameLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByPreSellLicenseId': {
            url: ENV.apiUrl + '/simplequery/getProjectDataByPreSellLicenseId',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByProjectAddressLike': {
            url: ENV.apiUrl + '/simplequery/getProjectDataByProjectAddressLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByDeveloperLike': {
            url: ENV.apiUrl + '/simplequery/getProjectDataByDeveloperLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByDivision': {
            url: ENV.apiUrl + '/simplequery/getProjectDataByDivision',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByEarthBorrowFromBetween': {
            url: ENV.apiUrl + '/simplequery/getProjectDataByEarthBorrowFromBetween',
            method: 'GET',
            isArray: true
        }
    });
});