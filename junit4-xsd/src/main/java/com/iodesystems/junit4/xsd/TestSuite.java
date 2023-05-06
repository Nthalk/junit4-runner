package com.iodesystems.junit4.xsd;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "testsuite")
@Data
public class TestSuite {
  @XmlAttribute
  String name;
  @XmlAttribute
  String file;
  @XmlAttribute
  Integer tests = 0;
  @XmlAttribute
  String classname;
  @XmlAttribute
  Integer failures;
  @XmlAttribute
  Integer errors;
  @XmlAttribute
  Integer skipped;
  @XmlAttribute
  Integer assertions;
  @XmlAttribute
  BigDecimal time;
  @XmlAttribute
  String timestamp;
  @XmlElement
  Properties properties;
  @XmlElement(name = "system-out")
  String systemOut;
  @XmlElement(name = "system-err")
  String systemErr;
  @XmlElements({@XmlElement(name = "testcase", type = TestCase.class)})
  List<TestCase> testCases = new ArrayList<>();
}
