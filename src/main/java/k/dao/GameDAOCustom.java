package k.dao;

import k.model.Game;
import k.model.User;

public interface GameDAOCustom {
	public Game findGame(User user, Long idGame);
	public Game findAny();
}
