package modelo

import collection.mutable.ArrayBuffer

class Person(val name:String, val correo:String) extends Serializable{
  var contactos = ArrayBuffer.empty[Person]
}