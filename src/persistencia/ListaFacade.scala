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
    for(l<-resp){
      l.lista = TareaFacade.getTareasLista(l)
      l.shared = getShared(l)
    }
    resp
  }
  def addShare(ID:Int,p:Person){ ConectionBD.insert("insert into SharedList(lista,sharedUser) values ("+ID+",\""+p.name+"\");")}
  def deleteShare(ID:Int,p:Person){ ConectionBD.insert("delete from SharedList where lista="+ID+" and sharedUser=\""+p.name+"\";")}
  def updLista(l:ListaT){ ConectionBD.insert("update Lista set nombre=\""+l.name+"\" where idLista="+l.ID+";")}
  def deleteLista(l:ListaT){ ConectionBD.insert("delete from Lista where idLista="+l.ID+";")}
  def getShared(l:ListaT)={
    val result = ConectionBD.recover("select sharedUser from SharedList where lista="+l.ID+";")
    val shared = ArrayBuffer.empty[Person]
    while(result.next)
      shared += PersonFacade.getPerson(result.getString("sharedUser"))
    shared
  }
}