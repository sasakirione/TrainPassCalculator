import java.util.Calendar

case class TrainPass(from: String, to: String, passType: TrainPassType, passPeriod: TrainPassPeriod, StartDate: Calendar) {
  var section: SectionInterface = KeioSection(to, from)
  var endDate: Calendar = {
    calculateEndDate
  }

  private def calculateEndDate = {
    var workDay: Calendar = StartDate.clone().asInstanceOf[Calendar]
    workDay.add(Calendar.DAY_OF_MONTH, -1)
    passPeriod match {
      case OneMonth => workDay.add(Calendar.MONTH, 1)
      case ThreeMonth => workDay.add(Calendar.MONTH, 3)
      case SixMonth => workDay.add(Calendar.MONTH, 6)
    }
    workDay
  }

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