import scala.math.abs

/**
 * 京王線の区間を表すクラス
 * @param from 発駅
 * @param to 着駅
 */
case class KeioSection(from: String, to: String) extends SectionInterface {
  /** 発駅から着駅までの営業キロ数 */
  private val distance = calculateDistance()

  private def calculateDistance(): Double = {
    val fromStationExist = isExistStation(from)
    val toStationExist = isExistStation(to)
    (fromStationExist, toStationExist) match {
      case ((true, false),(true,false)) => calculateDistanceInLine(KeioConst.salesDistanceKeioLine, to, from)
      case ((true, false),(false, true)) => calculateDistanceInLine(KeioConst.salesDistanceInokashiraLineNorth, to, "明大前") +
        calculateDistanceInLine(KeioConst.salesDistanceKeioLine, "明大前", from)
      case ((false, true), (true, false)) => calculateDistanceInLine(KeioConst.salesDistanceInokashiraLineNorth, from, "明大前") +
        calculateDistanceInLine(KeioConst.salesDistanceKeioLine, "明大前", to)
      case ((false, true),(false, true)) => calculateDistanceInLine(KeioConst.salesDistanceInokashiraLineNorth, to, from)
    }
  }

  private def calculateDistanceInLine(line: List[(String, Double)], tempTo: String, tempFrom: String): Double = {
    abs(line.filter(s => s._1.equals(tempFrom)).last._2 - line.filter(s => s._1.equals(tempTo)).last._2)
  }

  private def isExistStation(stationName: String): (Boolean, Boolean) = {
    (KeioConst.salesDistanceKeioLine.exists(s => s._1.equals(stationName)),
      KeioConst.salesDistanceInokashiraLineNorth.exists(s => s._1.equals(stationName)))
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


