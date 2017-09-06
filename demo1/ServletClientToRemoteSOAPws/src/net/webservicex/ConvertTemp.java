
package net.webservicex;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Temperature" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="FromUnit" type="{http://www.webserviceX.NET/}TemperatureUnit"/>
 *         &lt;element name="ToUnit" type="{http://www.webserviceX.NET/}TemperatureUnit"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "temperature",
    "fromUnit",
    "toUnit"
})
@XmlRootElement(name = "ConvertTemp")
public class ConvertTemp {

    @XmlElement(name = "Temperature")
    protected double temperature;
    @XmlElement(name = "FromUnit", required = true)
    @XmlSchemaType(name = "string")
    protected TemperatureUnit fromUnit;
    @XmlElement(name = "ToUnit", required = true)
    @XmlSchemaType(name = "string")
    protected TemperatureUnit toUnit;

    /**
     * Gets the value of the temperature property.
     * 
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Sets the value of the temperature property.
     * 
     */
    public void setTemperature(double value) {
        this.temperature = value;
    }

    /**
     * Gets the value of the fromUnit property.
     * 
     * @return
     *     possible object is
     *     {@link TemperatureUnit }
     *     
     */
    public TemperatureUnit getFromUnit() {
        return fromUnit;
    }

    /**
     * Sets the value of the fromUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemperatureUnit }
     *     
     */
    public void setFromUnit(TemperatureUnit value) {
        this.fromUnit = value;
    }

    /**
     * Gets the value of the toUnit property.
     * 
     * @return
     *     possible object is
     *     {@link TemperatureUnit }
     *     
     */
    public TemperatureUnit getToUnit() {
        return toUnit;
    }

    /**
     * Sets the value of the toUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemperatureUnit }
     *     
     */
    public void setToUnit(TemperatureUnit value) {
        this.toUnit = value;
    }

}
