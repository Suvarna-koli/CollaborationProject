/**
 * 
 */
app.factory('UserService',function($http){
var userService={};
var BASE_URL="http://localhost:8087/BackEndDemo/User"
	
userService.registerUser=function(user){
	alert("in service")
	return $http.post(BASE_URL+"/registerUser",user)
	
}
userService.validateUser=function(user){
	return $http.post(BASE_URL+"/validateUser",user)
}
userService.logout=function(){
	alert("in service")
	return $http.post(BASE_URL+"/logout")
}

userService.getUser=function(){
	return $http.get(BASE_URL+"/getUser")
}
userService.updateUser=function(user){
	return $http.put(BASE_URL+"/updateUser",user)
}

return userService;//it will returning the instace

})




/*app.factory('PersonService',function($http){
var personService={};
personService.getAllPersons=function(){
	$http.get("http://localhost:8087/BackEndDemo/Person/getallPersons")
	
}
return personService;

})*/