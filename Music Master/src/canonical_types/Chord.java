package canonical_types;

import canonical_types.Interval;
import general_Utils.Utils;
import java.util.ArrayList;

public class Chord {
	
	String root;//C or Eb etc.
	String type;//Maj or diminished etc.
	
	//////////////////////TO DO!!///////////////////////
	//May want to make this a set instead since we will
	//be checking for membership of a certain note in a
	//chord.
	
	ArrayList<Interval> notes;//List of the intervals used in the chord 
							  //WITH the actual notes filled in.
	
	///////////////////////TO DO!!/////////////////////
	//Later add support for DIFFERENT INVERSIONS!!!
	
	public Chord(String root, String type)
	{
		this.root = root;
		this.type = type;
		notes = Utils.chord_type_to_formula.get(type);//The type passed in MUST be a valid chord type
		
		///////////////////////TO DO!!/////////////////////
		//This will need standardization of the chord types as per the songs database later
		
		//Computing the actual notes corresponding to the intervals in the chord, given the root
		for(Interval i : notes)
		{
			i.setInterval_note(i.get_interval_note_given_root(root));
		}
	}
	
	public boolean is_note_in_chord(String note)
	{
		for(Interval i : this.notes)
		{
			//Its note enought to check if one of the intervals has the same interval_note string
			//as the arg note due to equivalency of different representaitons of the same note
			//i.e D+ = E-. So, check if the indices of the interval_note and arg note are the same
			if (Utils.note_to_index.get(note) == Utils.note_to_index.get(i.getInterval_note()))
			{
				return(true);
			}			
		}
		return(false);
	}

}
