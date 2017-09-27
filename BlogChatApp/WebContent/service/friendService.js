/**
 * 
 */
app.factory('FriendService',function($http){
	var friendService={};
	
	friendService.listOfSuggestedUser=function(){
		
		return $http.get("http://localhost:8087/BackEndDemo/getSuggestedUser")
	}
	friendService.addFriendRequest=function(toId){
		return $http.post("http://localhost:8087/BackEndDemo/addfriendrequest/"+toId)
	}
	friendService.getPendingRequest=function(){
		return $http.get("http://localhost:8087/BackEndDemo/getpendingrequest")
	}
	friendService.getUserDetails=function(fromId){
		alert(" in service")
		return $http.get("http://localhost:8087/BackEndDemo/getuserdetails/"+fromId)
	}
	friendService.updateRequest=function(pendingRequest){
		return $http.put("http://localhost:8087/BackEndDemo/updatependingrequest",pendingRequest)
	}
	friendService.getlistOfFriends=function()
	{	
		return $http.get("http://localhost:8087/BackEndDemo/getfriendlist")
	}
	
	return friendService;
	
})