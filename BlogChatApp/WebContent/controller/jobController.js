/**
 * 
 */
app.controller('JobController',function(JobService,$scope,$location,$rootScope){
	$scope.showjobDetails=false;
	
	function getAllJobs(){
		JobService.getAllJobs().then(function(response){
			alert("get job all show")
			
			//alert(job.jobTitle)
			
			$scope.jobs=response.data;
		
		},function(response){
			console.log(response.status)
			$location.path('/validateUser')
			error=response.data
		})
	}
	/*$scope.saveJob=function(){
		alert("controller save")
		JobService.saveJob($scope.job).then(function(response){
			$location.path('/getAllJobs')
	},function(response)
	{
		console.log(response.status)
	if(response.status==401){
		alert("401")
		$scope.error=response.data
		$location.path('/validateUser')
	}
		if(response.status==500){
			$scope.error=response.data
			ERROR=response.data
			alert(ERROR.message)
		$location.path('/saveJob')	
		}
		$location.path('/UserHome')
		})
	}*/
	$scope.saveJob=function(){
		alert("controller save")
		JobService.saveJob($scope.job).then(function(response){
			console.log(response.status)
			$location.path('/getAllJobs')//mapping url same as backend controller
			//$location.path('/UserHome')
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
		$scope.showjobDetails=true
		JobService.getJobById(id).then(function(response){
			console.log(response.data)
			$scope.job=response.data
			$location.path('/getJobById')
		},function(response){
			
			error=response.data
			alert(error.message)
			$location.path('/validateUser')
			
		})
		
	}
	getAllJobs()
	
})