package io.univalence.sucre_syntaxique

import Rational.{monoidMultiplication, semigroupSum, I}
import cats.Monoid.combineAll

@main
def _01_rational(): Unit =
  val rationals = List(1 I 2, 1 I 2)
  println(monoidMultiplication.combineAll(rationals).simplify)
  println(semigroupSum.combineN(1 I 10, 10).simplify)
