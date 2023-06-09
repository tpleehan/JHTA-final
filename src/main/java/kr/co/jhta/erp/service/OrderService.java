package kr.co.jhta.erp.service;

import java.util.List;

import kr.co.jhta.erp.dto.OrderProductDto;
import kr.co.jhta.erp.form.OrderRegisterForm;
import kr.co.jhta.erp.vo.Order;

public interface OrderService {

	List<Order> getOrderByNo(int orderNo);

	void updateOrder(Order order);

	void deleteProduct(int orderNo);

	void insertOrder(OrderRegisterForm orderRegisterForm);

	OrderProductDto getAllOrderProduct(int orderProductNo);
}
