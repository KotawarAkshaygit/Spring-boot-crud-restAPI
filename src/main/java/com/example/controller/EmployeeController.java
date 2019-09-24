package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.EmployeeDaoImpl;
import com.example.entity.Employee;
import com.example.exception.RecordNotFoundException;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeDaoImpl edao;

	@GetMapping
	public List<Employee> getListEmployee() {

		// List<Employee> elist =
		return edao.allEmployees();

	}

	@PostMapping(consumes = { "application/json" })
	public ResponseEntity<Employee> save(@Valid @RequestBody Employee e) {

		edao.save(e);
		return new ResponseEntity<Employee>(e, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}")
	@DeleteMapping()
	public String del(@PathVariable("id") int id) {
		System.out.println("deleting id is" + id);

		int de = edao.delete(id);
		if (de <= 0) {
			throw new RecordNotFoundException("Given Id Does Not Exist Please Provide Valid id");
		}
		return "SuccessFully deleted Given Id details";

	}

	@PutMapping
	public String update(@Valid @RequestBody Employee e) {
		int res = edao.updateEmp(e);
		if (res <= 0) {
			throw new RecordNotFoundException("Given Details Not Updated Please Provide Valid Details");
		} else {
			System.out.println("successfully updated of id" + e.getId());
			return "Updated successfully";
		}
	}

	@GetMapping("/{ids}")
	public Employee retriveById(@PathVariable("ids") int ids) {
		Employee e = edao.retriveById(ids);
		if (e.getId() == 0) {
			throw new RecordNotFoundException("Given Id does Not exist Please Enter Correct Employee Id");
		}
		return e;

	}

}
