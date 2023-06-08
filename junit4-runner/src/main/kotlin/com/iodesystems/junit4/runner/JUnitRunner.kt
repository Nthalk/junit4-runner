package com.iodesystems.junit4.runner

import org.junit.runner.JUnitExpose

object JUnitRunner {
  data class Config(
    val testPackageScan: String? = null,
    val classes: List<Class<*>>? = null,
    val enableTextOutput: Boolean = true,
    val outputXmlFile: String? = null,
  )

  fun run(cfg: Config, vararg junitArgs: String) {
    JUnitExpose.run(cfg, *junitArgs)
  }
}
