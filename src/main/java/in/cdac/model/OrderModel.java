package in.cdac.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class OrderModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String productId;
	
	@Size(min = 3, max=10)
	private String productName;
	
	@Min(1)
	@Max(10)
	private int productQty;
	
	private double productPrice;
	
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate purchaseDate;
	private boolean offer;
	
}
