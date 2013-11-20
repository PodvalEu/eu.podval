package simulations


class Wire {
  private var sigVal = false
  private var actions: List[Simulator#Action] = List()

  def getSignal: Boolean = sigVal

  def setSignal(s: Boolean) {
    if (s != sigVal) {
      sigVal = s
      actions.foreach(action => action())
    }
  }

  def addAction(a: Simulator#Action) {
    actions = a :: actions
    a()
  }

  override def toString() = {
    sigVal.toString
  }
}

abstract class CircuitSimulator extends Simulator {

  val InverterDelay: Int
  val AndGateDelay: Int
  val OrGateDelay: Int

  def probe(name: String, wire: Wire) {
    wire addAction {
      () => afterDelay(0) {
        println(
          "  " + currentTime + ": " + name + " -> " + wire.getSignal)
      }
    }
  }

  def inverter(input: Wire, output: Wire) {
    def invertAction() {
      val inputSig = input.getSignal
      afterDelay(InverterDelay) {
        output.setSignal(!inputSig)
      }
    }
    input addAction invertAction
  }

  def andGate(a1: Wire, a2: Wire, output: Wire) {
    def andAction() {
      val a1Sig = a1.getSignal
      val a2Sig = a2.getSignal
      afterDelay(AndGateDelay) {
        output.setSignal(a1Sig & a2Sig)
      }
    }
    a1 addAction andAction
    a2 addAction andAction
  }

  //
  // to complete with orGates and demux...
  //

  def orGate(a1: Wire, a2: Wire, output: Wire) {
    def orAction() {
      afterDelay(OrGateDelay) {
        output.setSignal(a1.getSignal || a2.getSignal)
      }
    }
    a1 addAction orAction
    a2 addAction orAction
  }

  def orGate2(a1: Wire, a2: Wire, output: Wire) {
    val a1n = new Wire
    val a2n = new Wire
    inverter(a1, a1n)
    inverter(a2, a2n)

    val and = new Wire
    andGate(a1n, a2n, and)

    inverter(and, output)
  }

  def demux(in: Wire, c: List[Wire], out: List[Wire]):List[Wire] = {
    println("In: " + in)
    println("S: " + c.mkString(" "))
    println("====")

    val result: List[Wire] = demuxR(in, c)

    assert(out.length == result.length)
    println("O: " + result.mkString(" "))

    (out, result).zipped.foreach((o, r) => o.setSignal(r.getSignal))
    result
  }

  def demuxR(in: Wire, c: List[Wire]): List[Wire] = {
    println("In: " + in)

    c match {
      case h :: rest => {
        println("Head: " + h)
        val andOutput = new Wire
        andGate(in, h, andOutput)
        println("Head and in: " + andOutput)
        
        val negativeHead = new Wire
        inverter(h, negativeHead)
        println("Negative head: " + negativeHead)
        val negativeOutput = new Wire
        andGate(in, negativeHead, negativeOutput)
        println("Negative head and in: " + andOutput)

        demuxR(andOutput, rest) ::: demuxR(negativeOutput, rest)
      }
      case Nil => {
        println("Returning signal: " + in)
        List(in)
      }
    }
  }

}

object Circuit extends CircuitSimulator {
  val InverterDelay = 1
  val AndGateDelay = 3
  val OrGateDelay = 5

  def andGateExample {
    val in1, in2, out = new Wire
    andGate(in1, in2, out)
    probe("in1", in1)
    probe("in2", in2)
    probe("out", out)
    in1.setSignal(false)
    in2.setSignal(false)
    run

    in1.setSignal(true)
    run

    in2.setSignal(true)
    run
  }

  //
  // to complete with orGateExample and demuxExample...
  //
}

object CircuitMain extends App {
  // You can write tests either here, or better in the test class CircuitSuite.
  Circuit.andGateExample
}
