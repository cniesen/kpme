package edu.iu.uis.hr.tk.employee.entity;

import org.apache.log4j.Logger;

public class Employee extends AbstractEmployee {
	private static final Logger LOG = Logger.getLogger(Employee.class);

	public Employee() {
		super();
	}

    public Employee(String universityId) {
        this();
        setUniversityId(universityId);
    }

}

