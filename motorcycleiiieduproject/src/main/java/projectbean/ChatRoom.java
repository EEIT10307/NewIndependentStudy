package projectbean;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ChatRoom implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer chatRoomSerialNum ; 
	private MemberDetail chatSender; 
	private MemberDetail chatReceiver; 
	private String chatContent; 
	private java.util.Date chatTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getChatRoomSerialNum() {
		return chatRoomSerialNum;
	}
	public void setChatRoomSerialNum(Integer chatRoomSerialNum) {
		this.chatRoomSerialNum = chatRoomSerialNum;
	}
	@ManyToOne
	@JoinColumn(referencedColumnName = "email" , nullable = false)
	public MemberDetail getChatSender() {
		return chatSender;
	}
	public void setChatSender(MemberDetail chatSender) {
		this.chatSender = chatSender;
	}
	@ManyToOne
	@JoinColumn(referencedColumnName = "email" , nullable = false)
	public MemberDetail getChatReceiver() {
		return chatReceiver;
	}
	public void setChatReceiver(MemberDetail chatReceiver) {
		this.chatReceiver = chatReceiver;
	}
	@Column(columnDefinition = "varchar(MAX)" , nullable = false)
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	@Column(nullable = false)
	public java.util.Date getChatTime() {
		return chatTime;
	}
	public void setChatTime(java.util.Date chatTime) {
		this.chatTime = chatTime;
	}
	
}
