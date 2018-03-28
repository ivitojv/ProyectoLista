package modelo
import collection.mutable.ArrayBuffer
class ListaT(var name:String, var author:Person) extends Serializable{
  var lista = ArrayBuffer.empty[Tarea]
  def this(name:String, p:Person, l:ArrayBuffer[Tarea]){
    this(name,p)
    lista = l
  }
  def +=(t:Tarea) {lista+=t}
  def -=(t:Tarea) {lista-=t}
  /*
  def apply(index:Int) = lista(index)
  def size = lista.length
  def sortWith(func:(Tarea,Tarea) => Boolean):ListaT = new ListaT(name,author,lista.sortWith(func))
  def filter(func:(Tarea)=> Boolean):ListaT = new ListaT(name,author,lista.filter(func))
  def indexOf(t:Tarea) = lista.indexOf(t)
  def remove(index:Int) = {lista.remove(index)}
  */
}

