package controlador

import javax.swing.JFrame;
import java.io._
abstract class Controller {

  var frame:JFrame = null
  def close {frame.dispose()}
  def saveOnFile(objeto:Any,filename:String){
    val out = new ObjectOutputStream(new FileOutputStream(filename))
    out.writeObject(objeto)
    out.close
  }
  
}


class AppendingObjectOutputStream(out:OutputStream) extends ObjectOutputStream {


  override def writeStreamHeader(){
    // do not write a header, but reset:
    // this line added after another question
    // showed a problem with the original
    reset();
  }

}