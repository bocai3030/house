'use strict';
angular.module('adminApp').factory('SimpleQueryService', function ($resource, ENV) {
    return $resource(ENV.apiUrl, {}, {
        'getProjectDataByProjectId': {
            url: ENV.apiUrl + '/jpa/simpleQuery/getProjectDataByProjectId',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByProjectNameLike': {
            url: ENV.apiUrl + '/jpa/simpleQuery/getProjectDataByProjectNameLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByPreSellLicenseId': {
            url: ENV.apiUrl + '/jpa/simpleQuery/getProjectDataByPreSellLicenseId',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByProjectAddressLike': {
            url: ENV.apiUrl + '/jpa/simpleQuery/getProjectDataByProjectAddressLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByDeveloperLike': {
            url: ENV.apiUrl + '/jpa/simpleQuery/getProjectDataByDeveloperLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByDivision': {
            url: ENV.apiUrl + '/jpa/simpleQuery/getProjectDataByDivision',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByEarthBorrowFromBetween': {
            url: ENV.apiUrl + '/jpa/simpleQuery/getProjectDataByEarthBorrowFromBetween',
            method: 'GET',
            isArray: true
        }
    });
});