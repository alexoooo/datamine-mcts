package ao.ai.opt.tree.phase.impl

import ao.ai.opt.tree.phase.Statistic

class Mean
    extends Statistic[Mean]
{
  //--------------------------------------------------------------------------
  private var total       : Double = 0
  private var sampleCount : Int    = 0


  //--------------------------------------------------------------------------
  override def summary = this

//  override def newBuilder: Statistic.Builder[Mean] =
//    () => this


  //--------------------------------------------------------------------------
  override def sample(reward: Double) =
  {
    total       += reward
    sampleCount += 1
  }


  //--------------------------------------------------------------------------
  def mean: Double =
    total / sampleCount

  def count: Int = sampleCount
}


//--------------------------------------------------------------------------
object Mean
{
  class Factory extends Statistic.Builder[Mean] {
    override def newInstance: Statistic[Mean] =
      new Mean
  }

//  def newBuilder: Statistic.Builder[Mean] =
//    () => new Mean
}