package modelo
import collection.mutable.ArrayBuffer
class Lista(val name:String, val author:Person) extends Serializable{
  var lista = ArrayBuffer.empty[Any]
  def this(name:String, p:Person, l:ArrayBuffer[Any]){
    this(name,p)
    lista = l
  }
  def +=(t:Any) {lista+=t}
  def -=(t:Any) {lista-=t}
}
