app.controller('annoncesCtrl', ['$scope', '$http', function ($scope, $http) { 
	$scope.annonces=[{}];
    $http({
    	method: 'GET',
    	url: 'http://localhost:8181/aldaProject/alda/annonces',
    	isArray: true
  }).then(function(response) {
    	  console.log(response.data);
    	  $scope.annonces = response.data;
    	});
}])