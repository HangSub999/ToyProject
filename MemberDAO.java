import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private String MEMBER_LIST = "select member_id, name, phone_number from member";
    private String MEMBER_INSERT = "insert into member values(?, ?, ?)";
    private String MEMBER_UPDATE = "update member set name = ?, phone_number = ? where member_id = ?";
    private String MEMBER_DELETE = "delete member where member_id = ?";
    private String MEMBER_DETAILED = "select * from member where member_id = ?";

    //회원  등록
    public void insertMember(Member member) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MEMBER_INSERT);
            stmt.setString(1, member.getMemberId());
            stmt.setString(2, member.getName());
            stmt.setString(3, member.getPhoneNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    //회원 상세 조회
   public boolean getMemberDetailed(Member member) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MEMBER_DETAILED);
            stmt.setString(1,member.getMemberId());
            rs = stmt.executeQuery();
            while (rs.next()){
                if (member.getMemberId().equals(rs.getString("MEMBER_ID"))){
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return true;
    }

    //맴버 목록 조회
    public  List<Member> getMemberList() {
        List<Member> memberList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MEMBER_LIST);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("MEMBER_ID"));
                member.setName(rs.getString("NAME"));
                member.setPhoneNumber(rs.getString("PHONE_NUMBER"));
                memberList.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return memberList;
    }

    //맴버 정보 수정
    public void updateMember(Member member) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MEMBER_UPDATE);
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getPhoneNumber());
            stmt.setString(3, member.getMemberId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

    }

    //맴버 삭제
    public void deleteMember(Member member) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MEMBER_DELETE);
            stmt.setString(1, member.getMemberId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

    }
}


