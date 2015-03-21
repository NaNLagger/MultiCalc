package com.nanlagger.main

import com.nanlagger.main.entities.Commands

/**
 * Created by NaNLagger on 19.03.15.
 * @author Stepan Lyashenko
 */
object LauncherTest extends App {
  def exec(message: String) = {
    println(message.reverse)
    message.reverse
  }

  println(Commands.RESULT)
}
