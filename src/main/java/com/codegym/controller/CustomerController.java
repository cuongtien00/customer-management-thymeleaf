package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import com.codegym.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final ICustomerService customerService = new CustomerService();

    @GetMapping("")
    public String index(Model model) {

        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers", customerList);
        return "/index";
    }
@GetMapping("/create")
public String creatForm(Model model){
        model.addAttribute("customer",new Customer());
        return "/create";
}
@PostMapping("/save")
public String create(@ModelAttribute Customer customer,Model model,RedirectAttributes redirectAttributes ){
    customer.setId((int) (Math.random() * 10000));
    customerService.save(customer);
        model.addAttribute("message","New Customer was created!");
        return "redirect:/customer";
}
    @GetMapping("/{id}/edit")
   public String edit(@PathVariable int id, Model model){
        model.addAttribute("customer",customerService.findById(id));
        return "/edit";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes){
        customerService.update(customer.getId(),customer);
        redirectAttributes.addFlashAttribute("success","Customer was updated!");
        return "redirect:/customer";
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model){
        model.addAttribute("customer",customerService.findById(id));
        return "/delete";
    }
    @PostMapping("/delete")
    public String remove(@ModelAttribute Customer customer,RedirectAttributes redirectAttributes){
        customerService.remove(customer.getId());
        redirectAttributes.addFlashAttribute("success","Removed customer success!");
        return "redirect:/customer";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id,Model model){
        model.addAttribute("customer",customerService.findById(id));
        return "/view";
    }
}