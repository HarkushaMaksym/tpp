package laba3.hmsite.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @EventListener(ContextRefreshedEvent.class)
    public void initializeDatabase() {
        Integer userCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);

        if (Objects.equals(userCount, 0)) {
            System.out.println("Database is empty. Initializing data...");

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getClass().getResourceAsStream("/data.sql"), StandardCharsets.UTF_8))) {

                String sql = reader.lines().collect(Collectors.joining("\n"));
                for (String statement : sql.split(";")) {
                if (!statement.trim().isEmpty()) {
                    jdbcTemplate.execute(statement);
                }
            }
                resetSequences();

                System.out.println("Database initialized successfully.");
            } catch (Exception e) {
                System.err.println("Failed to initialize database: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Database already contains data. Skipping initialization.");
        }
    }

    private void resetSequences() {
        try {
            jdbcTemplate.execute("SELECT setval(pg_get_serial_sequence('planet','id'), COALESCE((SELECT MAX(id) FROM planet), 1), true)");
            jdbcTemplate.execute("SELECT setval(pg_get_serial_sequence('satellite','id'), COALESCE((SELECT MAX(id) FROM satellite), 1), true)");
            jdbcTemplate.execute("SELECT setval(pg_get_serial_sequence('users','id'), COALESCE((SELECT MAX(id) FROM users), 1), true)");
            System.out.println("Sequences reset successfully.");
        } catch (Exception e) {
            System.err.println("Failed to reset sequences: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
