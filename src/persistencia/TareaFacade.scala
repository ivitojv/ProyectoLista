package persistencia
import modelo._
import collection.mutable.ArrayBuffer
import java.util.Calendar
object TareaFacade {
  def insertTarea(lista:Int,author:Person,titulo:String,fecha:Calendar,comment:String)={
    
    ConectionBD.insert("insert into Tarea(owner,titulo,fecha,descripcion,lista,finalizada) values (\""
                        +author.name+"\",\""+titulo+"\","+parsear(fecha)+",\""+comment+"\","+lista
                        +",false);")
    val result = ConectionBD.recover("select idTarea from Tarea where owner=\""+author.name+"\" and titulo=\""+titulo+"\";")
    result.last //Para que en caso de que haya 2 tareas con el mismo nombre y owner coja la última creada
    result.getInt("idTarea")
  }
  def getTareasLista(l:ListaT)={
    val result = ConectionBD.recover("select * from Tarea where lista="+l.ID+";")
    val tareas = ArrayBuffer.empty[Tarea]
    while(result.next){
      var date = Calendar.getInstance
      try{date.setTime(result.getDate("fecha"))}catch{case e:NullPointerException => date = null}
      tareas += new Tarea(result.getInt("idTarea"),
                          PersonFacade.getPerson(result.getString("owner")),
                          result.getString("titulo"),
                          date,
                          result.getString("descripcion"),
                          result.getBoolean("finalizada"))
    }
    tareas
  }
  def updTarea(t:Tarea){
    ConectionBD.insert("update Tarea set titulo=\""+t.title+"\",fecha="+parsear(t.date)
                      +",descripcion=\""+t.comment+"\",finalizada="+t.finalizada+
                      " where idTarea="+t.ID+";")
  }
  def deleteTarea(t:Tarea){
    ConectionBD.insert("delete from Tarea where idTarea="+t.ID+";")
  }
  private def parsear(fecha:Calendar)={
    if(fecha!=null) "\""+fecha.get(Calendar.YEAR)+"-"+(fecha.get(Calendar.MONTH)+1)+"-"+fecha.get(Calendar.DAY_OF_MONTH)+"\""
    else "NULL"
  }
}