/**
 * 
 */
app.factory('ForumService',function($http){
	var forumService={};
	var BASE_URL="http://localhost:8087/BackEndDemo";
	forumService.saveForum=function(forumPost){
		return $http.post(BASE_URL+"/saveForum",forumPost)
	}
	forumService.getForumPostWaitingForApproval=function(){
		return $http.get(BASE_URL+"/getforum/"+0)
	}
	
	forumService.getForumPostApproved=function(){
		return $http.get(BASE_URL+"/getforum/"+1)
	}
	forumService.getForumById=function(fid){
		return $http.get(BASE_URL+"/getforumtbyid/"+fid)
	}
	forumService.UpdateForumPost=function(forumPost){
		return $http.put(BASE_URL+"/updateforumpost/",forumPost)
	}
	forumService.AddForumComment=function(forumComment){
		return $http.post(BASE_URL+"/commentOnforum/",forumComment)
	}
	forumService.GetForumComments=function(fid){
		return $http.get(BASE_URL+"/getforumcomments/"+fid)
	}
	return forumService;
})