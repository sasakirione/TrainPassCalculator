import scala.math.abs

/**
 * 京王線の区間を表すクラス
 * @param from 発駅
 * @param to 着駅
 */
case class KeioSection(from: String, to: String) extends SectionInterface {
  /** 定期運賃表 */
  private var sectionPrice = List((4, 4700, 1870), (6, 5080, 2040), (9, 5890, 2270), (12, 6680, 2620), (15, 7430, 2860), (19, 8990, 3200))
  /** 営業キロ数 */
  private var salesDistance = List(("新宿", 0.0), ("明大前",5.2 ), ("調布", 15.5), ("武蔵野台",18.8))
  /** 発駅の新宿からの営業キロ数 */
  private var fromDouble:Double = salesDistance.filter(s => s._1.equals(from)).last._2
  /** 着駅の新宿からの営業キロ数 */
  private var toDouble:Double = salesDistance.filter(s => s._1.equals(to)).last._2
  /** 発駅から着駅までの営業キロ数 */
  private var distance = abs(toDouble - fromDouble)

  /**
   * 実際に定期運賃を計算する
   * @param passType 定期券の種類
   * @return 定期運賃
   */
  def calculatePrice(passType: TrainPassType): Int = {
    // 営業キロ数から運賃を取得する
    var price = sectionPrice.filter(p =>  distance <= p._1).head

    passType match {
      case NormalTrainPass => price._2
      case SchoolTrainPass => price._3
    }
  }
}


