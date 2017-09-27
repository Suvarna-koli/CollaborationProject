/**
 * 
 */
app.controller('FriendController',function($scope,$location,FriendService){
	function listOfSuggestedUser(){
		/*alert("Frnd controller")*/
		FriendService.listOfSuggestedUser().then(function(response){
			$scope.suggestedUsers=response.data
			//$location.path('/listOfSuggestedUser')
			
		},function(response){
			if(response.status==401)
		$location.path('/validateUser')
		//console.log(response.status)
		$location.path('/listOfSuggestedUser')
		
		})
		}
	
	listOfSuggestedUser()
	getPendingRequest()
	getlistOfFriends()
	//addFriendRequest()
	
	function getPendingRequest(){
		FriendService.getPendingRequest().then(function(response){
			$scope.pendingRequest=response.data
		},function(response){
			if(response.status==401)
		$location.path('/validateUser')
		console.log(response.status)
		})
		}
	$scope.addFriendRequest=function(toId){
		/*alert(toId)*/
		FriendService.addFriendRequest(toId).then(function(response){
			listOfSuggestedUser()
			$location.path('/listOfSuggestedUser')
		},function(response){
			if(response.status==401)
		$location.path('/validateUser')
		console.log(response.status)
		})
		}
	function getlistOfFriends(){
		FriendService.getlistOfFriends().then(function(response){
			console.log(response.status)
			$scope.friendlist=response.data
		},function(response){	
			if(response.status==401)
			$location.path('/validateUser')
			console.log(response.status)
	})
	}
	$scope.updateRequest=function(request,status)
	{
		request.status=status
		FriendService.updateRequest(request).then(function(response){
		alert("Rquest Accept Successfully")	
		
		},function(response){
			if(response.status==401)
				$location.path('/validateUser')
				console.log(response.status)
		})
	}
	
	
})