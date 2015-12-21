//factory qui permet de recupere un utilisateur par mail
app.factory('userFact', ['$resource', function($resource){
	return $resource('http://localhost:8181/aldaProject/alda/users/:email',{email : '@email'},
			{
		'get':    {method:'GET',isArray:true }
			});
}])


//factory recuperation des annonces
app.factory('annonceFactory', ['$resource', function($resource){
	return $resource('http://localhost:8181/aldaProject/alda/annonces/:id',{id : '@id'},
			{
		'get':    {method:'GET',isArray:true}  //configuer get pour pouvoir recevoir jSON
			});
}])


//factory qui gere la gestion de la session
app.factory("AppSession", function($window, $rootScope) {

	angular.element($window).on('storage', function(event) {
		if (event.key === 'my-storage') {
			$rootScope.$apply();
		}
	});

	return {
		setData: function(val) {
			$window.localStorage && $window.localStorage.setItem('my-storage', JSON.stringify(val));
			return this;
		},
		getData: function() {
			return JSON.parse($window.localStorage && $window.localStorage.getItem('my-storage'));
		},

		isConnected: function(val){
			if(val!= null){
				return true 

			}else{
				return false
			}
		}
	};
});