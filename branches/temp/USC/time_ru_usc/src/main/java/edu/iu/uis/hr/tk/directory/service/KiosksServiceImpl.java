package edu.iu.uis.hr.tk.directory.service;

import java.util.List;

import edu.iu.uis.hr.directory.dataaccess.AdsDataAccess;
import edu.iu.uis.hr.position.entity.Department;
import edu.iu.uis.hr.service.AbstractService;

public class KiosksServiceImpl extends AbstractService implements KiosksService {
    private AdsDataAccess adsDataAccess;

    public List getMachines(Department department) {
        return edu.iu.uis.hr.Utility.getDefaultListValue();
    }

    private AdsDataAccess getAdsDataAccess() {
        return adsDataAccess;
    }

    public void setAdsDataAccess(AdsDataAccess adsDataAccess) {
        if (adsDataAccess != null) {
            this.adsDataAccess = adsDataAccess;
        }
    }
}