/**
 * 区間を表すトレイト
 */
trait SectionInterface {
  /**
   * 実際に定期運賃を計算する
   * @param passType 定期券の種類
   * @return 定期運賃
   */
  def calculatePrice(passType: TrainPassType): Int
}
