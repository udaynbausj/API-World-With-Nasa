package io.code.nasa.Dao;
import io.code.nasa.Entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiDetails extends JpaRepository<Info,Long> {
}
