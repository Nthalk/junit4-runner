package com.iodesystems.junit4.runner

import org.junit.Test


class SampleTest {
  @Test
  fun test() {

  }

  @Test
  fun testWithOutput() {
    println("Output!")
    System.err.println("Oh no! stderr!")
  }

  @Test
  fun testWithFailure() {
    error("Oh no!")
  }
}
