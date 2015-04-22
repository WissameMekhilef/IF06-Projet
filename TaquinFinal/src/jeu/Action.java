package jeu;

public class Action {
	String action;
	Action inverse;
	
	public Action(String pAction){
		action=pAction;
		inverse=null;
	}
	
	public Action(String pAction, Action pInverse){
		action=pAction;
		inverse=pInverse;
	}

	public String getAction() {
		return action;
	}

	public Action getInverse() {
		return inverse;
	}	
	
	public int hashCode(){
		return action.hashCode();
	}
	
	public boolean equals(Object o){
		if (o instanceof Action){
			return ((Action) o).getAction().hashCode()==hashCode();
		}
		return false;
	}
	
}
