package kr.co.jhta.erp.web.cotroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jhta.erp.dto.EmployeeDetailDto;
import kr.co.jhta.erp.service.LoginService;
import kr.co.jhta.erp.vo.Employee;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping("/login.erp")
	public String login(@RequestParam("employeeno") int employeeNo, @RequestParam("password") String password,
		HttpSession session) {

		Employee employee = loginService.login(employeeNo, password);
		if (employee == null) {
			return "redirect:/home.erp";
		}
		EmployeeDetailDto detail = loginService.getEmployeeDto(employeeNo);

		session.setAttribute("LE", employee);
		session.setAttribute("LE_detail", detail);

		return "redirect:/main.erp";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home.erp";
	}

}
