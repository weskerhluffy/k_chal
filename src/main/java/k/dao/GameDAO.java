package k.dao;


import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import k.model.Game;

// XXX: https://stackoverflow.com/questions/14014086/what-is-difference-between-crudrepository-and-jparepository-interfaces-in-spring?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
@Repository("gameDAO") @Named("gameDAO")
public interface GameDAO extends JpaRepository<Game, Long>, GameDAOCustom {
}
