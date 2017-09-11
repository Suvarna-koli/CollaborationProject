/**
 * 
 */
app.controller('BlogDetailController', function(BlogService, $scope,$location,routeParams) {
	var blogid = $routeParams.blogid
	
	$scope.blogPost = BlogService.getBlogPostById(blogid).then(function(response) {
		$scope.blogPost = response.data;
		console.log(response.data)
	}, function(response) {
		console.log(response.status)
		if (response.status == 401)
			$location.path('/validateUser')
	})

	$scope.UpdateBlogPost = function() {
		BlogService.UpdateBlogPost($scope.blogPost).then(function(response) {
			console.log(response.status)
			alert("Updated Successsfully")
			$location.path('/getallblogs')
		}, function(response) {
			if (response.status == 401)
				$location.path('/validateUser')
		})
	}
})
