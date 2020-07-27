package Common.Entity
import WhatsApp.Entity._

import scala.collection.mutable.ListBuffer

class Graph {
  private var nodesSet:Set[User] = Set.empty
  private var edges = new ListBuffer[(User,User)]


  def Add(Sender:User, Receiver:User):Unit={

    edges.append((Sender,Receiver))
    nodesSet += Sender
    nodesSet += Receiver
  }

  def Add(Sender:User, group: Group):Unit={

    for(grpusr<-group.Members)
      {
        edges.append((Sender,grpusr))
      }


    nodesSet += Sender
    for(grpusr<-group.Members)
    {
      nodesSet += grpusr
    }
  }

  def Edges=edges
  def Nodes = nodesSet

  def IsOwner(Usr:User): Boolean =
  {
    this.nodesSet.contains(Usr)
  }

}
