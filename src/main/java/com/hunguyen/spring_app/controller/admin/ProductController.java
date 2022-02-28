package com.hunguyen.spring_app.controller.admin;

import com.hunguyen.spring_app.domain.Category;
import com.hunguyen.spring_app.domain.Product;
import com.hunguyen.spring_app.model.CategoryDTO;
// import com.hunguyen.spring_app.model.CategoryDTO;
import com.hunguyen.spring_app.model.ProductDTO;
import com.hunguyen.spring_app.service.CategoryService;
import com.hunguyen.spring_app.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/products")
public class ProductController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    public List<CategoryDTO> getCategories() {
        return categoryService.findAll().stream().map(item -> {
            CategoryDTO dto = new CategoryDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).toList();
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(ModelMap model){
        model.addAttribute("product", new ProductDTO());
        return "admin/products/addOrEdit";
    }

    @RequestMapping(value = "edit/{productId}", method = RequestMethod.GET)
    public ModelAndView edit(ModelMap model, @PathVariable("productId") Long productId){

        Optional<Product> opt = productService.findById(productId);
        ProductDTO dto = new ProductDTO();
        if(opt.isPresent()){
            Product entity = opt.get();

            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true);
            model.addAttribute("category", dto);
            System.out.println(model.addAttribute("category", dto));
            return new ModelAndView("admin/products/addOrEdit", model);
        }
        model.addAttribute("message", "product is not exits");
        return new ModelAndView("forward:/admin/products", model);
    }

    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(ModelMap model, 
           @Valid @ModelAttribute("product") ProductDTO dto, BindingResult result){

        if(result.hasErrors())    {
            return new ModelAndView("admin/products/addOrEdit");
        }
        Product entity = new Product();
        BeanUtils.copyProperties(dto, entity);
        productService.save(entity);
        model.addAttribute("message", "product is saved!");

        return new ModelAndView("forward:/admin/products",model);
    }

    @RequestMapping("")
    public String list(ModelMap model){
        List<Product> list = (List<Product>) productService.findAll() ; 
        model.addAttribute("products", list);
        return "admin/products/list";
    }

    @RequestMapping(value = "delete/{productId}", method = RequestMethod.GET)
    public ModelAndView delete(ModelMap model ,@PathVariable Long productId){
        productService.deleteById(productId);
        return new ModelAndView("forward:/admin/products/search",model);
    }
    
    // @RequestMapping(value = "search", method = RequestMethod.GET)
    // public String search(ModelMap model, @RequestParam(name = "name", required = false) String name){

    //     List<Product> list = null;
    //     if(StringUtils.hasText(name)){
    //         list = categoryService.findByNameContaining(name);
    //     }
    //     else{
    //         list = (List<Category>) categoryService.findAll();
    //     }
    //     model.addAttribute("categories", list);
    //     System.out.println(list);
    //     return "admin/categories/search";
    // }

    @RequestMapping(value = "searchpaginated", method = RequestMethod.GET)
    public String search(ModelMap model,
     @RequestParam(name = "name", required = false) String name,
     @RequestParam("page") Optional<Integer> page,
     @RequestParam("size") Optional<Integer> size ){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("name"));

        Page<Category> resultPage = null;
        if(StringUtils.hasText(name)){
            resultPage = categoryService.findByNameContaining(name,pageable);
            model.addAttribute("name", name);
        }
        else{
            resultPage = (Page<Category>)categoryService.findAll(pageable);
        }
// tổng tất cả các page có được
        int totalPages = resultPage.getTotalPages();
        if(totalPages > 0){
            //
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);

            if(totalPages > 5){
                if(end == totalPages) start = end -5;
                else if(start == 1) end = start + 5;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start,end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
         model.addAttribute("categoryPage", resultPage);
        return "admin/categories/searchpaginated";
    }
}