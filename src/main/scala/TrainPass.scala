import java.util.Calendar
import scala.math

/**
 * 定期券を表すクラスです
 * @param from 発駅
 * @param to 着駅
 * @param passType 定期券の種類
 * @param passPeriod 定期券の期間
 * @param StartDate 定期券の利用開始日
 */
case class TrainPass(from: String, to: String, passType: TrainPassType, passPeriod: TrainPassPeriod, StartDate: Calendar) {
  /** 定期券区間 */
  var section: SectionInterface = KeioSection(to, from)
  /** 定期券の終了日 */
  var endDate: Calendar = {
    calculateEndDate
  }

  /**
   * 定期券の期限を計算する
   * @return 定期券の終了日
   */
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

  /**
   * 定期の料金を取得する
   * @return 定期運賃
   */
  def getPrice: Int = {
    val oneMonthPrice = section.calculatePrice(passType)
    passPeriod match {
      case OneMonth => oneMonthPrice
      case ThreeMonth => (math.ceil(oneMonthPrice * 3 * 0.095) * 10).toInt
      case SixMonth => (math.ceil(oneMonthPrice * 6 * 0.09) * 10).toInt
    }
  }
}

/** 定期券の期間を表すクラス(Enum代用) */
sealed abstract class TrainPassType
/** 通学定期 */
case object SchoolTrainPass extends TrainPassType
/** 通勤定期 */
case object NormalTrainPass extends TrainPassType

/** 定期券の期間を表すクラス(Enum代用) */
sealed abstract class TrainPassPeriod
/** 1ヶ月定期 */
case object OneMonth extends TrainPassPeriod
/** 3ヶ月定期 */
case object ThreeMonth extends TrainPassPeriod
/** 6ヶ月定期 */
case object SixMonth extends TrainPassPeriod