package com.tlc.crm.crud.action;

import com.tlc.commons.json.Json;
import com.tlc.commons.json.JsonArray;
import com.tlc.commons.json.JsonObject;

import com.tlc.crm.common.action.secure.CrmConfigAction;
import com.tlc.crm.common.config.ConfigManager;
import com.tlc.crm.crud.api.EmployeeManager;
import com.tlc.crm.crud.model.Employee;
import com.tlc.crm.crud.validator.EmployeeValidation;

import com.tlc.validator.type.Group.Create;
import com.tlc.validator.type.Group.Update;
import com.tlc.web.WebAction;

import java.util.ArrayList;
import java.util.Collection;

/**
 * To perform CRUD operation for employee management using API
 *
 * @author NandiniRakAS
 */
@WebAction(path= "/employees/mgmt")
public class EmployeeManagement extends CrmConfigAction<Employee> {

    /**
     * Gets all data and return as json object
     *
     * @param model
     */
    @Override
    public JsonObject construct(final Employee model) {
        final JsonObject jsonObject = Json.object();

        jsonObject.put("id", model.id());
        jsonObject.put("name", model.getName());
        jsonObject.put("phoneNumber", model.getPhoneNumber());
        jsonObject.put("salary", model.getSalary());

        return jsonObject;
    }

    /**
     * Gets instance for ConfigManager
     */
    @Override
    public ConfigManager getConfigManager() {
        return EmployeeManager.getInstance();
    }

    /**
     * Stores data in employee object
     *
     * @param jsonObject
     */
    @Override
    public Employee construct(final JsonObject jsonObject) {
        final Long id = jsonObject.optLong("id", 0);
        final String name = jsonObject.optString("name", null);
        final String phoneNumber = jsonObject.optString("phoneNumber", null);
        final Long salary = jsonObject.optLong("salary", 0);
        final Employee employee = new Employee(id, name, phoneNumber, salary);
        final String type = jsonObject.getString("type");

        if ("create".equals(type) || "update".equals(type)) {
            EmployeeValidation.validate(employee, getGroups(type));
        }
        return employee;
    }

    /**
     * To create and update operation by using type
     *
     * @param type
     */
    private Class getGroups(final String type) {

        if ("create".equals(type)) {
            return Create.class;
        } else {
            return Update.class;
        }
    }

    /**
     * Stores collection of data in list
     *
     * @param employeeData
     */
    public Collection<Employee> constructArray(final JsonObject employeeData) {
        final Collection<Employee> employeeList = new ArrayList<>();
        final JsonArray jsonArray = employeeData.getJsonArray("arrayData");

        for (int index = 0; index < jsonArray.size(); index++) {
            final JsonObject jsonObject = jsonArray.getJsonObject(index);
            final Long id = jsonObject.optLong("id", 0);
            final String name = jsonObject.optString("name", null);
            final String phoneNumber = jsonObject.optString("phoneNumber", null);
            final Long salary = jsonObject.optLong("salary", 0);
            final Employee employee = new Employee(id, name, phoneNumber, salary);
            final String type = employeeData.getString("type");

            if ("create".equals(type)) {

                try {
                    EmployeeValidation.validate(employee, getGroups(type));
                    employeeList.add(employee);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        }
        return employeeList;
    }
}
