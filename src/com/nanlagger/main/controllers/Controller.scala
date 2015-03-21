package com.nanlagger.main.controllers

import com.nanlagger.main.entities.{TFrac, TEditor, Commands}


/**
 * Created by NaNLagger on 21.03.15.
 * @author Stepan Lyashenko
 */
object Controller {
  type WorkType = TFrac
  val editor = new TEditor
  val proc = new TProc[WorkType]

  def doCommand(command: Int): String = {
    command match {
      case x if(x >= 0 && x <= Commands.DEL || x == Commands.BS || x == Commands.CL) => editor.edit(x)
      case x if(x >= Commands.PLUS && x <= Commands.DIV) => {
        editor.fState = false
        proc.getOperation match {
          case TProc.TPROC_NONE => proc.setLop_res(new WorkType(editor.read)); proc.setOperation(x); editor.edit(Commands.CL)
          case _ => proc.setOperation(x); editor.edit(Commands.CL)
        }
      }
      case Commands.RESULT => {
        if (editor.fState) {
          editor.fState = false;
          proc.setRop(new WorkType(editor.read))
          proc.runOperation();
          proc.getLop_res.toString
        } else {
          proc.runOperation();
          proc.getLop_res.toString
        }
      }
    }
  }

}
