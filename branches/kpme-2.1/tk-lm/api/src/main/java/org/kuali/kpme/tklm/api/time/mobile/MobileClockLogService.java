/**
 * Copyright 2004-2015 The Kuali Foundation
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
package org.kuali.kpme.tklm.api.time.mobile;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("/")
public interface MobileClockLogService {

public static final String ADD_CLOCK_LOG_PATH_NAME = "/addClockLog";
public static final String TYPE_APPLICATION_JSON = "application/json";
public static final String HEARTBEAT_PATH_NAME = "/heartbeat";

/* **
* Receive JSON data from mobile device and save clockLog
* @param clockLogString
* @param hsr
* @return
*/

@POST
@Path(ADD_CLOCK_LOG_PATH_NAME)
@Produces(TYPE_APPLICATION_JSON)
public @ResponseBody
String addClockLog(@RequestBody String clockLogString, @Context HttpServletRequest hsr);

/**
* Receive request from mobile devices and update RemoteSwipeDevice maintenance table
* @param hsr
* @return
*/
@GET
@Path(HEARTBEAT_PATH_NAME)
public void heartbeat(@Context HttpServletRequest hsr);
}

