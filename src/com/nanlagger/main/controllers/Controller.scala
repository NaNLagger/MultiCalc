package com.nanlagger.main.controllers

import com.nanlagger.main.entities.{Commands, TFrac, TEditor}


/**
 * Created by NaNLagger on 21.03.15.
 * @author Stepan Lyashenko
 */
object Controller {
  private type WorkType = TFrac
  private val editor = new TEditor
  private val proc = new TProc[WorkType]
  private var ctrlState = TCtrlState.cStart

  def doCommand(command: Int): String = {
    command match {
      case x if(x >= 0 && x <= Commands.DEL || x == Commands.BS) => runEditor(x)
      case Commands.CL => ctrlState = TCtrlState.cStart; proc.resetProc(); editor.edit(Commands.CL)
      case x if(x >= Commands.PLUS && x <= Commands.DIV) => runOperation(x)
      case Commands.RESULT => runResult()
    }
  }

  def runEditor(command: Int): String = {
    ctrlState match {
      case TCtrlState.cEditing => {
        editor.edit(command)
      }
      case TCtrlState.cOpChange => {
        ctrlState = TCtrlState.cEditing
        editor.edit(command)
      }
      case TCtrlState.cExpDone => {
        ctrlState = TCtrlState.cEditing
        editor.edit(Commands.CL)
        editor.edit(command)
      }
      case TCtrlState.cStart => {
        editor.edit(command)
      }
    }
  }

  def runOperation(command: Int): String = {
    ctrlState match {
      case TCtrlState.cStart => {
        proc.setLop_res(new WorkType(editor.read))
        proc.setOperation(command)
        ctrlState = TCtrlState.cOpChange
        editor.edit(Commands.CL)
      }
      case TCtrlState.cExpDone => {
        proc.setOperation(command)
        ctrlState = TCtrlState.cOpChange
        editor.edit(Commands.CL)
      }
      case TCtrlState.cOpChange => {
        proc.setOperation(command)
        editor.edit(Commands.CL)
      }
      case TCtrlState.cEditing => {
        proc.setRop(new WorkType(editor.read))
        proc.runOperation()
        proc.setOperation(command)
        ctrlState = TCtrlState.cExpDone
        proc.getLop_res.toString
      }
    }
  }

  def runResult(): String = {
    ctrlState match {
      case TCtrlState.cEditing => {
        proc.setRop(new WorkType(editor.read))
        proc.runOperation()
        ctrlState = TCtrlState.cExpDone
        proc.getLop_res.toString
      }
      case TCtrlState.cExpDone => {
        proc.runOperation()
        proc.getLop_res.toString
      }
      case _ => editor.read
    }
  }

  private object TCtrlState extends Enumeration {
    val cStart, cEditing, FunDone, cValDone, cExpDone, cOpChange, cError = Value
  }
}
