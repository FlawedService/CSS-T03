package css;

/**
 * @author antonialopes
 *
 */
public class Greeting {

	private String name;
	
	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	private String title;

	public void setName(String name) {
		this.name = name.toUpperCase();	
	}

	public void setTitle(String title) {
		this.title = title.length()>3?title.toUpperCase().substring(0, 4)+".":title;
	}

	private int points;
	
	public void setPoints(int points) {
		this.points = points;
	}

	public int getPoints() {
		return points;
	}

}
