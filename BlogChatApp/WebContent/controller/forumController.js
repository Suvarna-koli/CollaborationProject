/**
 * 
 */
app.controller('ForumController',function(ForumService,$scope,$location){
	ForumService.getForumPostWaitingForApproval().then(function(response){
		$scope.forumWaiting=response.data;
	},function(response){
		
			$location.path('/validateUser')
	})
	
	
	ForumService.getForumPostApproved().then(function(response){
		$scope.forumPostsApproved=response.data;
		
	},function(response){
		if(response.status==401)
			$location.path('/validateUser')
	})
	$scope.saveForum=function(){
		alert("controller")
		ForumService.saveForum($scope.forumPost).then(function(response){
			console.log(response.status)
			console.log(response.data)
			alert("Forum Saved successfully")
			
			$location.path('/UserHome')
			
		},function(response){
			
	
			error=response.data
			alert(error.message)
			$location.path('/validateUser')
			
			$location.path('/saveforums')
		})
	}
})
