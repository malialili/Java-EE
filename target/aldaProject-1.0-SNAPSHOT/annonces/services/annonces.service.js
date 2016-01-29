// Invocar modo JavaScript 'strict'
'use strict';

// Creer le service 'personneService'
angular.module('annonces').factory('AnnonceService', ['$resource', function($resource) {
	// Utiliser le service '$resource' pour retourner un objet '$resource' annonce
    return $resource('api/annonces/:id', {
        id: '@id'
    }, {
        update: {
            method: 'PUT'
        }
    });
}]);