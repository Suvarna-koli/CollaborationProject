/**
 * 
 */
app.factory('JobService',function($http){
var jobService={};

	
jobService.saveJob=function(job){
	alert("in service")
	console.log(job)
	return $http.post("http://localhost:8087/BackEndDemo/saveJob",job)
	
}
jobService.getAllJobs=function(){

	return $http.get("http://localhost:8087/BackEndDemo/getAllJobs")
	
}
jobService.getJobById=function(id){

	return $http.get("http://localhost:8087/BackEndDemo/getJobById"+id)
	
}

return jobService;//it will returning the instace

})
