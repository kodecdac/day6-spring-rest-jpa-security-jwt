package in.cdac.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import lombok.Data;

@Data
@Entity
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// UNIQUE CONSTRAINT
	@Column(unique = true) 
	private String username;
	private String password;
	
	@Email
	private String email;
	private String mobile;
}
