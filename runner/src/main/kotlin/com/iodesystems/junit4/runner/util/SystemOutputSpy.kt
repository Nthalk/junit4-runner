package com.iodesystems.junit4.runner.util

import java.io.ByteArrayOutputStream
import java.io.PrintStream

class SystemOutputSpy {
  companion object {
    val originalOut = System.out
    val originalErr = System.err
    fun restore() {
      System.setErr(originalErr)
      System.setOut(originalOut)
    }
  }

  val out = ByteArrayOutputStream()
  val err = ByteArrayOutputStream()
  fun restore(): Output {
    SystemOutputSpy.restore()
    val ret = Output(out.toString(), err.toString())
    out.reset()
    err.reset()
    return ret
  }

  data class Output(val std: String, val err: String)

  fun trap() {
    System.setOut(
      PrintStream(
        MultiplexOutputStream(
          listOf(
            out,
            originalOut
          )
        )
      )
    )
    System.setErr(
      PrintStream(
        MultiplexOutputStream(
          listOf(
            err,
            originalErr
          )
        )
      )
    )
  }

}
