package ao.ai.opt.tree.model


trait State/*[A, R]*/
{
  //--------------------------------------------------------------------------
  def nextToAct: Int

//  def actions: Seq[A]
  def actionCount: Int

  def outcome: Option[Outcome]


  //--------------------------------------------------------------------------
//  def representation: R


  //--------------------------------------------------------------------------
//  def advance(action: A): Unit
  def advance(actionIndex: Int): Unit

//  /**
//   * Has to work at least once
//   */
//  def retreat(action: A): Unit

//  def prototype: State[A]
}