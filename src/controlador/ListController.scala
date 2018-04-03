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
  val SHARED = 2
  private val FILENAME = "listas.obj"
  
  var listas = ArrayBuffer.empty[ListaT]
  
  cargarListas(FILENAME)
  def ordFiltL(lts:List[ListaT], op:Int):List[ListaT]={
    op match{
      case AZ => lts.sortWith(_.name < _.name)
      case ZA => lts.sortWith(_.name > _.name)
      case SHARED => lts.filter(_.shared.length > 0)
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
  def addLista(ss:Sesion, name:String){ listas += new ListaT(name, ss.person); save}
  def getListas(ss:Sesion) = for(l<-listas; if(l.author.name == ss.person.name || l.shared.map(_.name).contains(ss.person.name))) yield l
  def shareList(ss:Sesion, ppl:List[Person]){
    ss.lista.shared = ppl.to[ArrayBuffer]
    save
    sendMail(ss)
  }
  def mod(ss:Sesion,name:String)={
    if(lookForList(ss,name)==null){
      ss.lista.name = name
      save
      true
    }else
      false
  }
  def borrarLista(ss:Sesion,list:ListaT)={
    if(ss.person.name == list.author.name){
      listas-=list
      save
      true
    }else
      false
  }
  def save {saveOnFile(listas,FILENAME)}
  def callMenu(ss: Sesion) {
    try {
      frame = new MenuPersona(ss,getListas(ss))
      frame.setVisible(true)
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
  def callCrearLista(ss:Sesion){
    try {
      frame = new CrearLista(ss)
      frame.setVisible(true)
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
  def callMostrarLista(ss:Sesion,list:ListaT){
    try {
      ss.lista = list
      frame = new MostrarLista(list.lista,ss)
      frame.setVisible(true)
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
  def callModificarLista(ss:Sesion,list:ListaT)={
    if(ss.person.name == list.author.name){
      try {
        ss.lista = list
        frame = new ModificarLista(ss)
        frame.setVisible(true)
        true
      } catch {
        case e: Exception => e.printStackTrace()
        false
      }
    }else
      false
  }
  def callCompartirLista(ss: Sesion,list:ListaT)={
    if(ss.person.name == list.author.name){
      try {
        ss.lista = list
        frame = new CompartirLista(ss)
        frame.setVisible(true)
        true
      } catch {
        case e: Exception => e.printStackTrace()
        false
      }
    }else
      false
      
  }
}