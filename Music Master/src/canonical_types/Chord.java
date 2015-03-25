package canonical_types;

import canonical_types.Interval;
import general_utils.Utils;
import java.util.HashSet;
import java.util.ArrayList;

public class Chord {
	
	private String root;//C or Eb etc.
	private String type;//Maj or diminished etc.
	
	//////////////////////TO DO!!///////////////////////
	//May want to make this a set instead since we will
	//be checking for membership of a certain note in a
	//chord.
	
	private ArrayList<Interval> notes;//List of the intervals used in the chord 
							  //WITH the actual notes filled in.
	private HashSet<Integer> note_ids_set;
	
	///////////////////////TO DO!!/////////////////////
	//Later add support for DIFFERENT INVERSIONS!!!

	public Chord(String root, String type)
	{
		this.root = root;
		this.type = type;
		this.notes = Utils.chord_type_to_formula.get(type);//The type passed in MUST be a valid chord type
		
		///////////////////////TO DO!!/////////////////////
		//This will need standardization of the names of chord types as per the songs database later
		
		//Computing the actual notes corresponding to the intervals in the chord, given the root
		for(Interval i : notes)
		{
			i.setInterval_note(i.get_interval_note_given_root(root));
			note_ids_set.add(Utils.note_to_index.get(i.getInterval_note()));
			//No need to do note synonym replacement here since get_interval_note_given_root
			//already does it
		}
	}
	
	public String getRoot() {
		return root;
	}

	public String getType() {
		return type;
	}

	public ArrayList<Interval> getNotes() {
		return notes;
	}
	
	public HashSet<Integer> getNote_ids_set() {
		return note_ids_set;
	}
	
	public boolean is_note_in_chord(String note)
	{	
		if(note_ids_set.contains(Utils.note_to_index.get(note)))
		{
			return(true);
		}
		return(false);
	}
	
	public String get_notes_in_chord_as_string()
	{
		String ret_str="";
		for(Interval i : this.notes)
		{
			ret_str += i.getInterval_note() +",";
		}
		return(ret_str);
	}

}
