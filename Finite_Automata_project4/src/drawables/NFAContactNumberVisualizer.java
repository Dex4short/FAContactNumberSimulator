package drawables;

import java.awt.Graphics2D;

public class NFAContactNumberVisualizer extends StateGraph{
	public State states[];
	public float scale = 0.95f;
	public boolean rescale=true;
	
	public NFAContactNumberVisualizer() {
		states = new State[27];
		for(int n=0; n<states.length;n++) {
			states[n] = new State("q" + n);
		}
				
		states[0].setTransitions(new Transition[] {
				new Transition("+", new char[] {'+'}, states[1]),
				new Transition("0", new char[] {'0'}, states[2])
		});
		states[1].setTransitions(new Transition[] {
				new Transition("6", new char[] {'6'}, states[3])
		});
		states[2].setTransitions(new Transition[] {
				new Transition("9", new char[] {'9'}, states[10])
		});
		states[3].setTransitions(new Transition[] {
				new Transition("3", new char[] {'3'}, states[4])
		});
		states[4].setTransitions(new Transition[] {
				new Transition(" ", new char[] {' '}, states[5])
		});
		states[5].setTransitions(new Transition[] {
				new Transition("(", new char[] {'('}, states[6]),
				new Transition("9", new char[] {'9'}, states[7])
		});
		states[6].setTransitions(new Transition[] {
				new Transition("9", new char[] {'9'}, states[17])
		});
		states[7].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[8])
		});
		states[8].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[9])
		});
		states[9].setTransitions(new Transition[] {
				new Transition(" ", new char[] {' '}, states[23])
		});
		states[10].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[16])
		});
		states[11].setTransitions(new Transition[] {
				new Transition(" -", new char[] {' ','-'}, states[24])
		});
		states[12].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[13])
		});
		states[13].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[14])
		});
		states[14].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[15])
		});
		states[15].setTransitions(null);
		states[16].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[20])
		});
		states[17].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[18])
		});
		states[18].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[19])
		});
		states[19].setTransitions(new Transition[] {
				new Transition(")", new char[] {')'}, states[9])
		});
		states[20].setTransitions(new Transition[] {
				new Transition(" ", new char[] {' '}, states[23]),
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[21])
		});
		states[21].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[22])
		});
		states[22].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[24])
		});
		states[23].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[25])
		});
		states[24].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[12])
		});
		states[25].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[26])
		});
		states[26].setTransitions(new Transition[] {
				new Transition("0...9", new char[] {'0','1','2','3','4','5','6','7','8','9'}, states[11])
		});

		states[15].isFinalState = true;
		setStates(states);
		
		showInputTransitions = true;
	}
	@Override
	public void onDraw(Graphics2D g2d) {
		super.onDraw(g2d);
		x_origin = -Math.round((1300*scale)/2);
		y_origin = +Math.round((350*scale)/2);
		
		if(rescale) {
			int p[][] = {
				{0,0},//q0
				{0,-200},//q1
				{300, 0},//q2
				{100,-200},//q3
				{200,-200},//q4
				{300,-200},//q5
				{300,-300},//q6
				{400,-200},//q7
				{500,-200},//q8
				{600,-200},//q9
				{400,0},//q10
				{900,-100},//q11
				{1000,0},//q12
				{1100,0},//q13
				{1200,0},//q14
				{1300,0},//q15
				{500,0},//q16
				{400,-300},//q17
				{500,-300},//q18
				{600,-300},//q19
				{600,0},//q20
				{700,0},//q21
				{800,0},//q22
				{600,-100},//q23
				{900,0},//q24
				{700,-100},//q25
				{800,-100}//q26
			};
			for(int i=0; i<p.length; i++) {
				states[i].point.setLocation(Math.round(p[i][0]*scale), Math.round(p[i][1]*scale));
			}
			rescale = false;
		}
	}
}
