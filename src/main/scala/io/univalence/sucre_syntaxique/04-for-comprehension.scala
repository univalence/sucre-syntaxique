package io.univalence.sucre_syntaxique

@main
def _01_for_comprehension(): Unit =
  val maybeV1: Option[Int] = Some(5)
  val maybeV2: Option[Int] = Some(10)

  val maybeSum = maybeV1.flatMap(v1 => maybeV2.map(v2 => v1 + v2))
  val maybeSumSugar =
    for {
      v1 <- maybeV1
      v2 <- maybeV2
    } yield v1 + v2

  assert(maybeSum == maybeSumSugar)
