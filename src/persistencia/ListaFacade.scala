package persistencia
import modelo._
import collection.mutable.ArrayBuffer

object ListaFacade {
  def insertLista(name:String,owner:Person)={
    ConectionBD.insert("insert into Lista(nombre,owner) values (\""+name+"\",\""+owner.name+"\");")
    val result = ConectionBD.recover("select idLista from Lista where nombre=\""+name+"\" and owner=\""+owner.name+"\";")
    result.next
    result.getInt("idLista")
  }
  def getAll = {
    val result = ConectionBD.recover("select * from Lista;")
    val resp = ArrayBuffer.empty[ListaT]
    while(result.next)
      resp += new ListaT(result.getInt("idLista"),result.getString("nombre"),PersonFacade.getPerson(result.getString("owner")))
    for(l<-resp)
      l.lista = TareaFacade.getTareasLista(l)
    resp
  }
  def updLista(l:ListaT){ ConectionBD.insert("update Lista set nombre=\""+l.name+"\" where idLista="+l.ID+";")}
  def deleteLista(l:ListaT){ ConectionBD.insert("delete from Lista where idLista="+l.ID+";")}
}