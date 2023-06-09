package kr.co.jhta.erp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jhta.erp.dao.EmployeeDao;
import kr.co.jhta.erp.dto.EmployeeDetailDto;
import kr.co.jhta.erp.dto.EmployeeExcelDto;
import kr.co.jhta.erp.dto.TotalStatusByDeptDto;
import kr.co.jhta.erp.dto.TotalStatusByGradeDto;
import kr.co.jhta.erp.dto.TotalStatusDto;
import kr.co.jhta.erp.vo.Employee;
import kr.co.jhta.erp.vo.HrPagination;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public EmployeeDetailDto getMyInfoByNo(int employeeNo) {

		return employeeDao.getMyInfoByNo(employeeNo);
	}

	public List<EmployeeDetailDto> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}

	@Override
	public Map<String, Object> searchByoption(Map<String, Object> criteria) {
		if (criteria.get("option") != null && criteria.get("option").equals("no")) {
			int keyword = Integer.parseInt((String)criteria.get("keyword"));
			criteria.put("keyword", keyword);
		}
		int totalSize = employeeDao.getTotalPageSize(criteria);

		Map<String, Object> employeesBypage = new HashMap<String, Object>();
		if (criteria.get("pageNo") == null || criteria.get("pageNo").equals(1)) {
			HrPagination pagination = new HrPagination(1, totalSize);
			criteria.put("beginIndex", pagination.getBeginIndex());
			criteria.put("endIndex", pagination.getEndIndex());
			employeesBypage.put("pagination", pagination);
		} else {
			HrPagination pagination = new HrPagination((Integer)criteria.get("pageNo"), totalSize);
			criteria.put("beginIndex", pagination.getBeginIndex());
			criteria.put("endIndex", pagination.getEndIndex());
			employeesBypage.put("pagination", pagination);
		}

		List<EmployeeDetailDto> employees = employeeDao.searchByOption(criteria);
		employeesBypage.put("employees", employees);
		return employeesBypage;
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);
	}

	@Override
	public void addOneEmployee(Employee employee) {
		employeeDao.addOneEmployee(employee);

	}

	@Override
	public void deleteEmployeeByNo(int[] employeeNos) {
		for (int employeeNo : employeeNos) {
			employeeDao.deleteEmployeeByNo(employeeNo);
		}
	}

	@Override
	public List<EmployeeDetailDto> getEmployeesByDepartmentNo(int no) {
		return employeeDao.getEmployeesByDepartmentNo(no);
	}

	@Override
	public void addExcelEmployee(EmployeeExcelDto employeeExcelDto) {
		employeeDao.addExcelEmployee(employeeExcelDto);

	}

	@Override
	public EmployeeDetailDto getEmployeeDetailByNo(int employeeNo) {
		return employeeDao.getEmployeeDetailByNo(employeeNo);
	}

	@Override
	public List<EmployeeDetailDto> getSelectEmployees(Map<String, Object> criteria) {

		return employeeDao.getSelectEmployees(criteria);
	}

	// 인원현황 찾기
	@Override
	public Map<String, Object> getPersonnelStatus(String year) {

		TotalStatusDto totalStatus = employeeDao.getTotalStatus(year);
		TotalStatusByGradeDto totalStatusByGrade = employeeDao.getTotalStatusByGrade(year);
		List<TotalStatusByDeptDto> totalStatusByDepts = employeeDao.getTotalStatusByDept(year);

		Map<String, Object> sumTotalStatus = new HashMap<String, Object>();
		sumTotalStatus.put("totalStatus", totalStatus);
		sumTotalStatus.put("totalStatusByGrade", totalStatusByGrade);
		sumTotalStatus.put("totalStatusByDepts", totalStatusByDepts);

		return sumTotalStatus;
	}

	@Override
	public List<EmployeeDetailDto> getAllEmployeesName() {
		// TODO Auto-generated method stub
		return employeeDao.getAllEmployeesName();
	}

}
