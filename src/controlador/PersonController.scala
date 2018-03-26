package controlador

import vista._
import modelo._
import utilities._
import javax.swing.JFrame
import collection.mutable.ArrayBuffer
import java.io._

object PersonController extends Controller {
  frame = null
  var personas = ArrayBuffer.empty[Person]
  val FILENAME = "personas.obj"
  cargarPersonas(FILENAME)

  def cargarPersonas(filename: String) {
    try {  
      val in = new ObjectInputStream(new FileInputStream(filename))
      personas = in.readObject().asInstanceOf[ArrayBuffer[Person]]
    }catch {case _ => println("fail_personas")}
  }
  def callCrearPersona {
    try {
      frame = new CrearPersona;
      frame.setVisible(true);
    } catch {
      case e: Exception => e.printStackTrace();
    }
  }
  def callMenu(ss: Sesion) {
    try {
      frame = new MenuPersona(ss);
      frame.setVisible(true);
    } catch {
      case e: Exception => e.printStackTrace();
    }
  }
  def add {
    var frameX = frame.asInstanceOf[CrearPersona] 
    personas += new Person(frameX.getText)
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
      PersonController.callMenu(new Sesion(pp))
      true
    } else false
  }
  def mostrarPersonas = personas.foreach(print)
}