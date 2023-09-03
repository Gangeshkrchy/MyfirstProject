/*function validation(frm){
	//empty the error message
	document.getElementById("enameErr").innerHTML="";
	document.getElementById("jobErr").innerHTML="";
	document.getElementById("salErr").innerHTML="";
	
	//read the data form
	let ename=frm.ename.value;
	let job=frm.job.value;
	let sal=frm.sal.value;
	
	let flag=true;
	
	//from validation from client side
	
	if(ename==""){
		document.getElementById("enameErr").innerHTML="employee name is required(cs)";
	 flag=false;
	}
	else if(ename.length>20){
		document.getElementById("enameErr").innerHTML="employee name must have 20 character(cs)";
		flag=false;
	}
	else if(job==""){
		document.getElementById("jobErr").innerHTML="employee job is required";
		flag=false;
		
	}
	else if(job.length>20){
		document.getElementById("jobErr").innerHTML="employee job must have 20 character(cs)";
		flag=false;
		}
		else if(sal==""){
			document.getElementById("salErr").innerHTML="employee salary is  required(cs)";
			flag=false;
			
		}
		else if(isNaN(sal)){
			document.getElementById("salErr").innerHTML="employee salary must numeric value(cs)";
			flag=false;
			}
			else if(sal<0 || sal>1000000){
				document.getElementById("salErr").innerHTML="employee salary must have in the range of 1 to 1000000(cs)";
			    flag=false;
			}
			
			
			return flag
}*/