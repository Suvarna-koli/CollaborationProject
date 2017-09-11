/**
 * 
 */
app.factory('FriendService',function($http){
	var friendService={}
	
	friendService.listOfSuggestedUser=function(){
		return $http.get("http://localhost:8087/BackEndDemo/getSuggestedUser")
	}
	friendService.addFriendRequest=function(toId){
		return $http.post("http://localhost:8087/BackEndDemo/addfriendrequest",+toId)
	}
	friendService.getPendingRequest=function(){
		return $http.get("http://localhost:8087/BackEndDemo/getpendingrequest")
	}
	friendService.getUserDetails=function(fromId){
		return $http.get("http://localhost:8087/BackEndDemo/getuserdetails",+fromId)
	}
	friendService.updatePendingRequest=function(pendingRequest){
		return $http.put("http://localhost:8087/BackEndDemo/updatependingrequest",+pendingRequest)
	}
	return friendService;
	
})