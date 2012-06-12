
package org.kuali.hr.time.mobile.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addClockAction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addClockAction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="principalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assignmentKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clockAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addClockAction", propOrder = {
    "principalId",
    "assignmentKey",
    "clockAction"
})
public class AddClockAction {

    protected String principalId;
    protected String assignmentKey;
    protected String clockAction;

    /**
     * Gets the value of the principalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrincipalId() {
        return principalId;
    }

    /**
     * Sets the value of the principalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrincipalId(String value) {
        this.principalId = value;
    }

    /**
     * Gets the value of the assignmentKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssignmentKey() {
        return assignmentKey;
    }

    /**
     * Sets the value of the assignmentKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssignmentKey(String value) {
        this.assignmentKey = value;
    }

    /**
     * Gets the value of the clockAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClockAction() {
        return clockAction;
    }

    /**
     * Sets the value of the clockAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClockAction(String value) {
        this.clockAction = value;
    }

}
