package com.example.userdetail.controller;

import com.example.userdetail.entity.User;
import com.example.userdetail.repo.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // 👉 Show login page
    @PostConstruct
    public void init() {
        System.out.println("AuthController loaded");
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // templates/login.html
    }

    // 👉 Process login
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {

            // store user in session
            session.setAttribute("loggedInUser", user);

            return "redirect:/dashboard";
        }

        return "login"; // back to login page if failed
    }

    // 👉 Dashboard page (protected)
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        return "dashboard"; //
    }

    // 👉 Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}