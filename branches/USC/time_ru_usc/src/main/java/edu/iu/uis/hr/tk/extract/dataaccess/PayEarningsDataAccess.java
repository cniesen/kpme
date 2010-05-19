package edu.iu.uis.hr.tk.extract.dataaccess;

import java.util.ArrayList;
import java.util.Date;

import edu.iu.uis.hr.dataaccess.DataAccessOjb;

public interface PayEarningsDataAccess extends DataAccessOjb {
    public ArrayList getPayEarnings(Date payEndDate,String universityId);

}
