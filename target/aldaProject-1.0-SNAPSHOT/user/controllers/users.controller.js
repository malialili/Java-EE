'use strict';

// Creer le controller 'usersController'
angular.module('users').controller('userController', ['$scope', '$routeParams', '$location', 'userService',
    'RoleService', function($scope, $routeParams, $location, UserService, RoleService) {
        // Service d'Authentication
        //$scope.authentication = Authentication;
	    $scope.roles = RoleService.query(); 
        // methode controller pour créer des users 
        $scope.create = function() {
            // Utiliser les champs du form pour créer un nouveau $resource user
            var userService = new UserService({
            	prenom: this.prenom,
            	nom: this.nom,
                titulo: this.titulo,
                mail: this.mail,
                pass: this.pass,
                tel: this.tel,
                ville: this.ville,
                adresse: this.adresse,
                role: this.role
            });

            // le methode '$save' de userService pour envoyer une petition POST 
            UserService.$save(function(response) {
                // Si un user est cree du mode correct retourne a liste des users 
                $location.path('users');
            }, function(errorResponse) {
                // En autre cas, presenter l'utilisateur el message d'erreur
                $scope.error = errorResponse.data.message;
            });
        };

        // methode pour recuperer une liste des users
        $scope.find = function() {
            // Utiliser le methode 'query' de userService pour envoyer une petition GET
            $scope.users = UserService.query();
        };

        // methode pour recuperer un user
        $scope.findOne = function() {
            // Methode 'get' de userService pour envoyer une petition GET
            $scope.user = UserService.get({
            	id: $routeParams.idUser
            });
        };

       // methode pour faire la mise a jour d'un user
        $scope.update = function() {
            // Utiliser le methode '$update' de userService pour envoyer une petition PUT
            $scope.user.$update(function() {
                // Si un user etait bien actualise on retourne a la page d'utilisateurs 
                $location.path('users');
            }, function(errorResponse) {
            	// En autre cas, presenter l'utilisateur el message d'erreur
                $scope.error = errorResponse.data.message;
            });
        };

        // methode pour supprimer un user 
        $scope.delete = function(user) {
            // si un user est envoie dans la methode
            if (user) {
                // Utiliser la methode '$remove' pour supprimer la user 
            	user.$remove(function() {
                    // Supprimer la user de la liste d'utilisateurs
                    for (var i in $scope.users) {
                        if ($scope.users[i].id === user.id) {
                            $scope.users.splice(i, 1);
                        }
                    }
                });
            } else {
            	// En autre cas, utiliser la methode '$remove' de l'utilisateur
                $scope.user.$remove(function() {
                    $location.path('users');
                });
            }
        };

    }
]);