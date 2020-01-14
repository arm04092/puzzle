package vo;

public class Report {
	private int reportId;
	private String memberId;
	private String reportDate;
	private int count;
	private int timer;
	
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTimer() {
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	@Override
	public String toString() {
		return "Report [reportId=" + reportId + ", memberId=" + memberId + ", reportDate=" + reportDate + ", count="
				+ count + ", timer=" + timer + "]";
	}
	
	
}
