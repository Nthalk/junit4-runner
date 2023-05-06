package com.iodesystems.junit4.xsd;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "properties")
@Data
public class Properties {
  @Getter
  @Setter
  @XmlElements({@XmlElement(name = "property", type = Property.class)})
  List<Property> properties = new ArrayList<>();

}
