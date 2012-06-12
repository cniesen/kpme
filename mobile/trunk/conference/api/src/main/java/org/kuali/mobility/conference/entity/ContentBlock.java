/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.mobility.conference.entity;

import java.io.Serializable;

public class ContentBlock implements Serializable {

	private static final long serialVersionUID = -2826816981140315473L;

	private String contentBlock;

	public String getContentBlock() {
    	return contentBlock;
    }

	public void setContentBlock(String contentBlock) {
    	this.contentBlock = contentBlock;
    }
}
