package ao.ai.opt.tree.demo.bin

import ao.ai.opt.tree.model.{Outcome, State}

object BinaryGame
{
  //--------------------------------------------------------------------------
  sealed abstract class StateNode(
    val kids   : Seq[StateNode],
    val reward : Option[Double])

  object Root
    extends StateNode(
      List(LeftTerminal, RightTerminal), None)
//      List(LeftTerminal .asInstanceOf[StateNode],
//           RightTerminal.asInstanceOf[StateNode]), None)

  object LeftTerminal
    extends StateNode(Nil, Some( 0.4 ))

  object RightTerminal
    extends StateNode(Nil, Some( 0.3 ))


  //--------------------------------------------------------------------------
  class StateBuffer extends State
  {
    //------------------------------------------------------------------------
    private var state : StateNode = Root


    //------------------------------------------------------------------------
    def nextToAct: Int = 0

    def actionCount: Int =
      state.kids.length

    def outcome: Option[Outcome] =
      state.reward match {
        case None    => None
        case Some(r) => Some(Outcome( List(r) ))
      }


    //------------------------------------------------------------------------
    def advance(actionIndex: Int): Unit =
    {
      state = state.kids( actionIndex )
    }
  }
}