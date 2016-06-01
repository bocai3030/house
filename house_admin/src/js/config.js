'use strict';
/* Constants */
angular.module('config', [])
          .constant('ENV', {name: 'prod', apiUrl: 'http://localhost:8080/api/internal'})
    ;
