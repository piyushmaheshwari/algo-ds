import scala.collection.mutable
import scala.io.Source

object WordProximity extends App {

  def readFile(fileName: String) = Source.fromFile(s"src/${fileName}").mkString

  def processDocument(document: String) = {
    var isWord = false
    var word = ""
    var wordList: mutable.MutableList[(String, Int)] = mutable.MutableList.empty
    var counter = 0
    for (ch <- document) {
      if (ch != ' ' && ch != '\n') {
        if (!isWord) isWord = true
        word = word + ch
      } else if (isWord) {
        wordList += ((word, (counter - word.length)))
        isWord = false
        word = ""
      }
      counter += 1
    }

    if (word.nonEmpty) {
      wordList += ((word, (counter - word.length)))
    }
    wordList.toList.reverse
  }


  readFile("data.txt")
}
