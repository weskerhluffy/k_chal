package k.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import k.model.Game;
import k.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional(readOnly = true)
public class GameDAOCustomImpl implements GameDAOCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Game findGame(User user, Long idGame) {
		Query query = entityManager
				.createNativeQuery("select distinct g.* from Game g,User u,Authorities a,UserAuthorities ua"
						+ " where u.id=ua.idUSer and a.id=ua.idAuthority" + " and u.id=? and g.id=?"
						+ " and (g.idUserX=u.id or g.idUserO=u.id) or a.name='admin'");
		query.setParameter(1, user.getId());
		query.setParameter(2, idGame);
		log.debug("query exxxecuted");

		return (Game) query.getSingleResult();
	}

	@Override
	public Game findAny() {
		Game game = null;
		Query quer = entityManager.createQuery("from Game g");
		quer.setMaxResults(1);
		log.debug("before q");
		game = (Game) quer.getSingleResult();
		log.debug("after q");
		return game;
	}

}
