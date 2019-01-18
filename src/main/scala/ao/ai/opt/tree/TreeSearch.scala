package ao.ai.opt.tree

import ao.ai.opt.tree.model._
import phase.{Statistic, Expansion, Selection, Simulation}

class TreeSearch[/*A,*/ S/*, R*/](
    statisticBuilder : Statistic.Builder[S],
    selection        : Selection [/*A, */S],
//    expansion        : Expansion [A, S],
    simulation       : Simulation/*[/*A,*/ S]*/)
{
  //--------------------------------------------------------------------------
  def search(
      state : State/*[A/*, R*/]*/,
      from  : Branch[/*A, */S    ]) =
  {
//    val nodeFactory = new Node.Factory[S](statisticBuilder, state)

    val path = select(state, from, Nil)

//    var statistics = Nil



//    var path = (state.nextToAct, from) :: Nil
//    var cursor = from
//    var actionToLeaf : Int = -1
//    while (path.head._2.isExpandedNonLeaf)
//    while (path.head._2.isBranch)
//    {
//      val nextActionIndex = selection.select( path.head._2 )
//
//      state.advance( nextActionIndex )
//
//      val nextNode = cursor.child(
//        nextActionIndex, statisticBuilder)
//
//      val nextStatistic =
//        cursor.statistic( nextActionIndex )
//          .getOrElse( statisticBuilder.newInstance )
//
//      statistics = (state.nextToAct, nextStatistic) :: statistics
//
////      path = (state.nextToAct, nextNode) :: path
//      cursor = nextNode
//    }

//    path.head._2 match {
//      case leaf : Leaf[S] => {
//        leaf.expand( state.actionCount )
//      }
//      case _ =>
//    }

//    if (path.head._2.isLeaf) {
////    if (! cursor.isExpandedLeaf) {
//      cursor.expand(
//        state.actionCount,
//        statisticBuilder.newInstance)
//
////      if (expansion.shouldExpand( path.head._2 )) {
////        path.head._2.expand( state.actions )
////      }
//    }

    val outcome = simulation.playOut( state )

    for (step <- path) {
      step._2.statistic.
        sample( outcome.rewards(step._1) )
    }
  }


  //--------------------------------------------------------------------------
  private def select(
        state       : State,
        cursor      : Node[S],
        path        : List[(Int, Node[S])]
      ) : List[(Int, Node[S])] =
  {
    cursor match {
      case b : Branch[S] =>
      {
        val step = (state.nextToAct, b)
//        val nextPath = step :: path

        val nextActionIndex = selection.select( b )

        state.advance( nextActionIndex )

        val nextNode = b.child(
          nextActionIndex, statisticBuilder, state)

        select(state, nextNode, step :: path)
      }

      case leaf : Leaf[S] => {
//        if (expansion.shouldExpand( leaf )) {
//          // ...
//        } else {
//          (state.nextToAct, cursor) :: path
//        }

        val expansion = leaf.expand( state.actionCount )
        (state.nextToAct, expansion) :: path
      }

      case terminal : Terminal[S] => {
        (state.nextToAct, cursor) :: path
      }
    }
  }
}
