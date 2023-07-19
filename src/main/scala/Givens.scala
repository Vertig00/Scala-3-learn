

import scala.collection.immutable.TreeSeqMap
import scala.math.Ordering

case class Person(surname: String, name: String, age: Int)
object Givens {


  // use everywhere
  val personOrdering: Ordering[Person] = new Ordering[Person]:
    override def compare(x: Person, y: Person): Int = x.surname compareTo y.surname

  def listPeople(persons: List[Person])(ordering: Ordering[Person]): Seq[Person] = ???
  def someOtherMethod(alice: Person, bob: Person)(ordering: Ordering[Person]): Int = ???


  // given/using
//  given standardPersonOrdering: Ordering[Person] with {
//    override def compare(x: Person, y: Person): Int = x.surname compareTo y.surname
//  }

  def someMethodRequiringordering(persons: List[Person])(using ordering: Ordering[Person]): List[Person] = ???

  // import givens
  // 1 - import
  //  import StandardValues.standardPersonOrdering //

  // 2 - import for a Type (only one)
//  import StandardValues.given Ordering[Person]

  // 3- import all
  import StandardValues.given
//  import StandardValues._ // not import givens

  someMethodRequiringordering(List(Person("Kluch", "Łukasz", 12)))


  // deriving givens
  given optOrdering[T](using normalOrdering: Ordering[T]): Ordering[Option[T]] with {
    override def compare(x: Option[T], y: Option[T]): Int = (x, y) match
      case (None, None) => 0
      case (None, _) => -1
      case (_, None) => 1
      case (Some(a), Some(b)) => normalOrdering.compare(a, b)
  }

  def sortThings[T](things: List[T])(using ordering: Ordering[T]) = ???

  val maybePersons: List[Option[Person]] = List()
  sortThings(maybePersons) // (optOrdering(standardPersonOrdering))

  def main(args: Array[String]): Unit = {

  }

}

object StandardValues {

  given standardPersonOrdering: Ordering[Person] with {
    override def compare(x: Person, y: Person): Int = x.surname compareTo y.surname
  }

}