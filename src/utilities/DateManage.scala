package utilities

object DateManage {
  private def monthsDays(month: Int) = {
    month match {
      case 1  => 31
      case 2  => 28
      case 3  => 31
      case 4  => 30
      case 5  => 31
      case 6  => 30
      case 7  => 31
      case 8  => 31
      case 9  => 30
      case 10 => 31
      case 11 => 30
      case 12 => 31
    }
  }
  def daysOfMonth(month:Int,year:Int)={   
		if(month==2 && year%4 == 0 && (year%100 != 0 || year%400 == 0))
			monthsDays(month)+1
		else
		  monthsDays(month)
	}
}