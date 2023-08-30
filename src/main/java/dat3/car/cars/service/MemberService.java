package dat3.car.cars.service;

import dat3.car.cars.dto.MemberRequest;
import dat3.car.cars.dto.MemberResponse;
import dat3.car.cars.entity.Member;
import dat3.car.cars.repositories.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MemberService {
    final
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public List<MemberResponse> getMembers(boolean includeAll) {
        /*List<Member> members = memberRepository.findAll();
        // List<MemberResponse> response = new ArrayList<>();
        for (Member member : members) {
          MemberResponse mr = new MemberResponse(member, includeAll);
        response.add(mr);
        }
        */
        return memberRepository.findAll().stream().map
                ((member -> new MemberResponse(member, includeAll))).toList();
    }

    public MemberResponse addMember(MemberRequest body) {
        Member newMember = MemberRequest.getMemberEntity(body);

        if (memberRepository.existsById(body.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user already exists");
        }
        newMember = memberRepository.save(newMember);
        return new MemberResponse(newMember, true);
    }

    public void editMember(MemberRequest body, String username) {
        Member member = getMemberByUsername(username);
        if(!body.getUsername().equals(username)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot change username");
        }
        member.setUsername(body.getUsername());
        member.setPassword(body.getPassword());
        member.setEmail(body.getEmail());
        member.setFirstName(body.getFirstName());
        member.setLastName(body.getLastName());
        member.setStreet(body.getStreet());
        member.setCity(body.getCity());
        member.setZip(body.getZip());
        memberRepository.save(member);
    }


    public MemberResponse findById(String username) {
        return new MemberResponse(getMemberByUsername(username), true);
    }

    public void setRankingForUser(String username, Integer ranking){
        Member member = getMemberByUsername(username);
        member.setRanking(ranking);
        memberRepository.save(member);
    }

    public Member getMemberByUsername(String username){
       return memberRepository.findById(username).orElseThrow(()
               -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
               "Member with this username does not exist"));
    }

    public void deleteById(String username) {
        memberRepository.deleteById(username);
    }
}
