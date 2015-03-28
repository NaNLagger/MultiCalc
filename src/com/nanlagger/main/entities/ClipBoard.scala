package com.nanlagger.main.entities

import java.awt.Toolkit
import java.awt.datatransfer._
import java.io.IOException

/**
 * Created by NaNLagger on 28.03.15.
 * @author Stepan Lyashenko
 */
class ClipBoard extends ClipboardOwner {
  private val clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

  override def lostOwnership(clipboard: Clipboard, transferable: Transferable): Unit = {}

  def setData(data: String) = {
    clipboard.setContents(new StringSelection(data), this)
  }

  @throws(classOf[IOException])
  @throws(classOf[UnsupportedFlavorException])
  def getData() = {
    clipboard.getData(DataFlavor.stringFlavor).toString
  }
}
