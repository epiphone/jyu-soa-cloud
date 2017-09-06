
package net.webservicex;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TemperatureUnit.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TemperatureUnit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="degreeCelsius"/>
 *     &lt;enumeration value="degreeFahrenheit"/>
 *     &lt;enumeration value="degreeRankine"/>
 *     &lt;enumeration value="degreeReaumur"/>
 *     &lt;enumeration value="kelvin"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TemperatureUnit")
@XmlEnum
public enum TemperatureUnit {

    @XmlEnumValue("degreeCelsius")
    DEGREE_CELSIUS("degreeCelsius"),
    @XmlEnumValue("degreeFahrenheit")
    DEGREE_FAHRENHEIT("degreeFahrenheit"),
    @XmlEnumValue("degreeRankine")
    DEGREE_RANKINE("degreeRankine"),
    @XmlEnumValue("degreeReaumur")
    DEGREE_REAUMUR("degreeReaumur"),
    @XmlEnumValue("kelvin")
    KELVIN("kelvin");
    private final String value;

    TemperatureUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TemperatureUnit fromValue(String v) {
        for (TemperatureUnit c: TemperatureUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
