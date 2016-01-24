'use strict';

var app = angular.module('myApp', ['ngResource', 'ngRoute']);
app.config(['$routeProvider', function($routeProvider){
	$routeProvider
	.when('/',{templateUrl:"index.html", controller:'loginCtrl'})
	.when('/users', {templateUrl:"usersList.html", controller:'usersCtrl'})
	.when('/users/:id', {templateUrl:"usersList.html", controller:'usersCtrl'})
	.when('/annonces', {templateUrl:"annoncesList.html", controller:'annoncesCtrl'})
	.when('/connexion', {templateUrl:"register.html", controller:'registerCtrl'})
	.when('/login', {templateUrl:"login.html", controller:'loginCtrl'})
	.otherwise({redirectTo:'/'});
}]) 

//list user
app.controller('usersCtrl', ['$scope', '$http', function ($scope, $http) { 
	$scope.users=[{}];
    $http({	
    	method: 'GET',
    	url: 'http://localhost:8181/aldaProject/alda/users',
    	isArray: true
  }).then(function(response) {
    	  console.log(response);
    	  $scope.users = response.data;
    	});
}])

appa.controller('userCtrl', function($scope, $resource){
	var User=$resource('http://localhost:8181/aldaProject/alda/users/{email}');
	users= User.query();
})
/*
//add user
app.controller('registerCtrl', ['$scope','$http', function($scope, $http){
	$scope.register=function(){
		if(($scope.password) != ($scope.passwordConfirm)){
			alert("mot de passe différent "+$scope.Password+"  "+$scope.passwordConfirm)
		} else if($scope.password.length < 8){
			alert("le mot de passe doit avoir au moins 8 caracteres")
		} else{
			$scope.user = {
				email:$scope.email	,
				password : $scope.password
			}
			$http({
			    	method: 'POST',
			    	url: 'http://localhost:8181/aldaProject/alda/users/addUser',
			  }).then(function(response) {
			    	  console.log(response.data);
			    	  $scope.user= response.data;
			    	});			
		}
	}
}]);
*/

//controller pour l'inscription
app.controller("registerCtrl",['$scope','$http','$location','AppSession', function($scope,$http,$location,AppSession) {

	$scope.register=function(){

		if(($scope.password) != ($scope.passwordConfirm)){
			alert("mot de passe différent ")
		} else if($scope.password.length < 8){
			alert("le mot de passe doit avoir au moins 8 caracteres")
		} else{
			var params ={ email: $scope.email,password :$scope.password }
			alert($scope.email + "  "+ $scope.password)
			$http.post('http://localhost:8181/aldaProject/alda/users/addUser',params)
			.success(function(user) {
				AppSession.setData(user);
				$location.url('/');
			})
			.error(function(status) {
				console.log(status);
			});
		}
	}
}])

app.controller("loginCtrl",['$scope','$http','$location', function($scope,$http,$location) {

	$scope.submitLogin = function() {
		var params =JSON.stringify( { email: $scope.email,password :$scope.password})
		$http.post('http://localhost:8181/aldaProject/alda/users/login',params)
		.success(function(user) {
			if(user==""){
				alert("mot de passe éronné")
				$location.url('/connexion');
			}else {
				AppSession.setData(user);
				$location.url('/');
			}
		})
		.error(function(status) {
			console.log(status);
		});
	};
}])

//list annonces
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
/*
app.controller('loginCtrl', ['$scope', function($scope){
	$scope.login=function(){
		if(($scope.password) != ($scope.passwordConfirm)){
			alert("mot de passe différent "+$scope.signupPassword+"  "+$scope.passwordConfirm)
		} else if($scope.Password.length < 8){
			alert("le mot de passe doit avoir au moins 8 caracteres")
		} else{
			$scope.user = {
				email:$scope.email	,
				password : $scope.password
			}

			alert(JSON.stringify($scope.email + " "+$scope.password))
			userFactory.save($scope.user)
			
		}
	}
}])

*/