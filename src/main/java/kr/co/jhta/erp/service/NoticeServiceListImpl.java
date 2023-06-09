package kr.co.jhta.erp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jhta.erp.dao.NoticeDao;
import kr.co.jhta.erp.dto.NoticeListDto;
import kr.co.jhta.erp.vo.HrPagination;
import kr.co.jhta.erp.vo.Notices;
import kr.co.jhta.erp.vo.ProductPagination;

@Service
public class NoticeServiceListImpl implements NoticeService {

	@Autowired
	private NoticeDao noticeDao;

	@Override
	public Map<String, Object> getNoticeLists(Map<String, Object> criteria) {

		int deptNo = (Integer)criteria.get("deptno");

		int totalSize = noticeDao.getNoticeCount(deptNo);
		Map<String, Object> pagedNotices = new HashMap<String, Object>();
		if ((Integer)criteria.get("pageno") != null) {
			HrPagination pagination = new HrPagination((Integer)criteria.get("pageno"), totalSize);
			criteria.put("beginIndex", pagination.getBeginIndex());
			criteria.put("endIndex", pagination.getEndIndex());
			pagedNotices.put("pagination", pagination);
		}
		if (criteria.get("pageno") == null || criteria.get("pageno").equals(1)) {
			ProductPagination pagination = new ProductPagination(1, totalSize);
			criteria.put("beginIndex", pagination.getBeginIndex());
			criteria.put("endIndex", pagination.getEndIndex());
			pagedNotices.put("pagination", pagination);
		}
		List<NoticeListDto> notices = noticeDao.getNoticeLists(criteria);
		pagedNotices.put("notices", notices);
		return pagedNotices;

	}

	@Override
	public NoticeListDto getNoticeByNo(int noticeNo) {
		return noticeDao.getNoticeByNo(noticeNo);
	}

	@Override
	public void updateView(int noticeNo) {

		noticeDao.updateView(noticeNo);
	}

	@Override
	public NoticeListDto nextArticle(Notices notice) {
		return noticeDao.nextArticle(notice);
	}

	@Override
	public NoticeListDto prevArticle(Notices notice) {
		return noticeDao.prevArticle(notice);
	}

	@Override
	public void addNotice(Notices notice) {
		noticeDao.addNotice(notice);
	}

	@Override
	public void updateNotice(Notices notice) {
		noticeDao.updateNotice(notice);
	}

	@Override
	public void deleteNoticeByNo(int noticeNo) {
		noticeDao.deleteNoticeByNo(noticeNo);
	}

	@Override
	public void deleteSelectNotice(int[] noticeNo) {
		for (int notices : noticeNo) {
			noticeDao.deleteNoticeByNo(notices);
		}
	}

	@Override
	public List<NoticeListDto> getMainNoticeLists(int deptNo) {
		return noticeDao.getMainNoticeLists(deptNo);
	}

	@Override
	public List<NoticeListDto> getModifyNoticeLists(int deptNo) {
		// TODO Auto-generated method stub
		return noticeDao.getModifyNoticeLists(deptNo);
	}

}
