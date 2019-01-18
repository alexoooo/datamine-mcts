package ao.ai.opt.tree.demo.ttt


//----------------------------------------------------------------------------
import Coordinate.Row._
import Coordinate.Column._
import Player._
import actors.Actor


//----------------------------------------------------------------------------
case class Cell(row: Row, column: Column)

case class Move(
    cell: Cell, side: Player)

class Board()
{
  private val cells: Map[Cell, Player]

  def isAvailable(cell: Cell) =
    cells.get( cell ).isEmpty

  def update(move: Move) : Unit =
  {
    require( isAvaliable(move.cell) )

    cells( move.cell ) = move.side
  }

  def winner: Option[Player] =
  {
    val spans = for (span <- List(
          // rows
          List(Cell(One  , A), Cell(One  , B), Cell(One  , C)),
          List(Cell(Two  , A), Cell(Two  , B), Cell(Two  , C)),
          List(Cell(Three, A), Cell(Three, B), Cell(Three, C)),

          // columns
          List(Cell(One  , A), Cell(Two  , A), Cell(Three, A)),
          List(Cell(One  , B), Cell(Two  , B), Cell(Three, B)),
          List(Cell(One  , C), Cell(Two  , C), Cell(Three, C)),

          // diagonals
          List(Cell(One  , A), Cell(Two  , B), Cell(Three, C)),
          List(Cell(One  , C), Cell(Two  , B), Cell(Three, A))
          )
        ) yield span

    val definedSpans = spans.find(
      _.forall( cells.get(_).isDefined ))

    val winningSpans = definedSpans.find(
      _.forall(  ))
  }
}


//----------------------------------------------------------------------------
case object Start
case class Play(sender:Actor)


//----------------------------------------------------------------------------
class Player extends Actor
{
  def act()
  {

  }
}


//----------------------------------------------------------------------------
class TicTacToe extends Actor
{
  def act()
  {
    val xPlayer = new Player().start
    val oPlayer = new Player().start

    loop
    {
      react
      {
        case Start => {
          xPlayer ! Play(self)
          oPlayer ! Play(self)
        }

        case Move(cell, side) => {
          react
        }
      }
    }
  }
}