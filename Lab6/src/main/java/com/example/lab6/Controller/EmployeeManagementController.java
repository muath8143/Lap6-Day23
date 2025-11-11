package com.example.lab6.Controller;

import com.example.lab6.Api.ApiResponse;
import com.example.lab6.Model.EmployeeManagementSystem;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeManagementController {
    ArrayList<EmployeeManagementSystem> employees=new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity<?> display (){
        if (employees.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no employees"));
        }
        return ResponseEntity.status(200).body(employees);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody @Valid EmployeeManagementSystem employee, Errors error){
        if (error.hasErrors()){
            String message= error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        employees.add(employee);
        return ResponseEntity.status(200).body(new ApiResponse("The employee added successfully"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable int id,@RequestBody @Valid EmployeeManagementSystem employee,Errors error){
        if (error.hasErrors()){
            String message=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        int counter=-1;
        for (EmployeeManagementSystem empl:employees){
            counter++;
            if (empl.getID()==id){
                employees.set(counter,employee);
                return ResponseEntity.status(200).body(new ApiResponse("The employee have id: "+id+" updated successfully"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("The employee have id: "+id+" is not exists"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable int id){
        for (EmployeeManagementSystem empl:employees){
            if (empl.getID()==id){
                employees.remove(empl);
                return ResponseEntity.status(200).body(new ApiResponse("The employee have id: "+id+" deleted successfully"));
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("The employee have id: "+id+" is not exists"));
    }

    @GetMapping("/search/{position}")
    public ResponseEntity<?> searchByPosition(@PathVariable String position){

        if (!position.equals("supervisor") && !position.equals("coordinator")){
            return ResponseEntity.status(400).body(new ApiResponse("Please enter a correct position: supervisor or coordinator"));
        }

        ArrayList<EmployeeManagementSystem> employeesByPosition=new ArrayList<>();

        for (EmployeeManagementSystem employee:employees){
            if (employee.getPosition().equals(position)){
                employeesByPosition.add(employee);
            }
        }

        if (employeesByPosition.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no employees who hold a position: "+position));
        }
        return ResponseEntity.status(200).body(employeesByPosition);
    }

    @GetMapping("/range/{minAge}/{maxAge}")
    public ResponseEntity<?> getEmployeesByRange(@PathVariable int minAge,@PathVariable int maxAge){
        if (minAge<=25 || maxAge>=120){
            return ResponseEntity.status(400).body(new ApiResponse("Please enter a valid age"));
        }
        ArrayList<EmployeeManagementSystem> employeesByRange=new ArrayList<>();
        for (EmployeeManagementSystem employee:employees){
            if (employee.getAge()>=minAge && employee.getAge()<=maxAge){
                employeesByRange.add(employee);
            }
        }
        if (employeesByRange.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There no employees aged between: "+minAge+" and "+maxAge));
        }
        return ResponseEntity.status(200).body(employeesByRange);
    }

    @PutMapping("/apply-leave/{id}")
    public ResponseEntity<?> applyForAnnualLeave(@PathVariable int id){
        for (EmployeeManagementSystem employee:employees) {
            if (employee.getID() == id) {

                if (employee.isOnLeave()) {
                    return ResponseEntity.status(400).body(new ApiResponse("Yor are in leave you can't apply leave at this time"));
                }

                if (employee.getAnnualLeave() <1) {
                    return ResponseEntity.status(400).body(new ApiResponse("You have used up you can't apply more leave"));
                }

                employee.setOnLeave(true);
                employee.setAnnualLeave(employee.getAnnualLeave()-1);
                return ResponseEntity.status(200).body(employee);
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("The employee have id: "+id+" is not exists"));
    }

    @GetMapping("/get-employees-by-filter")
    public ResponseEntity<?> getEmployeeWithNoAnnualLeaves(){
        if (employees.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no employees in system"));
        }
        ArrayList<EmployeeManagementSystem> employeesWithNoLeaves=new ArrayList<>();
         for (EmployeeManagementSystem employee:employees){
             if (employee.getAnnualLeave()==0){
                 employeesWithNoLeaves.add(employee);
             }
         }
         if (employeesWithNoLeaves.isEmpty()){
             return ResponseEntity.status(400).body(new ApiResponse("All employees have annual leave"));
         }
         return ResponseEntity.status(200).body(employeesWithNoLeaves);
    }

    @PutMapping("/promote/{idOfSupervisor}/{idOfEmployee}")
    public ResponseEntity<?> promoteEmployee(@PathVariable int idOfSupervisor,@PathVariable int idOfEmployee){
        for (EmployeeManagementSystem employee:employees){
            if (employee.getID()==idOfEmployee){

                for (EmployeeManagementSystem employeeSupervisor:employees) {
                    if (idOfSupervisor==employeeSupervisor.getID()) {
                        if (!employeeSupervisor.getPosition().equals("supervisor")) {
                            return ResponseEntity.status(400).body(new ApiResponse("You don't have the authority to promote an employee"));
                        }
                    }
                }

                    if (employee.getAge()<30){
                        return ResponseEntity.status(400).body(new ApiResponse("The age of employee under 30 years can't be prompted"));
                    }

                    if (employee.isOnLeave()){
                        return ResponseEntity.status(400).body(new ApiResponse("The employee on leave can't be promoted"));
                    }

                    employee.setPosition("supervisor");
                    return ResponseEntity.status(200).body(new ApiResponse("The employee with id: "+idOfEmployee+" has promoted to supervisor"));

            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("No employee has an id: "+idOfEmployee));
    }
}
