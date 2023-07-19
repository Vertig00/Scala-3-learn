object TypeLambdas {

  /*
  Scala types:
  - kinds = types of types
  - Int, String - (level-0)
  - List, Option - (level-1)(generics)
  - Mona, Functor - level-2 (generics of generics):
  * */

  val aNum = 4 // level-0
  val intList = List.empty[Int] // level-1

  class Functor[F[_]] // level-2
  val functorOpt = new Functor[Option]


  // List is similar to a function = type constructor
  type MyList = [T] =>> List[T] // MyList == List
  type MapWithStringKey = [T] =>> Map[String, T]
  type MapWithStringKey2[T] = Map[String, T] // same in work
  val addressBook: MapWithStringKey[Int] = Map()

  type SpecialEither = [T, E] =>> Either[E, Option[T]]

  // monads
  trait Monad[M[_]] {
    def pure[A](value: A): M[A]
    def flatMap[A,B](ma: M[A])(trans: A => M[B]): M[B]
  }

  // Not work
  // class EitherMonad[E] extends Monad[Either[E, ?]] {}
  // work for Either[String, Int | String etc.]

  // This works
  class EitherMonad[E] extends Monad[[T] =>> Either[E, T]] {
     override def pure[A](value: A): Either[E, A] = ???
  
     override def flatMap[A, B](ma: Either[E, A])(trans: A => Either[E, B]): Either[E, B] = ???
  }

  def main(args: Array[String]): Unit = {

  }

}
