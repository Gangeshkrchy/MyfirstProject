package com.nt.service;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nt.model.Employee;
import com.nt.repository.EmployeeRepo;
@Service("empService")
public class EmployeeMgmtServiceImpl implements EmployeeMgmtService {

	@Autowired
	private EmployeeRepo empRepo;
	
	@Override
	public List<Employee> getAllEmployee() {
		
		return empRepo.findAll();
	}

	@Override
	public String RegisterEmployee(Employee emp) {
		int idVal=empRepo.save(emp).getEmpno();
		return"Employee is saved with the id value:"+idVal;
	}

	@Override
	public Employee getEmployeeByNo(int no) {
		Employee emp=empRepo.findById(no).get();
		
		return emp;
	}

	@Override
	public String editEmployee(Employee emp) {
		int idVal=empRepo.save(emp).getEmpno();
		
		return " Employee is updated with the id value"+idVal;
	}

	@Override
	public String deleteEmployee(int no) {
		empRepo.deleteById(no);
		return no+" Emp No Employee is Deleted";
	}

	@Override
	public Page<Employee> getEmployeePageData(Pageable pageable) {
		Page<Employee> page=empRepo.findAll(pageable);
		return page;
	}

	@Override
	public Set<String> getAllCountries() {
		Locale locales[]=Locale.getAvailableLocales();
		
		Set<String> countrySet=new TreeSet();
		for(Locale l:locales) {
			if(l!=null)
				countrySet.add(l.getDisplayCountry());
		}
		return countrySet;
	}

}
