package com.nanlagger.main.entities

import scala.collection.mutable.ArrayBuffer

/**
 * Created by NaNLagger on 02.04.15.
 * @author Stepan Lyashenko
 */
class THistory {

  val history = new ArrayBuffer[TRow]()

  def addFunction(mCommands: Int, mRop: TFrac, mResult: TFrac) = history += TRow(mCommands, mRop, mResult)
  def addOperation(command: Int, lop: TFrac, rop: TFrac, result: TFrac) = history += TRow(command, lop, rop, result)
  override def toString() = history.mkString("\n")
  def clear(): String = {
    history.clear()
    ""
  }

  private[THistory] class TRow(val command: Int, val lop: TFrac, val rop: TFrac, val result: TFrac, val typeCommand: Int = TRow.OPERATION) {
    def this(mCommands: Int, mRop: TFrac, mResult: TFrac) {
      this(mCommands, new TFrac(), mRop, mResult, TRow.FUNCTION)
    }

    override def toString(): String = {
      typeCommand match {
        case TRow.OPERATION => lop + {
          command match {
            case Commands.PLUS => " + "
            case Commands.MINUS => " - "
            case Commands.DIV => " / "
            case Commands.MUL => " * "
            case _ => " ? "
          }
        } + rop + " = " + result
        case TRow.FUNCTION => {
          command match  {
            case Commands.SQR => "sqr("
            case Commands.REV => "reverse("
            case _ => "unknown("
          }
        } + rop + " = " + result + ")"
        case _ => "UNKNOWN TYPE"
      }
    }
  }
  private[THistory] object TRow {
    val OPERATION = 1
    val FUNCTION = 2

    def apply(mCommands: Int, mRop: TFrac, mResult: TFrac) = new TRow(mCommands, mRop, mResult)
    def apply(command: Int, lop: TFrac, rop: TFrac, result: TFrac) = new TRow(command, lop, rop, result)
  }
}
