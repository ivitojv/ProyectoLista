package controlador

import modelo._
import collection.mutable.ArrayBuffer
import java.util.List
import collection.JavaConversions._
import utilities._
import vista._
import java.io._


object ListController extends Controller{
  
  val AZ = 0
  val ZA = 1
  private val FILENAME = "listas.obj"
  
  var listas = ArrayBuffer.empty[ListaT]
  
  cargarListas(FILENAME)
  def ordFiltL(lts:List[ListaT], op:Int):List[ListaT]={
    op match{
      case AZ => lts.sortWith(_.name < _.name)
      case ZA => lts.sortWith(_.name > _.name)
    }
  }
  private def cargarListas(filename: String) {
    try{
      val in = new ObjectInputStream(new FileInputStream(filename))
      listas = in.readObject().asInstanceOf[ArrayBuffer[ListaT]]
    }catch {
      case e: Exception => e.printStackTrace()
      }
  }
  def lookForList(ss:Sesion, name:String) = {val ans = listas.filter((l:ListaT) => l.author.name == ss.person.name && l.name == name);  if(ans.length > 0) ans(0) else null}
  def addLista(ss:Sesion, name:String){ listas += new ListaT(name, ss.person); saveOnFile(listas,FILENAME)}
  def getListas(ss:Sesion) = for(l<-listas; if(l.author.name == ss.person.name)) yield l
  def addTarjeta(ss:Sesion,t:Tarjeta){}
  def modTarjeta(ss:Sesion,t:Tarjeta){}
  def borrarTarjeta(ss:Sesion,t:Tarjeta){}
  def callMenu(ss: Sesion) {
    try {
      frame = new MenuPersona(ss,getListas(ss));
      frame.setVisible(true);
    } catch {
      case e: Exception => e.printStackTrace();
    }
  }
  def callCrearLista(ss:Sesion){
    try {
      frame = new CrearLista(ss);
      frame.setVisible(true);
    } catch {
      case e: Exception => e.printStackTrace();
    }
  }
  def callMostrarLista(list:ListaT,ss:Sesion){
    try {
      ss.lista = list;
      frame = new MostrarLista(list.lista,ss)
      frame.setVisible(true)
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
  def borrarLista(list:ListaT) {
    listas-=list
    saveOnFile(listas,FILENAME)
    }
  def save { saveOnFile(listas,FILENAME)}
}