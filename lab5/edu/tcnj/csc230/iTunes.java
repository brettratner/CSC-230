/** Brett Ratner and Ricky Zhao
  *	CSC230-02
  *	Lab5
*/

/**
 * This program creates an iTunes object and stores information about the
 * song name, artist, album, size and time length into the object. 
 */

package edu.tcnj.csc230;

public class iTunes
{
	private String name;
	private String artist;
	private String album;
	private long size;
	private int time; 

	/**
     * Constructor for iTunes class.
     */
	public iTunes(String songName, String artistName, String albumName, long songSize, int songTime)
	{
		name = songName;
		artist = artistName;
		album = albumName;
		size = songSize;
		time = songTime;
	}

    /**
     * Obtains the name of the track
     * @return Returns the name
     */	
	public String getName()
	{
		return name;
	}

	/**
     * Obtains the name of the track
     * @return Returns the artist
     */
	public String getArtist()
	{
		return artist;
	}

	/**
     * Obtains the album of the track
     * @return Returns the name
     */
	public String getAlbum()
	{
		return album;
	}

	/**
     * Obtains the size of the track
     * @return Returns the size
     */

	public long getSize()
	{
		return size;
	}

	/**
     * Obtains the time of the track
     * @return Returns the time
     */

	public int getTime()
	{
		return time;
	}
	
	
	public String toString()
	{
		return "Song: " + name + "," + "Artist: " + artist + "," + "Album: " + album + "," + "Size: " + size + "," + "Time: " + time + ".";
	}

	/**
	*	This method creates the hashcode value
	*/
	public int hashCode()
	{
		String combined = name + size; 
		
		/**
		 *	Calls hash code from the string class
		 */
		int hashint = combined.hashCode();

		return hashint; 
	}

}