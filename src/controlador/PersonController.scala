package controlador

import vista._
import modelo._
import utilities._
import javax.swing.JFrame
import collection.mutable.ArrayBuffer
import java.io._
import java.util.List
import collection.JavaConversions._

object PersonController extends Controller {
  var personas = ArrayBuffer.empty[Person]
  private val FILENAME = "personas.obj"
  private val regex = """[A-Za-z0-9_\.\+-]+@[A-Za-z]+\.[A-Za-z]+""".r

  cargarPersonas(FILENAME)

  private def cargarPersonas(filename: String) {
    try {  
      val in = new ObjectInputStream(new FileInputStream(filename))
      personas = in.readObject().asInstanceOf[ArrayBuffer[Person]]
    }catch {
      case e: Exception => e.printStackTrace()
      }
  }
  def addContact(ss:Sesion, name:String)={
    if(lookForPerson(name) != null && !ss.person.contactos.map(_.name).contains(name) && name != ss.person.name){
      ss.person.contactos += lookForPerson(name)
      saveOnFile(personas,FILENAME)
      true
    }else
      false
  }
  def deleteContact(ss:Sesion,ppl:List[Person]){
    ss.person.contactos = ss.person.contactos.filter((p:Person)=>if(ppl.map(_.name).contains(p.name))false else true)
    saveOnFile(personas,FILENAME)
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
  
  def add(name:String, correo:String) = {
    println("PersonController add")
    if(regex.findAllIn(correo).length > 0){
      personas += new Person(name,correo)
      saveOnFile(personas,FILENAME)
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