package ao.ai.opt.tree.phase.impl

import ao.ai.opt.tree.phase.Decision
import ao.ai.opt.tree.model.{Branch, Node}

class RobustDecision
    extends Decision[Mean]
{
  //--------------------------------------------------------------------------
  def bestChild(of : Node[Mean]) : Int =
  {
    of match {
      case b : Branch[Mean] => {
        (0 until b.childCount).max(Ordering.by(
          b.childStatistic( _ : Int ) match {
            case Some(s) => s.summary.count
            case None    => -1
          }))
      }

      case _ => throw new IllegalArgumentException
    }
  }
}