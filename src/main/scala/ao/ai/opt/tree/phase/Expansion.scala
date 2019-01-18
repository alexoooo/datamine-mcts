package ao.ai.opt.tree.phase

import ao.ai.opt.tree.model._

trait Expansion[S]
{
//  def expand(childrenOf: Node[A, S])
  def shouldExpand(leaf: Node[S]) : Boolean
}