/**
 * 
 */
app.controller('UserController',function(UserService,$scope,$rootScope,$location){

	
	$scope.user={}
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
		
	})
	}
	
	$scope.validateUser=function(){
		alert("validate controller")
		UserService.validateUser($scope.user).then(function(response){
			console.log(response.data)
			//console.log(response.status)
			$rootScope.currentUser=response.data
			$location.path('/UserHome')
	},function(response)
	{
		$scope.error=response.data
		console.log(response.status)
		$location.path('/validateUser')
		
	}
	)}	
	$rootScope.logout=function(){
		alert("logout controller")
		UserService.logout().then(function(response){
		$scope.logoutSuccess="LoggedOut Successfully.."
			$location.path=('/validateUser');
		
		},function(response){
			$scope.error=response.data
			$location.pat('/validateUser')
		})
	}
	$scope.updateUser=function(){
		UserService.updateUser($scope.user).then(function(response){
			
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
