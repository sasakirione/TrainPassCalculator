/**
 * 区間を表すトレイト
 */
trait SectionInterface {
  /**
   * 通勤定期の運賃を取得する
   * @param i 定期券の期間
   * @return 定期運賃
   */
  def normalCommutePrice(i: TrainPassPeriod): Int

  /**
   * 通学定期の運賃を取得する
   * @param i 定期券の期間
   * @return 定期運賃
   */
  def schoolCommutePrice(i: TrainPassPeriod): Int
}
