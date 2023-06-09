package kr.co.jhta.erp.dao;

import java.util.List;

import kr.co.jhta.erp.dto.StoreFindDto;
import kr.co.jhta.erp.dto.StoreManagementDto;
import kr.co.jhta.erp.vo.Store;

public interface StoreDao {

	List<StoreFindDto> getStoreFinds(String keyword);

	List<Store> getAllStores();

	Store getStoreByNo(int storeNo);

	void updateStore(Store store);

	StoreManagementDto getStoreDetailByNo(int storeNo);

	List<StoreManagementDto> getAllStoreDetails();
	
}
