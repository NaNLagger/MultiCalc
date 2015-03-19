package com.nanlagger.test

import com.nanlagger.main.entities.TFrac
import org.junit.Test
import org.junit.Assert._
/**
 * Created by NaNLagger on 19.03.15.
 * @author Stepan Lyashenko
 */
class TFrac$Test {

  @Test
  def testConstructor(): Unit = {
    assertEquals(new TFrac(10, -11) toString, "-10/11")
    assertEquals(new TFrac(4, 6) toString, "2/3")
    assertEquals(new TFrac("-10") toString, "-10/1")
  }

  @Test(expected = classOf[IllegalArgumentException])
  def testConstructor2(): Unit = {
    new TFrac(1,0);
    ()
  }

  @Test(expected = classOf[IllegalArgumentException])
  def testConstructor3(): Unit = {
    new TFrac("-1/0");
    ()
  }

  @Test(expected = classOf[NumberFormatException])
  def testConstructor4(): Unit = {
    new TFrac("1/sfs");
    ()
  }

}
