package test

import org.scalatest._
import modelo._

class PersonTest extends FlatSpec with Matchers {
  
  behavior of "Person"
  
  it should "create a person" in{
    val p = new Person("Jose","email@email.com")
    assert(p != null)
    p.name should be ("Jose")
    p.correo should be ("email@email.com")
    p.contactos.size should be (0)
  }
}