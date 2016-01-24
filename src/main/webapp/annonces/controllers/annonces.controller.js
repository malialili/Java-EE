// appeller en mode JavaScript 'strict'
'use strict';

// Creer le controller 'annoncesController'
angular.module('annonces').controller('annonceController', ['$scope', '$routeParams', '$location', 'AnnonceService',
    function($scope, $routeParams, $location, AnnonceService) {
        // Service d'Authentication
        //$scope.authentication = Authentication;

        // methode controller pour créer des annonces 
        $scope.create = function() {
            // Utiliser les champs du form pour créer un nouveau $resource annonce
            var annonceService = new AnnonceService({
                titulo: this.titulo,
                contenido: this.contenido
            });

            // le methode '$save' de annonceneService pour envoyer une petition POST 
            annonceService.$save(function(response) {
                // Si un annonce est cree du mode correct retourne a liste des annonces 
                $location.path('annonces');
            }, function(errorResponse) {
                // En autre cas, presenter l'utilisateur el message d'erreur
                $scope.error = errorResponse.data.message;
            });
        };

        // methode pour recuperer une liste des annonces
        $scope.find = function() {
            // Utiliser le methode 'query' de annonceService pour envoyer une petition GET
            $scope.annonces = AnnonceService.query();
        };

        // methode pour recuperer un annonce
        $scope.findOne = function() {
            // Methode 'get' de annonceService pour envoyer une petition GET
            $scope.annonce = AnnonceService.get({
            	id: $routeParams.idAnnonce
            });
        };

       // methode pour faire la mise a jour d'un annonce
        $scope.update = function() {
            // Utiliser le methode '$update' de annonceService pour envoyer une petition PUT
            $scope.annonce.$update(function() {
                // Si un annonce etait bien actualise on retourne a la page d'utilisateurs 
                $location.path('annonce');
            }, function(errorResponse) {
            	// En autre cas, presenter l'utilisateur el message d'erreur
                $scope.error = errorResponse.data.message;
            });
        };

        // methode pour supprimer un annonce 
        $scope.delete = function(annonce) {
            // si un annonce est envoie dans la methode
            if (annonce) {
                // Utiliser la methode '$remove' pour supprimer la annonce 
            	annonce.$remove(function() {
                    // Supprimer la annonce de la liste d'utilisateurs
                    for (var i in $scope.annonces) {
                        if ($scope.annonces[i].id === annonce.id) {
                            $scope.annonces.splice(i, 1);
                        }
                    }
                });
            } else {
            	// En autre cas, utiliser la methode '$remove' 
                $scope.annonce.$remove(function() {
                    $location.path('annonces');
                });
            }
        };

    }
]);