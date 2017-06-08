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
                _refreshshipmentData();
                

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
                      
                /* Private Methods */
                //HTTP GET- get all shipments collection
                function _refreshshipmentData() {
                    $http({
                        method : 'GET',
                        url : 'http://localhost:8080/Shipments'
                    }).then(function successCallback(response) {
                        $scope.shipments = response.data;
                    }, function errorCallback(response) {
                        console.log(response.statusText);
                    });
                }
                function _success(response) {
                    _refreshshipmentData();
                    _clearFormData()
                }
         
                function _error(response) {
                    console.log(response.statusText);
                }
            });