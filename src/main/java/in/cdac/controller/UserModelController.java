package in.cdac.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.model.UserModel;
import in.cdac.service.UserModelService;

import java.util.List;


@Validated
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserModelController {
	
	@Autowired
	UserModelService userModelService;

	// http://localhost:8080/user/
	@PostMapping("/")
	public ResponseEntity<?> createUser(@RequestBody @Valid UserModel userModel) {
		userModelService.createUser(userModel);
		return new ResponseEntity<>(userModel, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readByUserName(@PathVariable long id) {
		UserModel userModel =  userModelService.readById(id);
		return new ResponseEntity<>(userModel, HttpStatus.OK);
	}

	// http://localhost:8080/user/
	@GetMapping("/")
	public ResponseEntity<?> readAlluser() {
		List<UserModel> userList =  userModelService.readAllUser();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}

	// http://localhost:8080/user/2
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable long id) {
		userModelService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@PathVariable long id, @RequestBody @Valid UserModel userModel) {
		userModelService.updateById(id, userModel);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}











