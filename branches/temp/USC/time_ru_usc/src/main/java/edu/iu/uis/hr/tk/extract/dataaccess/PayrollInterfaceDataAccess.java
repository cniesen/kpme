package edu.iu.uis.hr.tk.extract.dataaccess;

import java.util.Date;

import edu.iu.uis.hr.dataaccess.DataAccessOjb;

public interface PayrollInterfaceDataAccess extends DataAccessOjb {
    public void deletePayrollInterfaces(Date payEndDate);
}
