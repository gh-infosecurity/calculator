var app = angular.module("app", []);

app.controller("appCtrl", function ($scope, $http) {
    var val = "";
    var delimiter = undefined;
    var path = undefined;
    $scope.state = "has-success";
    $scope.operations = getOperations();

    function isEmpty(str) {
        return (!str || 0 === str.length);
    }

    function hasOperator(str) {
        var array = str.split('');
        var char = array[array.length - 1];
        return char === '+' || char === '-' || char === '*' || char === '/';
    }

    $scope.digit = function (item) {
        val = val + item;
        $scope.value = val;
    };

    $scope.clear = function () {
        val = "";
        $scope.state = "has-success";
        $scope.value = "0";
    };

    $scope.clearlastchar = function () {
        val = val.substring(0, val.length - 1);
        $scope.value = val;
    };

    $scope.sum = function () {
        delimiter = '+';
        if (!isEmpty(val) && !hasOperator(val)) {
            val = val + delimiter;
            path = 'sum';
            $scope.value = val;
        }
    };

    $scope.difference = function () {
        delimiter = '-';
        if (!isEmpty(val) && !hasOperator(val)) {
            val = val + delimiter;
            path = 'difference';
            $scope.value = val;
        }
    };

    $scope.multiplication = function () {
        delimiter = '*';
        if (!isEmpty(val) && !hasOperator(val)) {
            val = val + delimiter;
            path = 'multiplication';
            $scope.value = val;
        }
    };

    $scope.division = function () {
        delimiter = '/';
        if (!isEmpty(val) && !hasOperator(val)) {
            val = val + delimiter;
            path = 'division';
            $scope.value = val;
        }
    };

    $scope.result = function () {
        const host = "http://localhost:8080/";
        var operators = $scope.value.split(delimiter);
        var request = $http({
            method: "post",
            headers: {
                'Accept': 'application/json'
            },
            url: host + path,
            data: {
                first: operators[0],
                second: operators[1]
            }
        });

        request.success(function (data) {
                val = data.result;
                $scope.value = data.result;
                $scope.operations = getOperations();
            }
        );

        request.error(function (data) {
                $scope.state = "has-error";
                $scope.value = data.error;
                $scope.operations = getOperations();
            }
        );
    };

    function getOperations() {
        var request = $http({
            method: "post",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: "http://localhost:8080/operations",
            data: {}
        });

        request.success(function (data) {
                $scope.operations = data.operations;
            }
        );
    }

    $scope.sqrt = function () {
        var request = $http({
            method: "post",
            headers: {
                'Accept': 'application/json'
            },
            url: "http://localhost:8080/sqrt",
            data: {
                value: val
            }
        });

        request.success(function (data) {
                val = data.result;
                $scope.value = data.result;
                $scope.operations = getOperations();
            }
        );
    };

    $scope.pow = function () {
        var request = $http({
            method: "post",
            headers: {
                'Accept': 'application/json'
            },
            url: "http://localhost:8080/pow",
            data: {
                value: val
            }
        });

        request.success(function (data) {
                val = data.result;
                $scope.value = data.result;
                $scope.operations = getOperations();
            }
        );
    }
});