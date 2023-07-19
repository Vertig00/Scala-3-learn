object Indentation {

  // BASICALLY spaces

  // if-expressions
  val aCondition = if(2 > 3) "true" else "false"
  // NEW
  val condNew =
    if 2 > 3 then
      "bigger"
    else
      "smaller"
  val conNew2 = if 2 > 3 then "bigger" else "smaller"

  // for-comprehantion

  // old
  for {
    n <- List(1,2,3)
    c <- List("a", "b", "c")
  } yield s"$n$c"

  // new
  for // compiler add token <indent>
    n <- List(1, 2, 3)
    c <- List("a", "b", "c")
  yield s"$n$c"

  // methods
  // work up to 42 cause same indentation -> wcięcie
  def method(year: Int) =
    println("Computing")

    42
  end method // optional just to more readable

// ends are optional
  class Animal:
    def eat(): Unit =
      println("eating")
    end eat
  end Animal


  def main(args: Array[String]): Unit = {

  }

}
