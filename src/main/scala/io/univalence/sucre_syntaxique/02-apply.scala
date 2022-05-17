package io.univalence.sucre_syntaxique

@main
def _01_apply(): Unit =
  final case class CC(name: String, done: Boolean)

  object CC {
    def apply(name: String): CC = CC(name, true)
  }

  assert(CC("hello") == CC.apply("hello"))
  assert(CC("hello") == CC("hello", done = true))

@main
def _02_apply(): Unit =
  val mySet      = Set(1, 2, 3)
  val mySetSugar = Set.apply(1, 2, 3)

  assert(mySetSugar.apply(1) == mySetSugar(1))
  assert(mySetSugar.apply(4) == mySetSugar(4))
  assert(Set.apply(1).apply(1) == Set(1)(1))

@main
def _03_apply(): Unit =
  val myMap      = Map.apply((1, 2), (3, 4), (5, 6))
  val myMapSugar = Map((1, 2), (3, 4), (5, 6))

  assert(myMap == myMapSugar)

@main
def _04_apply(): Unit =
  val myMapSugar      = Map((1, 2), (3, 4), (5, 6))
  val myMapSugarSugar = Map(1 -> 2, 3 -> 4, 5 -> 6)

  assert(myMapSugar == myMapSugarSugar)

@main
def _05_apply(): Unit =
  def myFunction(x: Int): Int = x + 1

  assert(myFunction(5) == myFunction.apply(5))
