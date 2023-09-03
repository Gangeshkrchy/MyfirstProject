package com.nt.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.model.Employee;
import com.nt.service.EmployeeMgmtService;
import com.nt.validations.EmployeeValidator;

@Controller
public class EmployeeOperationController {

	@Autowired
	private EmployeeMgmtService service;
	@Autowired
	private EmployeeValidator empValidator;

	@GetMapping("/")
	public String showHome() {

		return "home";
	}

	@GetMapping("/report")
	public String showEmployeeReport(@PageableDefault(page=0,size=4,sort="job",direction=Sort.Direction.ASC) Pageable pageable,
			                                          Map<String, Object> map) {
		System.out.println("EmployeeOperationController.showEmployeeReport()");
		//List<Employee> list = service.getAllEmployee();
		Page<Employee> page=service.getEmployeePageData(pageable);
		map.put("empData", page);
		return "employee_report";
	}

	@GetMapping("/add")
	public String addEmployeeForm(@ModelAttribute("emp") Employee emp) {
		emp.setJob("Labour");
		return "employee_register";
	}

	@PostMapping("/add")
	public String addEmployee(RedirectAttributes attrs,
			@ModelAttribute("emp") Employee emp,BindingResult errors) {
		
		System.out.println("EmployeeOperationController.addEmployee()");
		
		//type misMatch error cfg
		
		  if(errors.hasFieldErrors())
			  
			  return "employee_register";
		  
		 // validate the class
		  if(empValidator.supports(emp.getClass())) {
		  empValidator.validate(emp,errors); 
		  if(errors.hasErrors()) 
			  return"employee_register";
		   
		 
			
			//application logics
			if(emp.getJob().equalsIgnoreCase("hacker")) {
				errors.rejectValue("job","job.reject");
				return "employee_register";
			}
		  }
		
		String result = service.RegisterEmployee(emp);
		attrs.addFlashAttribute("resultMsg",result);
		return "redirect:report";

	}
	@GetMapping("/edit")
	public String showEditEmployeeForm(@RequestParam("no") int no,
			                             @ModelAttribute("emp") Employee emp) {
		Employee emp1=service.getEmployeeByNo(no);
		BeanUtils.copyProperties(emp1,emp);
		
		return"employee_edit";
	}
	@PostMapping("/edit")
	public String editEmployee(@ModelAttribute("emp") Employee emp,
			                   RedirectAttributes attrs ,BindingResult errors ) {
		
		//type misMatch error cfg
		
		  if(errors.hasFieldErrors()) 
			  return "employee_edit";
		  
		  // validator method 
		  if(empValidator.supports(emp.getClass())) {
		  empValidator.validate(emp,errors);
		  if(errors.hasErrors())
			  return"employee_edit"; 
		  
		  }
		 
			
			//application logics
			if(emp.getJob().equalsIgnoreCase("hacker")) {
				errors.rejectValue("job","job.reject");
				return "employee_edit";
			}
						
		
			
		String msg=service.editEmployee(emp);
		attrs.addFlashAttribute("resultMsg",msg);
		return "redirect:report";
		
	}
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("no") int no,RedirectAttributes attrs) {
		String msg=service.deleteEmployee(no);
		attrs.addFlashAttribute("resultMsg",msg);
		return "redirect:report";
		
	}
	
	@ModelAttribute("coutriesInfo")
	public Set<String> populateCountries(){
		System.out.println("EmployeeOperationController.populateCountries()");
		Set<String> countrySet=service.getAllCountries();
		
		return countrySet;
	}
	

}
