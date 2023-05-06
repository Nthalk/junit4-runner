package com.iodesystems.junit4.xsd;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;


@XmlRootElement(name = "skip")
@Data
public class Skip implements Result {
    @XmlAttribute
    String message;
}
