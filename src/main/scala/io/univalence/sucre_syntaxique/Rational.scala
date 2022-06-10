package io.univalence.sucre_syntaxique

import cats.{Monoid, Semigroup}

import scala.annotation.tailrec

case class Rational private (n: Int, d: Int) { self =>
  def simplify: Rational = {
    val gcd = Rational.gcd(self.abs.n, d)
    Rational(n / gcd, d / gcd)
  }

  def +(other: Rational): Rational = Rational(n * other.d + other.n * d, d * other.d)
  def -(other: Rational): Rational = Rational(n * other.d - other.n * d, d * other.d)
  def *(other: Rational): Rational = Rational(n * other.n, d * other.d)

  def +(int: Int): Rational = Rational(n + int, d + int)
  def -(int: Int): Rational = Rational(n - int, d - int)

  def +:(int: Int): Rational = self + int
  def -:(int: Int): Rational = Rational(int - n, int - d)

  private def compare(other: Rational, f: (Int, Int) => Boolean) = f(n * other.d, d * other.n)

  def !=(other: Rational): Boolean = compare(other, _ != _)
  def ==(other: Rational): Boolean = compare(other, _ == _)
  def >(other: Rational): Boolean  = compare(other, _ > _)
  def <(other: Rational): Boolean  = compare(other, _ < _)
  def >=(other: Rational): Boolean = compare(other, _ >= _)
  def <=(other: Rational): Boolean = compare(other, _ <= _)

  def abs: Rational = if (n < 0) copy(n = n * -1) else self

  override def toString: String = s"$n/$d"
}

object Rational {
  given semigroupSum: Semigroup[Rational] = (x: Rational, y: Rational) => x + y
  given monoidMultiplication: Monoid[Rational] =
    new Monoid[Rational] {
      override def empty: Rational                             = 1 I 1
      override def combine(x: Rational, y: Rational): Rational = x * y
    }

  @tailrec
  def gcd(n1: Int, n2: Int): Int =
    if (n2 > n1) gcd(n2, n1)
    else if (n1 % n2 == 0) n2
    else gcd(n2, n1 % n2)

  def fromString(string: String): Option[Rational] =
    string match {
      case s"$n/$d" => n.toIntOption.flatMap(num => d.toIntOption.map(den => Rational(num, den)))
      case _        => None
    }

  def build(numerator: Int, denominator: Int): Rational =
    (numerator, denominator) match {
      case (n, d) if n >= 0 && d >= 0 => Rational(n, d)
      case (n, d) if n < 0 && d >= 0  => Rational(n, d)
      case (n, d) if n >= 0 && d < 0  => Rational(-1 * n, -1 * d)
      case (n, d) if n < 0 && d < 0   => Rational(-1 * n, -1 * d)
    }

  extension (numerator: Int) {
    def I(denominator: Int): Rational = Rational.build(numerator, denominator)
  }
}
