package WhatsApp.Entity

import Common.Entity.GroupId

class Group(val Name:String) {
  private val groupId: GroupId = new GroupId
  private var members:Set[User] = Set[User]()
  def addUser(user:User)={
    members += user
  }

  def Members = members

  def isMember(user:User) ={
    members.contains(user)
  }

}
