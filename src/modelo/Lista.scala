package modelo
import collection.mutable.ArrayBuffer
class Lista {
  var tarjetas = ArrayBuffer.empty[Tarjeta]
  def this(t:ArrayBuffer[Tarjeta]){
    this()
    tarjetas = t
  }
  def add(t:Tarjeta){tarjetas+=t}
  def del(t:Tarjeta){tarjetas-=t}
}