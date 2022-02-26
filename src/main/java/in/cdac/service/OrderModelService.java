package in.cdac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import in.cdac.model.OrderModel;
import in.cdac.repository.OrderModelRepository;

@Service
public class OrderModelService {
	
	@Autowired
	OrderModelRepository orderModelRepository;

	public List<OrderModel> readByOtherThanPrimaryKey() {
		// ...more logic
		return orderModelRepository.customFindAllNativeSQLQuery();
	}
	
	
	public OrderModel createOrder(OrderModel orderModel) {
		// ...more business logic
		orderModelRepository.save(orderModel);
		return orderModel;
	}
	
	
	public Optional<OrderModel> readOrderById(Long id) {
		return orderModelRepository.findById(id);
	}
	
	public List<OrderModel> readAllOrder(boolean sortingorder, String sortBy) {
		sortBy = sortBy != null ? sortBy : "id"; 
		
		Direction dir = sortingorder ? Direction.ASC : Direction.DESC; 
		List<OrderModel> list = orderModelRepository.findAll(Sort.by(dir, sortBy));
		return list;
	}
	
	
	public boolean deleteOrderById(Long id) {
		orderModelRepository.deleteById(id);
		return true;
	}

}
