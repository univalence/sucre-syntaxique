package io.univalence.sucre_syntaxique

@main
def _01_anonymous_function(): Unit =
  val myList = List(1, 2, 3)

  val myListPlusOne      = myList.map(value => value + 1)
  val myListPlusOneSugar = myList.map(_ + 1)

  assert(myListPlusOne == myListPlusOneSugar)

@main
def _02_anonymous_function(): Unit =
  val myList = List((1, 2), (3, 4), (5, 6))

  val myListCombine      = myList.map((left, right) => left + right)
  val myListCombineSugar = myList.map(_ + _)

  assert(myListCombine == myListCombineSugar)

@main
def _03_anonymous_function(): Unit =
  val myList = List(3, 2, 1)

  val myListReduced      = myList.reduce((left, right) => left - right)
  val myListReducedSugar = myList.reduce(_ - _)

  assert(myListReduced == myListReducedSugar)

@main
def _04_anonymous_function(): Unit =
  val myList = List(1, 2, 3)

  val myListReduced = myList.map(x => x * x)
// val myListReducedSugar = myList.map(_ * _) Ne fonctionne pas

@main
def _05_anonymous_function(): Unit =
  enum Action {
    case LEFT
    case RIGHT
    case CLIMB
    case SWIM
  }

  val myList = List(Action.LEFT, Action.CLIMB, Action.SWIM)

  val myListMapped =
    myList.map(direction =>
      direction match {
        case Action.LEFT  => "allez à gauche"
        case Action.RIGHT => "allez à droite"
        case Action.CLIMB => "grimpez"
        case Action.SWIM  => "nagez tout droit"
      }
    )
  val myListMappedSugar =
    myList.map {
      case Action.LEFT  => "allez à gauche"
      case Action.RIGHT => "allez à droite"
      case Action.CLIMB => "grimpez"
      case Action.SWIM  => "nagez tout droit"
    }

  assert(myListMapped == myListMappedSugar)
  println(myListMappedSugar.reduce(_ + " puis " + _))
