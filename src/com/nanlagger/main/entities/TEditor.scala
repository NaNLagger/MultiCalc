package com.nanlagger.main.entities

/**
 * Created by NaNLagger on 20.03.15.
 * @author Stepan Lyashenko
 */
class TEditor {
  private val DEFAULT_NULL = "0"
  private val DEFAULT_DEL = "/"
  var fState = false;

  private var dem: String = DEFAULT_NULL

  private def addDem(a: Int) = {
    if(dem.equals(DEFAULT_NULL))
      dem = ""
    dem += a;
  }

  private def addZero() = if (!dem.equals(DEFAULT_NULL)) dem += DEFAULT_NULL

  private def addDel() = if (!dem.contains(DEFAULT_DEL)) dem += DEFAULT_DEL

  private def backSpace() {
    if (dem.length <= 1) {
      dem = DEFAULT_NULL
    }
    else {
      dem = dem.substring(0, dem.length - 1)
    }
  }

  private def clear() = dem = DEFAULT_NULL

  def read: String = dem

  def edit(a: Int): String = {
    if(!fState) clear(); fState = true
    a match {
      case 0 =>
        addZero
      case Commands.DEL =>
        addDel
      case Commands.BS =>
        backSpace
      case Commands.CL =>
        clear
      case _ =>
        addDem(a)
    }
    return dem
  }
}
