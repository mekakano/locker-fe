package com.locker.gea.gealocker.controller;

import java.util.LinkedHashMap;

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
import com.locker.gea.gealocker.services.LockerService;
import com.locker.gea.gealocker.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private LockerService lockerService;

    @GetMapping("/home")
    public String home(HttpServletRequest request) {
        LinkedHashMap<String, Object> userData = (LinkedHashMap<String, Object>) request.getSession()
                .getAttribute("user"); // Your LinkedHashMap
        User user = new User();
        user.setEmail((String) userData.get("email"));
        user.setNama((String) userData.get("nama"));

        if (user != null) {
            return "home/home";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping({ "/peminjaman" })
    public String peminjaman(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "home/peminjaman";
    }

    @GetMapping({ "/pengembalian" })
    public String pengembalian(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "home/pengembalian";
    }

    @RequestMapping(value = "/lihatData", method = RequestMethod.GET)
    public @ResponseBody ApiResponse lihatData(HttpServletRequest request) {
        LinkedHashMap<String, Object> userData = (LinkedHashMap<String, Object>) request.getSession()
                .getAttribute("user"); // Your LinkedHashMap
        User user = new User();
        user.setId_user((int) userData.get("id_user"));

        ApiResponse result = null;
        try {
            result = lockerService.lihatData(user.getId_user());
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping(value = "/lihatAvailable", method = RequestMethod.GET)
    public @ResponseBody ApiResponse lihatAvailable(HttpServletRequest request) {
        LinkedHashMap<String, Object> userData = (LinkedHashMap<String, Object>) request.getSession()
                .getAttribute("user"); // Your LinkedHashMap
        User user = new User();
        user.setId_user((int) userData.get("id_user"));

        ApiResponse result = null;
        try {
            result = lockerService.lihatAvailable();
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping(value = "/validatePassword", method = RequestMethod.POST)
    public @ResponseBody ApiResponse doRegister(HttpServletRequest request,
            @RequestParam String password_locker,
            @RequestParam String nomor_locker) {
        LinkedHashMap<String, Object> userData = (LinkedHashMap<String, Object>) request.getSession()
                .getAttribute("user"); // Your LinkedHashMap
        User user = new User();
        user.setId_user((int) userData.get("id_user"));

        ApiResponse result = null;
        try {
            result = lockerService.validatePassword(user.getId_user(), password_locker, nomor_locker);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping(value = "/pilihLocker", method = RequestMethod.POST)
    public @ResponseBody ApiResponse pilihLocker(HttpServletRequest request,
            @RequestParam String nomor_locker) {
        LinkedHashMap<String, Object> userData = (LinkedHashMap<String, Object>) request.getSession()
                .getAttribute("user"); // Your LinkedHashMap
        User user = new User();
        user.setId_user((int) userData.get("id_user"));

        ApiResponse result = null;
        try {
            result = lockerService.pilihLocker(user.getId_user(), nomor_locker);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping(value = "/pengembalian", method = RequestMethod.POST)
    public @ResponseBody ApiResponse pengembalian(HttpServletRequest request,
            @RequestParam String password_locker,
            @RequestParam String nomor_locker) {
        LinkedHashMap<String, Object> userData = (LinkedHashMap<String, Object>) request.getSession()
                .getAttribute("user"); // Your LinkedHashMap
        User user = new User();
        user.setId_user((int) userData.get("id_user"));

        ApiResponse result = null;
        try {
            result = lockerService.pengembalian(user.getId_user(), password_locker, nomor_locker);
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }

        return result;
    }
}
