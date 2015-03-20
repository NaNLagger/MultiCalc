package com.nanlagger.main.entities

/**
 * Created by NaNLagger on 20.03.15.
 * @author Stepan Lyashenko
 */
class TEditor {
  private var dem: String = "0"

  private def addDem(a: Int) = {
    if(dem.equals("0"))
      dem = ""
    dem += a;
  }

  private def addZero() = if (!dem.equals("0")) dem += "0"

  private def addDel() = if (!dem.contains("/")) dem += ","

  private def backSpace() {
    if (dem.length <= 1) {
      dem = "0"
    }
    else {
      dem = dem.substring(0, dem.length - 1)
    }
  }

  private def clear() = dem = "0"


}
