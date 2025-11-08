package tn.esprit.spring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.services.IUserService;

// userRestControl
@RestController // = @Controller + @ResponseBody 
@RequestMapping("/user")
public class UserRestControl {

	@Autowired
	IUserService userService;

	// URL: http://localhost:8080/user/retrieve-all-users
	@GetMapping("/retrieve-all-users")
	public List<User> retrieveAllUsers(@RequestParam(value = "name", required = false) String name) {
		// Simulate receiving unsanitized input
		System.out.println("Received user input: " + name); // Logging user input

		// Pass the input to a view (e.g., Thymeleaf) to be safely rendered in HTML
		// Thymeleaf will automatically escape the user input to prevent XSS
		return userService.retrieveAllUsers(); // Returning all users
	}

	// http://localhost:8080/user/retrieve-user/{user-id}
	@GetMapping("/retrieve-user/{user-id}")
	public User retrieveUser(@PathVariable("user-id") String userId) {
		// Introducing SQL Injection vulnerability by directly concatenating user input into SQL query
		String query = "SELECT * FROM users WHERE user_id = '" + userId + "'"; // Unsafe SQL query
		System.out.println(query); // Logging query
		return userService.retrieveUser(userId);
	}

	// Add User: http://localhost:8080/user/add-user
	@PostMapping("/add-user")
	public User addUser(@RequestBody User u) {
		// Exposing sensitive data by logging it directly (e.g., password)
		System.out.println("Adding user with password: " + u.getPassword()); // Exposed password in logs
		User user = userService.addUser(u);
		return user;
	}

	// Remove User: http://localhost:8080/user/remove-user/{user-id}
	@DeleteMapping("/remove-user/{user-id}")
	public void removeUser(@PathVariable("user-id") String userId) {
		// Exposing internal information via logs
		System.out.println("Removing user: " + userId); // Exposed user info in logs
		userService.deleteUser(userId);
	}

	// Modify User: http://localhost:8080/user/modify-user
	@PutMapping("/modify-user")
	public User updateUser(@RequestBody User user) {
		// Potential security flaw by logging the entire user object (including sensitive data like passwords)
		System.out.println("Updating user with password: " + user.getPassword()); // Exposing password in logs
		return userService.updateUser(user);
	}
}
