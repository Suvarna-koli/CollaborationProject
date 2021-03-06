/**
 * 
 */
app.controller('BlogDetailController', function(BlogService,$scope,$location,$routeParams,$rootScope) {
	
	var blogid=$routeParams.blogid
	
	$scope.blogPost= BlogService.getBlogPostById(blogid).then(function(response) {
		
		$scope.blogPost= response.data
		console.log(response.status)
	}, function(response) {
		console.log(response.status)
		if (response.status == 401)
			$location.path('/validateUser')
	})

	$scope.UpdateBlogPost = function(blogPost) {
		
		BlogService.UpdateBlogPost($scope.blogPost).then(function(response) {
			console.log(response.status)
			alert("Updated Successsfully")
			$location.path('/getallblogs')
		}, function(response) {
			alert("unsuccessful to update blog")
			if (response.status == 401)
				$location.path('/validateUser')
				console.log(error.message)
		})
	}
	
	$scope.savecomment=function(){
		
		$scope.blogComment.blogPost=$scope.blogPost
		BlogService.AddBlogComment($scope.blogComment).then(function(response){
			alert("Saved successfully")
		},function(response){
			console.log(response.status)
			if(response.status==401)
			$location.path('/validateUser')
			$scope.error=response.data
			console.log(error.message)
		})
	}
	
	
	

	$scope.GetBlogComments=function(blogid){

		BlogService.GetBlogComments(blogid).then(function(response){
			console.log(response.data)
			console.log(response.status)
			$scope.blogcomments=response.data
			
			
		},function(response)
		{
			console.log(response.status)
			if(response.status==401)
			$location.path('/validateUser')
			$scope.error=response.data
			
		})
	}

})
