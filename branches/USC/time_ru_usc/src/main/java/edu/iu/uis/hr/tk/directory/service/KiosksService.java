package edu.iu.uis.hr.tk.directory.service;

import java.util.List;

import edu.iu.uis.hr.position.entity.Department;

// TODO implement this and configure in xml
public interface KiosksService extends edu.iu.uis.hr.service.Service {
    public List getMachines(Department department);
}