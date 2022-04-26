package com.tlc.crm.crud.api;

import com.tlc.commons.code.ErrorCode;
import com.tlc.crm.common.config.AuditEntry;
import com.tlc.crm.common.config.ConfigManager;
import com.tlc.crm.crud.model.Employee;
import com.tlc.crm.crud.status.EmployeeErrorCodes;

import com.tlc.sql.SQLAccess;
import com.tlc.sql.api.DataContainer;
import com.tlc.sql.api.Row;
import com.tlc.sql.api.dml.Table;
import com.tlc.sql.api.ds.OrgDataStore;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Crud operations using API
 *
 * @author NandiniRakAS
 */
public class EmployeeManager implements ConfigManager<Employee> {

    private static final Table TABLE = Table.get("Employee");
    private static final OrgDataStore ORG_DATA_STORE = getOrgDataStore();

    private static class Instance {
        private static final EmployeeManager INSTANCE = new EmployeeManager();
    }

    private EmployeeManager() {
    }

    /**
     * Gets org id for usage of OrgDataStore
     */
    public static OrgDataStore getOrgDataStore() {
        return SQLAccess.get().getOrgDataStore(1L);
    }

    /**
     * To create instance of class
     */
    public static EmployeeManager getInstance() {
        return Instance.INSTANCE;
    }

    /**
     * Inserts new data into database
     *
     * @param employee
     */
    //Not used
    @Override
    public void create(final Employee employee) {
        final Row row = new Row(TABLE);
        final DataContainer dataContainer = DataContainer.create();

        setColumns(row, employee);
        dataContainer.addNewRow(row);
        ORG_DATA_STORE.commitChanges(dataContainer);
    }

    /**
     * Inserts collection of data into database
     *
     * @param employeeData
     */
    public void create(final Collection<Employee> employeeData) {
        final DataContainer dataContainer = DataContainer.create();

        for (final Employee employee : employeeData) {
            final Row row = new Row(TABLE);

            setColumns(row, employee);
            dataContainer.addNewRow(row);
            ORG_DATA_STORE.commitChanges(dataContainer);
        }
    }

    /**
     * Updates employee data into database
     *
     * @param employee
     */
    @Override
    public void update(final Employee employee) {
        final Row row = new Row(TABLE, employee.id());
        final DataContainer dataContainer = DataContainer.create();

        if (exists(employee)) {
            setColumns(row, employee);
            dataContainer.updateRow(row);
            ORG_DATA_STORE.commitChanges(dataContainer);
        } else {
            throw ErrorCode.get(EmployeeErrorCodes.DATA_NOT_FOUND);
        }
    }

    /**
     * Used to select columns to update
     *
     * @param row
     * @param employee
     */
    private void setColumns(final Row row, final Employee employee) {
        row.set("NAME", employee.getName());
        row.set("PHONE_NUMBER", employee.getPhoneNumber());
        row.set("SALARY", employee.getSalary());
    }

    /**
     * Deletes employee data from database
     *
     * @param employee
     */
    @Override
    public void delete(final Employee employee) {
        if (exists(employee)) {
            delete(List.of(employee));
        } else {
            throw ErrorCode.get(EmployeeErrorCodes.DATA_NOT_FOUND);
        }
    }

    /**
     * Checks whether data already exists or not
     *
     * @param employee
     */
    @Override
    public boolean exists(final Employee employee) {
        return ORG_DATA_STORE.get(TABLE, employee.id()) != null ? true : false;
    }

    /**
     * Deletes employee data from database by using id
     *
     * @param employees
     */
    @Override
    public void delete(final Collection<Employee> employees) {
        final Collection<Long> employeeId = new HashSet<>();

        employees.forEach(model -> employeeId.add(model.id()));
        ORG_DATA_STORE.delete(TABLE, employeeId);
    }

    /**
     * Used to get data using id
     *
     * @param id
     */
    @Override
    public Employee partialGet(final Long id) {
        final Employee employee = new Employee();

        employee.setId(id);
        return employee;
    }

    /**
     * Used to get the data from database by using id
     *
     * @param id
     */
    @Override
    public Employee get(final Long id) {
        final Row row = ORG_DATA_STORE.get(TABLE, id);
        final Employee employee = new Employee();

        employee.setId(row.get("ID"));
        employee.setName(row.get("NAME"));
        employee.setPhoneNumber(row.get("PHONE_NUMBER"));
        employee.setSalary(row.get("SALARY"));

        return employee;
    }

    @Override
    public Collection<Employee> get(final Collection<Long> ids) {
        return null;
    }

    @Override
    public AuditEntry auditEntry(final Employee employee) {
        return null;
    }
}
