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

object TargetController extends Controller{
  var tarjetas = ArrayBuffer.empty[Tarjeta]
  var author = ""
  val regex = """[0-9][0-9][0-9][0-9]-[0-1]?[0-9]-[0-3]?[0-9]""".r
  val format = new SimpleDateFormat("yyyy-MM-dd")
  val FILENAME = "tarjetas.obj"
  val AZ = 0
  val ZA = 1
  val DATE = 2
  val END = 3
  
  cargarTarjetas(FILENAME)
  def getTarjetas(ss:Sesion):List[Tarjeta]=for(elem<-tarjetas; if(elem.author.name == ss.person.name)) yield elem
  
  def ordFiltT(tjs:List[Tarjeta], op:Int):List[Tarjeta]={
    op match{
      case AZ => tjs.sortWith(_.title < _.title)
      case ZA => tjs.sortWith(_.title > _.title)
      case DATE => tjs.filter(_.date!=null).sortWith((a:Tarjeta,b:Tarjeta) => a.date.before(b.date))
      case END => tjs.filter(_.finalizada)
    }
  }
  def cargarTarjetas(filename: String) {
    try{
      val in = new ObjectInputStream(new FileInputStream(filename))
      tarjetas = in.readObject().asInstanceOf[ArrayBuffer[Tarjeta]]
    }catch {case _ => println("fail_tarjetas")}
  }
  private def checkDate(date:String) = if(regex.findAllIn(date).length>0) if(format.parse(date).after(new Date())) true else false else false
  def add(author:Person,title:String,fecha:String,comment:String)={
    if(checkDate(fecha)){
      println("TargetController "+comment)
      tarjetas += new Tarjeta(author,title,format.parse(fecha),comment)
      println("TargetController "+tarjetas.last.comment)
      saveOnFile(tarjetas,FILENAME)
      true
    }else if(fecha == ""){
      tarjetas += new Tarjeta(author,title,comment)
      println("TargetController "+tarjetas.last.comment)
      saveOnFile(tarjetas,FILENAME)
      true
    }else false
  }
  def mod(t:Tarjeta, title:String, date:String, comment:String, fin:Boolean)={
    if(checkDate(date)){
      t.title = title
      t.date = format.parse(date)
      t.comment = comment
      t.finalizada = fin
      saveOnFile(tarjetas,FILENAME)
      true
     }else if(date == ""){
      t.title = title
      t.date = null
      t.comment = comment
      t.finalizada = fin
      saveOnFile(tarjetas,FILENAME)
      true
    }else false
  }
  def callDetalleTarjeta(t:Tarjeta,s:Sesion){
    try {
      frame = new DetalleTarjeta(t,s)
      frame.setVisible(true)
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
  def callMostrarTarjeta(ss:Sesion){
    for(e<-tarjetas)println("callMostrarTarjeta "+e.author.name)
    val pt = getTarjetas(ss)
    try {
      frame = new MostrarTarjeta(pt,ss)
      frame.setVisible(true)
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
  def borrarTarjeta(t:Tarjeta) {for(elem<-tarjetas;if(t==elem))tarjetas-=elem;saveOnFile(tarjetas,FILENAME)}
}