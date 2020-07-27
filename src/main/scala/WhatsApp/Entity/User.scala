package WhatsApp.Entity

import Common.Entity.UserId

class User(val Name:String) {
  private val _userId: UserId = new UserId

  def userId = _userId

}
