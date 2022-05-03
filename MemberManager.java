import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberManager {

    public Member member;
    public MemberDAO memberDAO;
    public List<Member> memberList;
    public Scanner sc;


    public MemberManager(){
        this.member = new Member();
        this.memberDAO = new MemberDAO();
        this.memberList = memberDAO.getMemberList();
        this.sc = new Scanner(System.in);
    }

    public void readMenu(){
        while (true){
            this.memberList = memberDAO.getMemberList();
            System.out.println("목록을 원하시면 1번을 입력하세요.");
            System.out.println("등록을 원하시면 2번을 입력하세요.");
            System.out.println("수정을 원하시면 3번을 입력하세요.");
            System.out.println("삭제를 원하시면 4번을 입력하세요.");
            System.out.println("종료를 원하시면 0번을 입력하세요.");

            int n = sc.nextInt();

            if (n == 1) {
                getMemberListMain();
            }else if (n == 2){
                insertMemberMain();
            }else if (n == 3){
                updateMemberMain();
            }else if (n ==4){
                deleteMemberMain();
            }else {
                break;
            }
        }
    }

    public void getMemberListMain(){
        if (memberList.isEmpty()){
            System.out.println("등록된 회원이 없습니다.");
        }else {
            System.out.println("현재 등록된 회원 목록입니다.");
            for (Member member : memberList){
                System.out.println("---> "+member);
            }
        }
    }

    public void insertMemberMain(){
        while (true) {
            System.out.print("아이디를 입력하세요 (형식 M-00001): ");
            member.setMemberId(sc.next());
            if (memberDAO.getMemberDetailed(member)){
                System.out.print("이름을 입력하세요 : ");
                member.setName(sc.next());
                System.out.print("전화번호를 입력하세요 : ");
                member.setPhoneNumber(sc.next());
                memberDAO.insertMember(member);
                System.out.println("---> 회원가입에 성공하셨습니다.");
                break;
            }else {
                System.out.println(member.getMemberId() + "가 이미 존재합니다.");
            }

        }
    }

    public void updateMemberMain(){
        System.out.print("수정할 아이디를 입력하세요 (형식 M-00001): ");
        member.setMemberId(sc.next());
        System.out.print("수정할 이름을 입력하세요 : ");
        member.setName(sc.next());
        System.out.print("수정할 전화번호를 입력하세요 : ");
        member.setPhoneNumber(sc.next());
        memberDAO.updateMember(member);
        System.out.println("---> 회원수정에 성공하셨습니다.");
    }

    public void deleteMemberMain(){
        System.out.print("삭제할 아이디를 입력하세요 : ");
        member.setMemberId(sc.next());
        memberDAO.deleteMember(member);
        System.out.println("---> "+member.getMemberId()+"회원 삭제에 성공하셨습니다.");
    }
}
