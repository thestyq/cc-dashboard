angular.module('dashboard', [])
    .controller('home', function($scope, $http) {
        $scope.red = {
            "color" : "red"
        };
        $scope.green = {
            "color" : "green"
        };

        $http.get('/data/').success(function(data) {
            $scope.ccInfo = data;
        })
    });