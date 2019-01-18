package ao.ai.opt.tree.phase.impl

import ao.ai.opt.tree.phase.Simulation
import ao.ai.opt.tree.model.{State, Outcome}
import util.Random

class RandomSimulation(rand : Random)
    extends Simulation
{
  //--------------------------------------------------------------------------
  def playOut(fromState: State): Outcome =
  {
    fromState.outcome match {
      case Some(o) => o
      case None    => {
        fromState.advance(
          rand.nextInt( fromState.actionCount ))
        playOut( fromState )
      }
    }
  }
}