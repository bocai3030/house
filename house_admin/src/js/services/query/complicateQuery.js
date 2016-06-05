'use strict';
angular.module('adminApp').factory('ComplicateQueryService', function ($resource, ENV) {
    return $resource(ENV.apiUrl, {}, {
        'getProjectDataByDivisionAndEarthBorrowFromBetween': {
            url: ENV.apiUrl + '/complicateQuery/getProjectDataByDivisionAndEarthBorrowFromBetween',
            method: 'GET',
            isArray: true
        }
    });
});