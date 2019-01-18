package ao.ai.opt.tree.demo.ttt

object Coordinate
{
  //--------------------------------------------------------------------------
  object Row extends Enumeration
  {
    type Row = Value
    val One, Tow, Three = Value
  }

  object Column extends Enumeration
  {
    type Column = Value
    val A, B, C = Value
  }
}