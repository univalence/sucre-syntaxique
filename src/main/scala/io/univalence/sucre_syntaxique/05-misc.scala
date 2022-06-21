package io.univalence.sucre_syntaxique

import scala.language.postfixOps

@main
def _01_misc(): Unit =
  case class Train(wagons: Int) { self =>
    def clear: Train = copy(wagons = 0)

    def attach(other: Train): Train = copy(wagons = wagons + other.wagons)

    def <>(other: Train): Train = self attach other
  }

  val train1 = Train(10)
  val train2 = Train(20)

  val emptyTrain       = train1.clear
  val bothTrains       = train1.attach(train2)
  val bothTrainsSymbol = train1.<>(train2)

  val emptyTrainSugar       = train1 clear
  val bothTrainsSugar       = train1 attach train2
  val bothTrainsSymbolSugar = train1 <> train2

  assert(emptyTrain == emptyTrainSugar)
  assert(bothTrains == bothTrainsSugar)
  assert(bothTrainsSymbol == bothTrainsSymbolSugar)

@main
def _02_misc(): Unit =
  case object capitalize {
    def +:(string: String): String = string.capitalize
  }

  assert("magic" +: capitalize == "MagicMagic")

@main
def _03_misc(): Unit =
  trait Serializer[T] {
    def serialize(t: T): String
  }

  case class Model(name: String, age: Int)

  given Serializer[Model] =
    (model: Model) => s"""{
                         |  "name": "${model.name}",
                         |  "age": ${model.age}
                         |}""".stripMargin

  def toJson[T](t: T)(using Serializer[T]): String = implicitly[Serializer[T]].serialize(t)

  assert(toJson(Model("Dylan", 24)) == """{
                                         |  "name": "Dylan",
                                         |  "age": 24
                                         |}""".stripMargin)
