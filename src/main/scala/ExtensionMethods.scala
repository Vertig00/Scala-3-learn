object ExtensionMethods {


  case class Person(name: String) {
    def greet: String = s"Hi, my name is $name"
  }

//  implicit class PersonLike(name: String) {
//    def greet: String = Person(name).greet
//  }

//  val lukeGreet = "Lukasz".greet // new PersonLike("Lukasz")


  // extensionMethod

  extension (name: String) {
    def greet: String = Person(name).greet
  }


  val lukeGreet = "Lukasz".greet // new PersonLike("Lukasz")


  // assume this is in external library
  sealed abstract class Tree[+A]
  case class Leaf[+A](value: A) extends Tree[A]
  case class Branch[+A](left: Tree[A], right: Tree[A]) extends Tree[A]
  //

  // generic extension
  extension [A](tree: Tree[A]) {
    def filter(predicate: A => Boolean): Boolean = tree match
      case Leaf(value) => predicate(value)
      case Branch(left, right) => left.filter(predicate) && right.filter(predicate)

    def map[B](f: A => B): Tree[B] = tree match
      case Leaf(value) => Leaf(f(value))
      case Branch(left, right) => Branch(left.map(f), right.map(f))
  }

  // with using

  extension [A](tree: Tree[A])(using numeric: Numeric[A]) {
    def sum /*(using numeric: Numeric[A])*/ : A = tree match
      case Leaf(value) => value
      case Branch(left, right) => numeric.plus(left.sum, right.sum)
  }


  def main(args: Array[String]): Unit = {
    val tree = Branch(Leaf(2), Leaf(3))
    println(tree.filter(_ > 0))

    println(tree.map(_ * 10))

    println(tree.sum)

  }

}
