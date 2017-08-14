/**
 * 
 */
var app=angular.module("app",['ngRoute','ngCookies']);
app.config(function($routeProvider){
	$routeProvider
	.when('/Navbar1',{
		//controller:'PersonController',
		templateUrl:'views/Navbar1.html'
	})
	.when('/Userhome',{
		//controller:'PersonController',
		templateUrl:'views/UserHome.html'
	})
	.when('/registerUser',{
		controller:'UserController',
		templateUrl:'views/SignUp.html'
	})
	.when('/validateUser',{
		controller:'UserController',
		templateUrl:'views/Login.html'
	})

app.run(function($rootScope,$cookieStore,UserService,$location){
	if($rootScope.currentUser==undefined)
		$rootScope.currentUser=$cookieStore.get("currentUser")
	$rootScope.logout=function(){
		UserService.logout().then(function(response){
			$rootScope.logoutSuccess="logout ..Sccuessfully"
	delete $rootScope.currentUser
$cookiesStore.remove("currentUser")
$location.path("validateUser");
		},function(response){
			$scope.error=response.data
			$location.path("validateUser")
		})
	}
})
})