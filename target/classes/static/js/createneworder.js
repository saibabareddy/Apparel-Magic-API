var app = angular.module("OrderManagement", []);
           		//Controller Part
            	app.controller("OrderController", function($scope, $http) {           
                $scope.orders = [];
                $scope.orderForm = {
                        id : -1,
                        customer_id : "",
                        division_id : "",
                        ar_acct : "",
                        warehouse_id : "",
                        currency_id : "",
                        sku_id : "",
                        qty : ""
                    };
                _refreshorderData();
                

                //HTTP POST/PUT methods for add/edit country 
                // with the help of id, we are going to find out whether it is put or post operation
                
                $scope.submitOrder = function() {
         
                    var method = "";
                    var url = "";
                    
                    if ($scope.orderForm.id == -1) {
                        //Id is absent in form data, it is create new country operation
                        method = "POST";
                        url = 'http://localhost:8080/newOrder';
                    } 
                    $http({
                        method : method,
                        url : url,
                        data : angular.toJson($scope.orderForm),
                        headers : {
                            'Content-Type' : 'application/json'
                        }
                    }).then( _success, _error );
                };

                function _refreshorderData() {
                    $http({
                        method : 'GET',
                        url : 'https://sandbox2.app.apparelmagic.com/api/json/orders/?time=171114279788&token=64ebd05e550b23a15be09ccef57b27c6'
                    }).then(function successCallback(response) {
                        $scope.orders = response.data;
                    }, function errorCallback(response) {
                        console.log(response.statusText);
                    });
                }
                function _success(response) {
                    _refreshorderData();
                    _clearFormData()
                }
         
                function _error(response) {
                    console.log(response.statusText);
                }
            });