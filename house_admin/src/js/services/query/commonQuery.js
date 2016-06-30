'use strict';
angular.module('adminApp').factory('CommonQueryService', function ($resource, ENV) {
    return $resource(ENV.apiUrl, {}, {
        'getProjectTagByProjectId': {
            url: ENV.apiUrl + '/solr/commonQuery/getProjectTagByProjectId',
            method: 'GET'
        },
        'updateFocusStatusByProjectId': {
            url: ENV.apiUrl + '/jpa/commonQuery/updateFocusStatusByProjectId',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            method: 'POST'
        },
        'getProjectHasTag': {
            url: ENV.apiUrl + '/solr/commonQuery/getProjectHasTag',
            method: 'GET'
        },
        'getProjectHasTags': {
            url: ENV.apiUrl + '/solr/commonQuery/getProjectHasTags',
            method: 'GET',
            isArray: true
        },
        'updateProjectTagByProjectId': {
            url: ENV.apiUrl + '/jpa/commonQuery/updateProjectTagByProjectId',
            method: 'POST'
        }
    });
});