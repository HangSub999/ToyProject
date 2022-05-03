public class MainClass {
    public static void main(String[] args) {

        System.out.println("#############################");
        System.out.println("### 회원 관리 프로그램 START ##");
        System.out.println("#############################");

        MemberManager memberManager = new MemberManager();
        memberManager.readMenu();

        System.out.println("#############################");
        System.out.println("### GOOD-BYE 프로그램 종료 ###");
        System.out.println("#############################");


    }
}
