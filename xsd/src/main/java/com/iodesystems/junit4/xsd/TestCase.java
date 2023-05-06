package com.iodesystems.junit4.xsd;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.math.BigDecimal;

@XmlRootElement(name = "test-case")
@Data
public class TestCase {
  @XmlAttribute
  String name;
  @XmlAttribute
  String className;
  @XmlAttribute
  Integer assertions;
  @XmlAttribute
  BigDecimal time;
  @XmlAttribute
  String file;
  @XmlAttribute
  Integer line;
  @XmlElements({
          @XmlElement(name = "skipped", type = Skip.class),
          @XmlElement(name = "failure", type = Failure.class),
          @XmlElement(name = "error", type = Error.class)
  })
  Result result;
  @XmlElement
  Properties properties;
  @XmlElement(name = "system-out")
  String systemOut;
  @XmlElement(name = "system-err")
  String systemErr;
}
