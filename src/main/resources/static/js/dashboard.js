angular.module('dashboard', [])
    .controller('home', function($scope, $interval, $http) {
        $scope.red = {
            "color" : "red"
        };
        $scope.green = {
            "color" : "green"
        };

        var fill = function(){
            $http.get('/data/').success(function (data) {
                $scope.time = data.lastChecked;
                $scope.ccInfo = data.ccInfo;
            })
        };
        fill();
        $interval(fill, 30000);
    });