/**
 * 
 */
app.controller('FriendController',function($scope,$location,FriendService){
	function listOfSuggestedUser(){
		alert("Frnd controller")
		FriendService.listOfSuggestedUser().then(function(response){
			$scope.suggestedUsers=response.data
		},function(response){
			if(response.status==401)
		$location.path('/validateUser')
		console.log(response.status)
		})
		}
	
	listOfSuggestedUser()
	getPendingRequest()
	
	function getPendingRequest(){
		FriendService.getPendingRequest().then(function(response){
			$scope.pendingRequest=response.data
		},function(response){
			if(response.status==401)
		$location.path('/validateUser')
		console.log(response.status)
		})
		}
	function addFriendRequest(){
		FriendService.addFriendRequest(toId).then(function(response){
			listOfSuggestedUser()
			$location.path('/getSuggestedUser')
		},function(response){
			if(response.status==401)
		$location.path('/validateUser')
		console.log(response.status)
		})
		}
	
	
})