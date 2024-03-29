package dat3.car.cars.api;

import dat3.car.cars.dto.MemberRequest;
import dat3.car.cars.dto.MemberResponse;
import dat3.car.cars.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/members")
class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //security --> Admin
    @GetMapping
    List<MemberResponse> getMembers() {
        return memberService.getMembers(false);
    }

    @GetMapping("/includeAll")
    List<MemberResponse> getMembersIncludeAll() {
        return memberService.getMembers(true);
    }

    @GetMapping("/findAllMembersWhereReservationsIsNotNull")
    List<MemberResponse> findAllMembersWhereReservationsIsNotNull() {
        return memberService.findMembersWhereReservationsIsNotNull();
    }

    //Admin
    @GetMapping(path = "/{username}")
    MemberResponse getMemberById(@PathVariable String username) throws Exception {
        return memberService.findById(username, false);
    }

    @GetMapping(path = "/{username}/includeAll")
    MemberResponse getMemberByIdIncludeAll(@PathVariable String username) throws Exception {
        return memberService.findById(username, true);
    }

    //Security --> Anonymous
    @PostMapping()
    MemberResponse addMember(@RequestBody MemberRequest body) {
        return memberService.addMember(body);
    }

    //Security --> Admin
    @PutMapping("/{username}")
    void editMember(@RequestBody MemberRequest body, @PathVariable String username) {
        memberService.editMember(body, username);
    }

    //Security --> Admin
    @PatchMapping("/ranking/{username}/{value}")
    void setRankingForUser(@PathVariable String username, @PathVariable int value) {
        memberService.setRankingForUser(username, value);
    }

    // Security --> Admin
    @DeleteMapping("/{username}")
    void deleteMemberByUsername(@PathVariable String username) {
        memberService.deleteById(username);
    }
}