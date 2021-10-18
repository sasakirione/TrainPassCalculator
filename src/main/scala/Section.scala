case class Section(from: String, to: String) {
  private var sectionPrice = ((8900, 3200) , (5080, 2040))

  def normalCommutePrice(i: Int): Int = calculatePrice(i, NormalTrainPass)

  def schoolCommutePrice(i: Int): Int = calculatePrice(i, SchoolTrainPass)

  def calculatePrice(i: Int, passType: TrainPassType): Int = {
    var price = to match {
      case "武蔵野台" => sectionPrice._1
      case "明大前" => sectionPrice._2
    }
    passType match {
      case NormalTrainPass => price._1
      case SchoolTrainPass => price._2
    }
  }

  sealed abstract class TrainPassType
  final case object SchoolTrainPass extends TrainPassType
  final case object NormalTrainPass extends TrainPassType
}
