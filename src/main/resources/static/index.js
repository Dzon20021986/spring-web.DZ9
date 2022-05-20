angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                console.log(response.data);
                $scope.ProductsList = response.data;
            });
    };

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.createProductJson = function () {
        console.log($scope.newProductJson);
        $http.post(contextPath + '/products;', $scope.newProductJson)
            .then(function (response) {
                $scope.loadProducts();
            });
    };

    $scope.changeCost = function (productId, delta) {
        $http({
            url: contextPath + '/products/change_cost',
            method: 'GET',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }

    // $scope.selectionProducts = function () {
    //     $http({
    //         url: contextPath + "select/products",
    //         method: 'get',
    //         params: {
    //             min: $scope.differenceAdd.min,
    //             max: $scope.differenceAdd.max
    //         }
    //     }).then(function (response){
    //         alert(response.ProductsList)
    //     })
    // };

    $scope.loadProducts();
});