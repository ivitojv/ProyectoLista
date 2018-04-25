package test

import org.scalatest._
import modelo._

class TareaTest extends FlatSpec with Matchers {
  
  behavior of "Tarea"
  
  val p = new Person("a","c")
  
  it should "crear una tarea" in{
    val t = new Tarea(0,p,"titulo")
    assert(t != null)
    t.title should be ("titulo")
    t.author should be (p)
    t.comment should be ("")
    t.finalizada should be (false)
    t.date should be (null)
  }
}