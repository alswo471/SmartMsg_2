package mj_crossShot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import lombok.extern.slf4j.Slf4j;
import mj_crossShot.Mapper.MemberMapper;
import mj_crossShot.common.error.BadRequestException;
import mj_crossShot.common.error.MemberNotFoundException;
import mj_crossShot.common.error.duplMemIdException;
import mj_crossShot.models.Member;

@Slf4j
@Service
public class MemberService implements MemberServiceIF {

	private MemberMapper memberMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// (단일 생성자일 경우는 스프링 4.3 부터 @Autowired 생략 가능)
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;

	}

	/** 회원 조회 */
	@Override
	public List<Member> getMemberList() {

		List<Member> memberList = new ArrayList<Member>();

		memberList = memberMapper.getMemberList();

		return memberList;
	}

	/** 나의 정보 조회 */
	@Override
	public List<Member> getMyList(Member member) {

		log.info("getMyList 서비스 진입");

		List<Member> myList = memberMapper.getMyList(member);

		if (myList.size() == 0) {

			throw new MemberNotFoundException();

		}

		return myList;
	}

	/** 아이디 중복 검사 */

	public Member findById(Member member) {
		log.info("data : " + member);
		Member memId = memberMapper.findById(member);

		if (memId == null) {
			return member;
		}

		throw new duplMemIdException();
	}

	/** 회원 등록 */
	@Override
	@Transactional
	public Member memberJoin(Member member, Errors errors) {

		if(errors.hasErrors()) {
			throw new BadRequestException();
		}
		log.info("data : " + member.getPwd());
		String enPw = passwordEncoder.encode(member.getPwd());
		member.setPwd(enPw);

		memberMapper.memberJoin(member);
		return member;

	}

	/** 회원 수정 */

	@Override
	public Member changeMember(Member member) {

		String enPw = passwordEncoder.encode(member.getPwd());
		member.setPwd(enPw);

		log.info("enPw : " + enPw);

		memberMapper.changeMember(member);

		return member;

	}

	/** 회원 삭제 */

	@Override
	public void deleteMember(Member member) {

		memberMapper.deleteMember(member);
	}

	/** 회원 로그인 */
	@Override
	public Member login(Member member) {

		Member login = memberMapper.login(member);

		if (login == null) {

			throw new MemberNotFoundException();
		}

		return login;
	}

	@Override
	public int adminLogin(Member member) {

		Member login = memberMapper.findById(member);

		if (login == null) {
			throw new MemberNotFoundException();
		}
		log.info("adminCk : " + member);
		int adminCk = memberMapper.adminLogin(member);
		log.info("adminCk : " + member);
		return adminCk;

	}

}
