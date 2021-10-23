import scala.math.abs

/**
 * 京王線の区間を表すクラス
 * @param from 発駅
 * @param to 着駅
 */
case class KeioSection(from: String, to: String) extends SectionInterface {
  /** 発駅から着駅までの営業キロ数 */
  private val distance = calculateDistance()

  /**
   * 営業キロ数計算
   * @param fromStation 計算対象の発駅
   * @param toStation 計算対象の着駅
   * @param distance 計算対象外の営業キロ
   * @return 営業キロ数
   */
  def calculateDistanceAllLine(fromStation: (String, Double, String), toStation: (String, Double, String), distance: Double): Double = {
    fromStation._3 match {
      case toStation._3 => distance + abs(fromStation._2 - toStation._2)
      case "京王線" => toStation._3 match {
        case "井の頭線北" | "井の頭線南" => calculateDistanceAllLine(fromStation, getStation("明大前"), distance + toStation._2)
      }
      case "井の頭線北" => toStation._3 match {
        case "京王線" => calculateDistanceAllLine(getStation("明大前"), toStation, distance + fromStation._2)
        case "井の頭線南" => fromStation._2 + toStation._2
      }
      case "井の頭線南" => toStation._3 match {
        case "京王線" => calculateDistanceAllLine(getStation("明大前"), toStation, distance + fromStation._2)
        case "井の頭線北" => fromStation._2 + toStation._2
      }
    }
  }

  /**
   * 営業キロ数計算
   * @return 営業キロ数
   */
  def calculateDistance(): Double = {
    val fromLine = getStation(from)
    val toLine = getStation(to)
    calculateDistanceAllLine(fromLine, toLine, 0)
  }

  /**
   * 駅データの取得
   * @param station 駅名
   * @return 駅データ
   */
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


