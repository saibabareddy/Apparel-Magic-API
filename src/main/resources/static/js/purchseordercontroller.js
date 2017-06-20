var app = angular.module("PurchaseOrderManagement", []);
           		//Controller Part
            	app.controller("PurchaseOrderController", function($scope, $http) {           
                $scope.purchaseorders = [];
                $scope.purchaseorderForm = {
                        id : -1,
                        date : ""
                    };
              /*//Now load the data from server
                _refreshshipmentData();*/
              
                //HTTP POST/PUT methods for add/edit country 
                // with the help of id, we are going to find out whether it is put or post operation
                
                $scope.submitPurchaseOrder = function() {
         $('#progress-image').show();
                    var method = "";
                    var url = "";
                    
                    if ($scope.purchaseorderForm.id == -1) {
                        //Id is absent in form data, it is create new operation
                        method = "POST";
                        url = 'http://localhost:8080/Purchase_order';
                    } 
                    $http({
                        method : method,
                        url : url,
                        data : $scope.purchaseorderForm.date,
                        headers : {
                            'Content-Type' : 'text/plain'
                        }
                    }).then(function successCallback(response) {
                        $scope.purchaseorders = response.data;
                        console.log($scope.purchaseorders);
                        $('#progress-image').hide();
                    }, function errorCallback(response) {
                        console.log(response.statusText);
                        $('#progress-image').hide();
                    });
                };
         
               
                
              /*  Private Methods 
                //HTTP GET- get all shipments collection
                function _refreshshipmentData() {
                    $http({
                        method : 'GET',
                        url : 'http://localhost:8080/Purchase_order'
                    }).then(function successCallback(response) {
                        $scope.shipments = response.data;
                    }, function errorCallback(response) {
                        console.log(response.statusText);
                    });
                }*/
         
               /* function _success(response) {
                    _refreshshipmentData();
                    _clearFormData()
                }*/
         /*
                function _error(response) {
                    console.log(response.statusText);
                }*/
});