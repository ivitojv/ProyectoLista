package persistencia
import modelo._
import collection.mutable.ArrayBuffer
import controlador._

object PersonFacade {
  def insertPerson(p:Person){
    ConectionBD.insert("insert into User(nombre,correo) values (\""+p.name+"\",\""+p.correo+"\");")
    for(friend<-p.contactos)
      ConectionBD.insert("insert into Friend values(\""+p.name+"\",\""+friend.name+"\");")
  }
  
  def getAll()={
    val result = ConectionBD.recover("select * from User;")
    var resp = ArrayBuffer.empty[Person]
    while(result.next)
      resp += new Person(result.getString("nombre"),result.getString("correo"))
    for(p<-resp){
      val friends = ConectionBD.recover("select * from Friend where user1=\""+p.name+"\";")
      while(friends.next())
        p.contactos += resp.filter(_.name == friends.getString("user2"))(0)
    }
    resp
  }
  
  def addFriend(p:Person, friend:Person){
    ConectionBD.insert("insert into Friend(user1,user2) values (\""+p.name+"\",\""+friend.name+"\");")
  }
  def deleteFriend(p:Person, friend:Person){
    ConectionBD.insert("delete from Friend where user1=\""+p.name+"\" and user2=\""+friend.name+"\";")
  }
//  def deletePerson(p:Person){
//    ConectionBD.insert("delete from User where nombre=\""+p.name+"\";")
//    ConectionBD.insert("delete from Friend")
//  }
}