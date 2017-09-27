/**
 * 
 */
app.controller('UserController',function(UserService,$scope,$rootScope,$location,$cookieStore){
	$scope.user={}
	if($rootScope.currentUser!=undefined){
	UserService.getUser().then(function(response){
		$scope.user=response.data;
		
	},function(response){
		
		$location.path('/Userhome')
	})
	}
	
	$scope.registerUser=function(){
		alert("controller")
		UserService.registerUser($scope.user).then(function(response){
		$scope.message='Registered Successfully, plz login'
			console.log(response.status)
			
			$location.path('/validateUser')
	},function(response)
	{
		console.log(response.status)
		console.log(response.data)
		error=response.data
		alert(error.message)
		
	})
	}
	
	$scope.validateUser=function(){
		alert("validate controller")
		UserService.validateUser($scope.user).then(function(response){
			console.log(response.data)
			
			$rootScope.currentUser=response.data
			$cookieStore.put("currentUser",response.data)
			
			$location.path('/Userhome')
	},function(response)
	{
		$scope.error=response.data
		console.log(response.status)
		$location.path('/validateUser')
		
	}
	)}
	$scope.updateUser=function(){
		UserService.updateUser($scope.user).then(function(response){
			alert("updated successfully")
			$location.path('/UserHome')
		},function(response){
			console.log(response.data)
			if(response.status==401)
				$location.path('/validateUser')
				$location.path('/editProfile')
			
		})
	}
	
	
})



