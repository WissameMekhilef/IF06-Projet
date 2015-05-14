package jeu;

public class Action {
	
	Action inverse;
	String action;
	
	/**
	 * Constructeur d'une action sans inverse
	 * @param pAction
	 * Le nom de l'action
	 */
	public Action(String pAction) {
		action = pAction;
		inverse = null;
	}
	
	/**
	 * Fonction d'egalite
	 * @param o
	 * L'objet a comparer
	 * @return
	 * true si o = this, false sinon
	 */
	public boolean equals(Object o) {
		if (o instanceof Action) {
			return((Action) o).getAction().hashCode() == hashCode();
		}
		return false;
	}

	/**
	 * Getter nom
	 * @return
	 * Le nom de l'action
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * Getter inverse
	 * @return
	 * Un pointeur vers l'action inverse
	 */
	public Action getInverse() {
		return inverse;
	}
	
	/**
	 * Setter inverse
	 * @param inverse
	 * Un pointeur sur l'action inverse de l'action courante
	 */
	public void setInverse(Action inverse) {
		this.inverse = inverse;
	}
	
}
