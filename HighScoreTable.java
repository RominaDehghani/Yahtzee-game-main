
public class HighScoreTable {
	private String name;
	private int score;
	private String NameScore;
	
	public HighScoreTable(String n, int s) {
	    name = n ; 
	    score = s ;
	    NameScore = n + " " + String.valueOf(s);
	}

	public void setName(String n) { name = n ; }

	public void setScore(int s) { score = s ; }

	public String getName() { return name ; }

	public double getScore() { return score ;}
	
	public String getNameScore() { return NameScore ; }
	
}
