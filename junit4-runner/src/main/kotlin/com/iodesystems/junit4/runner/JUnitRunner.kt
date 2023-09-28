package com.iodesystems.junit4.runner

import org.junit.runner.JUnitExpose
import org.junit.runner.Result

object JUnitRunner {
  data class Config(
    val testPackageScan: String? = null,
    val classes: List<Class<*>>? = null,
    val enableTextOutput: Boolean = true,
    val outputXmlFile: String? = null,
    val noExit: Boolean = false,
  )

  fun run(cfg: Config, vararg junitArgs: String): Result {
    return JUnitExpose.run(cfg, *junitArgs)
  }
}
