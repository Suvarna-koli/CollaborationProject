0/**
 * 
 */
app.factory('BlogService',function($http){
	var blogService={};
	var BASE_URL="http://localhost:8087/BackEndDemo";
	blogService.saveBlog=function(blogPost){
		return $http.post(BASE_URL+"/saveBlog",blogPost)
	}
	blogService.getBlogPostsWaitingForApproval=function(){
		return $http.get(BASE_URL+"/getblogposts/"+0)
	}
	
	blogService.getBlogPostsApproved=function(){
		return $http.get(BASE_URL+"/getblogposts/"+1)
	}
	blogService.getBlogPostById=function(blogid){
		return $http.get(BASE_URL+"/getblogpostbyid/"+blogid)
	}
	blogService.UpdateBlogPost=function(blogPost){
		return $http.put(BASE_URL+"/updateblogpost/"+blogPost)
	}
	return blogService;
})