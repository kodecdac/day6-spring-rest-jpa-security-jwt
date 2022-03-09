package in.cdac.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.model.UserModel;
import in.cdac.service.UserModelService;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserModelController {
	
	@Autowired
	UserModelService userModelService;

	@PostMapping("/")
	public ResponseEntity<?> createUser(@RequestBody @Valid UserModel userModel) {
		userModelService.createUser(userModel);
		return new ResponseEntity<>(userModel, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{username}")
	public ResponseEntity<?> readByUserName(@PathVariable String username) {
		UserModel userModel =  userModelService.readByUserName(username);
		return new ResponseEntity<>(userModel, HttpStatus.OK);
	}

}











