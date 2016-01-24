var app = angular.module('myApp',['ngResource','ui.router'])

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
