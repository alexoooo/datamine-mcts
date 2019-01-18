package ao.ai.opt.tree.phase

import ao.ai.opt.tree.model.Outcome

trait Statistic[S]
{
  //--------------------------------------------------------------------------
//  def newInstance: Statistic[S]

//  def newBuilder: Statistic.Builder[S]


  //--------------------------------------------------------------------------
  def summary: S

  def sample(reward: Double)
}

object Statistic
{
  trait Builder[S]
  {
    def newInstance: Statistic[S]
  }
}