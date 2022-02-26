package in.cdac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.cdac.model.OrderModel;

@Repository
public interface OrderModelRepository extends JpaRepository<OrderModel, Long> {

	// Method Declration
	List<OrderModel> findByProductName(String productName);
	
	// ASSIGNMENT
	// Reading by two columns, ProductName and ProductPrice
	// REGESH LIKE QUERY
	
	
	// NativeSQL
	// SELECT * FROM ORDER_MODEL;
	@Query(nativeQuery = true, value = "SELECT * FROM order_model")
	List<OrderModel> customFindAllNativeSQLQuery();
}
