package canonical_types;

import java.util.HashSet;

import general_Utils.Utils;

public class Interval {
	

	private int interval = 1;
	private int modifier = 0;
	private int offset_from_root = 0;
	private String tonic;
	private int octave_offset = 0;
	private String interval_note;
	
	
	///////////////////////TO DO!!/////////////////////
	//Add constructors that allow you to specify the tonic/interval_note and infer the other appropriately

	//To handle any increasing interval
	public Interval(String s_interval)
	{
		//Assumes intervals ONLY hold upwards in this constructor
		//To specify an interval downwards, pass -ve octave_offset
		//as a parameter to the constructor.
		int val = Integer.parseInt(get_interval_no_mod(s_interval));
		if (val > 0 && val < 8)
		{
			//Case of an interval between [1-7]
			this.interval = val;
			//octave_offset already defaults to 0 as required in
			//this case.
		}
		else
		{
			//Case of intervals like 9ths, 13ths etc.
			this.interval = val % 8 + 1;
			this.octave_offset += (int) val/8;
		}
		
		if (s_interval.length() > 1)
		{
			if (s_interval.substring(1, 2).equals("+"))
			{
				this.modifier = 1;
			}
			else if(s_interval.substring(1, 2).equals("-"))
			{
				this.modifier = -1;
			}
			else
			{
				this.modifier = 0;
			}
		}
		else
		{
			this.modifier = 0;
		}
		
		this.offset_from_root = (Utils.interval_to_offset.get(this.interval) + modifier + this.octave_offset * 12);
		//So a 9th will have the offset of a 2nd + 12 semitones
	}
	
	//To handle decreasing interval/highly increasing interval (beyond one octave)
	public Interval(String s_interval, int oct_offset)
	{
		//Assumes intervals ONLY hold upwards in this constructor
		//To specify an interval downwards, pass -ve octave_offset
		//as a parameter to the constructor.
		int val = Integer.parseInt(get_interval_no_mod(s_interval));
		this.octave_offset = oct_offset;
		if (val > 0 && val < 8)
		{
			//Case of an interval between [1-7]
			this.interval = val;
			//octave_offset already defaults to 0 as required in
			//this case.
		}
		else if (val >= 8)
		{
			//Case of intervals like 9ths, 13ths etc.
			this.interval = val % 8 + 1;
			this.octave_offset += (int) val/8;
		}
		
		if (s_interval.length() > 1)
		{
			if (s_interval.substring(1, 2).equals("+"))
			{
				this.modifier = 1;
			}
			else if(s_interval.substring(1, 2).equals("-"))
			{
				this.modifier = -1;
			}
			else
			{
				this.modifier = 0;
			}
		}
		else
		{
			this.modifier = 0;
		}
		
		this.offset_from_root = (Utils.interval_to_offset.get(this.interval) + modifier + this.octave_offset * 12);
	}
	
	
	
	public String getTonic() {
		return tonic;
	}


	public void setTonic(String tonic) {
		this.tonic = tonic;
	}
	
    public String getInterval_note() {
		return interval_note;
	}

	public void setInterval_note(String interval_note) {
		this.interval_note = interval_note;
	}


	public int getOctave() {
		return octave_offset;
	}

	
	public int getInterval() {
		return interval;
	}

	public int getModifier() {
		return modifier;
	}

	public int getOffset_from_root() {
		return offset_from_root;
	}

	private String get_interval_no_mod(String s_interval)
	{
		if(s_interval.contains("+") || s_interval.contains("-"))//These only occur at end of the string by convention
		{
			return(s_interval.substring(0, s_interval.length()-1));//omits the last character which is the modifier i.e flat or sharp
		}
		else
			return(s_interval);
	}
	
    public int get_semitonal_diff(Interval interval2)
    {
    	return(Math.abs(this.offset_from_root - interval2.offset_from_root));
    }
    
    @Override public String toString()
    {
    	String disp_modifier ="";
    	if(this.modifier == 1)
    	{
    		disp_modifier = "#";
    	}
    	else if (this.modifier == -1)
    	{
    		disp_modifier = "b";
    	}
    	return("Interval " + this.getInterval() + " " + disp_modifier + " which is " + this.getOffset_from_root() + " semitones from the root");
    }
    
    
    
    /////////////////////////////TO DO!!///////////////////////////
    //Add logic here to normalize the name of the note returned i.e
    //prefer F over E+ for example.
    
    private String get_note_from_set(HashSet<String> set)
    {
    	for(String str : set)
    	{
    		return(str);
    	}
    	return "";
    }
    
    //IMPLEMENT THIS PROPERLY WHEN NEEDED!!!
    public String get_interval_note_given_root(String root){
    	
    	int root_index = Utils.note_to_index.get(root);
    	int interval_index = root_index + this.offset_from_root % 12;    	    	
    	return(get_note_from_set(Utils.index_to_note.get(interval_index)));
    }
    
	
}
