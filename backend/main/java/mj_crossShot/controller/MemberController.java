package mj_crossShot.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mj_crossShot.common.error.BadRequestException;
import mj_crossShot.models.Member;

import mj_crossShot.service.MemberService;

/**
 * 회원 컨트롤러
 * 
 * @author 지민재
 *
 */

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("member/")
public class MemberController {

	

	private MemberService memberservice;

	@Autowired
	public MemberController(MemberService memberservice) {
		this.memberservice = memberservice;

	}
	
	/**
	 * 
	 * @param member
	 * @return 정상 : 상태코드 OK 200 반환 / 실패 : 상태코드 CONFLICT 409 반환 
	 */
	@PostMapping("idcheck")
	public ResponseEntity<Member> findById(@RequestBody Member member) {

		log.info("findById : " + member);
		Member memId = memberservice.findById(member);

		return ResponseEntity.ok(memId);
	}

	/**
	 * 
	 * @param member
	 * @return 정상 : 상태코드 OK 200 반환 / 실패 : 상태코드 BAD_REQUEST 404 반환 
	 */
	@GetMapping("memberSerchList")
	public ResponseEntity<Object> memberSerchList(@RequestBody Member member) {

//		if (member == null) {throw new MemberNotFoundException();}

		return ResponseEntity.ok(member);

	}

	/**
	 * 내 정보
	 * @param member
	 * @return 상태코드 OK 200 반환 / 실패 : 상태코드 BAD_REQUEST 404 반환 
	 */
	@PostMapping("myInfo")
	public ResponseEntity<List<Member>> MyInfoList(@RequestBody Member member) {
		
		
		List<Member> mydata = memberservice.getMyList(member);

		return ResponseEntity.ok(mydata);

	}

    /**
     * 
     * @param 회원 가입
     * @return 상태코드 OK 200 반환 / 실패 : 상태코드 BAD_REQUEST 404 반환 
     */
	
	@PostMapping("join")
	public ResponseEntity<Member> join(@RequestBody @Valid Member member, Errors errors) {
		
		
			memberservice.memberJoin(member, errors);
		
		

		return ResponseEntity.status(HttpStatus.CREATED).body(member);

	}

	/**
	 * 회원정보변경
	 * @param member
	 * @return 상태코드 OK 200 반환 / 실패 : 상태코드 BAD_REQUEST 404 반환 
	 */
	@PutMapping("changeMember")
	public ResponseEntity<Member> changeMember(@RequestBody Member member) {

		Member changeInfo = memberservice.changeMember(member);

		return ResponseEntity.ok(changeInfo);

	}

	/**
	 * 회원 탈퇴
	 * @param member
	 */
	@DeleteMapping("deleteMember")
	public void deleteMember(@RequestBody Member member) {

		memberservice.deleteMember(member);

	}

}
