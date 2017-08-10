/**
 * 
 */
var app=angular.module("app",['ngRoute']);
app.config(function($routeProvider){
	$routeProvider
	.when('/Navbar1',{
		//controller:'PersonController',
		templateUrl:'views/Navbar1.html'
	})
	.when('/UserHome',{
		//controller:'PersonController',
		templateUrl:'views/UserHome.html'
	})
	.when('/registerUser',{
		controller:'UserController',
		templateUrl:'views/SignUp.html'
	})
	.when('/validateUser',{
	//	controller:'UserController',
		templateUrl:'views/Login.html'
	})
})