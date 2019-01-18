package ao.ai.opt.tree.phase

import ao.ai.opt.tree.model._


trait Selection[/*A, */S]
{
  //--------------------------------------------------------------------------
  /**
   * @return index of selected child
   */
  def select(
        childOf : Branch[/*A, */S]
      ): Int
}