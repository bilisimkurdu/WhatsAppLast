package Common.Visual

import Common.Entity._
import scala.collection.mutable.ArrayBuffer

case class Edge(var from: Int, var to:Int, var arrows:String)

case class Node(var id: Int, var label:String)

class VisualGraph
{
  var nodes:ArrayBuffer[Node] = ArrayBuffer[Node]()
  var edges:ArrayBuffer[Edge] = ArrayBuffer[Edge]()

  def this(graph: Graph)
  {
    this
    for(edge <- graph.Edges)
    {
      //nodes
      var newEdge = Edge(edge._1.userId.code, edge._2.userId.code ,"to")
      edges.append(newEdge)

    }
    for(node <- graph.Nodes)
    {
      //nodes
      var newNode = Node(node.userId.code ,node.Name)
      nodes.append(newNode)


    }
  }
}
