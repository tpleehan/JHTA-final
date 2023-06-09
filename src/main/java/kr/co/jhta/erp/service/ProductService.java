package kr.co.jhta.erp.service;

import java.util.List;
import java.util.Map;

import kr.co.jhta.erp.dto.ProductDto;
import kr.co.jhta.erp.vo.Product;

public interface ProductService {

	boolean insertProduct(Product product);

	List<Product> getProductsByCategoryNo(int categoryNo);

	Product getProductByNo(int productNo);

	void updateProduct(Product product);

	void deleteProduct(int productNo);

	Map<String, Object> searchProducts(Map<String, Object> criteria);

	ProductDto getProductDetailByProductNo(int productNo);
}
