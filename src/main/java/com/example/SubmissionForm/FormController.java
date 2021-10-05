package com.example.SubmissionForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class FormController {

    @Autowired
    EmployeesRepo repo;
    @RequestMapping("/")
    public String details(){
        return "Reg";
    }

    @RequestMapping("/details")
    public String details(Employees employees){
        repo.save(employees);
        return "Reg";
    }
    @RequestMapping("/getdetails")
    public String getdetails(){

        return "viewDetails";
    }

    @PostMapping("/getdetails")
    public  ModelAndView getdetails(@RequestParam int id)
         {
             ModelAndView mv = new ModelAndView("Retreive");
             Employees employees = repo.findById(id).orElse(null);
             mv.addObject(employees);
             return  mv;
         }                        // show the details on web page and retreiving details by Id

                    //CRUD
    @RequestMapping("/employees")
    @ResponseBody
    public List<Employees> getEmployees(){
        return repo.findAll();
    }                                   // data details
    @RequestMapping("/employees/{id}")
    @ResponseBody
    public Optional<Employees> getEmployees2(@PathVariable("id") int id){
        return repo.findById(id);
    }                           // find the data by Id
    @PostMapping ("/employees")
    public Employees getEmployees3(@RequestBody Employees employees){
         repo.save(employees);
        return  employees;
    }                          // show the data  submitted
    @DeleteMapping  ("/employees/{id}")
    public Employees getEmployees4(@PathVariable("id") int id){
        Employees emp = repo.getOne(id);
        repo.delete(emp);
        return  emp;
    }                        // delete the data
    @PutMapping (path = "/employees" , consumes = {"application/json"})
    public Employees getEmployees5(@RequestBody Employees employees){

        repo.save(employees);
        return  employees;
    }                        // edit the data

}
