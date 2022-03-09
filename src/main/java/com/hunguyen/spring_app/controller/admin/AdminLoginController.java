package com.hunguyen.spring_app.controller.admin;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.hunguyen.spring_app.domain.Account;
import com.hunguyen.spring_app.model.AdminLoginDTO;
import com.hunguyen.spring_app.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;


@Controller
// @RequestMapping("admin/accounts")
public class AdminLoginController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private HttpSession session;

    @GetMapping("alogin")
    public String login(ModelMap model){
        model.addAttribute("account", new AdminLoginDTO());
        return "/admin/accounts/login";
    }
    @PostMapping("alogin")
    public ModelAndView login(ModelMap model, @Valid @ModelAttribute("account") AdminLoginDTO dto, BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("/admin/accounts/login", model);
        }
        Account account = accountService.login(dto.getUsername(), dto.getUsername());

        if(account == null){
            model.addAttribute("message", "Invalid username or password");
            return new ModelAndView("/admin/accounts/login", model);
        }
        session.setAttribute("username", account.getUsername());
        return new ModelAndView("forward:/admin/categories", model);
    }
}
