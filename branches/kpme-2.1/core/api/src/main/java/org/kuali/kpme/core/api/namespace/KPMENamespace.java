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
package org.kuali.kpme.core.api.namespace;

public enum KPMENamespace {
	
	KPME_WKFLW ("KPME-WKFLW"),
	KPME_HR ("KPME-HR"),
	KPME_TK ("KPME-TK"),
	KPME_LM ("KPME-LM"),
	KPME_PM ("KPME-PM");
	
	private String namespaceCode;
	
	private KPMENamespace(String namespaceCode) {
		this.namespaceCode = namespaceCode;
	}

	public String getNamespaceCode() {
		return namespaceCode;
	}

	public void setNamespaceCode(String namespaceCode) {
		this.namespaceCode = namespaceCode;
	}

}