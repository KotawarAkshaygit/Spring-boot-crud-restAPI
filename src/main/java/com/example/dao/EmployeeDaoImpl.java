package com.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.example.entity.Employee;
import com.example.exception.DuplicateEntryException;

@Repository
public class EmployeeDaoImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int save(Employee e) {

		try {
			String sql = "insert into employees(name,email,contact,dept)values(?,?,?,?)";
			int result = jdbcTemplate.update(sql, e.getName(), e.getEmail(), e.getContact(), e.getDept());

			return result;
		} catch (Exception es) {
			throw new DuplicateEntryException("Contact Should Be Unique");
		}

	}

	public List<Employee> allEmployees() {

		String url = "select * from employees";
		List<Employee> elist = jdbcTemplate.query(url, new ResultSetExtractor<List<Employee>>() {

			@Override
			public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {

				List<Employee> list = new ArrayList<Employee>();
				while (rs.next()) {
					Employee e = new Employee();
					e.setId(rs.getInt(1));
					e.setName(rs.getString(2));
					e.setEmail(rs.getString(3));
					e.setContact(rs.getString(4));
					e.setDept(rs.getString(5));
					list.add(e);
				}
				return list;
			}

		});
		return elist;
	}

	public int delete(int id) {

		String url = "delete from employees where id=?";
		int s = jdbcTemplate.update(url, id);

		return s;

	}

	public int updateEmp(Employee e) {

		String query = "update employees set name=?,email=?,contact=?,dept=? where id=?";

		int res = jdbcTemplate.update(query, e.getName(), e.getEmail(), e.getContact(), e.getDept(), e.getId());
		return res;

	}

	public Employee retriveById(int id) {
		Employee es = new Employee();

		String url = "select * from employees where id=?";

		return jdbcTemplate.query(url, new Object[] { id }, new ResultSetExtractor<Employee>() {
			@Override
			public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {

				// Employee list=new ArrayList<Employee>();
				while (rs.next()) {
					// Employee e=new Employee();
					es.setId(rs.getInt(1));
					es.setName(rs.getString(2));
					es.setEmail(rs.getString(3));
					es.setContact(rs.getString(4));
					es.setDept(rs.getString(5));
					// list.add(e);

				}
				return es;
			}
		});

	}
}

/*
 * e = jdbcTemplate.queryForObject(url, new Object[] { id }, new
 * RowMapper<Employee>() {
 * 
 * @Override public Employee mapRow(ResultSet rs, int rowNum) throws
 * SQLException {
 * 
 * Employee es = new Employee(); es.setId(rs.getInt(1));
 * es.setName(rs.getString(2)); es.setEmail(rs.getString(3));
 * es.setContact(rs.getString(4)); es.setDept(rs.getString(5)); if (es.getId()
 * <= 0) { throw new
 * CustomExceptions("Providing id does Not Exist in Database"); }
 * 
 * 
 * return es;
 * 
 * 
 * }
 * 
 * );
 */
