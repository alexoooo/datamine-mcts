package ao.ai.opt.tree.phase.impl

import ao.ai.opt.tree.phase.{Statistic, Selection}
import ao.ai.opt.tree.model.{Branch, Node}

class MeanUcbSelection
    extends Selection[Mean]
{
  //--------------------------------------------------------------------------
  override def select(
        childOf          : Branch[Mean]//,
//        statisticBuilder : Statistic.Builder[Mean]
      ): Int = //(A, Node[A, Mean]) =
  {
    val numerator = math.log(
      childOf.statistic.summary.count)

    (0 until childOf.childCount).max(Ordering.by(
      childOf.childStatistic( _ : Int ) match {
        case Some( s : Statistic[Mean] ) =>
          s.summary.mean + math.sqrt(numerator / s.summary.count)

        case None => Double.PositiveInfinity
      }))


//    var maxScore : Double = Double.NegativeInfinity
//    var maxIndex : Int    = -1
//    for (i <- 0 until childOf.count)
//    {
//      if (! childOf.isChildNodeDefined( 0 ))
//      {
//        return i
//      }
//
//      val meanStat : Mean = childOf.statistic( i ) match {
//        case Some( statistic ) => statistic.summary
//        case None              => require( false )
//      }
//
//      val score = meanStat.mean +
//                    math.sqrt(numerator / meanStat.count)
//      if (score > maxScore)
//      {
//        maxScore = score
//        maxIndex = i
//      }
//    }
//
//    maxIndex
  }
}