import org.scalatest
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.be
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

import java.util.Calendar

class TicketTest extends AnyFunSuite {
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

  test("新宿武蔵野台の通勤3ヶ月定期を表示する") {
    var calendar = Calendar.getInstance()
    var ticket = TrainPass("新宿", "武蔵野台", NormalTrainPass, ThreeMonth, calendar)
    ticket.getPrice should be (25630)
  }

  test("券面の作成") {
    var calendar = Calendar.getInstance()
    var ticket = TrainPass("新宿", "武蔵野台", NormalTrainPass, ThreeMonth, calendar)
    println(ticket)
  }

  test("井の頭線") {
    var section = KeioSection("新宿", "吉祥寺")
    section.calculatePrice(NormalTrainPass) should be(7430)
  }

}
