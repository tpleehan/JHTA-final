package kr.co.jhta.erp.dao;

import java.util.List;
import java.util.Map;

import kr.co.jhta.erp.dto.ProductDto;
import kr.co.jhta.erp.vo.Product;

public interface ProductDao {

	Product getProductByName(String productName);

	void insertNewProduct(Product product);

	List<Product> getProductsByCategoryNo(int categoryNo);

	Product getProductByNo(int productNo);

	void updateProduct(Product product);

	void deleteProduct(int productNo);

	List<ProductDto> searchProducts(Map<String, Object> criteria);

	ProductDto getProductDetailByProductNo(int productNo);

	List<Product> getCategoriesByProductsNo(int productNo);

	int getSearchedProductsCount(Map<String, Object> criteria);

}
