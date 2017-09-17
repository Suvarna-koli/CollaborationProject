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
	.when('/contactus',{
		//controller:'PersonController',
		templateUrl:'views/ContactUs.html'
	})
	.when('/registerUser',{
		controller:'UserController',
		templateUrl:'views/SignUp.html'
	})
	.when('/validateUser',{
		controller:'UserController',
		templateUrl:'views/Login.html'
	})
	.when('/editProfile',{
		controller:'UserController',
		templateUrl:'views/UpdateProfile.html'
		
	})
	.when('/saveJob',{
		controller:'JobController',
		templateUrl:'views/JobForm.html'
		
	})
	.when('/getAllJobs',{
		controller:'JobController',
		templateUrl:'views/JobPost.html'
		
	})
	
	.when('/saveblogs',{
		controller:'BlogController',
	templateUrl:'views/BlogPost.html'	
	})
	.when('/getallblogs',{
		controller:'BlogController',
	templateUrl:'views/ListOfBlogs.html'	
	})
	.when('/getblogbyid/:blogid',{
		controller:'BlogDetailController',
	templateUrl:'views/BlogPostDetail.html'	
	})
	
	.when('/approveblogpost/:blogid',{
		controller:'BlogDetailController',
	templateUrl:'views/BlogPostApprovalForm.html'	
	})
	
	.when('/profilepic',{
		templateUrl:'views/ProfileImage.html'
	})

	.when('/listOfSuggestedUser',{
		controller:'FriendController',
	templateUrl:'views/SuggestedUser.html'	
	})
	.when('/listOfPendingReq',{
		controller:'FriendController',
	templateUrl:'views/PendingRequest.html'	
	})
	
	.when('/getUserDetails/fromId',{
		controller:'FriendDetailController',
	templateUrl:'views/FrUserDetails.html'	
	})
	
	
	.when('/chat',{
		templateUrl:'views/chat.html',
		controller:'ChatCtrl'
	})
	.otherwise({
		templateUrl : 'views/UserHome.html'

	})
})

app.run(function($rootScope,$cookieStore,UserService,JobService,$location){
	if($rootScope.currentUser==undefined)
		$rootScope.currentUser=$cookieStore.get("currentUser")
		
		/*$rootScope.logout = function() {
		
		UserService.logout().then(function(response) {
			$rootScope.message = "logged out successfully.."
			delete $rootScope.currentUser;
			$cookieStore.remove("currentUser")
			$location.path('/validateUser')

		}, function(response) {
			console.log(response.status)
			$rootScope.message=response.data.message
			$location.path('/validateUser')
		})
*/
	

})