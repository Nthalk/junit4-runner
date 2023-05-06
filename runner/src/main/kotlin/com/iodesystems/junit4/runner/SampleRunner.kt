package com.iodesystems.junit4.runner

import org.junit.runner.JUnitExpose

fun main() {
  JUnitExpose.run(
    JUnitRunner.Config(
      testPackageScan = "com.iodesystems.junit4.runner",
      enableTextOutput = true,
      outputXmlFile = "test-run.xml",
    )
  )
}
