package com.iodesystems.junit4.xsd;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlValue;
import lombok.Data;


@XmlRootElement(name = "failure")
@Data
public class Error implements Result {
  @XmlAttribute
  String message;
  @XmlAttribute
  String type;
  @XmlValue
  String stackTrace;
}
