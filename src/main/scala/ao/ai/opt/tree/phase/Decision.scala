package ao.ai.opt.tree.phase

import ao.ai.opt.tree.model.Node

trait Decision[S]
{
  //--------------------------------------------------------------------------
  def bestChild(of : Node[S]) : Int
}