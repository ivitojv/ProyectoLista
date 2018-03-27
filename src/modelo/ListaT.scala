package modelo
import collection.mutable.ArrayBuffer
class ListaT(var name:String, var author:Person) extends Serializable{
  var lista = ArrayBuffer.empty[Tarjeta]
  def this(name:String, p:Person, l:ArrayBuffer[Tarjeta]){
    this(name,p)
    lista = l
  }
  def +=(t:Tarjeta) {lista+=t}
  def -=(t:Tarjeta) {lista-=t}
  def apply(index:Int) = lista(index)
  def size = lista.length
  def sortWith(func:(Tarjeta,Tarjeta) => Boolean):ListaT = new ListaT(name,author,lista.sortWith(func))
  def filter(func:(Tarjeta)=> Boolean):ListaT = new ListaT(name,author,lista.filter(func))
  def indexOf(t:Tarjeta) = lista.indexOf(t)
  def remove(index:Int) = {lista.remove(index)}
}

