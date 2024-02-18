package com.locker.gea.gealocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.locker.gea.gealocker.entity.ApiResponse;
import com.locker.gea.gealocker.entity.User;
import com.locker.gea.gealocker.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({ "/", "/login" })
    public String login() {
        // model.addAttribute("message", "Hello, Thymeleaf!");
        return "login/login";
    }

    @GetMapping({ "/register" })
    public String register(Model model) {
        return "login/register";
    }

    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    public @ResponseBody ApiResponse doRegister(RedirectAttributes redirect,
            HttpSession session,
            @RequestParam String nama,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String no_ktp,
            @RequestParam String phone_number) {
        ModelAndView model = new ModelAndView();

        ApiResponse result = null;
        try {
            result = userService.register(nama, email, password, no_ktp, phone_number);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            model.setViewName("redirect:/login");
        }

        return result;
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ModelAndView doLogin(RedirectAttributes redirect,
            HttpSession session,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = false) String password) {
        ModelAndView model = new ModelAndView();

        ApiResponse result = null;
        try {
            result = userService.getUserByEmail(email, password);
            if (result.getStatuscode() != 0) {
                model.setViewName("redirect:/login");
                redirect.addFlashAttribute("notif", result.getMessage());
            } else {
                session.setAttribute("user", result.getObject());
                model.setViewName("redirect:/home");
            }
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            model.setViewName("redirect:/login");
        }

        return model;
    }

}
