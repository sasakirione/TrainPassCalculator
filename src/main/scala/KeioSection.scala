import scala.math.abs

/**
 * 京王線の区間を表すクラス
 * @param from 発駅
 * @param to 着駅
 */
case class KeioSection(from: String, to: String) extends SectionInterface {
  /** 発駅から着駅までの営業キロ数 */
  private val distance = calculateDistance()

  def calculateDistanceAllLine(fromLine: (String, Double, String), toLine: (String, Double, String), distance: Double): Double = {
    fromLine._3 match {
      case toLine._3 => distance + abs(fromLine._2 - toLine._2)
      case "京王線" => toLine._3 match {
        case "井の頭線北" => calculateDistanceAllLine(fromLine, getStation("明大前"), distance + toLine._2)
      }
      case "井の頭線北" => toLine._3 match {
        case "京王線" => calculateDistanceAllLine(getStation("明大前"), toLine, distance + fromLine._2)
      }
    }
  }

  def calculateDistance(): Double = {
    val fromLine = getStation(from)
    val toLine = getStation(to)
    calculateDistanceAllLine(fromLine, toLine, 0)
  }

  private def getStation(station: String): (String, Double, String) = {
    KeioConst.salesDistanceAll.filter(s => s._1.equals(station)).last
  }

  /**
   * 実際に定期運賃を計算する
   * @param passType 定期券の種類
   * @return 定期運賃
   */
  def calculatePrice(passType: TrainPassType): Int = {
    // 営業キロ数から運賃を取得する
    var price = KeioConst.sectionPrice.filter(p =>  distance <= p._1).head

    passType match {
      case NormalTrainPass => price._2
      case SchoolTrainPass => price._3
    }
  }
}


