import org.scalatest
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import java.util.Date

class TicketTest extends AnyFunSuite {
  test("新宿武蔵野台の通勤1ヶ月定期代を表示する") {
    var ticket = KeioSection("新宿", "武蔵野台")
    ticket.normalCommutePrice(OneMonth) should be (8990)
  }

  test("新宿明大前の通勤3ヶ月定期代を表示する") {
    var ticket = KeioSection("新宿", "明大前")
    ticket.normalCommutePrice(OneMonth) should be (5080)
  }

  test("新宿武蔵野台の通学1ヶ月定期代を表示する") {
    var ticket = KeioSection("新宿", "武蔵野台")
    ticket.schoolCommutePrice(OneMonth) should be (3200)
  }

  test("定期券クラスを使用する") {
    var ticket = TrainPass("新宿", "武蔵野台", NormalTrainPass, OneMonth, new Date())
    ticket.getPrice should be (8990)
  }


}
