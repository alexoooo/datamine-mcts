package ao.ai.opt.tree.model

import ao.ai.opt.tree.phase._

//----------------------------------------------------------------------------
/**
 * @param <S> type of the statistic
// * @param <A> type of the action
// * @param <R> type of the state representation
 */
sealed abstract class Node[S](
    val statistic : Statistic[S])
{
//  def isBranch   : Boolean
//  def isLeaf     : Boolean
//  def isTerminal : Boolean
}

//object Node
//{
//  class Factory[S](
//      statisticBuilder : Statistic.Builder[S],
//      state            : State[S])
//  {
//    def newInstance: Node[S] = {
//      val statistic = statisticBuilder.newInstance
//      state.outcome match {
//        case Some(_) => Terminal( statistic )
//        case None    => Leaf    ( statistic )
//      }
//    }
//  }
//}


//----------------------------------------------------------------------------
case class Terminal[S] (
    override val statistic : Statistic[S])
  extends Node[S]( statistic )
{
//  override def isBranch   = false
//  override def isLeaf     = false
//  override def isTerminal = true
}


//----------------------------------------------------------------------------
class Branch[S](
      statistic  : Statistic[S],
  val childCount : Int)
    extends Node[S](statistic)
{
  def this(
      statisticFactory : Statistic.Builder[S],
      state            : State
    ) = this(statisticFactory.newInstance, state.actionCount)


//  override def isBranch   = true
//  override def isLeaf     = false
//  override def isTerminal = false


  private var kids : Array[Node[S]] = null

  def isChildNodeDefined(childIndex : Int) = {
    kids != null && kids( childIndex ) != null
  }

  def childStatistic(childIndex : Int) : Option[Statistic[S]] =
  {
    if (isChildNodeDefined( childIndex )) {
      Some(kids( childIndex ).statistic)
    } else {
      None
    }
  }


  def child(
        childIndex       : Int,
        statisticBuilder : Statistic.Builder[S],
        childState       : State
      ) : Node[S] =
  {
    if (kids == null) {
      kids = new Array[Node[S]]( childCount )
    }

    var kid = kids( childIndex )
    if (kid == null) {
//      kid = Leaf( statisticBuilder.newInstance )
//      kid = nextStateNodeFactory.newInstance

      val statistic = statisticBuilder.newInstance
      kid = childState.outcome match {
        case Some(_) => Terminal( statistic )
        case None    => Leaf    ( statistic )
      }

      kids( childIndex ) = kid
    }
    kid
  }
}


//----------------------------------------------------------------------------
case class Leaf[S]  (
  override val statistic : Statistic[S])
    extends Node[S](statistic)
{
//  override def isBranch   = false
//  override def isLeaf     = true
//  override def isTerminal = false


  def expand(childCount : Int) =
    new Branch[S](statistic, childCount)
}

////-----------------------------------------------------------------------------
///**
// * Nodes have no awareness of their parent.
// * All child information is stored at the parent.
// */
//class Node[/*A, */S]
//(
////  val statistic : Statistic[S]
//)
//{
//  //--------------------------------------------------------------------------
////  def statistic : Statistic[S] = stat
//
////  private var actions   : Array[A]
//  private var kidCount  : Int = -1
//  private var kids      : Array[Node[/*A, */S]]
//  private val statistic : Statistic[S]
//
//
//  //--------------------------------------------------------------------------
////  def expand(actions: Seq[A]) =
////  {
////    require(actions == null)
////    this.actions = actions
////  }
//  def expand(
//      childCount : Int,
//      statistic  : Statistic[S]) =
//  {
//    require(kidCount == -1)
//
//    this.kidCount  = childCount
//    this.statistic = statistic
//  }
//
//
//  //--------------------------------------------------------------------------
//  def childCount =
//    kidCount
////    actions.length
//
////  def action(childIndex : Int) =
////    actions( childIndex )
//
//  def statistic(
//        childIndex : Int
//      ) : Option[Statistic[S]] =
//  {
//    if (isChildNodeDefined( childIndex ))
//      Some(kids( childIndex )) else None
//  }
//
////  def statistic(
////        childIndex       : Int,
////        statisticFactory : Statistic.Builder[S]
////      ) : Statistic[S] =
////  {
////    if (statistics == null) {
////      statistics = new Array[Statistic[S]]( actions.length )
////    }
////
////    var statistic = statistics( childIndex )
////    if (statistic == null) {
////      statistic = statisticFactory.newInstance
////      statistics( childIndex ) = statistic
////    }
////
////    statistic
////  }
//
//  def isChildNodeDefined(childIndex : Int) =
//  {
//    kids != null && kids( childIndex ) != null
//  }
//
//  def child(
//        childIndex       : Int/*,
//        statisticBuilder : Statistic.Builder[S]*/
//      ) : Node[/*A, */S] =
//  {
//    if (kids == null) {
//      kids = new Array[Node[/*A, */S]]( actions.length )
//    }
//
//    var kid = kids( childIndex )
//    if (kid == null) {
////      kid = new Node[/*A, */S]( statisticBuilder.newInstance )
//      kid = new Node[/*A, */S]()
//      kids( childIndex ) = kid
//    }
//    kid
//  }
//
//
//  //--------------------------------------------------------------------------
//  def isExpanded =
//    actions != null
//
//  def isExpandedNonLeaf =
//    isExpanded && ! actions.isEmpty
//
//  def isExpandedLeaf =
//    isExpanded && actions.isEmpty
//}