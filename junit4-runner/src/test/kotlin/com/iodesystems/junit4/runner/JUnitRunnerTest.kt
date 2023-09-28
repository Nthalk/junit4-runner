package com.iodesystems.junit4.runner

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.io.File


class JUnitRunnerTest {

  @Test
  fun writesOutput() {
    val classes = listOf(
      Class.forName("com.iodesystems.junit4.runner.SampleTest")
    )
    val output = File("target/test-results/writesOutput.xml")
    // Ensure file is deleted
    output.delete()
    // Ensure directory is made
    output.parentFile.mkdirs()

    val result = JUnitRunner.run(
      JUnitRunner.Config(
        classes = classes,
        enableTextOutput = false,
        outputXmlFile = output.absolutePath,
        noExit = true
      )
    )
    val xml = output.readText()
    assertThat("Xml is empty", xml.isNotEmpty())
    // Result has no failures
    assertThat("Result has failures", result.failureCount == 1)
    assertThat("Result ran all tests", result.runCount == 3)
  }

}
