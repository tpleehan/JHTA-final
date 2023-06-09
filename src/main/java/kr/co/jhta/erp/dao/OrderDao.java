package kr.co.jhta.erp.dao;

import java.util.List;

import kr.co.jhta.erp.dto.OrderItemDto;
import kr.co.jhta.erp.dto.OrderProductDto;
import kr.co.jhta.erp.form.OrderRegisterForm;
import kr.co.jhta.erp.vo.Order;
import kr.co.jhta.erp.vo.OrderItem;

public interface OrderDao {

	void insertOrder(OrderRegisterForm orderRegisertForm);

	void insertOrderItem(OrderItem orderItem);

	List<Order> getOrderByNo(int orderNo);

	void updateOrder(Order order);

	void deleteOrder(int orderNo);

	OrderProductDto getAllOrderProduct();

	void signOrder(int orderNo);

	List<OrderItem> getOrderItemsByOrderNo(int orderNo);

	List<OrderItemDto> getOrderItemDetailsByOrderNo(int orderNo);

	Order getOrderByOrderNo(int orderNo);
	//List<Order> searchOrders(); 발주내역 조회    매장? - 발주내역 확인?

}
