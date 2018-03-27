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
  def getListas(ss:Sesion) = for(l<-listas; if(l.author == ss.person)) yield l
  def callMenu(ss: Sesion) {
    try {
      frame = new MenuPersona(ss,getListas(ss));
      frame.setVisible(true);
    } catch {
      case e: Exception => e.printStackTrace();
    }
  }
  def borrarLista(list:ListaT) {
    listas-=list
    saveOnFile(listas,FILENAME)
    }
}