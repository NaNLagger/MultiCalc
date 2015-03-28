package com.nanlagger.main.controllers

import com.nanlagger.main.entities._


/**
 * Created by NaNLagger on 21.03.15.
 * @author Stepan Lyashenko
 */
object Controller {
  private type WorkType = TFrac
  private val editor = new TEditor
  private val proc = new TProc[WorkType]
  private val memory = new TMemory[WorkType]
  private val clipboard = new ClipBoard
  private var ctrlState = TCtrlState.cStart

  def doCommand(command: Int): String = {
    command match {
      case x if(x >= 0 && x <= Commands.DEL || x == Commands.BS || x == Commands.CZ || x == Commands.CL) => runEditor(x)
      case Commands.CE => ctrlState = TCtrlState.cStart; proc.resetProc(); editor.edit(Commands.CL)
      case x if(x >= Commands.PLUS && x <= Commands.DIV) => runOperation(x)
      case x if(x == Commands.REV || x == Commands.SQR) => runFunction(x)
      case Commands.RESULT => runResult()
      case x if(x >= Commands.MC && x<= Commands.MP) => runMemory(x)
      case _ => editor.read
    }
  }

  def runMemory(command: Int): String = {
    command match {
      case Commands.MC => memory.clear(); editor.read
      case Commands.MS => memory.setMemory(new WorkType(editor.read)); editor.read
      case Commands.MP if(memory.getState) => memory.add(new WorkType(editor.read)); editor.read
      case Commands.MR if(memory.getState) => editor.write(memory.getMemory.toString); editor.read
      case _ => editor.read
    }
  }

  def runEditor(command: Int): String = {
    ctrlState match {
      case TCtrlState.cEditing => {
        editor.edit(command)
      }
      case TCtrlState.cOpChange => {
        ctrlState = TCtrlState.cEditing
        editor.edit(Commands.CL)
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
      case TCtrlState.FunDone => {
        ctrlState = TCtrlState.cEditing
        proc.resetProc()
        ctrlState = TCtrlState.cStart
        editor.edit(Commands.CL)
        editor.edit(command)
      }
      case _ => editor.read
    }
  }

  def runOperation(command: Int): String = {
    ctrlState match {
      case TCtrlState.cStart => {
        proc.setLop_res(new WorkType(editor.read))
        proc.setOperation(command)
        ctrlState = TCtrlState.cOpChange
        editor.write(proc.getLop_res.toString)
        editor.read
      }
      case TCtrlState.cExpDone => {
        proc.setOperation(command)
        ctrlState = TCtrlState.cOpChange
        editor.write(proc.getLop_res.toString)
        editor.read
      }
      case TCtrlState.cOpChange => {
        proc.setOperation(command)
        editor.write(proc.getLop_res.toString)
        editor.read
      }
      case TCtrlState.cEditing => {
        proc.setRop(new WorkType(editor.read))
        proc.runOperation()
        proc.setOperation(command)
        ctrlState = TCtrlState.cExpDone
        editor.write(proc.getLop_res.toString)
        editor.read
      }
      case TCtrlState.FunDone => {
        proc.setRop(new WorkType(editor.read))
        proc.runOperation()
        proc.setOperation(command)
        ctrlState = TCtrlState.cExpDone
        editor.write(proc.getLop_res.toString)
        editor.read
      }
      case _ => editor.read
    }
  }

  def runFunction(command: Int): String = {
    ctrlState match {
      case TCtrlState.cStart => {
        proc.setRop(new WorkType(editor.read))
        proc.runFunction(command)
        editor.write(proc.getRop.toString)
        editor.read
      }
      case TCtrlState.cEditing => {
        proc.setRop(new WorkType(editor.read))
        proc.runFunction(command)
        ctrlState = TCtrlState.FunDone
        editor.write(proc.getRop.toString)
        editor.read
      }
      case TCtrlState.cExpDone => {
        editor.write(proc.getLop_res.toString)
        proc.resetProc();
        proc.setRop(new WorkType(editor.read))
        proc.runFunction(command)
        editor.write(proc.getRop.toString)
        ctrlState = TCtrlState.cStart
        editor.read
      }
      case _ => editor.read
    }
  }

  def runResult(): String = {
    ctrlState match {
      case TCtrlState.cEditing => {
        proc.setRop(new WorkType(editor.read))
        proc.runOperation()
        ctrlState = TCtrlState.cExpDone
        editor.write(proc.getLop_res.toString)
        editor.read
      }
      case TCtrlState.cExpDone => {
        proc.runOperation()
        editor.write(proc.getLop_res.toString)
        editor.read
      }
      case TCtrlState.cOpChange => {
        proc.setRop(proc.getLop_res)
        proc.runOperation()
        ctrlState = TCtrlState.cExpDone
        editor.write(proc.getLop_res.toString)
        editor.read
      }
      case TCtrlState.FunDone => {
        proc.setRop(new WorkType(editor.read))
        proc.runOperation()
        ctrlState = TCtrlState.cExpDone
        editor.write(proc.getLop_res.toString)
        editor.read
      }
      case _ => editor.read
    }
  }

  def clipboardCommand(command: String): String = {
    command match {
      case "copy" => clipboard.setData(editor.read); editor.read
      case "insert" => editor.write(clipboard.getData()); editor.read
    }
  }

  def getStateMemory = if(memory.getState) "On" else "Off"

  private object TCtrlState extends Enumeration {
    val cStart, cEditing, FunDone, cValDone, cExpDone, cOpChange, cError = Value
  }
}
