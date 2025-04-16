package drawables;

public class NFAContactNumberVisualizer extends StateGraph{

	public NFAContactNumberVisualizer() {
		float scale = 1.5f;
		
		State states[] = new State[] {
				new State("q0", Math.round(0*scale), Math.round(0*scale)),
				new State("q1", Math.round(50*scale), Math.round(-50*scale)),
				new State("q2", Math.round(50*scale), Math.round(50*scale)),
				new State("q3", Math.round(150*scale), Math.round(-50*scale)),
				new State("q4", Math.round(250*scale), Math.round(-50*scale)),
				new State("q5", Math.round(100*scale), Math.round(0*scale)),
				new State("q6", Math.round(200*scale), Math.round(0*scale)),
				new State("q7", Math.round(250*scale), Math.round(50*scale)),
				new State("q8", Math.round(350*scale), Math.round(50*scale)),
				new State("q9", Math.round(450*scale), Math.round(50*scale)),
				new State("q10", Math.round(525*scale), Math.round(2*scale)),
				new State("q11", Math.round(525*scale), Math.round(-50*scale)),
				new State("q12", Math.round(600*scale), Math.round(50*scale)),
				new State("q13", Math.round(600*scale), Math.round(150*scale)),
				new State("q14", Math.round(450*scale), Math.round(150*scale)),
				new State("q15", Math.round(350*scale), Math.round(100*scale)),
				new State("q16", Math.round(250*scale), Math.round(150*scale)),
				new State("q17", Math.round(150*scale), Math.round(150*scale)),
				new State("q18", Math.round(50*scale), Math.round(150*scale)),
				new State("q19", Math.round(0*scale), Math.round(100*scale))
		};
		
		states[0].setTransitions(new Transition[] {
				new Transition("+", states[1]),
				new Transition("0", states[2])
		});
		states[1].setTransitions(new Transition[] {
				new Transition("6", states[3])
		});
		states[2].setTransitions(new Transition[] {
				new Transition(" ", states[5]),
				new Transition("(", states[6]),
				new Transition("9", states[7]),
		});
		states[3].setTransitions(new Transition[] {
				new Transition("3", states[4])
		});
		states[4].setTransitions(new Transition[] {
				new Transition(" ", states[5]),
				new Transition("(", states[6]),
				new Transition("9", states[7])
		});
		states[5].setTransitions(new Transition[] {
				new Transition("(", states[6])
		});
		states[6].setTransitions(new Transition[] {
				new Transition("9", states[7])
		});
		states[7].setTransitions(new Transition[] {
				new Transition("[0-9]", states[8])
		});
		states[8].setTransitions(new Transition[] {
				new Transition("[0-9]", states[9])
		});
		states[9].setTransitions(new Transition[] {
				new Transition(")", states[11]),
				new Transition(" ", states[10]),
				new Transition("[0-9]", states[12])
		});
		states[10].setTransitions(new Transition[] {
				new Transition("[0-9]", states[12])
		});
		states[11].setTransitions(new Transition[] {
				new Transition(" ", states[10]),
				new Transition("[0-9]", states[12])
		});
		states[12].setTransitions(new Transition[] {
				new Transition("[0-9]", states[13])
		});
		states[13].setTransitions(new Transition[] {
				new Transition("[0-9]", states[14])
		});
		states[14].setTransitions(new Transition[] {
				new Transition(" ", states[15]),
				new Transition("[0-9]", states[16])
		});
		states[15].setTransitions(new Transition[] {
				new Transition("[0-9]", states[16])
		});
		states[16].setTransitions(new Transition[] {
				new Transition("[0-9]", states[17])
		});
		states[17].setTransitions(new Transition[] {
				new Transition("[0-9]", states[18])
		});
		states[18].setTransitions(new Transition[] {
				new Transition("[0-9]", states[19])
		});
		
		states[19].isFinalState = true;
		
		setStates(states);
		
		x_origin = -450;
		y_origin = 0;
		showTransitionInputs = true;
	}
	

}
