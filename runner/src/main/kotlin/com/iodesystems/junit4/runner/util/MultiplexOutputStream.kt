package com.iodesystems.junit4.runner.util

import java.io.IOException
import java.io.OutputStream
import java.util.*


class MultiplexOutputStream(val outputStreams: List<OutputStream>) : OutputStream() {

  @Throws(IOException::class)
  override fun write(b: Int) {
    for (os in outputStreams) {
      os.write(b)
    }
  }

  @Throws(IOException::class)
  override fun write(b: ByteArray) {
    for (os in outputStreams) {
      os.write(b)
    }
  }

  @Throws(IOException::class)
  override fun write(b: ByteArray, off: Int, len: Int) {
    for (os in outputStreams) {
      os.write(b, off, len)
    }
  }

  @Throws(IOException::class)
  override fun flush() {
    for (os in outputStreams) {
      os.flush()
    }
  }

  @Throws(IOException::class)
  override fun close() {
    for (os in outputStreams) {
      os.close()
    }
  }
}
