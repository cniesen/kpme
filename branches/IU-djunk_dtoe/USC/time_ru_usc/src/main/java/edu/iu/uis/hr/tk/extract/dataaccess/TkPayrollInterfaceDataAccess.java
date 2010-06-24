package edu.iu.uis.hr.tk.extract.dataaccess;

import java.util.Date;

import edu.iu.uis.hr.dataaccess.DataAccessOjb;

public interface TkPayrollInterfaceDataAccess extends DataAccessOjb {
    public void deleteTkPayrollInterfaces(Date payEndDate);
}
