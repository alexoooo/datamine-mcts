package ao.ai.opt.tree.phase

import ao.ai.opt.tree.model._


trait Simulation/*[A]*/
{
  //--------------------------------------------------------------------------
  def playOut(fromState: State/*[A]*/): Outcome
}