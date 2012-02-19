
/**
 * 
 * @author Group 2
 * 
 * Handles a players course and id.
 */
public class CourseID {

	private int course, id;
	
	public CourseID(int course, int id)
	{
		this.course = course;
		this.id = id;
		
	}
	

	/**
 	* 
 	* @author Group 2
 	* 
 	* Returns ID
 	*/
	public int getID()
	{
		return id;
	}
	
	/**
 	* 
 	* @author Group 2
 	* 
 	* Returns course
 	*/
	public int getCourse()
	{
		return course;
	}
}
