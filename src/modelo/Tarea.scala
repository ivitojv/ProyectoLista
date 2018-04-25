package modelo

import java.util.Calendar
class Tarea(val ID:Int, val author:Person,var title:String) extends Serializable {
  var date:Calendar = null
  var comment = ""
  var finalizada = false
  def this(ID:Int, author:Person,title:String, date:Calendar){
    this(ID,author,title)
    this.date = date
  }
  def this(ID:Int, author:Person,title:String, comments:String){
    this(ID,author,title)
    this.comment = comments
  }
  def this(ID:Int,author:Person,title:String, date:Calendar,comments:String){
    this(ID,author,title)
    this.date = date
    this.comment = comments
  }
  def this(ID:Int,author:Person,title:String, date:Calendar,comments:String,finalizada:Boolean){
    this(ID,author,title,date,comments)
    this.finalizada = finalizada
  }
  override def toString={
    "Author: "+ author.name + "\nTitle: "+ title+"\nDate: "+ date+"\nComment: " + comment
  }
}