package kr.co.jhta.erp.dao;

import java.util.List;

import kr.co.jhta.erp.dto.FactoryOrderItemDto;
import kr.co.jhta.erp.form.FactoryOrderAddForm;
import kr.co.jhta.erp.vo.FactoryOrder;
import kr.co.jhta.erp.vo.FactoryOrderItem;

public interface FactoryOrderDao {

	void insertOrder(FactoryOrderAddForm orderForm);

	void insertOrderItem(FactoryOrderItem orderItem);

	List<FactoryOrderItemDto> getFactoryOrderItemsByOrderNo(int factoryOrderNo);

	void signFactoryOrder(int orderNo);

	FactoryOrder getFactoryOrderByNo(int factoryOrderNo);

}
