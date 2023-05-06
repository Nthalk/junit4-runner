package com.iodesystems.junit4.runner

import org.junit.runner.JUnitExpose

object JUnitRunner {
  data class Config(
    val testPackageScan: String,
    val enableTextOutput: Boolean = true,
    val outputXmlFile: String? = null,
  )

  fun run(cfg: Config, vararg junitArgs: String) {
    JUnitExpose.run(cfg, *junitArgs)
  }
}
