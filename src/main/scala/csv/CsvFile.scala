package csv

import scala.io.Source
import scala.util.{Try, Failure, Success}

class CsvFile(content : List[Map[String,String]]) {
  def getValue(id : Int, key : String): String = {
    return content(id)(key)
  }
}


object CsvFile {
  def apply (file: String) : Try[CsvFile] = {
    println(s"Try to open file ${file}")

    Try {
      val bufferedSource = Source.fromFile(file)
      val lines = bufferedSource.getLines()
      val header = lines.next().split(",")

      /**
        * For each line, we build a Map that
        * with the header value and the corresponding
        * string value
        */
      val tmp = for (line <- lines) yield {
        (header zip line.split(",")).toMap
      }
      val result = new CsvFile(content=tmp.toList)
      bufferedSource.close
      result

    }
  }
}
