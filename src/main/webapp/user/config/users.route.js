'use strict';

// Configurer le module routes de 'users'
angular.module('users').config(['$routeProvider',
	function($routeProvider) { $routeProvider
	.when('/users', { templateUrl: 'users/views/list-users.client.view.html'})
	.when('/users/create', {templateUrl: 'users/views/create-personne.client.view.html'})
	.when('/users/:userId', {templateUrl: 'users/views/view-personne.client.view.html'})
	.when('/users/:userId/edit', {templateUrl: 'users/views/edit-personne.client.view.html'});
	}
]);