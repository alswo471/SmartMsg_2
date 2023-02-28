package mj_crossShot.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import mj_crossShot.models.Member;

public interface MemberServiceIF {

	public List<Member> getMemberList();

	public List<Member> getMyList(Member member);

	public Member memberJoin(Member member, Errors errors);

	public Member login(Member member);

	public Member changeMember(Member member);

	public void deleteMember(Member member);

	public int adminLogin(Member member);

}
