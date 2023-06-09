package kr.co.jhta.erp.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import kr.co.jhta.erp.utils.DateUtils;
import kr.co.jhta.erp.utils.NumberUtils;

@Alias("EmployeeDetailDto")
public class EmployeeDetailDto {
	private int no;
	private String name;
	private Date hireDate;
	private int salary;
	private String tel;
	private String retired;
	private String email;
	private String imageName;
	private int managerNo;
	private int departmentNo;
	private String departmentName;
	private String departmentLocation;
	private int gradeNo;
	private String gradeName;
	private int gradeSalary;

	public String getRetired() {
		return retired;
	}

	public void setRetired(String retired) {
		this.retired = retired;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmployeeDetailDto() {
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getManagerNo() {
		return managerNo;
	}

	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;
	}

	public int getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(int departmentNo) {
		this.departmentNo = departmentNo;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentLocation() {
		return departmentLocation;
	}

	public void setDepartmentLocation(String departmentLocation) {
		this.departmentLocation = departmentLocation;
	}

	public int getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public int getGradeSalary() {
		return gradeSalary;
	}

	public void setGradeSalary(int gradeSalary) {
		this.gradeSalary = gradeSalary;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getFmtDate() {
		return DateUtils.dateToString(hireDate);
	}

	public String getNumberWithCommaSalary() {
		return NumberUtils.numberWithComma(salary);
	}

	@Override
	public String toString() {
		return "EmployeeDetailDto [no=" + no + ", name=" + name + ", hireDate=" + hireDate + ", salary=" + salary
			+ ", tel=" + tel + ", managerNo=" + managerNo + ", departmentNo="
			+ departmentNo + ", departmentName=" + departmentName + ", departmentLocation=" + departmentLocation
			+ ", gradeNo=" + gradeNo + ", gradeName=" + gradeName + ", gradeSalary=" + gradeSalary + "]";
	}

}
