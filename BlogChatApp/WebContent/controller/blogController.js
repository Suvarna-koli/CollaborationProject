/**
 * 
 */
app.controller('BlogController',function(BlogService,$scope,$location){
	BlogService.getBlogPostsWaitingForApproval().then(function(response){
		$scope.blogWaiting=response.data;
	},function(response){
		
			$location.path('/validateUser')
	})
	
	
	BlogService.getBlogPostsApproved().then(function(response){
		$scope.blogPostsApproved=response.data;
		
	},function(response){
		if(response.status==401)
			$location.path('/validateUser')
	})
	$scope.saveBlog=function(){

		BlogService.saveBlog($scope.blogPost).then(function(response){
			console.log(response.status)
			console.log(response.data)
			alert("blog Saved successfully")
			
			$location.path('/UserHome')
			
		},function(response){
			
			error=response.data
			alert(error.message)
			$location.path('/validateUser')
		
			$location.path('/saveblogs')
		})
	}
})
