package task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import task.users.User;
import task.usersDAO.RateDAO;
import task.usersDAO.UserDAO;

import java.sql.SQLOutput;

@Controller
@RequestMapping("/user")
public class FirstController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RateDAO rateDAO;

    @GetMapping()
    public String mainPage() {
        return "main";
    }

    @GetMapping("/new")
    public String registerPage(@ModelAttribute("user") User user) {
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("user") User user) {
        return "login";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") User user) throws Exception {
        userDAO.save(user);
        return "successfulCreation";
    }

    @PostMapping("/check")
    public String checkUser(@ModelAttribute("user") User user) throws Exception {
        if (userDAO.check(user)) {
            return "successfulLogin";
        } else return "unSuccessfulLogin";
    }

    @GetMapping("/BTC")
    public String BTCPage(Model model) {
        double result = rateDAO.getRate();
        model.addAttribute("result", result);
        return "BTC";
    }
}
