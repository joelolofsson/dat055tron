
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
    * Returns the ID
    * 
    * @return int
	*/
	public int getID()
	{
		return id;
	}
	
	/**
 	* 
 	* Returns the course
 	* 
 	* @return int
 	*/
	public int getCourse()
	{
		return course;
	}
}
