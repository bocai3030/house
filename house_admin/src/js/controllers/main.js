'use strict';
angular.module('adminApp').controller('MainCtrl', function ($scope, eehNavigation) {
    eehNavigation
        .menuItem('bar.query',{
            text: '查询',
            iconClass: 'fa-flag',
            isCollapsed: true
        })
        .menuItem('bar.query.house',{
            text: '房产查询',
            iconClass: 'fa-flag',
            state: 'site.house'
        })
    ;
    $scope.navbarBrand = {
        src: 'favicon.ico',
        text:'HOUSE管理后台',
        state: 'home'
    };
});
