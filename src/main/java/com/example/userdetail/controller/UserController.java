package com.example.userdetail.controller;

import com.example.userdetail.entity.User;
import com.example.userdetail.repo.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Create user
    @PostMapping("/createUser")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        List<User> users=userRepository.findAll();
       users.stream().forEach(user -> {
                   System.out.println("User ID: " + user.getId());
                   System.out.println("User Name: " + user.getName());
                   System.out.println("User Email: " + user.getEmail());
                   System.out.println("-------------------------");
               });
        return userRepository.findAll();
    }
    /*@PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {

            // store login state in session
            session.setAttribute("user", user);

            return "redirect:/dashboard";
        }

        return "login"; // back to login page if failed
    }*/
}