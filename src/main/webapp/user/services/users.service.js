// Invocar modo JavaScript 'strict'
'use strict';

// Creer le service 'userService'
angular.module('users').factory('UserService', ['$resource', function($resource) {
	// Utiliser le service '$resource' pour retourner un objet '$resource' user
    return $resource('api/users/:id', {
        id: '@id'
    }, {
        update: {
            method: 'PUT'
        }
    });
}]);