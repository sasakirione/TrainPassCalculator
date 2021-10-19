import scala.math.abs

case class Section(from: String, to: String) extends SectionInterface {
  private var sectionPrice = List((4, 4700, 1870), (6, 5080, 2040), (9, 5890, 2270), (12, 6680, 2620), (15, 7430, 2860), (19, 8990, 3200))
  private var salesDistance = List(("新宿", 0.0), ("明大前",5.2 ), ("武蔵野台",18.8))
  private var fromDouble:Double = salesDistance.filter(s => s._1.equals(from)).last._2
  private var toDouble:Double = salesDistance.filter(s => s._1.equals(to)).last._2
  private var distance = abs(toDouble - fromDouble)

  def normalCommutePrice(i: Int): Int = calculatePrice(i, NormalTrainPass)

  def schoolCommutePrice(i: Int): Int = calculatePrice(i, SchoolTrainPass)

  def calculatePrice(i: Int, passType: TrainPassType): Int = {
    var price = sectionPrice.filter(p =>  distance <= p._1).head
    passType match {
      case NormalTrainPass => price._2
      case SchoolTrainPass => price._3
    }
  }

  sealed abstract class TrainPassType
  final case object SchoolTrainPass extends TrainPassType
  final case object NormalTrainPass extends TrainPassType
}
