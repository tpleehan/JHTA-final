package kr.co.jhta.erp.dao;

import java.util.List;
import java.util.Map;

import kr.co.jhta.erp.dto.ApprovalDetail;
import kr.co.jhta.erp.vo.ProductApproval;

public interface ProductApprovalDao {

	void insertFactoryApproval(ProductApproval approval);

	void insertOrderApproval(ProductApproval approval);

	List<ApprovalDetail> getAllApprovals(Map<String, Object> criteria);

	List<ApprovalDetail> getApprovalsByRequesterNo(Map<String, Object> criteria);

	List<ApprovalDetail> getApprovalsByResponserNo(Map<String, Object> criteria);

	void updateApproval(Map<String, Object> approvalInfo);

}
