package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	//필드
	@Autowired
	private BoardDao boardDao;
	
	//리스트4(일반)
	public Map<String, Object> getBoardList4(int crtPage, String keyword){
		System.out.println("BoardService>getBoardList4()");
		
		//리스트 가져오기/////////////////////////////////////////////////////////
		//페이지당 게시글 수
		int listCnt = 10;
		
		//현재페이지
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);
		//if(crtPage>0) {}else {crtPage = 1;} 위의 식이랑 같다.
		
		//시작글번호
		int startRnum = (crtPage - 1) * listCnt + 1;
		//끝글번호
		int endRnum = (startRnum + listCnt) - 1;
		
		System.out.println(crtPage);
		System.out.println(startRnum);
		System.out.println(endRnum);
		
		
		List<BoardVo> boardList = boardDao.selectList4(startRnum, endRnum, keyword);
		
		
		
		
		
		//페이징 계산/////////////////////////////////////////////////////////
		//전체글갯수
		int totalCnt = boardDao.selectTotalCnt(keyword);
		System.out.println(totalCnt);
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount; //Math.ceil 숫자 올림
		//처음 버튼 번호
		int startPageBtnNo = (endPageBtnNo-pageBtnCount)+1;
		
		System.out.println(startPageBtnNo + "~" + endPageBtnNo);
		
		
		
		
		
		//다음 화살표 유무
		boolean next = false;
		if((listCnt*endPageBtnNo) < totalCnt) {
			next = true;
		}else {
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)(listCnt));
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		
		System.out.println(crtPage);
		System.out.println(prev + ", " + startPageBtnNo + ", " + endPageBtnNo + ", " + next);
		
		
		
		
		//리스트 페이징 정보 묶기
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("boardList", boardList);
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		
		pMap.put("keyword", keyword);
		
		System.out.println(pMap);
		
		
		
		return pMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//메소드 일반
	//1. 게시판 리스트 list
	public List<BoardVo> boardList(String keyword){
		System.out.println("BoardService>boardList()");
		List<BoardVo> boardList = boardDao.boardList(keyword);
		return boardList;		
	}
	
	
	
	
	//수업시간 코드///////////////////////////////////////////
	public List<BoardVo> getBoardList3(String keyword) {
		System.out.println("BoardService>getBoardList3()");
		List<BoardVo> boardList = boardDao.selectList3(keyword);	
		return boardList;
	}
	
	// 리스트(리스트만 출력할때)
	public List<BoardVo> getBoardList() {
		System.out.println("BoardService>getBoardList()");
		List<BoardVo> boardList = boardDao.selectList();	
		return boardList;
	}
	public List<BoardVo> getBoardList2(String keyword){
		System.out.println("BoardService>getBoardList2()");
		List<BoardVo> boardList = boardDao.selectList2(keyword);
		return boardList;
	}
	
	
	/////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	//2. 게시판 검색
	//3. 삭제
	public int boardDelete(int no) {
		System.out.println("BoardService>boardDelete()");
		return boardDao.boardDelete(no);
	}
	
	
	
	//4. 읽기 -조회수+
	public Map<String, Object> readBoard(int no) {
		System.out.println("BoardService>readBoard()");
		
		boardDao.updateHit(no);
		Map<String, Object> boardMap = boardDao.readBoard(no);
		
		return boardMap;
	}
	
	
	//5. 수정폼(게시글 가져오기)
	public Map<String, Object> getBoard(int no) {
		System.out.println("BoardService>getBoard()");
		Map<String, Object> boardMap = boardDao.getBoard(no);
		return boardMap;
	}
	//6. 수정
	public int boardUpdate(BoardVo boardVo) {
		System.out.println("BoardService>boardUpdate()");
		return boardDao.boardUpdate(boardVo);
	}
	
	
	//7. 글쓰기 insert
	public int boardInsert(BoardVo boardVo) {
		System.out.println("BoardService>boardInsert()");
		
		
		/*임시 - 페이징 위해, 게시글을 많이 필요하니까..
		for(int i=1; i<=127; i++) {
			boardVo.setTitle(i+"번째 게시글(제목)입니다.");
			boardVo.setContent(i+"번째 게시글(내용)입니다.");
			boardDao.boardInsert(boardVo);
		}*/
		
		//return 1;
		return boardDao.boardInsert(boardVo);
	}
	
	
	
}
