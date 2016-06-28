'use strict';
angular.module('adminApp').factory('SimpleQueryService', function ($resource, ENV) {
    return $resource(ENV.apiUrl, {}, {
        'getProjectDataByProjectId': {
            url: ENV.apiUrl + '/solr/simpleQuery/getProjectDataByProjectId',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByProjectNameLike': {
            url: ENV.apiUrl + '/solr/simpleQuery/getProjectDataByProjectNameLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByPreSellLicenseId': {
            url: ENV.apiUrl + '/solr/simpleQuery/getProjectDataByPreSellLicenseId',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByProjectAddressLike': {
            url: ENV.apiUrl + '/solr/simpleQuery/getProjectDataByProjectAddressLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByDeveloperLike': {
            url: ENV.apiUrl + '/solr/simpleQuery/getProjectDataByDeveloperLike',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByDivision': {
            url: ENV.apiUrl + '/solr/simpleQuery/getProjectDataByDivision',
            method: 'GET',
            isArray: true
        },
        'getProjectDataByEarthBorrowFromBetween': {
            url: ENV.apiUrl + '/solr/simpleQuery/getProjectDataByEarthBorrowFromBetween',
            method: 'GET',
            isArray: true
        }
    });
});