package org.junit.runner

import com.google.common.reflect.ClassPath
import com.iodesystems.junit4.runner.JUnitRunner
import com.iodesystems.junit4.runner.XmlRunListener
import org.junit.Test
import org.junit.internal.RealSystem
import org.junit.internal.TextListener
import java.io.FileOutputStream
import kotlin.system.exitProcess

object JUnitExpose {
  fun run(cfg: JUnitRunner.Config, vararg junitArgs: String): Result {
    if (cfg.classes == null && cfg.testPackageScan == null) {
      throw IllegalArgumentException("Must specify either classes or testPackageScan")
    }

    val classes = cfg.classes ?: ClassPath.from(Test::class.java.classLoader)
      .getTopLevelClassesRecursive(cfg.testPackageScan!!)
      .map { cls -> Class.forName(cls.name) }
      .filter { cls ->
        cls.declaredMethods
          .find { method ->
            method.isAnnotationPresent(Test::class.java)
          } != null
      }

    val system = RealSystem()
    val core = JUnitCore()

    val parse = JUnitCommandLineParseResult.parse(junitArgs)
    var request = Request.classes(*classes.toTypedArray())
    parse.filterSpecs.forEach { filterSpec ->
      val filter = FilterFactories.createFilterFromFilterSpec(
        request, filterSpec
      )
      request = request.filterWith(filter)
    }

    if (cfg.enableTextOutput) {
      core.addListener(TextListener(system))
    }

    val outputXmlFile = cfg.outputXmlFile
    if (outputXmlFile != null) {
      core.addListener(XmlRunListener(output = FileOutputStream(outputXmlFile)))
    }


    val result = core.run(request)

    if (!cfg.noExit) {
      exitProcess(if (result.wasSuccessful()) 0 else 1)
    }
    return result
  }
}
