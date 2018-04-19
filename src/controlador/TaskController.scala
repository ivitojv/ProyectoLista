package controlador

import utilities._
import modelo._
import java.io._
import collection.mutable.ArrayBuffer
import java.text.SimpleDateFormat
import javax.swing.JFrame
import java.util.List
import collection.JavaConversions._
import vista._
import java.util.Date

object TaskController extends Controller{
  private val regex = """[0-9][0-9][0-9][0-9]-[0-1]?[0-9]-[0-3]?[0-9]""".r
  private val format = new SimpleDateFormat("yyyy-MM-dd")
  val AZ = 0
  val ZA = 1
  val DATE = 2
  val END = 3
 
  def ordFiltT(tjs:List[Tarea], op:Int):List[Tarea]={
    op match{
      case AZ => tjs.sortWith(_.title < _.title)
      case ZA => tjs.sortWith(_.title > _.title)
      case DATE => tjs.filter(_.date!=null).sortWith((a:Tarea,b:Tarea) => a.date.before(b.date))
      case END => tjs.filter(_.finalizada)
    }
  }

  private def checkDate(date:String) = if(regex.findAllIn(date).length>0) if(format.parse(date).after(new Date())) true else false else false
  def add(ss:Sesion,title:String,fecha:String,comment:String)={
    val dest = ss.lista.shared.+:(ss.lista.author).filter((p:Person) => p.name != ss.person.name)
    if(checkDate(fecha)){ 
      ss.lista +=new Tarea(ss.person,title,format.parse(fecha),comment)
      ListController.save
      sendMail(ss,dest.toList,"Aviso: " +ss.lista.name,ss.person.name + " ha añadido la tarea "+title+" a la lista " + ss.lista.name)
      true
    }else if(fecha == ""){
      ss.lista += new Tarea(ss.person,title,comment)
      ListController.save
      sendMail(ss,dest.toList,"Aviso: " +ss.lista.name,ss.person.name + " ha añadido la tarea "+title+" a la lista " + ss.lista.name)
      true
    }else false
  }
  def mod(ss:Sesion,t:Tarea, title:String, date:String, comment:String, fin:Boolean)={
    val dest = ss.lista.shared.+:(ss.lista.author).filter((p:Person) => p.name != ss.person.name)
    if(checkDate(date)){
      t.title = title
      t.date = format.parse(date)
      t.comment = comment
      t.finalizada = fin
      ListController.save
      sendMail(ss,dest.toList,"Aviso: " +ss.lista.name,ss.person.name + " ha modificado la tarea "+t.title+" de la lista " + ss.lista.name)
      true
     }else if(date == ""){
      t.title = title
      t.date = null
      t.comment = comment
      t.finalizada = fin
      ListController.save
      sendMail(ss,dest.toList,"Aviso: " +ss.lista.name,ss.person.name + " ha modificado la tarea "+t.title+" de la lista " + ss.lista.name)
      true
    }else false
  }
  def callDetalleTarea(ss:Sesion,t:Tarea){
    try {
      frame = new DetalleTarea(t,ss)
      frame.setVisible(true)
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
  def callCrearTarea(ss:Sesion){
    try {
      frame = new CrearTarea(ss)
      frame.setVisible(true)
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
  def borrarTarea(ss:Sesion,t:Tarea) {
    val dest = ss.lista.shared.+:(ss.lista.author).filter((p:Person) => p.name != ss.person.name)
    ss.lista -= t
    ListController.save
    sendMail(ss,dest.toList,"Aviso: " +ss.lista.name,ss.person.name + " ha borrado la tarea "+t.title+" de la lista " + ss.lista.name)
  }
}