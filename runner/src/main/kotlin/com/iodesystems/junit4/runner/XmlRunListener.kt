package com.iodesystems.junit4.runner

import com.iodesystems.junit4.runner.util.SystemOutputSpy
import com.iodesystems.junit4.xsd.*
import jakarta.xml.bind.JAXBContext
import jakarta.xml.bind.Marshaller
import org.junit.runner.Description
import org.junit.runner.Result
import org.junit.runner.notification.Failure
import org.junit.runner.notification.RunListener
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter


class XmlRunListener(
  val suites: TestSuites = TestSuites(),
  val output: OutputStream = System.out
) : RunListener() {
  private val spy = SystemOutputSpy()
  private val mc = MathContext(2, RoundingMode.HALF_UP)
  private fun timestamp(): String {
    return OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
  }

  var currentSuite: TestSuite? = null
  var currentTest: TestCase? = null

  private fun now(): BigDecimal {
    return System.currentTimeMillis().toBigDecimal()
  }

  private fun end(start: BigDecimal): BigDecimal {
    return System.currentTimeMillis().toBigDecimal().minus(start).divide(1000.toBigDecimal(), mc)
  }


  override fun testRunStarted(description: Description) {
    suites.timestamp = timestamp()
  }

  override fun testRunFinished(result: Result) {
    suites.time = (result.runTime / 1000.0).toBigDecimal()
    suites.skipped = result.ignoreCount
    suites.failures = result.failureCount
    val writer = OutputStreamWriter(output)
    val context: JAXBContext = JAXBContext.newInstance(TestSuites::class.java)
    val m = context.createMarshaller()
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
    m.marshal(suites, writer)
    writer.flush()
    writer.close()
  }

  override fun testSuiteStarted(description: Description) {
    if (description.testClass == null) return
    currentSuite = TestSuite().apply {
      time = now()
      timestamp = timestamp()
      name = description.displayName
      suites.testSuites.add(this)
    }
  }

  override fun testSuiteFinished(description: Description) {
    if (description.testClass == null) return
    currentSuite?.apply {
      time = end(time)
      classname = description.className
    }
  }

  override fun testStarted(description: Description) {
    spy.trap()

    suites.testCount += 1
    currentSuite?.apply {
      tests += 1
      currentTest = TestCase().apply {
        name = description.methodName
        file = description.testClass.canonicalName
        time = now()
        testCases.add(this)
      }

    }
  }

  override fun testFinished(description: Description) {
    val out = spy.restore()
    currentTest?.apply {
      time = end(time)
      if (out.std.isNotBlank()) systemOut = out.std
      if (out.err.isNotBlank()) systemErr = out.err
    }
  }

  override fun testFailure(failure: Failure) {
    currentTest?.apply {
      result = Failure().apply {
        message = failure.message
        stackTrace = failure.trace
        type = failure.exception.javaClass.simpleName
      }
    }
  }

  override fun testIgnored(description: Description) {
    currentTest?.apply {
      result = Skip().apply {
        message = "Skipped"
      }
    }
  }
}
