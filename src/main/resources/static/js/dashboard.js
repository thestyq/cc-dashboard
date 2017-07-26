angular.module('dashboard', [])
    .controller('home', function($scope, $interval, $http, $filter) {
        $scope.red = {
            "color" : "red"
        };
        $scope.green = {
            "color" : "green"
        };

        var fill = function(){
            $scope.time = $filter('date')(new Date(), 'HH:mm:ss');
            $http.get('/data/').success(function (data) {
                $scope.ccInfo = data;
            })
        };
        fill();
        $interval(fill, 30000);
    });