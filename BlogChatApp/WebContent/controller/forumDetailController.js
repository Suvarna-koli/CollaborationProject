/**
 * 
 */
app.controller('ForumDetailController', function(ForumService,$scope,$location,$routeParams,$rootScope) {

	var fd=$routeParams.fid
	
	$scope.forumPost= ForumService.getForumById(fid).then(function(response) {
		
		$scope.forumPost= response.data
		console.log(response.status)
	}, function(response) {
		console.log(response.status)
		if (response.status == 401)
			$location.path('/validateUser')
	})

	$scope.UpdateForumPost = function(forumPost) {

		ForumService.UpdateForumPost($scope.forumPost).then(function(response) {
			console.log(response.status)
			alert("Updated Successsfully")
			$location.path('/getallforums')
		}, function(response) {
			alert("unsuccessful to update Forum")
			if (response.status == 401)
				$location.path('/validateUser')
				console.log(error.message)
		})
	}
	
	$scope.savecomment=function(){
		
		$scope.forumComment.forumPost=$scope.forumPost
		ForumService.AddForumComment($scope.forumComment).then(function(response){
			alert("Saved successfully")
		},function(response){
			console.log(response.status)
			if(response.status==401)
			$location.path('/validateUser')
			$scope.error=response.data
			console.log(error.message)
		})
	}
	
	
	

	function GetForumComments(){
		
		ForumService.GetForumComments(fid).then(function(response){
			console.log(response.data)
			console.log(response.status)
			$scope.forumcomments=response.data
			
			
		},function(response)
		{
			console.log(response.status)
			if(response.status==401)
			$location.path('/validateUser')
			$scope.error=response.data
			
		})
	}
	GetForumComments();
})
