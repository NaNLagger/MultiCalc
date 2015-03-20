package com.nanlagger.main

import javax.swing.UIManager

import com.nanlagger.main.ui.MainForm

/**
 * Created by NaNLagger on 19.03.15.
 * @author Stepan Lyashenko
 */
object Main extends App {
  UIManager.getInstalledLookAndFeels.find(_.getName == "Nimbus").headOption match {
    case Some(info) => UIManager.setLookAndFeel(info.getClassName)
    case None => UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName)
  }
  val mainForm: MainForm = new MainForm
}
