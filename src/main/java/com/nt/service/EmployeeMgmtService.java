package com.nt.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nt.model.Employee;

public interface EmployeeMgmtService {
	
	public List<Employee> getAllEmployee();
	
	public String RegisterEmployee(Employee emp);
	
	public Employee getEmployeeByNo(int no);
	
	public String editEmployee(Employee emp);
	
	public String deleteEmployee(int no);
	
	public Page<Employee> getEmployeePageData(Pageable pageable);
	
	public Set<String> getAllCountries();

}
