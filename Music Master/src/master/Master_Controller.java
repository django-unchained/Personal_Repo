package master;

import general_utils.Utils;
import canonical_types.*;

public class Master_Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utils.init_Variables();
		Chord tryout = new Chord("E-","Major");
		System.out.println(tryout.get_notes_in_chord_as_string());

	}

}
