package WhatsApp.Entity

import Common.Entity._

class Message(var comment:String) {
  private val messageId: MessageId = new MessageId
  private var Owners:Graph = new Graph

  def this(comment:String, Sender:User, Receiver:User)  =
  {
    this(comment)
    Owners.Add(Sender,Receiver)
  }
  def this(comment:String, Sender:User, group: Group)  =
  {
    this(comment)
    Owners.Add(Sender,group)
  }
  def Forward(Sender:User, Receiver:User):Boolean=
  {
    if(Owners.IsOwner(Sender))
    {
      Owners.Add(Sender,Receiver)
      true
    }
    else
    {
      false
    }
  }

  def Forward(Sender:User, Receiver:Group):Boolean=
  {
    if(Owners.IsOwner(Sender)&&Receiver.isMember(Sender))
    {
      Owners.Add(Sender,Receiver)
      true
    }
    else
    {
      false
    }
  }

  def owners = Owners

}
