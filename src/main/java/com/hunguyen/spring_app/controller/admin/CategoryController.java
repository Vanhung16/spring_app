package com.hunguyen.spring_app.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import com.hunguyen.spring_app.domain.Category;
import com.hunguyen.spring_app.model.CategoryDTO;
import com.hunguyen.spring_app.service.CategoryService;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(ModelMap model){
        model.addAttribute("category", new CategoryDTO());
        return "admin/categories/addOrEdit";
    }
    @RequestMapping(value = "edit/{categoryId}", method = RequestMethod.GET)
    public ModelAndView edit(ModelMap model, @PathVariable("categoryId") Long categoryId){

        Optional<Category> opt = categoryService.findById(categoryId);
        CategoryDTO dto = new CategoryDTO();
        if(opt.isPresent()){
            Category entity = opt.get();

            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true);
            model.addAttribute("category", dto);
            System.out.println(model.addAttribute("category", dto));
            return new ModelAndView("admin/categories/addOrEdit", model);
        }
        model.addAttribute("message", "category is not exits");
        return new ModelAndView("forward:/admin/categoies", model);
    }

    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(ModelMap model, 
           @Valid @ModelAttribute("category") CategoryDTO dto, BindingResult result){

        if(result.hasErrors())    {
            return new ModelAndView("admin/categories/addOrEdit");
        }
        Category entity = new Category();
        BeanUtils.copyProperties(dto, entity);
        categoryService.save(entity);
        model.addAttribute("message", "category is saved!");

        return new ModelAndView("forward:/admin/categories",model);
    }

    @RequestMapping("")
    public String list(ModelMap model){
        List<Category> list = (List<Category>) categoryService.findAll() ; 
        model.addAttribute("categories", list);
        return "admin/categories/list";
    }

    @RequestMapping(value = "delete/{categoryId}", method = RequestMethod.GET)
    public ModelAndView delete(ModelMap model ,@PathVariable Long categoryId){
        categoryService.deleteById(categoryId);
        model.addAttribute("message", "delete category have categoryId "+ categoryId +" succees");
        return new ModelAndView("forward:/admin/categories/search",model);
    }
    
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(ModelMap model, @RequestParam(name = "name", required = false) String name){

        List<Category> list = null;
        if(StringUtils.hasText(name)){
            list = categoryService.findByNameContaining(name);
        }
        else{
            list = (List<Category>) categoryService.findAll();
        }
        model.addAttribute("categories", list);
        System.out.println(list);
        return "admin/categories/search";
    }

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
