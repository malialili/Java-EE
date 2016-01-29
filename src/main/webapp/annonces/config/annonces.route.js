// Invocar modo JavaScript 'strict'
'use strict';

// Configurer le módule routes de 'personnes'
angular.module('annonces').config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
		when('/annonces', {
			templateUrl: 'annonces/views/list-annonces.view.html'
		}).
		when('/annonces/create', {
			templateUrl: 'annonces/views/create-annonce.view.html'
		}).
		when('/annonces/:personneId', {
			templateUrl: 'annonces/views/view-annonce.view.html'
		}).
		when('/annonces/:personneId/edit', {
			templateUrl: 'annonces/views/edit-annonce.view.html'
		});
	}
]);