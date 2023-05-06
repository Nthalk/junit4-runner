package com.iodesystems.junit4.xsd;


import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "testsuites")
@XmlType
@Data
public class TestSuites {
  @XmlAttribute
  String name;
  @XmlAttribute
  Integer testCount = 0;
  @XmlAttribute
  Integer failures = 0;
  @XmlAttribute
  Integer errors = 0;
  @XmlAttribute
  Integer skipped = 0;
  @XmlAttribute
  Integer assertions = 0;
  @XmlAttribute
  BigDecimal time;
  @XmlAttribute
  String timestamp;
  @XmlElements(value = {@XmlElement(name = "testsuite", type = TestSuite.class)})
  List<TestSuite> testSuites = new ArrayList<>();

}
