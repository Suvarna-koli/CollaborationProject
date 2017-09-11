/**
 * 
 */
app.controller('UserController',function(UserService,$scope,$rootScope,$location,$cookieStore){
	$scope.user={}
	if($rootScope.currentUser!=undefined){
	UserService.getUser().then(function(response){
		$scope.user=response.data;
		
	},function(response){
		//console.log(response.status)
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
			//console.log(response.status)
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
/*	$rootScope.logout=function(){
		alert("logout controller")
		UserService.logout().then(function(response){
		$scope.logoutSuccess="LoggedOut Successfully.."
			$location.path=('/validateUser');
		
		},function(response){
			alert("in errorlogout")
			$scope.error=response.data
			$location.path('/validateUser')
		})
	}*/
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
	
	$scope.logout=function(){
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


/*app.controller('PersonController',function(PersonService,$scope){
function getAllPersons(){
	alert("in person controller");
	PersonService.getAllPersons().then(function(response)
	{
		$scope.persons=response.data;
		console.log(response.status)
		
	},function(response){
		console.log(response.status)
		
	}		)

}	
getAllPersons();

})
*/
