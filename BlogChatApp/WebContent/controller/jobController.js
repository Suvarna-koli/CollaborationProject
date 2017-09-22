/**
 * 
 */
app.controller('JobController',function(JobService,$scope,$location,$rootScope){
	$scope.showjob=false;
	
	function getAllJobs(){
		JobService.getAllJobs().then(function(response){
			alert("get job all show")
			
			//alert(jobs.jobTitle)
			
			$scope.jobs=response.data;
		
		},function(response){
			console.log(response.status)
			$location.path('/validateUser')
			error=response.data
		})
	}
	
	$scope.saveJob=function(){
		alert("controller save")
		JobService.saveJob($scope.job).then(function(response){
			console.log(response.status)
			//$location.path('/getAllJobs')//mapping url same as backend controller
			$location.path('/UserHome')
			alert("Successfully added")
		},function(response)
	{
		console.log(response.status)
		console.log(response.data)
	
		error=response.data
		$location.path('/validateUser')
		})
	}
	$scope.getJobById=function(id){
		alert("get job id controller")
		$scope.showjob=true
		JobService.getJobById(id).then(function(response){
			console.log(response.data)
			$scope.job=response.data
			$location.path('/showjob')
		},function(response){
			
			error=response.data
			alert(error.message)
			$location.path('/validateUser')
			
		})
		
	}
	getAllJobs()
	
})