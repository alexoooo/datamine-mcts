package ao.ai.opt.tree.demo.bin

import ao.ai.opt.tree.TreeSearch
import util.Random
import ao.ai.opt.tree.model.Branch
import ao.ai.opt.tree.phase.impl.{RobustDecision, RandomSimulation, MeanUcbSelection, Mean}

object BinaryGameDemo extends Application
{
  //--------------------------------------------------------------------------
  val statisticBuilder = new Mean.Factory

  val mcts = new TreeSearch(
    statisticBuilder,
    new MeanUcbSelection,
    new RandomSimulation(new Random()))

  val root = new Branch(
        statisticBuilder,
        new BinaryGame.StateBuffer)

  for (i <- 1 to 100) {
    mcts.search(
      new BinaryGame.StateBuffer, root)
  }

  val bestChild =
    new RobustDecision().bestChild( root )

  println( "bestChild = " + bestChild)
}