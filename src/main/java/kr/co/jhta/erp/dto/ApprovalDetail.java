package kr.co.jhta.erp.dto;

import java.util.Date;

import kr.co.jhta.erp.utils.DateUtils;

public class ApprovalDetail {

	private int approvalNo;
	private Date requestDate;
	private Date responseDate;
	private int typeNo;
	private String typeName;
	private int requesterNo;
	private String requesterName;
	private int responserNo;
	private String responserName;
	private int orderNo;
	private int storeNo;
	private String storeName;
	private int factoryOrderNo;
	private int storageNo;
	private String storageName;
	private String status;
	private String note;

	public ApprovalDetail() {
	}

	public String getFmtRequestDate() {
		return DateUtils.dateToString(requestDate);
	}

	public String getFmtResponseDate() {
		return DateUtils.dateToString(responseDate);
	}

	public String getFmtRequestDateWithTime() {
		return DateUtils.dateToStringWithTime(requestDate);
	}

	public String getFmtResponseDateWithTime() {
		return DateUtils.dateToStringWithTime(responseDate);
	}

	public int getApprovalNo() {
		return approvalNo;
	}

	public void setApprovalNo(int approvalNo) {
		this.approvalNo = approvalNo;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public int getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getRequesterNo() {
		return requesterNo;
	}

	public void setRequesterNo(int requesterNo) {
		this.requesterNo = requesterNo;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public int getResponserNo() {
		return responserNo;
	}

	public void setResponserNo(int responserNo) {
		this.responserNo = responserNo;
	}

	public String getResponserName() {
		return responserName;
	}

	public void setResponserName(String responserName) {
		this.responserName = responserName;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getFactoryOrderNo() {
		return factoryOrderNo;
	}

	public void setFactoryOrderNo(int factoryOrderNo) {
		this.factoryOrderNo = factoryOrderNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(int storeNo) {
		this.storeNo = storeNo;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getStorageNo() {
		return storageNo;
	}

	public void setStorageNo(int storageNo) {
		this.storageNo = storageNo;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	@Override
	public String toString() {
		return "ApprovalDetail [approvalNo=" + approvalNo + ", requestDate=" + requestDate + ", responseDate="
			+ responseDate + ", typeNo=" + typeNo + ", typeName=" + typeName + ", requesterNo=" + requesterNo
			+ ", requesterName=" + requesterName + ", responserNo=" + responserNo + ", responserName="
			+ responserName + ", orderNo=" + orderNo + ", storeNo=" + storeNo + ", storeName=" + storeName
			+ ", factoryOrderNo=" + factoryOrderNo + ", storageNo=" + storageNo + ", storageName=" + storageName
			+ ", status=" + status + ", note=" + note + "]";
	}

}
