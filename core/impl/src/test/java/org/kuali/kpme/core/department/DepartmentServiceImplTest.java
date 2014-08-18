/**
 * Copyright 2004-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kpme.core.department;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.kpme.core.CoreUnitTestCase;
import org.kuali.kpme.core.IntegrationTest;
import org.kuali.kpme.core.api.department.Department;
import org.kuali.kpme.core.api.department.DepartmentService;
import org.kuali.kpme.core.service.HrServiceLocator;

import java.util.List;

import static org.junit.Assert.*;

@IntegrationTest
public class DepartmentServiceImplTest extends CoreUnitTestCase {
	DepartmentService departmentService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        departmentService = HrServiceLocator.getDepartmentService();
    }

    @Test
    public void testGetDepartment() {
        Department dept = departmentService.getDepartment("100");

        assertTrue(dept != null);
        assertEquals("Invalid dept value", "TEST-DEPT", dept.getDept());
        assertEquals("Invalid description value", "test department", dept.getDescription().trim());
    }

    @Test
    public void testGetDepartmentEffective() {
        Department dept = departmentService.getDepartment("TEST-DEPT5", "IU-IN", new LocalDate(2009, 6, 1));

        assertTrue(dept != null);
        assertEquals("Invalid dept value", "TEST-DEPT5", dept.getDept());
        assertTrue(dept.getEffectiveLocalDate().compareTo(new LocalDate(2009, 1, 1)) == 0);
        assertEquals("Invalid description value", "test department5 - old", dept.getDescription().trim());

        dept = departmentService.getDepartment("TEST-DEPT5", "IU-IN", new LocalDate(2010, 6, 1));

        assertTrue(dept != null);
        assertEquals("Invalid dept value", "TEST-DEPT5", dept.getDept());
        assertTrue(dept.getEffectiveLocalDate().compareTo(new LocalDate(2010, 1, 1)) == 0);
        assertEquals("Invalid description value", "test department5", dept.getDescription().trim());
    }

}