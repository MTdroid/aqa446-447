package academy.kata.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {
    @XmlElement(name = "id", required = true)
    private Long id;
    @XmlElement(name = "first_name", required = true)
    private String firstName;
    @XmlElement(name = "family_name", required = true)
    private String familyName;
    @XmlElement(name = "second_name", nillable = true)
    private String secondName;
}
