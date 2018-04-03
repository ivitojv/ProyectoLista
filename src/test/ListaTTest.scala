package test

import org.scalatest._
import modelo._

class ListaTTest extends FlatSpec with Matchers {
  
  behavior of "ListaT"
  
  val lista = new ListaT("",null)
  val tarea1 = new Tarea(null,"")
  val tarea2 = new Tarea(null,"")
  
  it should "return the size of the list" in{
    lista.size should be (0)
    lista += tarea1
    lista.size should be (1)
    lista -= tarea1
  }
  it should "add a new item to the list" in {
    lista += tarea1
    lista.size should be (1)
    lista(0) shouldEqual tarea1
    lista -= tarea1
  }
  
  it should "substract an item off the list" in{
    lista += tarea1
    lista += tarea2
    lista -= tarea1
    lista.size should be (1)
    lista(0) shouldEqual tarea2
  }
  
  it should "return index task" in{
    lista(0) shouldEqual tarea2
  }
  
  it should "throw a IndexOutOfBoundsException if you access to an empty index" in{
    a [IndexOutOfBoundsException] should be thrownBy {
      lista(1)
    }
  }
}