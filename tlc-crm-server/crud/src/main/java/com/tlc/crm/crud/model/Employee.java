package com.tlc.crm.crud.model;

import com.tlc.crm.crud.validator.Name;
import com.tlc.crm.crud.validator.PhoneNumber;
import com.tlc.validator.TlcModel;
import com.tlc.validator.type.Group.Create;
import com.tlc.validator.type.Group.Update;
import org.hibernate.validator.constraints.Range;

/**
 * For storing employee data
 *
 * @author NandiniRakAS
 */
public class Employee implements TlcModel {

    private Long id;

    @Name (message = "Please enter name", groups = {Create.class, Update.class})
    private String name;

    @PhoneNumber (message = "Please enter name", groups = {Create.class, Update.class})
    private String phoneNumber;

    @Range(min = 3000, message = "Enter a valid salary", groups = {Create.class, Update.class})
    private Long salary;

    public Employee() {

    }

    public Employee(Long id, String name, String phoneNumber, Long salary) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Long orgId() {
        return null;
    }

    @Override
    public Object identity() {
        return null;
    }

    public String toString() {
        return String.format("Id : %s \nName:%s \nNumber:%s\nSalary:%s \n", id, name, phoneNumber, salary);
    }
}
