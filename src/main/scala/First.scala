import WhatsApp.Entity._
import Common.Entity._
import Common.Visual.{VisualGraph, _}
import java.io._
import java.io.StringWriter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scala.collection.mutable.ArrayBuffer

object First {
  def main(args: Array[String])=
    {
      val Siavash : User = new User("Siavash")
      val Ali : User = new User("Ali")
      val Saeed : User = new User("Saeed")
      val Mehdi : User = new User("Mehdi")
      val Amir : User = new User("Amir")

      val Grp1:Group = new Group("developers")

      Grp1.addUser(Siavash)
      Grp1.addUser(Mehdi)
      Grp1.addUser(Ali)



      val Ms1 : Message = new Message("Hi Sia",Saeed,Siavash)
      val Ms2 : Message = new Message("Hi Saeed",Siavash,Saeed)
      val Ms3 : Message = new Message("Who is Siavash?",Mehdi,Saeed)
      val Ms4 : Message = new Message("He is c++ developer",Saeed,Mehdi)
      val Ms5 : Message = new Message("C++ developer?!",Mehdi,Saeed)
      val Ms6 : Message = new Message("Are you serious?",Mehdi,Saeed)
      val Ms7 : Message = new Message("He guys",Amir,Ali)



      if(!Ms1.Forward(Mehdi,Siavash))
        println("Mehdi is not owner of Message1")

      if(!Ms1.Forward(Siavash,Mehdi))
        println("Siavash is not owner of Message1")

      if(!Ms1.Forward(Siavash,Siavash))
        println("Siavash is not owner of Message1")

      if(!Ms1.Forward(Saeed,Ali))
        println("Saeed is not owner of Message1")

      if(!Ms7.Forward(Ali,Grp1))
        println(s"Ali is not owner of '${Ms7.comment}' or isn't member of this group.")

      val mapper = new ObjectMapper()
      mapper.registerModule(DefaultScalaModule)


      val out = new StringWriter
      val fileObject = new File("data.js" )     // Creating a file
      val printWriter = new PrintWriter(fileObject)       // Passing reference of file to the printwriter

      var VG : ArrayBuffer[VisualGraph] = ArrayBuffer[VisualGraph]()

      VG += new VisualGraph(Ms1.owners)
      VG += new VisualGraph(Ms2.owners)
      VG += new VisualGraph(Ms3.owners)
      VG += new VisualGraph(Ms4.owners)
      VG += new VisualGraph(Ms5.owners)
      VG += new VisualGraph(Ms6.owners)
      VG += new VisualGraph(Ms7.owners)

      mapper.writeValue(out, VG)
      val json = out.toString()
      println(json)


      printWriter.write("data=\'")
      printWriter.write(json)
      printWriter.write("\'")
      printWriter.close()

    }

}

