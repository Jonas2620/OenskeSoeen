package onskesoen.onskesoen.repository;

import onskesoen.onskesoen.model.Ønske;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ØnskeRepository {

    @Autowired
    private JdbcTemplate template;

    public void tilføjØnske(Ønske ønske) {
        String sql = "INSERT INTO ønskesøen.wishes (wishlist_id, wish_navn) VALUES (?, ?)";
        template.update(sql, ønske.getWishlistId(), ønske.getØnskeNavn());
    }

    public List<Ønske> findØnskerTilWishlist(int wishlist_id) {
        String sql = "SELECT * FROM ønskesøen.wishes WHERE wishlist_id = ?";
        RowMapper<Ønske> rowMapper = new BeanPropertyRowMapper<>(Ønske.class);
        return template.query(sql, rowMapper, wishlist_id);
    }

    public boolean sletØnske(int ønskeId) {
        String sql = "DELETE FROM ønskesøen.wishes WHERE wish_id = ?";
        return template.update(sql, ønskeId) > 0;
    }

    public void opdaterØnske(Ønske ønske) {
        String sql = "UPDATE ønskesøen.wishes SET wish_navn = ? WHERE wish_id = ?";
        template.update(sql, ønske.getØnskeNavn(), ønske.getId());
    }
}
