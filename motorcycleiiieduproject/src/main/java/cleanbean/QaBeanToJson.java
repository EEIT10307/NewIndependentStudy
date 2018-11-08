package cleanbean;

public class QaBeanToJson {
	private String  questioner;
	private String questionCotent;
	private String bikeModel;
	private String modelYear;
	private String administratorID;
	private String answerContent; //回答內容
	private String questionDate ; //問時間
	private String answerTime ;//回答時間
	
	
	
	
	
	
	
	public QaBeanToJson() {
	
	}
	
	
	public QaBeanToJson(String questioner, String questionCotent, String bikeModel, String modelYear,
			String administratorID, String answerContent, String questionDate, String answerTime) {
		super();
		this.questioner = questioner;
		this.questionCotent = questionCotent;
		this.bikeModel = bikeModel;
		this.modelYear = modelYear;
		this.administratorID = administratorID;
		this.answerContent = answerContent;
		this.questionDate = questionDate;
		this.answerTime = answerTime;
	}


	public String getQuestioner() {
		return questioner;
	}
	public void setQuestioner(String questioner) {
		this.questioner = questioner;
	}
	public String getQuestionCotent() {
		return questionCotent;
	}
	public void setQuestionCotent(String questionCotent) {
		this.questionCotent = questionCotent;
	}
	public String getBikeModel() {
		return bikeModel;
	}
	public void setBikeModel(String bikeModel) {
		this.bikeModel = bikeModel;
	}
	public String getModelYear() {
		return modelYear;
	}
	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}
	public String getAdministratorID() {
		return administratorID;
	}
	public void setAdministratorID(String administratorID) {
		this.administratorID = administratorID;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public String getQuestionDate() {
		return questionDate;
	}
	public void setQuestionDate(String questionDate) {
		this.questionDate = questionDate;
	}
	public String getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}
	
}
