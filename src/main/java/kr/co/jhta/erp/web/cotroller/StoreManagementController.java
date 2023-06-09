package kr.co.jhta.erp.web.cotroller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.jhta.erp.dto.EmployeeDetailDto;
import kr.co.jhta.erp.dto.StoreManagementDto;
import kr.co.jhta.erp.service.EmployeeService;
import kr.co.jhta.erp.service.StoreManagementService;
import kr.co.jhta.erp.vo.Store;

@Controller
@RequestMapping("/storemanagement")
public class StoreManagementController {

	@Autowired
	private StoreManagementService storeManagementService;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/main.erp")
	public String main() {
		return "storemanagement/main";
	}

	// 수정폼 첫 실행 시
	@GetMapping("/modify.erp")
	public String modifyStore(Model model) {
		List<Store> stores = storeManagementService.getAllStores();
		model.addAttribute("stores", stores);

		return "storemanagement/modifyform";
	}

	// 수정사항을 폼으로 전달 받았을 때
	@PostMapping("/modify.erp")
	public String modifyStore(Store store) {
		Store changedStore = new Store();
		BeanUtils.copyProperties(store, changedStore);

		storeManagementService.updateStore(changedStore);

		return "redirect:/storemanagement/modify.erp?result=success";
	}

	@GetMapping("/storedetail.erp")
	@ResponseBody
	public Store storeDetail(@RequestParam("no") int storeNo) {
		return storeManagementService.getStoreByNo(storeNo);
	}

	// 관리자 등록/수정 첫 실행시
	@GetMapping("/storemanager.erp")
	public String manager(Model model) {
		List<Store> stores = storeManagementService.getAllStores();
		model.addAttribute("stores", stores);

		return "storemanagement/storemanager";
	}

	@GetMapping("/changeManager.erp")
	public String changeManager(@RequestParam("no") int no, @RequestParam("employeeNo") int employeeNo) {
		Store store = new Store();
		store.setNo(no);
		store.setEmployeeNo(employeeNo);
		storeManagementService.updateStoreManager(store);

		return "redirect:/storemanagement/storemanager.erp?result=success";
	}

	@GetMapping("/inquiry.erp")
	public String inquiryStore(Model model) {
		List<StoreManagementDto> stores = storeManagementService.getAllStoreDetails();
		model.addAttribute("stores", stores);

		return "storemanagement/inquiry";
	}

	@RequestMapping("/storeManagerDetail.erp")
	@ResponseBody
	public EmployeeDetailDto storeManagerDetail(@RequestParam("no") int employeeNo) {
		EmployeeDetailDto employee = employeeService.getEmployeeDetailByNo(employeeNo);
		return employee;
	}
}
