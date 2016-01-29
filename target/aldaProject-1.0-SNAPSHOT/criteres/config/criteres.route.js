// Invocar modo JavaScript 'strict'
'use strict';

// Configurer le módule routes de 'personnes'
angular.module('criteres').config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
		when('/criteres', {
			templateUrl: 'public/personnes/views/list-criteres.client.view.html'
		}).
		when('/criteres/create', {
			templateUrl: 'public/personnes/views/create-critere.client.view.html'
		}).
		when('/criteres/:critereId', {
			templateUrl: 'public/criteres/views/view-critere.client.view.html'
		}).
		when('/criteres/:critereId/edit', {
			templateUrl: 'public/criteres/views/edit-critere.client.view.html'
		});
	}
]);