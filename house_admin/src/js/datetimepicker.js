'use strict';
angular.module('datetimepicker',[])  
.directive('datetimepicker', function() {  
    return {  
        //强制AngularJS把指令限定为只支持属性  
        restrict: 'A',  
        //总是和ng-model配合使用  
        require:'?ngModel',  
        scope: {  
            //此方法需要与预先定义好，然后传递给视图控制器中的指令  
            select: '&' //把我们所引用的select函数绑定到右边的作用域中  
        },  
        link: function(scope, element, attrs, ngModel) {  
            if(!ngModel) return;  
   
            var updateModel = function(dateTimeTxt) {  
                scope.$apply(function() {  
                    //调用AngularJS内部的工具更新双向绑定关系  
                    ngModel.$setViewValue(dateTimeTxt);  
                });  
            };  
              
            element.datetimepicker({
                format: 'Y-m-d',
                timepicker: false,
                onSelect:function(dateTimeTxt, picker) {
                    updateModel(dateTimeTxt);
                    if(scope.select) {
                        scope.$apply(function() {
                            scope.select({date: dateTimeTxt});
                        });
                    }
                }
            });
           
        }  
    };  
});  
