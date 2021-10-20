import scala.math.abs

/**
 * 京王線の区間を表すクラス
 * @param from 発駅
 * @param to 着駅
 */
case class KeioSection(from: String, to: String) extends SectionInterface {
  /** 発駅の新宿からの営業キロ数 */
  private val fromDouble:Double = KeioConst.salesDistanceKeioLine.filter(s => s._1.equals(from)).last._2
  /** 着駅の新宿からの営業キロ数 */
  private val toDouble:Double = KeioConst.salesDistanceKeioLine.filter(s => s._1.equals(to)).last._2
  /** 発駅から着駅までの営業キロ数 */
  private val distance = abs(toDouble - fromDouble)

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


