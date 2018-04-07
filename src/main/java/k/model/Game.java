package k.model;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class Game {
	private Integer id;
	private String state;
	private Integer idUserX;
	private Integer idUserO;
	private Integer idUserWin;
	private Character lastTurn;
}
