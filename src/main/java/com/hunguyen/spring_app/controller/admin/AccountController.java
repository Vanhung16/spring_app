package com.hunguyen.spring_app.controller.admin;

import com.hunguyen.spring_app.domain.Account;
import com.hunguyen.spring_app.model.AccountDTO;
import com.hunguyen.spring_app.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Controller
@RequestMapping("admin/accounts")
public class AccountController {
    @Autowired
    AccountService accountService ;
    @GetMapping("add")
    public String add(ModelMap model){
        AccountDTO accountDTO = new AccountDTO();
        model.addAttribute("account", accountDTO);
        return "/admin/accounts/addOrEdit";
    }
    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap modelMap, @Valid @ModelAttribute("account") AccountDTO dto, BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("admin/accounts/addOrEdit");
        }
        Account entity = new Account();
        BeanUtils.copyProperties(dto, entity);
        accountService.save(entity);
        modelMap.addAttribute("message", "account is saved!");

        return new ModelAndView("forward:/admin/accounts", modelMap);
    }
    @GetMapping("edit/{username}")
    public ModelAndView edit(ModelMap model, @PathVariable String username){
        Optional<Account> opt = accountService.findById(username);
        AccountDTO dto = new AccountDTO();
        if(opt.isPresent()){
            Account entity = opt.get();
            BeanUtils.copyProperties(entity, dto);

            dto.setIsEdit(true);

            model.addAttribute("account", dto);
            model.addAttribute("message", "account is updated!");
            return new ModelAndView("/admin/accounts/addOrEdit", model);
        }
        model.addAttribute("message", "account was not found!");
        return new ModelAndView("forward:/admin/accounts", model);
    }
    @GetMapping("")
    public String list(ModelMap model){
        List<Account> accounts = accountService.findAll();
        model.addAttribute("accounts", accounts);
        return "/admin/accounts/list";
    }
    @GetMapping("delete/{username}")
    public ModelAndView delete(ModelMap model, @PathVariable String username){
        if(accountService.findById(username).isPresent()){
            accountService.deleteById(username);
            model.addAttribute("message", "account is deleted!");
        }else{
        model.addAttribute("message", "account was not found!");
        }
        return new ModelAndView("forward:/admin/accounts", model);
    }
}
