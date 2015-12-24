object Main extends App{

  def isSorted[A] (arg: Array[A], compare: (A,A)=>Boolean): Boolean = {
    def go (index: Int, lastVal : A): Boolean = {
      if (index == arg.length)
        true
      else if (compare (arg(index), lastVal))
        false
      else
        go (index + 1, arg(index))
    }
    if (arg.length == 0)
      return true
    else go (1, arg(0))
  }

  override def main(arg: Array[String]) = {

    val compare = (a: String, b: String) => {
      a.compareTo(b) < 0
    }
    println (isSorted(Array("piyush", "ashish"), compare))
  }

}
