package mj_crossShot.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;

import mj_crossShot.models.Member;
import mj_crossShot.models.sendDto.SendDto;

@Mapper
public interface MemberMapper {

	public List<Member> getMemberList();

	public List<Member> getMyList(Member member);

	public void memberJoin(Member member);

	public Member login(Member member);

	public void changeMember(Member member);

	public void deleteMember(Member member);

	public Member findById(Member member);

	public int adminLogin(Member member);

}
