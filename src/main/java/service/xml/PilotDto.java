package service.xml;

import entity.Aircraft;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PilotDto {

    private Integer yearsOfExperience;
    private Integer totalFlights;
    @XmlTransient
    private Aircraft aircraft;
}
