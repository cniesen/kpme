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

package org.kuali.mobility.alerts.entity;

import java.io.Serializable;

/**
 * An object representing an alert. Alerts include events such as a crisis,
 * emergency, or warning on campus.
 * 
 * @author Kuali Mobility Team
 */
public class Alert implements Serializable, Comparable<Alert> {

	private static final long serialVersionUID = 3298337944905192830L;

	private String campus;
	private String type;
	private String title;
	private String priority;
	private String mobileText;
	private String url;
	private int key;

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getMobileText() {
		return mobileText;
	}

	public void setMobileText(String mobileText) {
		this.mobileText = mobileText;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public boolean equals(Object object) {
		if (object != null) {
			Alert alert = (Alert) object;
			if ((((this.getCampus() == null || this.getCampus().equals("")) && (alert.getCampus() == null || alert.getCampus().equals(""))) || (this.getCampus() != null && this.getCampus().equals(alert.getCampus()))) && 
				(((this.getMobileText() == null || this.getMobileText().equals("")) && (alert.getMobileText() == null || alert.getMobileText().equals(""))) || ( this.getMobileText() != null && this.getMobileText().equals(alert.getMobileText()))) && 
				(((this.getPriority() == null || this.getPriority().equals("")) && (alert.getPriority() == null || alert.getPriority().equals(""))) || (this.getPriority() != null && this.getPriority().equals(alert.getPriority()))) && 
				(((this.getTitle() == null || this.getTitle().equals("")) && (alert.getTitle() == null || alert.getTitle().equals(""))) || (this.getTitle() != null && this.getTitle().equals(alert.getTitle()))) && 
				(((this.getType() == null || this.getType().equals("")) && (alert.getType() == null || alert.getType().equals(""))) || (this.getType() != null && this.getType().equals(alert.getType()))) && 
				(((this.getUrl() == null || this.getUrl().equals("")) && (alert.getUrl() == null || alert.getUrl().equals(""))) || (this.getUrl() != null && this.getUrl().equals(alert.getUrl())))) {
				return true;
			}
		}
		return false;
	}

	public int compareTo(Alert that) {
		if (this.getCampus() == null || that == null || that.getCampus() == null) {
			return -1;
		}
		return this.getCampus().compareTo(that.getCampus());
	}

}
