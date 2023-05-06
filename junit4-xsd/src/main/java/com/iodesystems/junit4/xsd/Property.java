package com.iodesystems.junit4.xsd;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;
import lombok.Data;


@Data
public class Property {
  @XmlAttribute
  String name;
  @XmlAttribute
  String value;
  @XmlValue
  String text;
}

