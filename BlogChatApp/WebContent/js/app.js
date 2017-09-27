/**
 * 
 */
var app=angular.module("app",['ngRoute','ngCookies']);
app.config(function($routeProvider){
	$routeProvider
	.when('/Navbar1',{
		
		templateUrl:'views/Navbar1.html'
	})
	.when('/Userhome',{
		
		templateUrl:'views/UserHome.html'
	})
	.when('/contactus',{
	
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
	.when('/showjob',{
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
	templateUrl:'views/BlogPostApprovalComment.html'	
	})
	
	.when('/saveforums',{
		controller:'ForumController',
	templateUrl:'views/ForumPost.html'	
	})
	.when('/getallforums',{
		controller:'BlogController',
	templateUrl:'views/ListOfForum.html'	
	})
	.when('/getforumbyid/:fid',{
		controller:'ForumDetailController',
	templateUrl:'views/ForumPostDetail.html'	
	})
	
	.when('/approveforumpost/:fid',{
		controller:'ForumDetailController',
	templateUrl:'views/ForumPostApprovalComment.html'	
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
	
	.when('/getUserDetails/:fromId',{
		controller:'FriendDetailController',
	templateUrl:'views/FrUserDetails.html'	
	})
	.when('/friendlist',{
		controller:'FriendController',
		templateUrl:'views/FriendList.html'
	})
	
	
	.when('/chat',{
		controller:'ChatCtrl',
		templateUrl:'views/chat.html'
		
	})
	.otherwise({
		templateUrl : 'views/UserHome.html'

	})
})

app.run(function($rootScope,$cookieStore,UserService,JobService,$location){
	if($rootScope.currentUser==undefined)
		$rootScope.currentUser=$cookieStore.get("currentUser")
		
	
		
		$rootScope.logout=function(){
		UserService.logout().then(function(response){
			$scope.logoutSuccess="logout ..Sccuessfully"
	delete $rootScope.currentUser
$cookieStore.remove("currentUser")
$location.path('/validateUser');
		},function(response){
			$scope.error=response.data
			$location.path('/validateUser')
		})
	}



})