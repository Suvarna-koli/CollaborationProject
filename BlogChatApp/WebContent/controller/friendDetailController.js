/**
 * 
 */
app.controller('FriendDetailController',function($scope,$location,$FriendService,$routeParams){
var fromId=$routeParams.fromId;
	function getUserDetails(){
		FriendService.getUserDetails(fromId).then(function(response){
			$scope.user=response.data
		},function(response){
			if(response.status==401)
		$location.path('/validateUser')
		console.log(response.status)
		})
		}
	
})