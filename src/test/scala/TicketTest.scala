import org.scalatest
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import java.util.Calendar

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
    var ticket = TrainPass("新宿", "武蔵野台", NormalTrainPass, OneMonth, Calendar.getInstance())
    ticket.getPrice should be (8990)
  }

  test("定期の期限を計算する") {
    var c1 = Calendar.getInstance()
    c1.set(2021,9,1)
    var ticket = TrainPass("新宿", "武蔵野台", NormalTrainPass, OneMonth, c1)
    var c2 = Calendar.getInstance()
    c2.set(2021,9,30)
    ticket.endDate should be (c2)
  }


}
