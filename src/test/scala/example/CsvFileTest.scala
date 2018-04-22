package csv

import org.scalatest._
import java.io.File
import scala.util.{Try, Success, Failure}

class CsvFileTest extends org.scalatest.FunSuite {
  test("cannot open file") {
    val csvfile = CsvFile("plop")
    assert(csvfile.isFailure)
  }

  test("open basic file") {
    val f = File.createTempFile("csv",".csv")
    printToFile(f)(pw => pw.print("bla,bli\n3,4\n"))
    val csvfile = CsvFile(f.getAbsolutePath)
    assert (csvfile match {
      case Failure(e) => false
      case Success(foo) => foo.getValue(0,"bla") == "3"
    })
    assert (csvfile match {
      case Failure(e) => false
      case Success(foo) => foo.getValue(0,"bli") == "4"
    })
    f.delete()

  }

  def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
    val p = new java.io.PrintWriter(f)
    try { op(p) } finally { p.close() }
  }
}
