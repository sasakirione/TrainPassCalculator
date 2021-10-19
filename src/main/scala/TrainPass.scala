import java.util.Date

case class TrainPass(from: String, to: String, passType: TrainPassType, passPeriod: TrainPassPeriod, StartDate: Date) {
  private var section: SectionInterface = KeioSection(to, from)

  def getPrice: Int = {
    passType match {
      case NormalTrainPass => section.normalCommutePrice(passPeriod)
      case SchoolTrainPass => section.schoolCommutePrice(passPeriod)
    }
  }
}

sealed abstract class TrainPassType
case object SchoolTrainPass extends TrainPassType
case object NormalTrainPass extends TrainPassType

sealed abstract class TrainPassPeriod
case object OneMonth extends TrainPassPeriod
case object ThreeMonth extends TrainPassPeriod
case object SixMonth extends TrainPassPeriod