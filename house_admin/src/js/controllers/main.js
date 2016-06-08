'use strict';
angular.module('adminApp').controller('MainCtrl', function ($scope, eehNavigation) {
    eehNavigation
        .menuItem('bar.query',{
            text: '房产查询',
            iconClass: 'fa-flag',
            isCollapsed: true
        })
        .menuItem('bar.query.simple',{
            text: '简单查询',
            iconClass: 'fa-flag',
            state: 'site.simple'
        })
        .menuItem('bar.query.complicate',{
            text: '复杂查询',
            iconClass: 'fa-flag',
            state: 'site.complicate'
        })
        .menuItem('bar.query.tag',{
            text: '标签管理',
            iconClass: 'fa-flag',
            state: 'site.tag'
        })
    ;
    $scope.navbarBrand = {
        src: 'favicon.ico',
        text:'HOUSE管理后台',
        state: 'home'
    };
});
