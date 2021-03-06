package controlador

import vista._
import modelo._
import utilities._
import javax.swing.JFrame
import collection.mutable.ArrayBuffer
import java.io._
import java.util.List
import collection.JavaConversions._
import persistencia.PersonFacade

object PersonController extends Controller {
  var personas = ArrayBuffer.empty[Person]
  private val FILENAME = "personas.obj"
  private val regex = """[A-Za-z0-9_\.\+-]+@[A-Za-z]+\.[A-Za-z]+""".r

  cargarBD
  
  private def cargarPersonas(filename: String) {
    try {  
      val in = new ObjectInputStream(new FileInputStream(filename))
      personas = in.readObject().asInstanceOf[ArrayBuffer[Person]]
    }catch {
      case e: Exception => e.printStackTrace()
      }
  }
  private def cargarBD(){
    personas = PersonFacade.getAll
  }
  def addContact(ss:Sesion, name:String)={
    if(lookForPerson(name) != null && !ss.person.contactos.map(_.name).contains(name) && name != ss.person.name){
      ss.person.contactos += lookForPerson(name)
      PersonFacade.addFriend(ss.person, lookForPerson(name))
      true
    }else
      false
  }
  def deleteContact(ss:Sesion,ppl:List[Person])={
    if(ListController.getListas(ss).filter(_.shared.filter((p:Person)=>ppl.map(_.name).contains(p.name)).size > 0).size > 0){
      callContactsWarning()
      false
    }else{
      ss.person.contactos = ss.person.contactos.filter((p:Person)=> !ppl.map(_.name).contains(p.name))
      for(friend<-ppl)
        PersonFacade.deleteFriend(ss.person, friend)
      true
    }
  }
  def callCrearPersona {
    try {
      frame = new CrearPersona;
      frame.setVisible(true);
    } catch {
      case e: Exception => e.printStackTrace();
    }
  }
  
  def callContactos(ss:Sesion) {
    try {
      frame = new MenuContactos(ss);
      frame.setVisible(true);
    } catch {
      case e: Exception => e.printStackTrace();
    }
  }
  
  def callContactsWarning(){
    try {
      frame = new ContactsWarning();
      frame.setVisible(true);
    } catch {
      case e: Exception => e.printStackTrace();
    }
  }
  
  def add(name:String, correo:String) = {
    println("PersonController add")
    if(regex.findAllIn(correo).length == 1 && lookForPerson(name)==null && name.size>0){
      personas += new Person(name,correo)
      PersonFacade.insertPerson(new Person(name,correo))
      true
    }else
      false
  }
  def delete(name:String){
    personas -= lookForPerson(name)
    saveOnFile(personas,FILENAME)
  }
  def lookForPerson(p: String) = {
    var resp: Person = null
    for (elem <- personas; if (elem.name == p)) resp = elem
    resp
  }
  def login(p: String) = {
    val pp = lookForPerson(p)
    if (pp != null) {
      ListController.callMenu(new Sesion(pp))
      true
    } else false
  }
  def mostrarPersonas = personas.foreach(print)
}