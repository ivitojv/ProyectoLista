package modelo

import java.util.Date
class Tarea(val author:Person,var title:String) extends Serializable {
  var date:Date = null
  var comment = ""
  var finalizada = false
  def this(author:Person,title:String, date:Date){
    this(author,title)
    this.date = date
  }
  def this(author:Person,title:String, comments:String){
    this(author,title)
    this.comment = comments
  }
  def this(author:Person,title:String, date:Date,comments:String){
    this(author,title)
    this.date = date
    this.comment = comments
  }
  override def toString={
    "Author: "+ author.name + "\nTitle: "+ title+"\nDate: "+ date+"\nComment: " + comment
  }
}