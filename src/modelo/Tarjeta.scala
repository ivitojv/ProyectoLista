package modelo

import java.util.Date
class Tarjeta(val author:Person,var title:String,var date:Date) extends Serializable {
  var comment = ""
  def this(author:Person,title:String, date:Date,comments:String){
    this(author,title,date)
    this.comment = comments
  }
  override def toString={
    "Author: "+ author.name + "\nTitle: "+ title+"\nDate: "+ date+"\nComment: " + comment
  }
}