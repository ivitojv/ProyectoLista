/*
import modelo._
import collection.mutable.ArrayBuffer
import java.text.SimpleDateFormat
import io.StdIn
object Ejecutable extends App {
  val p = ArrayBuffer.empty[Person]
  val t = ArrayBuffer.empty[Tarea]
  var in = 0
  while (in != 5) {
    println("Escoja una opción:\n1-Crear nuevo usuario\n2-Crear nueva tajeta\n3-Mostrar todos los usuarios\n4-Mostrar todas las tarjetas\n5-Salir")
    in = StdIn.readInt
    val format = new SimpleDateFormat("yyyy-MM-dd")
    in match {
      case 1 => {
        println("Introduzca el nombre:")
        p += new Person(StdIn.readLine)
      }
      case 2 => {
        var person: Person = null
        println("Introduzca el usuario que va a crear la tarjeta")
        for (elem <- p) if (elem.name == StdIn.readLine) person = elem
        if (person != null) {
          println("Introduzca el titulo y la fecha de la tarjeta (YYYY-MM-DD)")
          var resp = StdIn.readLine.split(" ")
          t += new Tarea(person, resp(0), format.parse(resp(1)))
        }else println("Esa persona no está en el sistema")
      }
      case 3 => {
        for (elem <- p) println(elem.name)
      }
      case 4 => {
        for (elem <- t) println(elem.toString())
      }
      case _ =>
    }
  }
}*/