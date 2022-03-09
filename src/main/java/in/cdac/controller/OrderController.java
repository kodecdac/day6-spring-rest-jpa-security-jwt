package in.cdac.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.model.OrderModel;
import in.cdac.repository.OrderModelRepository;
import in.cdac.service.OrderModelService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderModelService orderModelService;

	
	@GetMapping("/query")
	public List<OrderModel> readByOtherThanPrimaryKey() {
		// return orderModelRepository.findByProductName("WIND");
		return orderModelService.readByOtherThanPrimaryKey();
	}
	
	
	@GetMapping("/{id}")
	public Optional<OrderModel> readOrderById(@PathVariable Long id) {
		return orderModelService.readOrderById(id);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<?> readAllOrder(boolean sortingorder, String sortBy) {
		List<OrderModel> list = orderModelService.readAllOrder(sortingorder, sortBy);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/")
	public ResponseEntity<?> createOrder(@RequestBody @Valid OrderModel orderModel) {
		log.info("Creating order {}", orderModel);
		
		OrderModel orderModelRes =  orderModelService.createOrder(orderModel);
		return new ResponseEntity<>(orderModelRes, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOrderById(@PathVariable Long id) {
		orderModelService.deleteOrderById(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/")
	public ResponseEntity<?> deleteOrderByIdV1(@RequestBody OrderModel orderModel) {
		orderModelService.deleteOrderById(orderModel.getId());
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/health")
	public ResponseEntity<?> healthCheck() {
		// return "Order Api Is UP!";
		return new ResponseEntity<>("Order Api Is UP!", HttpStatus.OK);
	}
}
