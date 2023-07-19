import OpaqueTypes.Graphics.{Color, Green, halfTransparency}

object OpaqueTypes {

/*
* Opaque type have base type methods only in defined scope, outside scope they are blank card
* */


  object Network { // domain of types
    type Name = String
    opaque type Age = Int

    // How to add methods to type
    // 1 - companion object
    object Age {
      def positive(n: Int): Option[Age] = if (n <0) None else Some(n)
    }

    // 2 - extension method

    extension (n: Age) {
      def isEven: Boolean = n % 2 == 0
    }

  }

 /*
 *  types structure
 * */
  object Graphics {
    opaque type Color = Int
    opaque type ColorFilter <: Color = Int

    val Red: Color = 0xFF000000
    val Green: Color = 0x00FF0000
    val halfTransparency: ColorFilter = 0x88
  }

  case class OverlayFilter(color: Color)

  import Network._
  val name: Name = "Łukasz"
//  val age: Age = 2 // opaque type dont have base type methods outside scope (Network)

  def main(args: Array[String]): Unit = {
    val ageOption = Age.positive(14)
    val isEven = ageOption.map(_.isEven)
    println(isEven)

    val name: Name = "Łukasz"
    println(name.length)

    val greenFilter = OverlayFilter(Green)
    val fadeLayer = OverlayFilter(halfTransparency)


  }

}
