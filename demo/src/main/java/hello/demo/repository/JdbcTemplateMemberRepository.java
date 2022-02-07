package hello.demo.repository;

import hello.demo.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate(dataSource); //객체의 멤버 변수에 Datasource(h2같은 db) 객체 주입
    }
    
    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert=new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("name",member.getName());
        
        Number key=jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }
    
    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result= jdbcTemplate.query("select * from member where id=?", memberRowMapper(),id);
        //result에는 멤버 객체들이 요소들로 있는 리스트가 담김 (RowMapper이므로)
        return result.stream().findAny(); //findAny()는 Multi thread에서 Stream을 처리할 때 가장 먼저 찾은 요소를 리턴합니다
    }
    
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result= jdbcTemplate.query("select * from member where name=?", memberRowMapper(),name);
//        첫번째 파라미터 : PreparedStatement를 만들기 위한 SQL
//        두번째 파라미터 : RowMapper 콜백을 넣어주자
//        세번째 (마지막) 파라미터 : SQL의 치환자에 바인딩할 값을 차례대로 넣으면 된다.
    
        return result.stream().findAny();
    }
    
    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member",memberRowMapper());
    }
    
    private RowMapper<Member> memberRowMapper(){
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException { //rs가 sql문의 결과로 나온 각 객체들, rowNum횟수만큼 반복
                Member member=new Member();
                member.setId(rs.getLong("id"));
                member.setName((rs.getString("name")));
                return member;
            }
        };
    }
}
