package kr.co.jhta.erp.web.cotroller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jhta.erp.dto.EmployeeDetailDto;
import kr.co.jhta.erp.dto.EmployeeExcelDto;
import kr.co.jhta.erp.form.EmployeeAddform;
import kr.co.jhta.erp.form.InternalMobilityAddForm;
import kr.co.jhta.erp.form.ManagementAddForm;
import kr.co.jhta.erp.service.AttendanceService;
import kr.co.jhta.erp.service.DepartmentSerivce;
import kr.co.jhta.erp.service.EmployeeService;
import kr.co.jhta.erp.service.HrhistoryService;
import kr.co.jhta.erp.service.JobGradeService;
import kr.co.jhta.erp.service.ManagementService;
import kr.co.jhta.erp.service.PayrollTableService;
import kr.co.jhta.erp.vo.Department;
import kr.co.jhta.erp.vo.Employee;
import kr.co.jhta.erp.vo.Management;
import kr.co.jhta.erp.vo.PayrollTable;
import kr.co.jhta.erp.vo.jobGrade;

@Controller
@RequestMapping("/hr")
public class HrController {

	@Value("${employeeImage.source.directory}")
	private String employeeImageFileDirectory;

	@Value("${employeeExcel.source.directory}")
	private String employeeExcelFileDirectory;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DepartmentSerivce departmentService;

	@Autowired
	private JobGradeService jobGradeService;

	@Autowired
	private HrhistoryService hrHistoryService;

	@Autowired
	private ManagementService managementService;

	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	private PayrollTableService payrollTableService;

	@RequestMapping("/hrm")
	public String hrm() {
		return "hr/hrm";
	}

	//부서 관련 컨트롤러
	@RequestMapping("/department")
	public String departments() {
		return "hr/department";
	}

	@GetMapping("/addDepartment")
	public String addDepartment(Department department) {
		departmentService.addDepartment(department);

		return "redirect:/hr/department.erp";
	}

	@RequestMapping("/modifyDepartment")
	public String modifyDepartment(Department department) {

		departmentService.updateDepartment(department);

		return "hr/department";
	}

	@RequestMapping("/deleteDepartment")
	public String deleteDepartment(@RequestParam("no") int departmentNo) {

		departmentService.delteDepartmentByNo(departmentNo);

		return "redirect:/hr/department.erp";
	}

	@GetMapping("/getDepartments")
	@ResponseBody
	public List<Department> getAlldepartments() {
		List<Department> departments = departmentService.getAllDepartments();
		return departments;
	}

	@RequestMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("employeeNo") int[] employeeNos) {
		System.out.println(employeeNos.length);
		employeeService.deleteEmployeeByNo(employeeNos);
		return "redirect:/hr/hrm.erp";
	}

	@RequestMapping("/searchDepartmentForm")
	public String searchDepartment() {
		return "hr/search_departmentform";
	}

	//사원 관련 컨트롤러 
	@GetMapping("/searchEmployees.erp")
	@ResponseBody
	public Map<String, Object> searchEmployees(
		@RequestParam(value = "option", required = false) String option,
		@RequestParam(value = "keyword", required = false) String keyword,
		@RequestParam(value = "startDate", required = false) String startDate,
		@RequestParam(value = "endDate", required = false) String endDate,
		@RequestParam(value = "pageNo", required = false) Integer pageNo) {

		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("keyword", keyword);
		criteria.put("option", option);
		criteria.put("startDate", startDate);
		criteria.put("endDate", endDate);
		criteria.put("pageNo", pageNo);

		Map<String, Object> employeesByPage = employeeService.searchByoption(criteria);

		return employeesByPage;
	}

	@RequestMapping("/searchGradeForm")
	public String searchGradeNo() {
		return "hr/search_gradeform";
	}

	@RequestMapping("/searchDepartments")
	@ResponseBody
	public List<Department> searchDepartments(
		@RequestParam(value = "option", required = false) String option,
		@RequestParam(value = "keyword", required = false) String keyword) {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("option", option);
		criteria.put("keyword", keyword);

		List<Department> departments = departmentService.searchDepartmentByOption(criteria);
		return departments;
	}

	@RequestMapping("/searchGrades")
	@ResponseBody
	public List<jobGrade> searchJobGrades(
		@RequestParam(value = "option", required = false) String option,
		@RequestParam(value = "keyword", required = false) String keyword) {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("option", option);
		criteria.put("keyword", keyword);

		List<jobGrade> grades = jobGradeService.searchGradeByOption(criteria);
		return grades;
	}

	@GetMapping("/getEmployeesByDepartmentNo.erp")
	@ResponseBody
	public List<EmployeeDetailDto> getEmployeesByDepartmentNo(@RequestParam("no") int no) {
		return employeeService.getEmployeesByDepartmentNo(no);

	}

	@PostMapping("/addemployee.erp")
	void addEmployee(EmployeeAddform employeeForm) throws Exception {
		Employee employee = new Employee();

		BeanUtils.copyProperties(employeeForm, employee);

		MultipartFile employeeImageFile = employeeForm.getImgFile();
		if (!employeeImageFile.isEmpty()) {
			String imageFileName = employeeImageFile.getOriginalFilename();
			employee.setImageName(imageFileName);

			FileCopyUtils.copy(employeeImageFile.getBytes(), new File(employeeImageFileDirectory, imageFileName));
		}

		employeeService.addOneEmployee(employee);
	}

	// 사원 등록
	@RequestMapping("/employeeRegisterForm")
	public String EmployeeRegisterFrom() {
		return "hr/employee_registerform";
	}

	// 엑셀로 사원 일괄등록
	@GetMapping("/employee_xlsx.erp")
	public String downloadXls(Model model) {

		List<EmployeeDetailDto> employees = employeeService.getAllEmployees();
		model.addAttribute("employees", employees);

		return "employeeListXlsView";
	}

	// 엑셀 파일 업로드
	@PostMapping("/addXlsx.erp")
	public String addXlsx(@RequestParam(value = "xlsxfile", required = false) MultipartFile xlsxFile) throws Exception {

		Workbook workbook = new XSSFWorkbook(xlsxFile.getInputStream());

		Sheet sheet = workbook.getSheetAt(0);
		int rowsCount = sheet.getLastRowNum();

		for (int index = 1; index <= rowsCount; index++) {
			// 1번째 행부터 순서대로 행을 조회하기
			Row row = sheet.getRow(index);

			// 각셀의 데이터 조회하기
			String name = row.getCell(0).getStringCellValue();
			String tel = row.getCell(1).getStringCellValue();
			String email = row.getCell(2).getStringCellValue();
			Integer departmentNo = (int)(row.getCell(3).getNumericCellValue());
			Integer gradeNo = (int)(row.getCell(4).getNumericCellValue());
			Date hireDate = row.getCell(5).getDateCellValue();

			EmployeeExcelDto employeeExcelDto = new EmployeeExcelDto();
			employeeExcelDto.setName(name);
			employeeExcelDto.setTel(tel);
			employeeExcelDto.setEmail(email);
			employeeExcelDto.setDepartmentNo(departmentNo);
			employeeExcelDto.setGradeNo(gradeNo);
			employeeExcelDto.setHireDate(hireDate);

			employeeService.addExcelEmployee(employeeExcelDto);
		}
		workbook.close();
		return "redirect:/hr/hrm.erp";
	}

	// 인사발령 관련 컨트롤러

	@RequestMapping("/search_internal_mobility.erp")
	public String searchInternalMobility() {
		return "hr/search_internal_mobility";
	}

	@RequestMapping("/internal_mobility_registerform.erp")
	public String internalMobilityRegisterform() {
		return "hr/internal_mobility_registerform";
	}

	@RequestMapping("/internal_mobility_status.erp")
	public String internalMobilityStatus() {
		return "hr/internal_mobility_status";
	}

	@RequestMapping("/getEmployee.erp")
	@ResponseBody
	public EmployeeDetailDto getEmployee(@RequestParam("empno") int employeeNo) {
		return employeeService.getEmployeeDetailByNo(employeeNo);
	}

	@RequestMapping("/add_internal_mobility.erp")
	public String addInternalMobilityForm(InternalMobilityAddForm internalMobilityAddform) throws Exception {

		hrHistoryService.addInternalMobility(internalMobilityAddform);
		return "hr/search_internal_mobility";
	}

	@RequestMapping("/searchOneEmployeeByNo.erp")
	@ResponseBody
	public EmployeeDetailDto searchOneEmployeeByNo(@RequestParam("no") int employeeNo) {
		return employeeService.getEmployeeDetailByNo(employeeNo);
	}

	// 인사발령 조회 컨트롤러
	@RequestMapping("/searchHrHistories.erp")
	@ResponseBody
	Map<String, Object> getHrHistories(
		@RequestParam(value = "option", required = false) String option,
		@RequestParam(value = "keyword", required = false) String keyword,
		@RequestParam(value = "startDate", required = false) String startDate,
		@RequestParam(value = "endDate", required = false) String endDate,
		@RequestParam(value = "pageNo", required = false) Integer pageNo
	) {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("option", option);
		criteria.put("keyword", keyword);
		criteria.put("startDate", startDate);
		criteria.put("endDate", endDate);
		criteria.put("pageNo", pageNo);

		Map<String, Object> hrHistories = hrHistoryService.searchHrHistories(criteria);
		return hrHistories;
	}

	// 엑셀 다운 컨트롤러
	@GetMapping("/employees_xlsx.erp")
	public String downloadEmployeesXls(@RequestParam(value = "keyword", required = false) String keyword,
		@RequestParam(value = "option", required = false) String option,
		@RequestParam(value = "startDate", required = false) String startDate,
		@RequestParam(value = "endDate", required = false) String endDate,
		Model model) {

		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("keyword", keyword);
		criteria.put("option", option);
		criteria.put("startDate", startDate);
		criteria.put("endDate", endDate);

		List<EmployeeDetailDto> selectEmployee = employeeService.getSelectEmployees(criteria);

		model.addAttribute("employees", selectEmployee);

		return "employeeListXlsView";
	}

	// 인사인원현황 컨트롤러
	@RequestMapping("/personnel_status.erp")
	public String personnelStatus() {
		return "hr/personnel_status";
	}

	@RequestMapping("/getPersonnelStatus.erp")
	@ResponseBody
	public Map<String, Object> getPersonnelStatus(@RequestParam(value = "year", required = false) String year) {
		return employeeService.getPersonnelStatus(year);
	}

	// 급여계산 컨트럴로
	@RequestMapping("/calculate_salary.erp")
	public String calculateSalary() {
		return "hr/calculate_salary";
	}

	// 급여계산 클릭 컨트롤러
	@RequestMapping("/calculateTotalPayment.erp")
	@ResponseBody
	public int calculateTotalPayment(@RequestParam("payMonth") String payMonth,
		@RequestParam("empNos") String[] selectedEmpNos, @RequestParam("payType") String paymentType) {
		int totalPayment = managementService.getTotalPayment(payMonth, selectedEmpNos, paymentType);
		return totalPayment;
	}

	// 총 지급급여 등록 컨트롤러
	@RequestMapping("/registerPayroll.erp")
	public String registerPayroll(@RequestParam("payDate") String payrollDate,
		@RequestParam("payMonth") String payrollMonth,
		@RequestParam("type") String payrollType, @RequestParam("selectedAmount") Integer payrollCount,
		@RequestParam("totalSalary") Integer payrollAmount) {
		PayrollTable payrollTable = new PayrollTable();
		payrollTable.setPayrollDate(payrollDate);
		payrollTable.setPayrollMonth(payrollMonth);
		payrollTable.setPayrollType(payrollType);
		payrollTable.setPayrollCount(payrollCount);
		payrollTable.setPayrollAmount(payrollAmount);

		payrollTableService.addPayrollTable(payrollTable);

		return "hr/salary_status";
	}

	// 총지급급여 조회 컨트롤러
	@RequestMapping("/searchPayrollTables")
	@ResponseBody
	public Map<String, Object> searchPayrollTable(
		@RequestParam(value = "startDate", required = false) String startDate,
		@RequestParam(value = "endDate", required = false) String endDate,
		@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("startDate", startDate);
		criteria.put("endDate", endDate);
		criteria.put("pageNo", pageNo);

		return payrollTableService.searchPayrollTable(criteria);
	}

	// 출근기록 확정 컨트롤러
	@RequestMapping("/attendanceFixed.erp")
	@ResponseBody
	public List<Management> attendanceFixed(@RequestParam("payMonth") String payMonth) {
		return attendanceService.attendanceFixed(payMonth);

	}

	// 근무기록 컨트롤러
	@RequestMapping("/attendance_history.erp")
	public String attendanceHistory() {
		return "hr/attendance_history";
	}

	// 출근기록 컨트롤러
	@RequestMapping("/searchAttendances.erp")
	@ResponseBody
	public Map<String, Object> searchAttendances(
		@RequestParam(value = "option", required = false) String option,
		@RequestParam(value = "keyword", required = false) String keyword,
		@RequestParam(value = "startDate", required = false) String startDate,
		@RequestParam(value = "endDate", required = false) String endDate,
		@RequestParam(value = "pageNo", required = false) Integer pageNo
	) {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("option", option);
		criteria.put("keyword", keyword);
		criteria.put("startDate", startDate);
		criteria.put("endDate", endDate);
		criteria.put("pageNo", pageNo);

		Map<String, Object> attendances = attendanceService.searchAttendances(criteria);
		return attendances;
	}

	// 급여조회 컨트롤러
	@RequestMapping("/salary_status.erp")
	public String attendanceStatus() {
		return "hr/salary_status";
	}

	// 근태 근태입력 컨트롤러
	@RequestMapping("/management_registerform.erp")
	public String managementResiterform() {
		return "hr/management_registerform";
	}

	// 근태입력 컨트롤러
	@RequestMapping("/add_management.erp")
	public String addManagement(ManagementAddForm managementAddForm) throws Exception {
		managementService.addManagement(managementAddForm);
		return "hr/search_management";
	}

	// 근태조회 컨트롤러
	@RequestMapping("/searchManagementEmp.erp")
	@ResponseBody
	public Map<String, Object> searchManagement(
		@RequestParam(value = "option", required = false) String option,
		@RequestParam(value = "keyword", required = false) String keyword,
		@RequestParam(value = "startDate", required = false) String startDate,
		@RequestParam(value = "endDate", required = false) String endDate,
		@RequestParam(value = "pageNo", required = false) Integer pageNo
	) {
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("option", option);
		criteria.put("keyword", keyword);
		criteria.put("startDate", startDate);
		criteria.put("endDate", endDate);
		criteria.put("pageNo", pageNo);

		Map<String, Object> management = managementService.searchManagment(criteria);
		return management;
	}

	@RequestMapping("/search_management.erp")
	public String searchManagement() {
		return "hr/search_management";
	}
}
