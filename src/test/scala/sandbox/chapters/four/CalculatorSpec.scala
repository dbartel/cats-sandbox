package sandbox.chapters.four

import org.scalatest._

class CalculatorSpec extends WordSpec {
  "#evalInput" when {
    "valid" should {
      "do math" in {
        assert(Calculator.evalInput("1 1 +") == 2)
      }
    }
  }
}
