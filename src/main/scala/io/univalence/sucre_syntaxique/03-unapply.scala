package io.univalence.sucre_syntaxique

@main
def _01_unapply(): Unit =
  final case class CC(name: String, done: Boolean)

  val cc = CC("hello", done = false)

  cc match {
    case CC(name, true) => println(name)
    case _              => println("Le unnaply n'a pas fonctionné")
  }

  CC.unapply(cc) match {
    case CC(name, true) => println(name)
    case _              => println("Le unnaply n'a pas fonctionné")
  }

@main
def _02_unapply(): Unit =
  final case class CC(name: String, done: Boolean)

  object CC {
    def unapply(cc: CC): Option[String] = if (cc.done) Some(cc.name) else None
  }

  val cc = CC("hello", done = false)

  cc match {
    case CC(name) => println(name)
    case _        => println("Le unnaply n'a pas fonctionné")
  }

@main
def _03_unapply(): Unit =
  val myTuple = 1 -> 2

  myTuple match {
    case Tuple2(1, value) => println(s"La valeur de ma clé/value est $value")
    case ->(2, value)     => println(s"La valeur de ma clé/value est $value")
    case 3 -> value       => println(s"La valeur de ma clé/value est $value")
    case _                => println("Je ne peux pas extraire ma valeur")
  }

@main
def _04_unapply(): Unit =
  final case class URL(address: String, port: Int)

  object :: {
    def unapply(url: URL): Option[(String, Int)] = Some((url.address, url.port))
  }

  val localHost = URL("localhost", 8080)

  localHost match {
    case ::("localhost", port)                     => println(s"I am in localhost and the port is $port")
    case address :: port if address == "localhost" => println("I am not in localhost")
  }

  localHost match {
    case "localhost" :: port                       => println(s"I am in localhost and the port is $port")
    case address :: port if address == "localhost" => println("I am not in localhost")
  }
