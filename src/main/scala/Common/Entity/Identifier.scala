package Common.Entity


object MessageId {
  //Companion
  protected var Counter = 0
  protected def Increase = {Counter+=1; Counter}
}

class MessageId {
  protected val _code : Int = MessageId.Increase
  def code = _code
}

object UserId {
  //Companion
  protected var Counter = 0
  protected def Increase = {Counter+=1; Counter}
}

class UserId {
  protected val _code : Int = UserId.Increase
  def code = _code
}


object GroupId {
  //Companion
  protected var Counter = 0
  protected def Increase = {Counter+=1; Counter}
}

class GroupId {
  protected val _code : Int = GroupId.Increase
  def code = _code
}
