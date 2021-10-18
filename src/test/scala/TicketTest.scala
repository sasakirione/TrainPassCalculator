import org.scalatest
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class TicketTest extends AnyFunSuite {
  test("新宿武蔵野台の通勤1ヶ月定期代を表示する") {
    var ticket = new Section("新宿", "武蔵野台")
    ticket.normalCommutePrice(1) should be (8900)
  }

  test("新宿明大前の通勤3ヶ月定期代を表示する") {
    var ticket = new Section("新宿", "明大前")
    ticket.normalCommutePrice(1) should be (5080)
  }

  test("新宿武蔵野台の通学1ヶ月定期代を表示する") {
    var ticket = new Section("新宿", "武蔵野台")
    ticket.schoolCommutePrice(1) should be (3200)
  }
}
