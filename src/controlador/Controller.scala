package controlador

import javax.swing.JFrame;
import java.io._
import utilities._
import modelo._
abstract class Controller {

  var frame:JFrame = null
  def close {frame.dispose()}
  def saveOnFile(objeto:Any,filename:String){
    val out = new ObjectOutputStream(new FileOutputStream(filename))
    out.writeObject(objeto)
    out.close
  }
  def sendMailShare(ss:Sesion, list:List[Person], addition:Boolean){
    val me = "javier.ibanez.soloaga.st@everis.com"
    val a = new MailAgent(me,null,null,me,"prueba","content","127.0.0.1")
    //a.sendMessage
    /*
    if(addition)
      for(elem<-list) new MailAgent(elem.correo,null,null,ss.person.correo,"Aviso: " +ss.lista.name,ss.person.name + " te ha compartido la lista " + ss.lista.name,"127.0.0.1").sendMessage
    else
      for(elem<-list) new MailAgent(elem.correo,null,null,ss.person.correo,"Aviso: " +ss.lista.name,ss.person.name + " te ha descompartido la lista " + ss.lista.name,"127.0.0.1").sendMessage
    */      
    println("Enviar mensaje a "+list.map(_.name))
  }
  
}

//No esta funcionando 
class AppendingObjectOutputStream(out:OutputStream) extends ObjectOutputStream {


  override def writeStreamHeader(){
    // do not write a header, but reset:
    // this line added after another question
    // showed a problem with the original
    reset();
  }

}